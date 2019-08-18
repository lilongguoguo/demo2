package com.common.district.ad.dao;

import com.common.district.ad.model.ReceivablesBillDerateRelation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceivablesBillDerateRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReceivablesBillDerateRelation record);

    int insertSelective(ReceivablesBillDerateRelation record);

    ReceivablesBillDerateRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReceivablesBillDerateRelation record);

    int updateByPrimaryKeyWithBLOBs(ReceivablesBillDerateRelation record);

    int updateByPrimaryKey(ReceivablesBillDerateRelation record);

    List<ReceivablesBillDerateRelation> selectByBillId(Long billId);
}