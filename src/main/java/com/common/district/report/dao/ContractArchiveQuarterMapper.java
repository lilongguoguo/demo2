package com.common.district.report.dao;

import com.common.district.report.model.ContractArchiveQuarter;

public interface ContractArchiveQuarterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ContractArchiveQuarter record);

    int insertSelective(ContractArchiveQuarter record);

    ContractArchiveQuarter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ContractArchiveQuarter record);

    int updateByPrimaryKey(ContractArchiveQuarter record);
}