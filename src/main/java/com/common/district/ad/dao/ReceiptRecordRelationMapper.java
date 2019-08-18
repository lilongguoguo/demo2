package com.common.district.ad.dao;

import com.common.district.ad.model.ReceiptRecordRelation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRecordRelationMapper {
    int deleteByPrimaryKey(Long rrrId);

    int insert(ReceiptRecordRelation record);

    int insertSelective(ReceiptRecordRelation record);

    ReceiptRecordRelation selectByPrimaryKey(Long rrrId);

    int updateByPrimaryKeySelective(ReceiptRecordRelation record);

    int updateByPrimaryKey(ReceiptRecordRelation record);

    List<ReceiptRecordRelation> selectByBillId(Long billId);

    Long selectBillIdByRbdId(Long rbdId);
}