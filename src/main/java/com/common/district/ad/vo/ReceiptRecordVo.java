package com.common.district.ad.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 收款管理vo
 */
public class ReceiptRecordVo implements Serializable {

    //已收账单编号
    private Long rrId;
    //已收账单编号
    private String rrNo;
    //合同编号
    private String contNo;
    //应收账单编号
    private String rbNo;
    //合同所属公司
    private String contCompany;
    //客户名称
    private String custName;
    //经办人
    private String operName;
    //收款开始日期
    private Date startDate;
    //收款结束日期
    private Date endDate;
    //pageNum
    private Integer pageNum;
    //pageSize
    private Integer pageSize;
    //租户id
    private String organId;


    //应收账单数量
    private Integer billCount;
    //收款时间
    private String receDate;

    public String getReceDate() {
        return receDate;
    }

    public void setReceDate(String receDate) {
        this.receDate = receDate;
    }

    public Integer getBillCount() {
        return billCount;
    }

    public void setBillCount(Integer billCount) {
        this.billCount = billCount;
    }

    public void setRrId(Long rrId) {
        this.rrId = rrId;
    }

    public Long getRrId() {
        return rrId;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getRrNo() {
        return rrNo;
    }

    public void setRrNo(String rrNo) {
        this.rrNo = rrNo;
    }

    public String getContNo() {
        return contNo;
    }

    public void setContNo(String contNo) {
        this.contNo = contNo;
    }

    public String getRbNo() {
        return rbNo;
    }

    public void setRbNo(String rbNo) {
        this.rbNo = rbNo;
    }

    public String getContCompany() {
        return contCompany;
    }

    public void setContCompany(String contCompany) {
        this.contCompany = contCompany;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
