package com.common.district.report.dao;

import com.common.district.report.model.ContractArchiveHalfyear;

public interface ContractArchiveHalfyearMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ContractArchiveHalfyear record);

    int insertSelective(ContractArchiveHalfyear record);

    ContractArchiveHalfyear selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ContractArchiveHalfyear record);

    int updateByPrimaryKey(ContractArchiveHalfyear record);
}