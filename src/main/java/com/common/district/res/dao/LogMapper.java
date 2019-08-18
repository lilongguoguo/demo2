package com.common.district.res.dao;

import com.common.district.res.model.Log;

import java.util.List;

public interface LogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

    List<Log> selectByCustomerId(Long customerId);
}