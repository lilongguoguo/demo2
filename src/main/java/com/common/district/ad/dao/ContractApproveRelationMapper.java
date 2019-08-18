package com.common.district.ad.dao;

import com.common.district.ad.model.ContractApproveRelation;

public interface ContractApproveRelationMapper {
    int deleteByPrimaryKey(Long relationId);

    int insert(ContractApproveRelation record);

    int insertSelective(ContractApproveRelation record);

    ContractApproveRelation selectByPrimaryKey(Long relationId);

    int updateByPrimaryKeySelective(ContractApproveRelation record);

    int updateByPrimaryKey(ContractApproveRelation record);
}