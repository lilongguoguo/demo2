package com.common.district.report.model;

import java.math.BigDecimal;
import java.util.Date;

public class ReportProject {
    private Long id;

    private String companyId;

    private String companyName;

    private String areaId;

    private String areaName;

    private String projectId;

    private String projectName;

    private Integer positionResourceNum;

    private Integer mediumResourceNum;

    private Integer advertResourceNum;

    private Integer siteResourceNum;

    private Integer propagandaResourceNum;

    private Integer signedCustomerNum;

    private Integer signedCustomersNum;

    private Integer signedContractNum;

    private BigDecimal signedContractAmount;

    private Integer executionContractNum;

    private BigDecimal executionContractAmount;

    private Integer frameworkContractNum;

    private BigDecimal frameworkContractAmount;

    private Date createDate;

    private String organId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getPositionResourceNum() {
        return positionResourceNum;
    }

    public void setPositionResourceNum(Integer positionResourceNum) {
        this.positionResourceNum = positionResourceNum;
    }

    public Integer getMediumResourceNum() {
        return mediumResourceNum;
    }

    public void setMediumResourceNum(Integer mediumResourceNum) {
        this.mediumResourceNum = mediumResourceNum;
    }

    public Integer getAdvertResourceNum() {
        return advertResourceNum;
    }

    public void setAdvertResourceNum(Integer advertResourceNum) {
        this.advertResourceNum = advertResourceNum;
    }

    public Integer getSiteResourceNum() {
        return siteResourceNum;
    }

    public void setSiteResourceNum(Integer siteResourceNum) {
        this.siteResourceNum = siteResourceNum;
    }

    public Integer getPropagandaResourceNum() {
        return propagandaResourceNum;
    }

    public void setPropagandaResourceNum(Integer propagandaResourceNum) {
        this.propagandaResourceNum = propagandaResourceNum;
    }

    public Integer getSignedCustomerNum() {
        return signedCustomerNum;
    }

    public void setSignedCustomerNum(Integer signedCustomerNum) {
        this.signedCustomerNum = signedCustomerNum;
    }

    public Integer getSignedCustomersNum() {
        return signedCustomersNum;
    }

    public void setSignedCustomersNum(Integer signedCustomersNum) {
        this.signedCustomersNum = signedCustomersNum;
    }

    public Integer getSignedContractNum() {
        return signedContractNum;
    }

    public void setSignedContractNum(Integer signedContractNum) {
        this.signedContractNum = signedContractNum;
    }

    public BigDecimal getSignedContractAmount() {
        return signedContractAmount;
    }

    public void setSignedContractAmount(BigDecimal signedContractAmount) {
        this.signedContractAmount = signedContractAmount;
    }

    public Integer getExecutionContractNum() {
        return executionContractNum;
    }

    public void setExecutionContractNum(Integer executionContractNum) {
        this.executionContractNum = executionContractNum;
    }

    public BigDecimal getExecutionContractAmount() {
        return executionContractAmount;
    }

    public void setExecutionContractAmount(BigDecimal executionContractAmount) {
        this.executionContractAmount = executionContractAmount;
    }

    public Integer getFrameworkContractNum() {
        return frameworkContractNum;
    }

    public void setFrameworkContractNum(Integer frameworkContractNum) {
        this.frameworkContractNum = frameworkContractNum;
    }

    public BigDecimal getFrameworkContractAmount() {
        return frameworkContractAmount;
    }

    public void setFrameworkContractAmount(BigDecimal frameworkContractAmount) {
        this.frameworkContractAmount = frameworkContractAmount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId == null ? null : organId.trim();
    }
}