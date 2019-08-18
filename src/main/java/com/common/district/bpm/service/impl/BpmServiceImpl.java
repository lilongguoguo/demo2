package com.common.district.bpm.service.impl;

import com.common.district.bpm.Enums.BpmButtonEnum;
import com.common.district.bpm.Enums.BpmStatusEnum;
import com.common.district.bpm.Enums.BpmStepEnum;
import com.common.district.bpm.dao.*;
import com.common.district.bpm.model.*;
import com.common.district.bpm.service.BpmService;
import com.common.district.bpm.vo.*;
import com.common.district.common.SysUtil.RespData;
import com.common.district.common.utils.DateUtil;
import com.common.district.common.utils.StringUtils;
import com.common.district.org.model.LoginUser;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * bpm接口实现 by lilong
 */
@Service
public class BpmServiceImpl implements BpmService {

    @Autowired
    private BpmTemplateMapper templateMapper;
    @Autowired
    private BpmInstanceMapper instanceMapper;
    @Autowired
    private BpmTemplateConfigMapper templateConfigMapper;
    @Autowired
    private BpmInstanceNoteMapper instanceNoteMapper;
    @Autowired
    private BpmInstanceNotePeopleMapper instanceNotePeopleMapper;
    @Autowired
    private BpmApproveRecordsMapper approveRecordsMapper;

    @Override
    public RespData<RespBpmInstanceNoteVo> selectNoteList(Long relationId, Integer relationType, String templateCode, LoginUser loginInfo) {
        try {
            if (StringUtils.isBlank(templateCode) || StringUtils.isBlank(relationType)) {
                return RespData.fail("参数异常");
            }
            String createUserId = loginInfo.getUserId();
            String createUserName = loginInfo.getUserName();
            if (StringUtils.isNotBlank(relationId)) {
                List<BpmInstance> bpmInstanceList = instanceMapper.getInstanceByRelationId(relationId, relationType, 0);

                if (null != bpmInstanceList && bpmInstanceList.size()>0) {
                    BpmInstance bpmInstance = bpmInstanceList.get(0);
                    createUserId = bpmInstance.getCreateUserId();
                    createUserName = bpmInstance.getCreateUserName();
                }
            }
            //1、获取节点信息
            BpmTemplate bpmTemplate = templateMapper.getByTemplateCode(templateCode);
            List<BpmTemplateConfig> configList = templateConfigMapper.getconfigByTemplateId(bpmTemplate.getId());
            //2、组装节点信息
            RespBpmInstanceNoteVo vo = new RespBpmInstanceNoteVo();
            List<BpmInstanceNoteVo> resultListVo = new ArrayList<>();
            String finalCreateUserId = createUserId;
            String finalCreateUserName = createUserName;
            resultListVo = Lists.transform(configList, new Function<BpmTemplateConfig, BpmInstanceNoteVo>() {
                @Override
                public BpmInstanceNoteVo apply(BpmTemplateConfig bpmTemplateConfig) {
                    BpmInstanceNoteVo vo = new BpmInstanceNoteVo();
                    BeanUtils.copyProperties(bpmTemplateConfig, vo);
                    List<BpmUser> list = new ArrayList<>();
                    if (bpmTemplateConfig.getNoteSort() == 1) {
                        BpmUser user = new BpmUser();
                        user.setUserId(finalCreateUserId);
                        user.setUserName(finalCreateUserName);
                        list.add(user);
                    }
                    vo.setUserList(list);
                    return vo;
                }
            });
            vo.setNotes(resultListVo);
            vo.setTemplateId(bpmTemplate.getId());
            vo.setRelationType(relationType);
            return RespData.success(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return RespData.fail("系统异常");
        }
    }

    @Override
    public RespData<RespBpmButtonVo> selectButtonList(Long relationId, Integer relationType, LoginUser loginInfo) {
        RespBpmButtonVo respBpmButtonVo = new RespBpmButtonVo();
        List<Map> buttonList = new ArrayList<>();
        respBpmButtonVo.setButtonList(buttonList);
        //
        String createUserId = loginInfo.getUserId();
        String createUserName = loginInfo.getUserName();
        Long instanceId = null;
        Integer status = null;
        if (StringUtils.isNotBlank(relationId)) {
            List<BpmInstance> bpmInstanceList = instanceMapper.getInstanceByRelationId(relationId, relationType, 0);
            if (null != bpmInstanceList && bpmInstanceList.size()>0) {
                BpmInstance bpmInstance = bpmInstanceList.get(0);
                status = bpmInstance.getApproveStatus();
                createUserId = bpmInstance.getCreateUserId();
                createUserName = bpmInstance.getCreateUserName();
                instanceId = bpmInstance.getId();
            }
        }
        if (null == status
                || status == BpmStatusEnum.DRAFT.getValue().intValue()
                || status == BpmStatusEnum.APPROVED.getValue().intValue()) {//新申请、草稿 、已驳回
            //是否为发起人
            if (createUserId.equals(loginInfo.getUserId())) {
                if (null != status) {
                    Map buttonMap1 = new HashMap();
                    buttonMap1.put("name", BpmButtonEnum.EDIT.getKey());
                    buttonMap1.put("val", BpmButtonEnum.EDIT.getValue().intValue());
                    buttonList.add(buttonMap1);
                    Map buttonMap3 = new HashMap();
                    buttonMap3.put("name", BpmButtonEnum.DISCARD.getKey());
                    buttonMap3.put("val", BpmButtonEnum.DISCARD.getValue().intValue());
                    buttonList.add(buttonMap3);
                    respBpmButtonVo.setBaseInfoShow(false);
                    respBpmButtonVo.setBpmShow(false);
                } else {
                    respBpmButtonVo.setBaseInfoShow(true);
                    respBpmButtonVo.setBpmShow(false);
                }
                Map buttonMap2 = new HashMap();
                buttonMap2.put("name", BpmButtonEnum.SUBMIT_APPROVAL.getKey());
                buttonMap2.put("val", BpmButtonEnum.SUBMIT_APPROVAL.getValue().intValue());
                buttonList.add(buttonMap2);
            }
        } else if (status == BpmStatusEnum.APPROVEING.getValue().intValue()) {//审批中
            if (null != instanceId) {
                //查询当前执行节点的审批人审批状况
                BpmInstanceNote note = instanceNoteMapper.getCurrentNoteByInstanceId(instanceId);
                if (note.getNoteCode().equals(BpmStepEnum.JG.getValue())
                        && createUserId.equals(loginInfo.getUserId())) {//校稿并且是创建人
                    Map buttonMap1 = new HashMap();
                    buttonMap1.put("name", BpmButtonEnum.PROOFS.getKey());
                    buttonMap1.put("val", BpmButtonEnum.PROOFS.getValue().intValue());
                    Map buttonMap2 = new HashMap();
                    buttonMap2.put("name", BpmButtonEnum.NOPROOFS.getKey());
                    buttonMap2.put("val", BpmButtonEnum.NOPROOFS.getValue().intValue());
                    buttonList.add(buttonMap1);
                    buttonList.add(buttonMap2);
                    respBpmButtonVo.setBaseInfoShow(false);
                    respBpmButtonVo.setBpmShow(true);
                } else {
                    if (checkSelf(note.getId(), loginInfo)) {//审批人存在当前登录人
                        Map buttonMap1 = new HashMap();
                        buttonMap1.put("name", BpmButtonEnum.ADOPT.getKey());
                        buttonMap1.put("val", BpmButtonEnum.ADOPT.getValue().intValue());
                        Map buttonMap2 = new HashMap();
                        buttonMap2.put("name", BpmButtonEnum.CONSULT.getKey());
                        buttonMap2.put("val", BpmButtonEnum.CONSULT.getValue().intValue());
                        buttonList.add(buttonMap1);
                        buttonList.add(buttonMap2);
                        if (!note.getNoteCode().equals(BpmStepEnum.HQ.getValue())) {//非会签
                            Map buttonMap3 = new HashMap();
                            buttonMap3.put("name", BpmButtonEnum.REJECT.getKey());
                            buttonMap3.put("val", BpmButtonEnum.REJECT.getValue().intValue());
                            buttonList.add(buttonMap3);
                        }
                    }
                    if (createUserId.equals(loginInfo.getUserId())) {//是否为发起人
                        Map buttonMap1 = new HashMap();
                        buttonMap1.put("name", BpmButtonEnum.RETRACT.getKey());
                        buttonMap1.put("val", BpmButtonEnum.RETRACT.getValue().intValue());
                        buttonList.add(buttonMap1);
                    }
                    respBpmButtonVo.setBaseInfoShow(true);
                    respBpmButtonVo.setBpmShow(true);
                }
            }
        } else if (status == BpmStatusEnum.CONSULT.getValue().intValue()) {//协商
            if (createUserId.equals(loginInfo.getUserId())) {//发起人
                Map buttonMap1 = new HashMap();
                buttonMap1.put("name", BpmButtonEnum.CONFIRM_REPLY.getKey());
                buttonMap1.put("val", BpmButtonEnum.CONFIRM_REPLY.getValue().intValue());
                Map buttonMap2 = new HashMap();
                buttonMap2.put("name", BpmButtonEnum.RETRACT.getKey());
                buttonMap2.put("val", BpmButtonEnum.RETRACT.getValue().intValue());
                buttonList.add(buttonMap1);
                buttonList.add(buttonMap2);
                respBpmButtonVo.setBaseInfoShow(false);
                respBpmButtonVo.setBpmShow(true);
            }
        }
        return RespData.success(respBpmButtonVo);
    }

    public boolean checkSelf(Long noteId, LoginUser loginUser) {
        List<BpmInstanceNotePeople> peopleList = instanceNotePeopleMapper.getCurrentPeopleByNoteId(noteId);
        for (BpmInstanceNotePeople people : peopleList) {
            if (loginUser.getUserId().equals(people.getUserId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public RespData selectRecordList(Long relationId, Integer relationType) {
        try {
            List<BpmInstance> instanceList = instanceMapper.getInstanceByRelationId(relationId, relationType, null);
            List<Long> intanceIds = new ArrayList<>();
            for (BpmInstance instance : instanceList) {
                intanceIds.add(instance.getId());
            }
            List<BpmApproveRecords> list = approveRecordsMapper.selectRecordList(intanceIds);
            List<RespApproveRecordsVo> resultList = new ArrayList<>();
            resultList = Lists.transform(list, new Function<BpmApproveRecords, RespApproveRecordsVo>() {
                @Override
                public RespApproveRecordsVo apply(BpmApproveRecords bpmApproveRecords) {
                    RespApproveRecordsVo vo = new RespApproveRecordsVo();
                    BeanUtils.copyProperties(bpmApproveRecords, vo);
                    vo.setCreateTime(DateUtil.formatDateTime(bpmApproveRecords.getCreateTime()));
                    return vo;
                }
            });
            return RespData.success(resultList);
        } catch (Exception e) {
            e.printStackTrace();
            return RespData.fail("系统异常");
        }
    }

    @Override
    public RespData selectNoteListR(Long relationId, Integer relationType) {
        try {
            if (StringUtils.isBlank(relationId) || StringUtils.isBlank(relationType)) {
                return RespData.fail("参数异常");
            }
            List<BpmInstance> bpmInstanceList = instanceMapper.getInstanceByRelationId(relationId, relationType, 0);
            if (null == bpmInstanceList || bpmInstanceList.size()<=0) {
                return RespData.fail("实例未找到！");
            }
            BpmInstance bpmInstance = bpmInstanceList.get(0);
            List<Map> list = instanceNoteMapper.getNoteByInstanceId(bpmInstance.getId());
            List<Map> resultList = new ArrayList<>();
            resultList = Lists.transform(list, new Function<Map, Map>() {
                @Override
                public Map apply(Map map) {
                    Map m = new HashMap();
                    m.put("noteSort", map.get("noteSort"));
                    m.put("noteName", map.get("noteName"));
                    if (StringUtils.isNotBlank(map.get("userNames"))) {
                        String uns = map.get("userNames").toString();
                        m.put("userNames", uns.split(","));
                    } else {
                        m.put("userNames", "");
                    }
                    if (StringUtils.isNotBlank(map.get("active"))) {
                        String sort = map.get("active").toString();
                        if ("2".equals(sort)) {
                            m.put("active", "√");
                        } else if ("1".equals(sort)) {
                            m.put("active", "正在进行中...");
                        }
                    } else {
                        m.put("active", "");
                    }
                    return m;
                }
            });
            return RespData.success(resultList);
        } catch (Exception e) {
            e.printStackTrace();
            return RespData.fail("系统异常");
        }
    }

    @Override
    public RespData doApprove(ApproveParam approveParam, LoginUser loginUser) {
        try {
            BpmInstance bpmInstance = null;
            Integer status = null;
            List<BpmInstance> bpmInstanceList = instanceMapper.getInstanceByRelationId(approveParam.getRelationId(),  approveParam.getRelationType(), 0);
            if (null != bpmInstanceList && bpmInstanceList.size()>0) {
                bpmInstance = bpmInstanceList.get(0);
                status = bpmInstance.getApproveStatus();
            }
            if (null == status || status == BpmStatusEnum.DRAFT.getValue().intValue()
                    || status == BpmStatusEnum.REJECT.getValue().intValue()) {//草稿和已驳回
                if (approveParam.getOperate() == BpmButtonEnum.SUBMIT_APPROVAL.getValue().intValue()) {//提交
                    return submitApprove(approveParam, loginUser);
                } else if (approveParam.getOperate() == BpmButtonEnum.DISCARD.getValue().intValue()) {//废弃
                    return discardApprove(approveParam,bpmInstance,loginUser);
                }
            }else if(status == BpmStatusEnum.APPROVEING.getValue().intValue()){//审批中
                if(null==bpmInstance){
                    return RespData.fail("实例不存在！");
                }
                if(approveParam.getOperate()==BpmButtonEnum.ADOPT.getValue().intValue()){//通过
                    return commonApprove(approveParam,bpmInstance,loginUser);
                }else if(approveParam.getOperate()==BpmButtonEnum.CONSULT.getValue().intValue()){//协商
                    return consultApprove(approveParam,bpmInstance,loginUser);
                }else if(approveParam.getOperate()==BpmButtonEnum.RETRACT.getValue().intValue()
                        || approveParam.getOperate()==BpmButtonEnum.REJECT.getValue().intValue()){//撤回、驳回
                    return discardApprove(approveParam,bpmInstance,loginUser);
                }
            }else if(status == BpmStatusEnum.CONSULT.getValue().intValue()) {//协商
                if(approveParam.getOperate()==BpmButtonEnum.CONFIRM_REPLY.getValue().intValue()){//确认回复
                    return confirmReplyApprove(approveParam,bpmInstance,loginUser);
                }else if(approveParam.getOperate()==BpmButtonEnum.RETRACT.getValue().intValue()){//撤回
                    return discardApprove(approveParam,bpmInstance,loginUser);
                }
            }
            return RespData.fail("没有操作权限！");
        }catch (Exception e){
            e.printStackTrace();
            return RespData.fail("操作失败，请稍后再试！");
        }
    }

    /**
     * 提交审批
     * @param approveParam
     * @param loginUser
     * @return
     * @throws Exception
     */
    public RespData submitApprove(ApproveParam approveParam, LoginUser loginUser) throws Exception{
        //找出当前节点和下一个节点name
        Long currentNoteId = null;
        String currentNoteName = "";
        String nextNoteName = "";
        List<ApproveNoteParam> noteParams = approveParam.getNotes();
        for (ApproveNoteParam noteParam : noteParams) {
            if (StringUtils.isNotBlank(currentNoteName) && StringUtils.isNotBlank(nextNoteName)) {
                break;
            }
            if (noteParam.getNoteSort() == 1) {
                currentNoteName = noteParam.getNoteName();
            }
            if (noteParam.getNoteSort() == 2) {
                nextNoteName = noteParam.getNoteName();
            }
        }
        //保存实例
        BpmInstance instance = new BpmInstance();
        BeanUtils.copyProperties(approveParam, instance);
        instance.setCreateUserId(loginUser.getUserId());
        instance.setCreateUserName(loginUser.getUserName());
        instance.setApproveStatus(BpmStatusEnum.APPROVEING.getValue().intValue());
        instance.setCurrentNoteName(nextNoteName);
        instanceMapper.insertSelective(instance);
        //保存节点信息
        List<BpmInstanceNotePeople> peopleList = new ArrayList<>();
        for (ApproveNoteParam approveNoteParam : noteParams) {
            BpmInstanceNote note = new BpmInstanceNote();
            BeanUtils.copyProperties(approveNoteParam, note);
            note.setInstanceId(instance.getId());
            if (note.getNoteSort() == 1) {
                note.setActive(2);
            } else if (note.getNoteSort() == 2) {
                note.setActive(1);
            }
            instanceNoteMapper.insertSelective(note);
            List<BpmUser> userList = approveNoteParam.getUserList();
            List<BpmInstanceNotePeople> peoples = new ArrayList<>();
            for (BpmUser user : userList) {
                BpmInstanceNotePeople people = new BpmInstanceNotePeople();
                people.setFinish(0);
                people.setInstanceNoteId(note.getId());
                people.setUserId(user.getUserId());
                people.setUserName(user.getUserName());
                if (note.getNoteSort() == 1) {
                    people.setFinish(1);
                    currentNoteId = note.getId();
                }
                peoples.add(people);
            }
            peopleList.addAll(peoples);
        }
        instanceNotePeopleMapper.batchInsert(peopleList);
        //保存审批记录
        saveRecord(instance, approveParam, BpmButtonEnum.SUBMIT_APPROVAL.getKey(), currentNoteName, currentNoteId,loginUser);
        return RespData.success("提交成功");
    }

    /**
     * 通用节点审批操作
     * @return
     * @throws Exception
     */
    public RespData commonApprove(ApproveParam approveParam, BpmInstance bpmInstance,LoginUser loginUser) throws Exception{
        BpmInstanceNote note = instanceNoteMapper.getCurrentNoteByInstanceId(bpmInstance.getId());
        if(null==note){
            return RespData.fail("当前步骤不需要审批！");
        }
        List<BpmInstanceNotePeople> peoples = instanceNotePeopleMapper.getCurrentPeopleByNoteId(note.getId());
        if(null!=peoples && peoples.size()>0){
            boolean flag = false;
            BpmInstanceNotePeople pp = null;
            for (BpmInstanceNotePeople people : peoples) {
                if(people.getUserId().equals(loginUser.getUserId())){
                    flag = true;
                    pp = people;
                    break;
                }
            }
            if(flag){
                BpmInstanceNotePeople people = new BpmInstanceNotePeople();
                people.setFinish(1);
                people.setId(pp.getId());
                instanceNotePeopleMapper.updateByPrimaryKeySelective(people);
                peoples.remove(pp);
                if(peoples.size()<=0){//该节点所有人已审批完
                    BpmInstance instance = new BpmInstance();
                    instance.setId(bpmInstance.getId());
                    if(note.getNoteCode().equals(BpmStepEnum.NOTEEND.getValue())){//最后的节点,标记已审批
                        instance.setApproveStatus(BpmStatusEnum.APPROVED.getValue());
                        instance.setCurrentNoteName("");
                        instanceMapper.updateByPrimaryKeySelective(instance);
                        //
                        BpmInstanceNote currentNote = new BpmInstanceNote();
                        currentNote.setId(note.getId());
                        currentNote.setActive(2);
                        instanceNoteMapper.updateByPrimaryKeySelective(currentNote);
                    }else{
                        //跳转到下一个节点
                        BpmInstanceNote currentNote = new BpmInstanceNote();
                        currentNote.setId(note.getId());
                        currentNote.setActive(2);
                        instanceNoteMapper.updateByPrimaryKeySelective(currentNote);
                        //
                        BpmInstanceNote nextNote = instanceNoteMapper.getNextNote(bpmInstance.getId(),note.getNoteSort()+1);
                        nextNote.setActive(1);
                        instanceNoteMapper.updateByPrimaryKeySelective(nextNote);
                        //
                        instance.setCurrentNoteName(nextNote.getNoteName());
                        instanceMapper.updateByPrimaryKeySelective(instance);
                    }
                }
                //审批记录
                saveRecord(bpmInstance, approveParam,BpmButtonEnum.ADOPT.getKey(), note.getNoteName(), note.getId(),loginUser);
            }else{
                return RespData.fail("不存在当前审批人！");
            }
        }else{
            return RespData.fail("审批人已审批！");
        }

        return RespData.success("操作成功！");
    }

    /**
     * 协商
     * @return
     * @throws Exception
     */
    public RespData consultApprove(ApproveParam approveParam, BpmInstance bpmInstance,LoginUser loginUser) throws Exception{
        BpmInstance instance = new BpmInstance();
        instance.setId(bpmInstance.getId());
        instance.setApproveStatus(BpmStatusEnum.CONSULT.getValue().intValue());
        instanceMapper.updateByPrimaryKeySelective(instance);
        //保存日志
        BpmInstanceNote note = instanceNoteMapper.getCurrentNoteByInstanceId(bpmInstance.getId());
        saveRecord(bpmInstance, approveParam,BpmButtonEnum.CONSULT.getKey(), note.getNoteName(), note.getId(),loginUser);
        return RespData.success("操作成功");
    }
    /**
     * 确认回复
     * @return
     * @throws Exception
     */
    public RespData confirmReplyApprove(ApproveParam approveParam, BpmInstance bpmInstance,LoginUser loginUser) throws Exception{
        BpmInstance instance = new BpmInstance();
        instance.setId(bpmInstance.getId());
        instance.setApproveStatus(BpmStatusEnum.APPROVEING.getValue().intValue());
        instanceMapper.updateByPrimaryKeySelective(instance);
        //保存日志
        BpmInstanceNote note = instanceNoteMapper.getCurrentNoteByInstanceId(bpmInstance.getId());
        saveRecord(bpmInstance, approveParam,BpmButtonEnum.CONFIRM_REPLY.getKey(), note.getNoteName(), note.getId(),loginUser);
        return RespData.success("操作成功");
    }


    /**
     * 废弃、撤回、驳回
     * @return
     * @throws Exception
     */
    public RespData discardApprove(ApproveParam approveParam, BpmInstance bpmInstance,LoginUser loginUser) throws Exception{
        String approveAction = "";
        BpmInstance instance = new BpmInstance();
        instance.setId(bpmInstance.getId());
        if(approveParam.getOperate()==BpmButtonEnum.RETRACT.getValue().intValue()){//撤回
            instance.setApproveStatus(BpmStatusEnum.DRAFT.getValue().intValue());
            approveAction = BpmButtonEnum.RETRACT.getKey();
        }else if(approveParam.getOperate()==BpmButtonEnum.DISCARD.getValue().intValue()){//废弃
            instance.setDel(1);
            instance.setApproveStatus(BpmStatusEnum.DISCARD.getValue().intValue());
            approveAction = BpmButtonEnum.DISCARD.getKey();
        }else if(approveParam.getOperate()==BpmButtonEnum.REJECT.getValue().intValue()){//驳回
            instance.setApproveStatus(BpmStatusEnum.REJECT.getValue().intValue());
            approveAction = BpmButtonEnum.REJECT.getKey();
        }
        instanceMapper.updateByPrimaryKeySelective(instance);
        //保存日志
        BpmInstanceNote note = instanceNoteMapper.getCurrentNoteByInstanceId(bpmInstance.getId());
        saveRecord(instance, approveParam,approveAction, note.getNoteName(), note.getId(),loginUser);
        return RespData.success("操作成功");
    }

    public void saveRecord(BpmInstance instance, ApproveParam approveParam,
                           String approveAction, String currentNoteName, Long currentNoteId,LoginUser loginUser) {
        BpmApproveRecords records = new BpmApproveRecords();
        records.setInstanceId(instance.getId());
        records.setApproveUserId(loginUser.getUserId());
        records.setApproveUserName(loginUser.getUserName());
        records.setApproveContent(approveParam.getApproveContent());
        records.setApproveAction(approveAction);
        records.setNoteId(currentNoteId + "");
        records.setNoteName(currentNoteName);
        approveRecordsMapper.insertSelective(records);
    }
}

