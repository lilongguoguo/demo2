package com.common.district.report.dao;

import com.common.district.report.model.ReportProject;

public interface ReportProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReportProject record);

    int insertSelective(ReportProject record);

    ReportProject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReportProject record);

    int updateByPrimaryKey(ReportProject record);
}