package com.common.district.res.dao;

import com.common.district.res.model.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionMapper {
    int deleteByPrimaryKey(String dictRegionId);

    int insert(Region record);

    int insertSelective(Region record);

    Region selectByPrimaryKey(String dictRegionId);

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKey(Region record);

    List<Region> getProvince();

    List<Region> selectByParentId(@Param("parentId")String parentId);
}