package com.common.district.res.dao;

import com.common.district.res.model.BranchBank;

public interface BranchBankMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BranchBank record);

    int insertSelective(BranchBank record);

    BranchBank selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BranchBank record);

    int updateByPrimaryKey(BranchBank record);
}