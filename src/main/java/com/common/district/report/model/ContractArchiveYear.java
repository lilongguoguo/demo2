package com.common.district.report.model;

import java.math.BigDecimal;
import java.util.Date;

public class ContractArchiveYear {
    private Long id;

    private String selfCompanyId;

    private String selfCompanyName;

    private String deptId;

    private String deptName;

    private Integer archiveNum;

    private Integer lastArchiveNum;

    private Integer aroundGrowthNum;

    private BigDecimal aroundGrowthRate;

    private Integer yoyGrowthNum;

    private BigDecimal yoyGrowthRate;

    private Integer qualifiedNum;

    private Integer lastQualifiedNum;

    private BigDecimal qualifiedRate;

    private Integer qualifiedAroundGrowthNum;

    private BigDecimal qualifiedAroundGrowthRate;

    private Integer qualifiedYoyGrowthNum;

    private BigDecimal qualifiedYoyGrowthRate;

    private Integer unqualifiedNum;

    private Integer lastUnqualifiedNum;

    private BigDecimal unqualifiedRate;

    private Integer unqualifiedAroundGrowthNum;

    private BigDecimal unqualifiedAroundGrowthRate;

    private Integer unqualifiedYoyGrowthNum;

    private BigDecimal unqualifiedYoyGrowthRate;

    private Date startDate;

    private Date endDate;

    private Integer year;

    private String organId;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSelfCompanyId() {
        return selfCompanyId;
    }

    public void setSelfCompanyId(String selfCompanyId) {
        this.selfCompanyId = selfCompanyId == null ? null : selfCompanyId.trim();
    }

    public String getSelfCompanyName() {
        return selfCompanyName;
    }

    public void setSelfCompanyName(String selfCompanyName) {
        this.selfCompanyName = selfCompanyName == null ? null : selfCompanyName.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public Integer getArchiveNum() {
        return archiveNum;
    }

    public void setArchiveNum(Integer archiveNum) {
        this.archiveNum = archiveNum;
    }

    public Integer getLastArchiveNum() {
        return lastArchiveNum;
    }

    public void setLastArchiveNum(Integer lastArchiveNum) {
        this.lastArchiveNum = lastArchiveNum;
    }

    public Integer getAroundGrowthNum() {
        return aroundGrowthNum;
    }

    public void setAroundGrowthNum(Integer aroundGrowthNum) {
        this.aroundGrowthNum = aroundGrowthNum;
    }

    public BigDecimal getAroundGrowthRate() {
        return aroundGrowthRate;
    }

    public void setAroundGrowthRate(BigDecimal aroundGrowthRate) {
        this.aroundGrowthRate = aroundGrowthRate;
    }

    public Integer getYoyGrowthNum() {
        return yoyGrowthNum;
    }

    public void setYoyGrowthNum(Integer yoyGrowthNum) {
        this.yoyGrowthNum = yoyGrowthNum;
    }

    public BigDecimal getYoyGrowthRate() {
        return yoyGrowthRate;
    }

    public void setYoyGrowthRate(BigDecimal yoyGrowthRate) {
        this.yoyGrowthRate = yoyGrowthRate;
    }

    public Integer getQualifiedNum() {
        return qualifiedNum;
    }

    public void setQualifiedNum(Integer qualifiedNum) {
        this.qualifiedNum = qualifiedNum;
    }

    public Integer getLastQualifiedNum() {
        return lastQualifiedNum;
    }

    public void setLastQualifiedNum(Integer lastQualifiedNum) {
        this.lastQualifiedNum = lastQualifiedNum;
    }

    public BigDecimal getQualifiedRate() {
        return qualifiedRate;
    }

    public void setQualifiedRate(BigDecimal qualifiedRate) {
        this.qualifiedRate = qualifiedRate;
    }

    public Integer getQualifiedAroundGrowthNum() {
        return qualifiedAroundGrowthNum;
    }

    public void setQualifiedAroundGrowthNum(Integer qualifiedAroundGrowthNum) {
        this.qualifiedAroundGrowthNum = qualifiedAroundGrowthNum;
    }

    public BigDecimal getQualifiedAroundGrowthRate() {
        return qualifiedAroundGrowthRate;
    }

    public void setQualifiedAroundGrowthRate(BigDecimal qualifiedAroundGrowthRate) {
        this.qualifiedAroundGrowthRate = qualifiedAroundGrowthRate;
    }

    public Integer getQualifiedYoyGrowthNum() {
        return qualifiedYoyGrowthNum;
    }

    public void setQualifiedYoyGrowthNum(Integer qualifiedYoyGrowthNum) {
        this.qualifiedYoyGrowthNum = qualifiedYoyGrowthNum;
    }

    public BigDecimal getQualifiedYoyGrowthRate() {
        return qualifiedYoyGrowthRate;
    }

    public void setQualifiedYoyGrowthRate(BigDecimal qualifiedYoyGrowthRate) {
        this.qualifiedYoyGrowthRate = qualifiedYoyGrowthRate;
    }

    public Integer getUnqualifiedNum() {
        return unqualifiedNum;
    }

    public void setUnqualifiedNum(Integer unqualifiedNum) {
        this.unqualifiedNum = unqualifiedNum;
    }

    public Integer getLastUnqualifiedNum() {
        return lastUnqualifiedNum;
    }

    public void setLastUnqualifiedNum(Integer lastUnqualifiedNum) {
        this.lastUnqualifiedNum = lastUnqualifiedNum;
    }

    public BigDecimal getUnqualifiedRate() {
        return unqualifiedRate;
    }

    public void setUnqualifiedRate(BigDecimal unqualifiedRate) {
        this.unqualifiedRate = unqualifiedRate;
    }

    public Integer getUnqualifiedAroundGrowthNum() {
        return unqualifiedAroundGrowthNum;
    }

    public void setUnqualifiedAroundGrowthNum(Integer unqualifiedAroundGrowthNum) {
        this.unqualifiedAroundGrowthNum = unqualifiedAroundGrowthNum;
    }

    public BigDecimal getUnqualifiedAroundGrowthRate() {
        return unqualifiedAroundGrowthRate;
    }

    public void setUnqualifiedAroundGrowthRate(BigDecimal unqualifiedAroundGrowthRate) {
        this.unqualifiedAroundGrowthRate = unqualifiedAroundGrowthRate;
    }

    public Integer getUnqualifiedYoyGrowthNum() {
        return unqualifiedYoyGrowthNum;
    }

    public void setUnqualifiedYoyGrowthNum(Integer unqualifiedYoyGrowthNum) {
        this.unqualifiedYoyGrowthNum = unqualifiedYoyGrowthNum;
    }

    public BigDecimal getUnqualifiedYoyGrowthRate() {
        return unqualifiedYoyGrowthRate;
    }

    public void setUnqualifiedYoyGrowthRate(BigDecimal unqualifiedYoyGrowthRate) {
        this.unqualifiedYoyGrowthRate = unqualifiedYoyGrowthRate;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId == null ? null : organId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}