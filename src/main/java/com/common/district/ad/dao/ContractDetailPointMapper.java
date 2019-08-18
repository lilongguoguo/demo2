package com.common.district.ad.dao;

import com.common.district.ad.model.ContractDetailPoint;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContractDetailPointMapper {
    int deleteByPrimaryKey(Long contDetailPointId);

    int insert(ContractDetailPoint record);

    int insertSelective(ContractDetailPoint record);

    ContractDetailPoint selectByPrimaryKey(Long contDetailPointId);

    int updateByPrimaryKeySelective(ContractDetailPoint record);

    int updateByPrimaryKey(ContractDetailPoint record);

    int insertDetailPoint(@Param("lists")List<ContractDetailPoint> lists);

    List<ContractDetailPoint> contractDetailPointByContId(Long contId);
}