package com.common.district.ad.model;

import java.math.BigDecimal;
import java.util.Date;

public class RefundAmount {
    private Long raId;

    private String raNo;

    private Long rrId;

    private String rrNo;

    private Date raDate;

    private BigDecimal raAllAmount;

    private BigDecimal raReceivablesAllAmount;

    private BigDecimal raViolateAllAmount;

    private Integer raType;

    private String applyUserId;

    private String applyUserName;

    private Date applyTime;

    private Integer raStatus;

    private Integer refundStatus;

    private String createUserId;

    private String createUserName;

    private Date createTime;

    private String updateUserId;

    private String updateUserName;

    private Date updateTime;

    private Integer del;

    private String remark;

    public Long getRaId() {
        return raId;
    }

    public void setRaId(Long raId) {
        this.raId = raId;
    }

    public String getRaNo() {
        return raNo;
    }

    public void setRaNo(String raNo) {
        this.raNo = raNo == null ? null : raNo.trim();
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

    public Date getRaDate() {
        return raDate;
    }

    public void setRaDate(Date raDate) {
        this.raDate = raDate;
    }

    public BigDecimal getRaAllAmount() {
        return raAllAmount;
    }

    public void setRaAllAmount(BigDecimal raAllAmount) {
        this.raAllAmount = raAllAmount;
    }

    public BigDecimal getRaReceivablesAllAmount() {
        return raReceivablesAllAmount;
    }

    public void setRaReceivablesAllAmount(BigDecimal raReceivablesAllAmount) {
        this.raReceivablesAllAmount = raReceivablesAllAmount;
    }

    public BigDecimal getRaViolateAllAmount() {
        return raViolateAllAmount;
    }

    public void setRaViolateAllAmount(BigDecimal raViolateAllAmount) {
        this.raViolateAllAmount = raViolateAllAmount;
    }

    public Integer getRaType() {
        return raType;
    }

    public void setRaType(Integer raType) {
        this.raType = raType;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId == null ? null : applyUserId.trim();
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName == null ? null : applyUserName.trim();
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getRaStatus() {
        return raStatus;
    }

    public void setRaStatus(Integer raStatus) {
        this.raStatus = raStatus;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
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

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName == null ? null : updateUserName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}