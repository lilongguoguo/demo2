package com.common.district.res.dao;

import com.common.district.res.model.IndustryBusiness;

public interface IndustryBusinessMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IndustryBusiness record);

    int insertSelective(IndustryBusiness record);

    IndustryBusiness selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IndustryBusiness record);

    int updateByPrimaryKey(IndustryBusiness record);
}