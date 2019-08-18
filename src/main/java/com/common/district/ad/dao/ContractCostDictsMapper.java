package com.common.district.ad.dao;

import com.common.district.ad.model.ContractCostDicts;

import java.util.List;

public interface ContractCostDictsMapper {
    int deleteByPrimaryKey(Long costId);

    int insert(ContractCostDicts record);

    int insertSelective(ContractCostDicts record);

    ContractCostDicts selectByPrimaryKey(Long costId);

    int updateByPrimaryKeySelective(ContractCostDicts record);

    int updateByPrimaryKey(ContractCostDicts record);

    /**
     * 获取费项
     */
    List<ContractCostDicts> getCostItemList(String organId);
}