package com.common.district.ad.dao;

import com.common.district.ad.model.ContractSupAttach;

public interface ContractSupAttachMapper {
    int deleteByPrimaryKey(Long contAttachId);

    int insert(ContractSupAttach record);

    int insertSelective(ContractSupAttach record);

    ContractSupAttach selectByPrimaryKey(Long contAttachId);

    int updateByPrimaryKeySelective(ContractSupAttach record);

    int updateByPrimaryKey(ContractSupAttach record);
}