package com.common.district.sys.dao;

import com.common.district.sys.model.SysApproveRelation;

public interface SysApproveRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysApproveRelation record);

    int insertSelective(SysApproveRelation record);

    SysApproveRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysApproveRelation record);

    int updateByPrimaryKey(SysApproveRelation record);
}