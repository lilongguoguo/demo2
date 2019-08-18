package com.common.district.report.dao;

import com.common.district.report.model.ArchiveContract;

public interface ArchiveContractMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArchiveContract record);

    int insertSelective(ArchiveContract record);

    ArchiveContract selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArchiveContract record);

    int updateByPrimaryKey(ArchiveContract record);
}