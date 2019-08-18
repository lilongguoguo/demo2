package com.common.district.report.dao;

import com.common.district.report.model.ContractArchiveHalfmonth;

public interface ContractArchiveHalfmonthMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ContractArchiveHalfmonth record);

    int insertSelective(ContractArchiveHalfmonth record);

    ContractArchiveHalfmonth selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ContractArchiveHalfmonth record);

    int updateByPrimaryKey(ContractArchiveHalfmonth record);
}