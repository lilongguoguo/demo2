package com.common.district.bpm.service;

import com.common.district.bpm.vo.ApproveParam;
import com.common.district.bpm.vo.RespBpmButtonVo;
import com.common.district.bpm.vo.RespBpmInstanceNoteVo;
import com.common.district.common.SysUtil.RespData;
import com.common.district.org.model.LoginUser;

import java.util.List;
import java.util.Map;

/**
 * bpm接口 by lilong on 2019-08-08
 */
public interface BpmService {

    /**
     * 查询审批流对应节点(编辑)
     * @param relationId
     * @param relationType
     *@param templateCode
     * @param loginInfo   @return
     */
    RespData<RespBpmInstanceNoteVo> selectNoteList(Long relationId, Integer relationType, String templateCode, LoginUser loginInfo);

    /**
     * 查询审批按钮
     * @param relationId
     * @param relationType
     * @param loginUser
     * @return
     */
    RespData<RespBpmButtonVo> selectButtonList(Long relationId, Integer relationType, LoginUser loginUser);

    /**
     * 查询审批记录
     * @param relationId
     * @param relationType
     * @return
     */
    RespData selectRecordList(Long relationId, Integer relationType);

    /**
     * 查询审批流对应节点（查看）
     * @param relationId
     * @param relationType
     * @return
     */
    RespData selectNoteListR(Long relationId, Integer relationType);

    /**
     * 通用审批入口
     * @param approveParam
     * @param loginInfo
     * @return
     */
    RespData doApprove(ApproveParam approveParam, LoginUser loginInfo);
}
