package com.common.district.report.dao;

import com.common.district.report.model.ContractArchiveWeek;

public interface ContractArchiveWeekMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ContractArchiveWeek record);

    int insertSelective(ContractArchiveWeek record);

    ContractArchiveWeek selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ContractArchiveWeek record);

    int updateByPrimaryKey(ContractArchiveWeek record);
}