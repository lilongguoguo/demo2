package com.common.district.res.dao;

import com.common.district.res.model.District;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DistrictMapper {
    int deleteByPrimaryKey(String regionId);

    int insert(District record);

    int insertSelective(District record);

    District selectByPrimaryKey(String regionId);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);

    List<District> getDistrict(@Param("organId") String organId, @Param("companyId")String companyId);


    District selectByNameAndOrganId(@Param("regionName") String regionName,@Param("organId")String organId);
}