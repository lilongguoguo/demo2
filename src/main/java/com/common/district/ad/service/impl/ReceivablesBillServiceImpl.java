package com.common.district.ad.service.impl;

import com.common.district.ad.dao.*;
import com.common.district.ad.model.*;
import com.common.district.ad.service.ReceivablesBillService;
import com.common.district.ad.vo.ReceivablesBillVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceivablesBillServiceImpl implements ReceivablesBillService {
    @Autowired
    private ReceivablesBillMapper receivablesBillMapper;
    @Autowired
    private RefundAmountMapper refundAmountMapper;
    @Autowired
    private ReceivablesBillDerateRelationMapper relationMapper;
    @Autowired
    private ReceiptRecordRelationMapper recordRelationMapper;
    @Autowired
    private ViolateDerateRelationMapper violateDerateRelationMapper;
    @Override
    public List<ReceivablesBill> selectReceivablesBillByCondition(ReceivablesBillVo vo) {
        return receivablesBillMapper.selectReceivablesBillByCondition(vo);
    }
    @Override
    public Integer selectReceivablesBillCountByCondition(ReceivablesBillVo vo) {
        return receivablesBillMapper.selectReceivablesBillCountByCondition(vo);
    }

    @Override
    public ReceivablesBill selectReceivablesBillDetailByCondition(Long billId) {
        //应收账单
        ReceivablesBill receivablesBill = receivablesBillMapper.selectByPrimaryKey(billId);
        //应收减免申请信息
        List<ReceivablesBillDerateRelation> receivablesBillDerateList=relationMapper.selectByBillId(billId);
        //违约金减免
        List<ViolateDerateRelation> violateDerateRelations = violateDerateRelationMapper.selectByBillId(billId);
        return receivablesBill.new ReceivablesBillBuilder()
                .withReceivablesBillDerateList(receivablesBillDerateList)
                .withViolateDerate(violateDerateRelations)
                .build();
    }

    @Override
    public List<ReceivablesBill> selectReceivablesBillByRbIdList(List<String> rbIdList) {
        //应收账单
        List<ReceivablesBill> receivablesBill = receivablesBillMapper.selectByPrimaryKeyList(rbIdList);
        receivablesBill.forEach(f->{
//            //应收减免申请信息
//            List<ReceivablesBillDerateRelation> receivablesBillDerateList=relationMapper.selectByBillId(f.getRbId());
            //违约金减免
//            List<ViolateDerateRelation> violateDerateRelations = violateDerateRelationMapper.selectByBillId(f.getRbId());
            f.new ReceivablesBillBuilder()
//                    .withReceivablesBillDerateList(receivablesBillDerateList)
//                    .withViolateDerate(violateDerateRelations)
                    .build();
        });
        return receivablesBill;
    }
}
