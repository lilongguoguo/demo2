package com.common.district.ad.model;

import java.math.BigDecimal;
import java.util.Date;

public class ViolateDerate {
    private Long rvdId;

    private Integer derateType;

    private Date byEndDate;

    private BigDecimal byRatio;

    private BigDecimal bySumMoney;

    private String applyUserId;

    private String applyUserName;

    private Date applyDate;

    private Integer derateStatus;

    private Long contId;

    private String createUserId;

    private String createUserName;

    private Date createTime;

    private Integer del;

    private String remark;

    public Long getRvdId() {
        return rvdId;
    }

    public void setRvdId(Long rvdId) {
        this.rvdId = rvdId;
    }

    public Integer getDerateType() {
        return derateType;
    }

    public void setDerateType(Integer derateType) {
        this.derateType = derateType;
    }

    public Date getByEndDate() {
        return byEndDate;
    }

    public void setByEndDate(Date byEndDate) {
        this.byEndDate = byEndDate;
    }

    public BigDecimal getByRatio() {
        return byRatio;
    }

    public void setByRatio(BigDecimal byRatio) {
        this.byRatio = byRatio;
    }

    public BigDecimal getBySumMoney() {
        return bySumMoney;
    }

    public void setBySumMoney(BigDecimal bySumMoney) {
        this.bySumMoney = bySumMoney;
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

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Integer getDerateStatus() {
        return derateStatus;
    }

    public void setDerateStatus(Integer derateStatus) {
        this.derateStatus = derateStatus;
    }

    public Long getContId() {
        return contId;
    }

    public void setContId(Long contId) {
        this.contId = contId;
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