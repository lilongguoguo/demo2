package com.common.district.ad.dao;

import com.common.district.ad.model.ReceiptRecord;
import com.common.district.ad.model.ReceivablesBill;
import com.common.district.ad.vo.ReceiptRecordVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRecordMapper {
    int deleteByPrimaryKey(Long rrId);

    int insert(ReceiptRecord record);

    int insertSelective(ReceiptRecord record);

    ReceiptRecord selectByPrimaryKey(Long rrId);

    int updateByPrimaryKeySelective(ReceiptRecord record);

    int updateByPrimaryKey(ReceiptRecord record);

    /**
     * 获取收款管理列表
     */
    List<ReceiptRecordVo> getReceiptList(ReceiptRecordVo receVo);
    /**
     * 获取收款管理列表条数
     */
    Integer getReceiptListCount(ReceiptRecordVo receVo);
    /**
     * 获取应收账单列表
     */
    List<ReceivablesBill> getReceiptBillList(Long rrId);
}