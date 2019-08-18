package com.common.district.ad.service.impl;

import com.common.district.ad.dao.ReceiptRecordMapper;
import com.common.district.ad.model.ReceiptRecord;
import com.common.district.ad.model.ReceivablesBill;
import com.common.district.ad.service.ReceiptRecordService;
import com.common.district.ad.vo.ReceiptRecordVo;
import com.common.district.common.SysUtil.PageInfo;
import com.common.district.common.SysUtil.RespData;
import com.common.district.res.dao.CompanyMapper;
import com.common.district.res.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptRecordServiceImpl implements ReceiptRecordService {
    @Autowired
    private ReceiptRecordMapper receiptRecordMapper;
    @Autowired
    private CompanyMapper companyMapper;


    @Override
    public PageInfo<ReceiptRecord> getReceiptList(ReceiptRecordVo receVo) {
        if(receVo.getPageNum() == null || receVo.getPageNum() <= 0 ){
            receVo.setPageNum(1);
        }
        if(receVo.getPageSize() == null || receVo.getPageSize() <= 0){
            receVo.setPageSize(10);
        }
        List<ReceiptRecordVo> receiptRecords = receiptRecordMapper.getReceiptList(receVo);
        Integer totalCount = receiptRecordMapper.getReceiptListCount(receVo);
        return new PageInfo(totalCount.longValue() , receiptRecords);
    }

    @Override
    public List<Company> getCompanyListByOrganId(String organId) {
        return companyMapper.getCompany(organId);
    }

    /**
     * 获取应收账单列表
     */
    @Override
    public List<ReceivablesBill> getReceiptBillList(Long rrId) {
        return receiptRecordMapper.getReceiptBillList(rrId);
    }

    @Override
    public ReceiptRecord getReceiptById(Long rrId) {
        return receiptRecordMapper.selectByPrimaryKey(rrId);
    }
}
