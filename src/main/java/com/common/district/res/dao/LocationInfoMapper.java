package com.common.district.res.dao;

import com.common.district.res.model.LocationInfo;
import com.common.district.res.vo.LocationInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LocationInfoMapper {
    int deleteByPrimaryKey(String locationId);

    int insert(LocationInfo record);

    int insertSelective(LocationInfo record);

    LocationInfo selectByPrimaryKey(String locationId);

    int updateByPrimaryKeySelective(LocationInfo record);

    int updateByPrimaryKey(LocationInfo record);

    LocationInfo selectByNameAndOrganId(@Param("locationName") String locationName,@Param("organId")String organId,@Param("projectId")String projectId,@Param("type")Integer type);

    //根据id查询子级树
    List<LocationInfoVo> selectByParentId(@Param("parentId")String parentId);

    /**
     * 根据项目id查询位置信息 （删除项目 用）
     */
    List<LocationInfo> selectByProjectId(@Param("organId")String organId,@Param("projectId")String projectId);
}