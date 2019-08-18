package com.common.district.ad.model;

import java.math.BigDecimal;
import java.util.Date;

public class ViolateRecord {
    private Long vrId;

    private Long rbId;

    private Integer violateType;

    private BigDecimal violateValue;

    private Integer computeType;

    private BigDecimal violateAmount;

    private Date computeViolateTime;

    private String createUserId;

    private String createUserName;

    private Date createTime;

    private Integer isDel;

    public Long getVrId() {
        return vrId;
    }

    public void setVrId(Long vrId) {
        this.vrId = vrId;
    }

    public Long getRbId() {
        return rbId;
    }

    public void setRbId(Long rbId) {
        this.rbId = rbId;
    }

    public Integer getViolateType() {
        return violateType;
    }

    public void setViolateType(Integer violateType) {
        this.violateType = violateType;
    }

    public BigDecimal getViolateValue() {
        return violateValue;
    }

    public void setViolateValue(BigDecimal violateValue) {
        this.violateValue = violateValue;
    }

    public Integer getComputeType() {
        return computeType;
    }

    public void setComputeType(Integer computeType) {
        this.computeType = computeType;
    }

    public BigDecimal getViolateAmount() {
        return violateAmount;
    }

    public void setViolateAmount(BigDecimal violateAmount) {
        this.violateAmount = violateAmount;
    }

    public Date getComputeViolateTime() {
        return computeViolateTime;
    }

    public void setComputeViolateTime(Date computeViolateTime) {
        this.computeViolateTime = computeViolateTime;
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

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}