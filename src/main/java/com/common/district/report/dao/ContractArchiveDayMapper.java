package com.common.district.report.dao;

import com.common.district.report.model.ContractArchiveDay;

public interface ContractArchiveDayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ContractArchiveDay record);

    int insertSelective(ContractArchiveDay record);

    ContractArchiveDay selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ContractArchiveDay record);

    int updateByPrimaryKey(ContractArchiveDay record);
}