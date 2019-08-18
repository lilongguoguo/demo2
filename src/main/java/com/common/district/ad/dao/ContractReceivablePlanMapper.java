package com.common.district.ad.dao;

import com.common.district.ad.model.ContractReceivablePlan;

public interface ContractReceivablePlanMapper {
    int deleteByPrimaryKey(Long receivablePlanId);

    int insert(ContractReceivablePlan record);

    int insertSelective(ContractReceivablePlan record);

    ContractReceivablePlan selectByPrimaryKey(Long receivablePlanId);

    int updateByPrimaryKeySelective(ContractReceivablePlan record);

    int updateByPrimaryKey(ContractReceivablePlan record);
}