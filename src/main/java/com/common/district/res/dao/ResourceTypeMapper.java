package com.common.district.res.dao;

import com.common.district.res.model.ResourceType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(ResourceType record);

    int insertSelective(ResourceType record);

    ResourceType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResourceType record);

    int updateByPrimaryKey(ResourceType record);

    List<ResourceType> selectResourceTypeByLevel(@Param("organId")String organId,@Param("type")Integer type);

    List<ResourceType> selectResourceTypeByParentId(@Param("organId")String organId,@Param("parentId")String parentId);

    ResourceType selectByName(@Param("organId")String organId,@Param("name")String name);

    int deleteByParentId(@Param("organId")String organId,@Param("parentId")String parentId,@Param("level")Integer level);

    List<ResourceType> getResourceType(@Param("organId")String organId,@Param("parentId")String parentId);

    ResourceType selectSecondResByName(@Param("organId")String organId,@Param("name")String name,@Param("parentId")String parentId);

    String selectResourceNameById(@Param("id")String id);
}