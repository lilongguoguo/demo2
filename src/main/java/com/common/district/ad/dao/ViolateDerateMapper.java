package com.common.district.ad.dao;

import com.common.district.ad.model.ViolateDerate;
import org.springframework.stereotype.Repository;

@Repository
public interface ViolateDerateMapper {
    int deleteByPrimaryKey(Long rvdId);

    int insert(ViolateDerate record);

    int insertSelective(ViolateDerate record);

    int insertSelectiveKey(ViolateDerate record);

    ViolateDerate selectByPrimaryKey(Long rvdId);

    int updateByPrimaryKeySelective(ViolateDerate record);

    int updateByPrimaryKeyWithBLOBs(ViolateDerate record);

    int updateByPrimaryKey(ViolateDerate record);
}