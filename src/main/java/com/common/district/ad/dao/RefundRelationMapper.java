package com.common.district.ad.dao;

import com.common.district.ad.model.RefundRelation;

public interface RefundRelationMapper {
    int deleteByPrimaryKey(Long arrId);

    int insert(RefundRelation record);

    int insertSelective(RefundRelation record);

    RefundRelation selectByPrimaryKey(Long arrId);

    int updateByPrimaryKeySelective(RefundRelation record);

    int updateByPrimaryKey(RefundRelation record);
}