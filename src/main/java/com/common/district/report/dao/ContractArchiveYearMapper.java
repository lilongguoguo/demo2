package com.common.district.report.dao;

import com.common.district.report.model.ContractArchiveYear;

public interface ContractArchiveYearMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ContractArchiveYear record);

    int insertSelective(ContractArchiveYear record);

    ContractArchiveYear selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ContractArchiveYear record);

    int updateByPrimaryKey(ContractArchiveYear record);
}