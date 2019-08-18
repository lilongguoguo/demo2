package com.common.district.ad.model;

import java.math.BigDecimal;
import java.util.Date;

public class ContractReceivablePlan {
    private Long receivablePlanId;

    private Long contId;

    private Long ipId;

    private String regionId;

    private String regionName;

    private Integer costItemId;

    private String costItemName;

    private BigDecimal receivableAmount;

    private Date receiveDate;

    private Date startDate;

    private Date endDate;

    private BigDecimal ownerCommitteePercent;

    private Date createTime;

    private Date updateTime;

    private Integer del;

    public Long getReceivablePlanId() {
        return receivablePlanId;
    }

    public void setReceivablePlanId(Long receivablePlanId) {
        this.receivablePlanId = receivablePlanId;
    }

    public Long getContId() {
        return contId;
    }

    public void setContId(Long contId) {
        this.contId = contId;
    }

    public Long getIpId() {
        return ipId;
    }

    public void setIpId(Long ipId) {
        this.ipId = ipId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public Integer getCostItemId() {
        return costItemId;
    }

    public void setCostItemId(Integer costItemId) {
        this.costItemId = costItemId;
    }

    public String getCostItemName() {
        return costItemName;
    }

    public void setCostItemName(String costItemName) {
        this.costItemName = costItemName == null ? null : costItemName.trim();
    }

    public BigDecimal getReceivableAmount() {
        return receivableAmount;
    }

    public void setReceivableAmount(BigDecimal receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
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

    public BigDecimal getOwnerCommitteePercent() {
        return ownerCommitteePercent;
    }

    public void setOwnerCommitteePercent(BigDecimal ownerCommitteePercent) {
        this.ownerCommitteePercent = ownerCommitteePercent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}