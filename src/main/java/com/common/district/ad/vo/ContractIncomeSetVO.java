package com.common.district.ad.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ContractIncomeSetVO {
    private Long incomePlanId;

    private Long contId;

    private String projectId;

    private String projectName;

    private Long costTypeId;

    private String costTypeName;

    private Byte receivePeriodId;

    private String receivePeriodName;

    private Byte increasePeriodId;

    private String increasePeriodName;

    private Byte increaseTypeId;

    private String increaseTypeName;

    private BigDecimal increaseValue;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date firstReceiveDate;

    private Integer fundDayType;

    private Integer afterReceiveDate;

    private BigDecimal receiveAmount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private BigDecimal ownerCommitteePercent;

    private Integer auto;

    private Date createTime;

    private String createUserId;

    private String createUserName;

    private Date updateTime;

    private String updateUserId;

    private String updateUserName;

    private Integer del;

    public Long getIncomePlanId() {
        return incomePlanId;
    }

    public void setIncomePlanId(Long incomePlanId) {
        this.incomePlanId = incomePlanId;
    }

    public Long getContId() {
        return contId;
    }

    public void setContId(Long contId) {
        this.contId = contId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public Long getCostTypeId() {
        return costTypeId;
    }

    public void setCostTypeId(Long costTypeId) {
        this.costTypeId = costTypeId;
    }

    public String getCostTypeName() {
        return costTypeName;
    }

    public void setCostTypeName(String costTypeName) {
        this.costTypeName = costTypeName == null ? null : costTypeName.trim();
    }

    public Byte getReceivePeriodId() {
        return receivePeriodId;
    }

    public void setReceivePeriodId(Byte receivePeriodId) {
        this.receivePeriodId = receivePeriodId;
    }

    public String getReceivePeriodName() {
        return receivePeriodName;
    }

    public void setReceivePeriodName(String receivePeriodName) {
        this.receivePeriodName = receivePeriodName == null ? null : receivePeriodName.trim();
    }

    public Byte getIncreasePeriodId() {
        return increasePeriodId;
    }

    public void setIncreasePeriodId(Byte increasePeriodId) {
        this.increasePeriodId = increasePeriodId;
    }

    public String getIncreasePeriodName() {
        return increasePeriodName;
    }

    public void setIncreasePeriodName(String increasePeriodName) {
        this.increasePeriodName = increasePeriodName == null ? null : increasePeriodName.trim();
    }

    public Byte getIncreaseTypeId() {
        return increaseTypeId;
    }

    public void setIncreaseTypeId(Byte increaseTypeId) {
        this.increaseTypeId = increaseTypeId;
    }

    public String getIncreaseTypeName() {
        return increaseTypeName;
    }

    public void setIncreaseTypeName(String increaseTypeName) {
        this.increaseTypeName = increaseTypeName == null ? null : increaseTypeName.trim();
    }

    public BigDecimal getIncreaseValue() {
        return increaseValue;
    }

    public void setIncreaseValue(BigDecimal increaseValue) {
        this.increaseValue = increaseValue;
    }

    public Date getFirstReceiveDate() {
        return firstReceiveDate;
    }

    public void setFirstReceiveDate(Date firstReceiveDate) {
        this.firstReceiveDate = firstReceiveDate;
    }

    public Integer getFundDayType() {
        return fundDayType;
    }

    public void setFundDayType(Integer fundDayType) {
        this.fundDayType = fundDayType;
    }

    public Integer getAfterReceiveDate() {
        return afterReceiveDate;
    }

    public void setAfterReceiveDate(Integer afterReceiveDate) {
        this.afterReceiveDate = afterReceiveDate;
    }

    public BigDecimal getReceiveAmount() {
        return receiveAmount;
    }

    public void setReceiveAmount(BigDecimal receiveAmount) {
        this.receiveAmount = receiveAmount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getOwnerCommitteePercent() {
        return ownerCommitteePercent;
    }

    public void setOwnerCommitteePercent(BigDecimal ownerCommitteePercent) {
        this.ownerCommitteePercent = ownerCommitteePercent;
    }

    public Integer getAuto() {
        return auto;
    }

    public void setAuto(Integer auto) {
        this.auto = auto;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}