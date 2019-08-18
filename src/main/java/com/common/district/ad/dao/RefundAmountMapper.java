package com.common.district.ad.dao;

import com.common.district.ad.model.RefundAmount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefundAmountMapper {
    int deleteByPrimaryKey(Long raId);

    int insert(RefundAmount record);

    int insertSelective(RefundAmount record);

    RefundAmount selectByPrimaryKey(Long raId);

    int updateByPrimaryKeySelective(RefundAmount record);

    int updateByPrimaryKeyWithBLOBs(RefundAmount record);

    int updateByPrimaryKey(RefundAmount record);

    List<RefundAmount> selectByRrIdlist(@Param("rrIdlist") List<Long> rrIdlist);
}