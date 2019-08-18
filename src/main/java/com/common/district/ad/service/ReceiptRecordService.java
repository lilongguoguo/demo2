package com.common.district.ad.service;

import com.common.district.ad.model.ReceiptRecord;
import com.common.district.ad.model.ReceivablesBill;
import com.common.district.ad.vo.ReceiptRecordVo;
import com.common.district.common.SysUtil.PageInfo;
import com.common.district.res.model.Company;

import java.util.List;

public interface ReceiptRecordService {
    /**
     *     查询收款管理列表
     */
    PageInfo<ReceiptRecord> getReceiptList(ReceiptRecordVo receVo);
    /**
     * 获取公司下拉列表
     */
    List<Company> getCompanyListByOrganId(String organId);
    /**
     * 获取应收账单列表
     */
    List<ReceivablesBill> getReceiptBillList(Long rrId);
    /**
     * 获取收款信息&发票信息
     */
    ReceiptRecord getReceiptById(Long rrId);
}
