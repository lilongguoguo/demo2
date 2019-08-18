package com.common.district.res.model;

import java.util.Date;

public class BranchBank {
    private Long id;

    private String branchBankCode;

    private String branchBankName;

    private Integer branchBankType;

    private String bankUrid;

    private Long parentId;

    private Integer activityStatus;

    private String tips;

    private String remark;

    private Integer indexOrder;

    private String createdUserId;

    private String createUserName;

    private Date createdDate;

    private String updateUserId;

    private String updateUserName;

    private Date updateDate;

    private String organId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranchBankCode() {
        return branchBankCode;
    }

    public void setBranchBankCode(String branchBankCode) {
        this.branchBankCode = branchBankCode == null ? null : branchBankCode.trim();
    }

    public String getBranchBankName() {
        return branchBankName;
    }

    public void setBranchBankName(String branchBankName) {
        this.branchBankName = branchBankName == null ? null : branchBankName.trim();
    }

    public Integer getBranchBankType() {
        return branchBankType;
    }

    public void setBranchBankType(Integer branchBankType) {
        this.branchBankType = branchBankType;
    }

    public String getBankUrid() {
        return bankUrid;
    }

    public void setBankUrid(String bankUrid) {
        this.bankUrid = bankUrid == null ? null : bankUrid.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips == null ? null : tips.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIndexOrder() {
        return indexOrder;
    }

    public void setIndexOrder(Integer indexOrder) {
        this.indexOrder = indexOrder;
    }

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId == null ? null : createdUserId.trim();
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId == null ? null : organId.trim();
    }
}