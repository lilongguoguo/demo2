package com.common.district.ad.service.impl;

import com.common.district.ad.dao.*;
import com.common.district.ad.model.*;
import com.common.district.ad.service.ReceivablesBillDerateService;
import com.common.district.ad.service.ReceivablesBillService;
import com.common.district.ad.vo.ReceiptAmountRequestVo;
import com.common.district.ad.vo.ReceivablesBillDerateReturnVo;
import com.common.district.ad.vo.ReceivablesBillDerateVo;
import com.common.district.ad.vo.ReceivablesBillVo;
import com.common.district.common.SysUtil.RespData;
import com.common.district.common.constants.ConstantsEnum;
import com.common.district.common.utils.IdCreateUtil;
import com.common.district.common.utils.StringUtils;
import com.common.district.org.model.LoginUser;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ReceivablesBillServiceDerateImpl implements ReceivablesBillDerateService {
    @Autowired
    private ReceivablesBillMapper receivablesBillMapper;
    @Autowired
    private RefundAmountMapper refundAmountMapper;
    @Autowired
    private ReceivablesBillDerateRelationMapper relationMapper;
    @Autowired
    private ReceiptRecordRelationMapper recordRelationMapper;
    @Autowired
    private ReceivablesBillDerateMapper billDerateMapper;
    @Autowired
    private ViolateDerateMapper violateDerateMapper;
    @Autowired
    private ViolateDerateRelationMapper derateRelationMapper;
    @Override
    public List<ReceivablesBillDerateReturnVo> selectReceivablesBillDerateByCondition(ReceivablesBillDerateVo vo) {
        return billDerateMapper.selectReceivablesBillDerateByCondition(vo);
    }
    @Override
    public Integer selectReceivablesBillDerateCountByCondition(ReceivablesBillDerateVo vo) {
        return billDerateMapper.selectReceivablesBillDerateCountByCondition(vo);
    }

    @Override
    public RespData amountDerateVal(List<ReceiptAmountRequestVo> vo) {
        for(ReceiptAmountRequestVo v: vo){
            if(StringUtils.isBlank(v.getRbId())||StringUtils.isBlank(v.getReceivableDerateAmount())){
                return RespData.fail("参数不足");
            }
            ReceivablesBill receivablesBill = receivablesBillMapper.selectByPrimaryKey(v.getRbId());
            //判断是否有正在走的流程  如果有不能发起流程
            Integer existFlowByBillId = billDerateMapper.isExistFlowByBillId(v.getRbId());
            if(StringUtils.isNotBlank(existFlowByBillId)&&existFlowByBillId > 0){
                return RespData.fail("编号为["+receivablesBill.getRbNo()+"]账单已存在进行中的减免流程！");
            }
            //减免金额不能大于剩余减免金额
            if(receivablesBill.getAfterRbAmount().doubleValue() < v.getReceivableDerateAmount()){
                return RespData.fail("编号为["+receivablesBill.getRbNo()+"]账单减免后应收金额不足！");
            }
        }
        return RespData.success("可发起审批流程");
    }

    @Override
    public RespData violateDerateVal(List<ReceiptAmountRequestVo> vo) {
        for(ReceiptAmountRequestVo v: vo){
            if(StringUtils.isBlank(v.getRbId())||StringUtils.isBlank(v.getViolateDerateAmount())){
                return RespData.fail("参数不足");
            }
            ReceivablesBill receivablesBill = receivablesBillMapper.selectByPrimaryKey(v.getRbId());
            //判断是否有正在走的流程  如果有不能发起流程
            Integer existFlowByBillId = billDerateMapper.isExistViolateFlowByBillId(v.getRbId());
            if(StringUtils.isNotBlank(existFlowByBillId)&&existFlowByBillId > 0){
                return RespData.fail("编号为["+receivablesBill.getRbNo()+"]账单已存在进行中的减免流程！");
            }
            //减免金额不能大于剩余减免金额
            if(receivablesBill.getAfterViolateAmount().doubleValue() < v.getViolateDerateAmount()){
                return RespData.fail("编号为["+receivablesBill.getRbNo()+"]账单减免后违约金不足！");
            }
        }
        return RespData.success("可发起审批流程");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RespData amountDerateSave(List<ReceiptAmountRequestVo> vo, LoginUser loginUser) {
        if(CollectionUtils.isEmpty(vo)){
            return RespData.fail("参数请求错误");
        }
        for(ReceiptAmountRequestVo v: vo) {
            if (StringUtils.isBlank(v.getRbId()) || StringUtils.isBlank(v.getReceivableDerateAmount())) {
                return RespData.fail("参数不足");
            }
            if (StringUtils.isBlank(v.getViolateDerateAmount())||v.getViolateDerateAmount()<=0) {
                return RespData.fail("应收减免金额应大于0");
            }
        }
        ReceivablesBillDerate derate=new ReceivablesBillDerate();
        derate.setApplyUserId(loginUser.getUserId());
        derate.setApplyUserName(loginUser.getUserName());
        derate.setApplyTime(new Date());
        derate.setCreateUserId(loginUser.getUserId());
        derate.setCreateUserName(loginUser.getUserName());
        derate.setCreateTime(new Date());
        //随意找一个合同-合同都一样
        derate.setContId(receivablesBillMapper.selectByPrimaryKey(vo.get(0).getRbId()).getContId());
        derate.setIsDel(0);
        billDerateMapper.insertSelectiveKey(derate);
        for(ReceiptAmountRequestVo v: vo) {
            ReceivablesBill rb = receivablesBillMapper.selectByPrimaryKey(v.getRbId());
            System.out.println(derate.getRbdId());
            ReceivablesBillDerateRelation relation=new ReceivablesBillDerateRelation();
            relation.setRbdId(derate.getRbdId());
            relation.setRbId(rb.getRbId());
            relation.setRbDerateAmount(new BigDecimal(v.getReceivableDerateAmount()));
            relation.setRbActualAmount(rb.getRbAmount().subtract(new BigDecimal(v.getReceivableDerateAmount())));
            relation.setRemark(v.getRemark());
            relation.setDel(0);
            relationMapper.insertSelective(relation);
        }
        return RespData.success(derate.getRbdId());
    }

    @Override
    public RespData violateDerateSave(List<ReceiptAmountRequestVo> vo, LoginUser loginUser) {
        if(CollectionUtils.isEmpty(vo)){
            return RespData.fail("参数请求错误");
        }
        for(ReceiptAmountRequestVo v: vo) {
            if (StringUtils.isBlank(v.getRbId()) || StringUtils.isBlank(v.getViolateDerateAmount())) {
                return RespData.fail("参数不足");
            }
            if (StringUtils.isBlank(v.getViolateDerateAmount())||v.getViolateDerateAmount()<=0) {
                return RespData.fail("违约金金额应大于0");
            }
        }
        ViolateDerate derate=new ViolateDerate();
        derate.setApplyUserId(loginUser.getUserId());
        derate.setApplyUserName(loginUser.getUserName());
        derate.setApplyDate(new Date());
        derate.setCreateUserId(loginUser.getUserId());
        derate.setCreateUserName(loginUser.getUserName());
        derate.setCreateTime(new Date());
        //随意找一个合同-合同都一样
        derate.setContId(receivablesBillMapper.selectByPrimaryKey(vo.get(0).getRbId()).getContId());
        derate.setDel(0);
        violateDerateMapper.insertSelectiveKey(derate);
        for(ReceiptAmountRequestVo v: vo) {
            ReceivablesBill rb = receivablesBillMapper.selectByPrimaryKey(v.getRbId());
            System.out.println(derate.getRvdId());
            ViolateDerateRelation relation=new ViolateDerateRelation();
            relation.setRvbId(derate.getRvdId());
            relation.setRbId(rb.getRbId());
            relation.setViolateDerateAmount(new BigDecimal(v.getViolateDerateAmount()));
            relation.setViolateActualAmount(rb.getViolateAmount().subtract(new BigDecimal(v.getViolateDerateAmount())));
            relation.setDel(0);
            derateRelationMapper.insertSelective(relation);
        }
        return RespData.success(derate.getRvdId());
    }
}
