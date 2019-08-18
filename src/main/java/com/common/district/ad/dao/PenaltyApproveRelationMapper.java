package com.common.district.ad.dao;

import com.common.district.ad.model.PenaltyApproveRelation;

public interface PenaltyApproveRelationMapper {
    int deleteByPrimaryKey(Long relationId);

    int insert(PenaltyApproveRelation record);

    int insertSelective(PenaltyApproveRelation record);

    PenaltyApproveRelation selectByPrimaryKey(Long relationId);

    int updateByPrimaryKeySelective(PenaltyApproveRelation record);

    int updateByPrimaryKey(PenaltyApproveRelation record);
}