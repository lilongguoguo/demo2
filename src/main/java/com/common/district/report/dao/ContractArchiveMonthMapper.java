package com.common.district.report.dao;

import com.common.district.report.model.ContractArchiveMonth;

public interface ContractArchiveMonthMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ContractArchiveMonth record);

    int insertSelective(ContractArchiveMonth record);

    ContractArchiveMonth selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ContractArchiveMonth record);

    int updateByPrimaryKey(ContractArchiveMonth record);
}