package com.common.district.ad.dao;

import com.common.district.ad.model.ReceivablesBillDerateRelation;
import com.common.district.ad.model.ViolateDerateRelation;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ViolateDerateRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ViolateDerateRelation record);

    int insertSelective(ViolateDerateRelation record);

    ViolateDerateRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ViolateDerateRelation record);

    int updateByPrimaryKey(ViolateDerateRelation record);

    List<ViolateDerateRelation> selectByBillId(Long billId);

}