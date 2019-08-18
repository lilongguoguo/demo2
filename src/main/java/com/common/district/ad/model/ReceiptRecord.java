package com.common.district.ad.model;

import java.math.BigDecimal;
import java.util.Date;

public class ReceiptRecord {
    private Long rrId;

    private String rrNo;

    private Integer billCount;

    private BigDecimal receivablesAmount;

    private BigDecimal violateAmount;

    private BigDecimal rrAmount;

    private String rrType;

    private String rrUserId;

    private String rrUserName;

    private Date rrDate;

    private Integer invoice;

    private String invoiceNo;

    private Integer invoiceType;

    //发票抬头
    private String invoiceTitle;

    //纳税人识别号
    private String invoiceCode;

    //开户行
    private String invoiceBank;

    //开户行账号
    private String invoiceBankAccount;

    //客户电话
    private String invoiceCusTel;

    //客户地址
    private String invoiceCusAddress;

    private String remark;

    private String createUserId;

    private String createUserName;

    private Date createTime;

    private String organId;

    private Integer del;


    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceBank() {
        return invoiceBank;
    }

    public void setInvoiceBank(String invoiceBank) {
        this.invoiceBank = invoiceBank;
    }

    public String getInvoiceBankAccount() {
        return invoiceBankAccount;
    }

    public void setInvoiceBankAccount(String invoiceBankAccount) {
        this.invoiceBankAccount = invoiceBankAccount;
    }

    public String getInvoiceCusTel() {
        return invoiceCusTel;
    }

    public void setInvoiceCusTel(String invoiceCusTel) {
        this.invoiceCusTel = invoiceCusTel;
    }

    public String getInvoiceCusAddress() {
        return invoiceCusAddress;
    }

    public void setInvoiceCusAddress(String invoiceCusAddress) {
        this.invoiceCusAddress = invoiceCusAddress;
    }

    public Long getRrId() {
        return rrId;
    }

    public void setRrId(Long rrId) {
        this.rrId = rrId;
    }

    public String getRrNo() {
        return rrNo;
    }

    public void setRrNo(String rrNo) {
        this.rrNo = rrNo == null ? null : rrNo.trim();
    }

    public Integer getBillCount() {
        return billCount;
    }

    public void setBillCount(Integer billCount) {
        this.billCount = billCount;
    }

    public BigDecimal getReceivablesAmount() {
        return receivablesAmount;
    }

    public void setReceivablesAmount(BigDecimal receivablesAmount) {
        this.receivablesAmount = receivablesAmount;
    }

    public BigDecimal getViolateAmount() {
        return violateAmount;
    }

    public void setViolateAmount(BigDecimal violateAmount) {
        this.violateAmount = violateAmount;
    }

    public BigDecimal getRrAmount() {
        return rrAmount;
    }

    public void setRrAmount(BigDecimal rrAmount) {
        this.rrAmount = rrAmount;
    }

    public String getRrType() {
        return rrType;
    }

    public void setRrType(String rrType) {
        this.rrType = rrType == null ? null : rrType.trim();
    }

    public String getRrUserId() {
        return rrUserId;
    }

    public void setRrUserId(String rrUserId) {
        this.rrUserId = rrUserId == null ? null : rrUserId.trim();
    }

    public String getRrUserName() {
        return rrUserName;
    }

    public void setRrUserName(String rrUserName) {
        this.rrUserName = rrUserName == null ? null : rrUserName.trim();
    }

    public Date getRrDate() {
        return rrDate;
    }

    public void setRrDate(Date rrDate) {
        this.rrDate = rrDate;
    }

    public Integer getInvoice() {
        return invoice;
    }

    public void setInvoice(Integer invoice) {
        this.invoice = invoice;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo == null ? null : invoiceNo.trim();
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId == null ? null : organId.trim();
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}