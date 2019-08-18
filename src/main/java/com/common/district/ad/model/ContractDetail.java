package com.common.district.ad.model;

import java.math.BigDecimal;
import java.util.Date;

public class ContractDetail {
    private Long contDetailId;

    private Long contId;

    private String companyId;

    private String companyName;

    private String areaId;

    private String areaName;

    private String projectId;

    private String projectName;

    private String resDetailTypeId;

    private String resDetailTypeName;

    private String thirdResourceTypeId;

    private String thirdResourceTypeName;

    private Date detailStartTime;

    private Date detailEndTime;

    private Integer pointCount;

    private BigDecimal unitPrice;

    private Integer unitId;

    private String unitName;

    private BigDecimal totalAmount;

    private Date createDate;

    private Date updateTime;

    private Integer status;

    private Integer del;

    public Long getContDetailId() {
        return contDetailId;
    }

    public void setContDetailId(Long contDetailId) {
        this.contDetailId = contDetailId;
    }

    public Long getContId() {
        return contId;
    }

    public void setContId(Long contId) {
        this.contId = contId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
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

    public String getResDetailTypeId() {
        return resDetailTypeId;
    }

    public void setResDetailTypeId(String resDetailTypeId) {
        this.resDetailTypeId = resDetailTypeId;
    }

    public String getResDetailTypeName() {
        return resDetailTypeName;
    }

    public void setResDetailTypeName(String resDetailTypeName) {
        this.resDetailTypeName = resDetailTypeName == null ? null : resDetailTypeName.trim();
    }

    public String getThirdResourceTypeId() {
        return thirdResourceTypeId;
    }

    public void setThirdResourceTypeId(String thirdResourceTypeId) {
        this.thirdResourceTypeId = thirdResourceTypeId;
    }

    public String getThirdResourceTypeName() {
        return thirdResourceTypeName;
    }

    public void setThirdResourceTypeName(String thirdResourceTypeName) {
        this.thirdResourceTypeName = thirdResourceTypeName == null ? null : thirdResourceTypeName.trim();
    }

    public Date getDetailStartTime() {
        return detailStartTime;
    }

    public void setDetailStartTime(Date detailStartTime) {
        this.detailStartTime = detailStartTime;
    }

    public Date getDetailEndTime() {
        return detailEndTime;
    }

    public void setDetailEndTime(Date detailEndTime) {
        this.detailEndTime = detailEndTime;
    }

    public Integer getPointCount() {
        return pointCount;
    }

    public void setPointCount(Integer pointCount) {
        this.pointCount = pointCount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}