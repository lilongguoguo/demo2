package com.common.district.ad.dao;

import com.common.district.ad.model.ContractPenaltyPlan;

public interface ContractPenaltyPlanMapper {
    int deleteByPrimaryKey(Long penaltyId);

    int insert(ContractPenaltyPlan record);

    int insertSelective(ContractPenaltyPlan record);

    ContractPenaltyPlan selectByPrimaryKey(Long penaltyId);

    int updateByPrimaryKeySelective(ContractPenaltyPlan record);

    int updateByPrimaryKeyWithBLOBs(ContractPenaltyPlan record);

    int updateByPrimaryKey(ContractPenaltyPlan record);
}