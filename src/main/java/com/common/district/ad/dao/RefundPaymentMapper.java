package com.common.district.ad.dao;

import com.common.district.ad.model.RefundPayment;

public interface RefundPaymentMapper {
    int deleteByPrimaryKey(Long riId);

    int insert(RefundPayment record);

    int insertSelective(RefundPayment record);

    RefundPayment selectByPrimaryKey(Long riId);

    int updateByPrimaryKeySelective(RefundPayment record);

    int updateByPrimaryKey(RefundPayment record);
}