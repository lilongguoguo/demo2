package com.common.district.ad.dao;

import com.common.district.ad.model.ViolateRecord;

public interface ViolateRecordMapper {
    int deleteByPrimaryKey(Long vrId);

    int insert(ViolateRecord record);

    int insertSelective(ViolateRecord record);

    ViolateRecord selectByPrimaryKey(Long vrId);

    int updateByPrimaryKeySelective(ViolateRecord record);

    int updateByPrimaryKey(ViolateRecord record);
}