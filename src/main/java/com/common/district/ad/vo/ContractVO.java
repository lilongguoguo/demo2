package com.common.district.ad.vo;

import com.common.district.ad.model.ContractAttach;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ContractVO {
    private Long contId;

    private Long ogContId;

    private String contName;

    private String contCode;

    private String companyId;

    private String companyName;

    private String companyCode;

    private BigDecimal contAmount;

    private Integer contTypeId;

    private String resTypeId;

    private String selfCompanyId;

    private String selfCompanyName;

    private String sideCompanyId;

    private String sideCompanyName;

    private String selfContactId;

    private String selfContactName;

    private String sideContactId;

    private String sideContactName;

    private String selfTel;

    private String sideTel;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date contStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date contEndTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date ogContStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date ogContEndTime;

    private Long frameContId;

    private String frameContName;

    private String createUserId;

    private String createUserName;

    private Integer contStatus;

    private Integer currentFlowSort;

    private Date approveFinishTime;

    private Date archiveTime;

    private Date overruleTime;

    private Date createTime;

    private Date updateTime;

    private Date signingTime;

    private Integer violateType;

    private BigDecimal violateValue;

    private Integer hasSup;

    private Integer hasRenewal;

    private Integer signed;

    private Integer contTerminated;

    private String organId;

    private Integer del;

    private String contDeclare;

    // 保证金
    private List<ContractDepositPlanVO> contractDepositPlanVos;
    // 收入计划
    private List<ContractIncomeSetVO> contractReceiptVos;
    // 能源信息
    private List<ContractEnergyPlanVO> adcEnergyBillingTypeVos;
    // 删除的能源信息
    private List<Long> deleteEnergyIds;
    // 投放明细
    private List<ContractDetailVO> contractDetailVoList;
    // 删除的投放明细
    private List<Long> deleteContractDetailIds;
    // 合同附件
    private List<ContractAttach> contractAttachVoList;
    // 删除的合同附件
    private List<Long> deleteContractAttacheIds;
    // 原合同名称
    private String ogContName;
    //合同类型
    private String contTypeName;
    // 合同状态
    private String contStatusName;
    // 合同大类
    private String resTypeName;
    // 是否是合同提报人
    private boolean createContMan;
    // 违约金
    private String violateTypeValue;

    public Long getContId() {
        return contId;
    }

    public void setContId(Long contId) {
        this.contId = contId;
    }

    public Long getOgContId() {
        return ogContId;
    }

    public void setOgContId(Long ogContId) {
        this.ogContId = ogContId;
    }

    public String getContName() {
        return contName;
    }

    public void setContName(String contName) {
        this.contName = contName == null ? null : contName.trim();
    }

    public String getContCode() {
        return contCode;
    }

    public void setContCode(String contCode) {
        this.contCode = contCode == null ? null : contCode.trim();
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

    public BigDecimal getContAmount() {
        return contAmount;
    }

    public void setContAmount(BigDecimal contAmount) {
        this.contAmount = contAmount;
    }

    public Integer getContTypeId() {
        return contTypeId;
    }

    public void setContTypeId(Integer contTypeId) {
        this.contTypeId = contTypeId;
    }

    public String getResTypeId() {
        return resTypeId;
    }

    public void setResTypeId(String resTypeId) {
        this.resTypeId = resTypeId;
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

    public String getSideCompanyId() {
        return sideCompanyId;
    }

    public void setSideCompanyId(String sideCompanyId) {
        this.sideCompanyId = sideCompanyId == null ? null : sideCompanyId.trim();
    }

    public String getSideCompanyName() {
        return sideCompanyName;
    }

    public void setSideCompanyName(String sideCompanyName) {
        this.sideCompanyName = sideCompanyName == null ? null : sideCompanyName.trim();
    }

    public String getSelfContactId() {
        return selfContactId;
    }

    public void setSelfContactId(String selfContactId) {
        this.selfContactId = selfContactId == null ? null : selfContactId.trim();
    }

    public String getSelfContactName() {
        return selfContactName;
    }

    public void setSelfContactName(String selfContactName) {
        this.selfContactName = selfContactName == null ? null : selfContactName.trim();
    }

    public String getSideContactId() {
        return sideContactId;
    }

    public void setSideContactId(String sideContactId) {
        this.sideContactId = sideContactId == null ? null : sideContactId.trim();
    }

    public String getSideContactName() {
        return sideContactName;
    }

    public void setSideContactName(String sideContactName) {
        this.sideContactName = sideContactName == null ? null : sideContactName.trim();
    }

    public String getSelfTel() {
        return selfTel;
    }

    public void setSelfTel(String selfTel) {
        this.selfTel = selfTel == null ? null : selfTel.trim();
    }

    public String getSideTel() {
        return sideTel;
    }

    public void setSideTel(String sideTel) {
        this.sideTel = sideTel == null ? null : sideTel.trim();
    }

    public Date getContStartTime() {
        return contStartTime;
    }

    public void setContStartTime(Date contStartTime) {
        this.contStartTime = contStartTime;
    }

    public Date getContEndTime() {
        return contEndTime;
    }

    public void setContEndTime(Date contEndTime) {
        this.contEndTime = contEndTime;
    }

    public Long getFrameContId() {
        return frameContId;
    }

    public void setFrameContId(Long frameContId) {
        this.frameContId = frameContId;
    }

    public String getFrameContName() {
        return frameContName;
    }

    public void setFrameContName(String frameContName) {
        this.frameContName = frameContName == null ? null : frameContName.trim();
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

    public Integer getContStatus() {
        return contStatus;
    }

    public void setContStatus(Integer contStatus) {
        this.contStatus = contStatus;
    }

    public Integer getCurrentFlowSort() {
        return currentFlowSort;
    }

    public void setCurrentFlowSort(Integer currentFlowSort) {
        this.currentFlowSort = currentFlowSort;
    }

    public Date getApproveFinishTime() {
        return approveFinishTime;
    }

    public void setApproveFinishTime(Date approveFinishTime) {
        this.approveFinishTime = approveFinishTime;
    }

    public Date getArchiveTime() {
        return archiveTime;
    }

    public void setArchiveTime(Date archiveTime) {
        this.archiveTime = archiveTime;
    }

    public Date getOverruleTime() {
        return overruleTime;
    }

    public void setOverruleTime(Date overruleTime) {
        this.overruleTime = overruleTime;
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

    public Date getSigningTime() {
        return signingTime;
    }

    public void setSigningTime(Date signingTime) {
        this.signingTime = signingTime;
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

    public Integer getHasSup() {
        return hasSup;
    }

    public void setHasSup(Integer hasSup) {
        this.hasSup = hasSup;
    }

    public Integer getHasRenewal() {
        return hasRenewal;
    }

    public void setHasRenewal(Integer hasRenewal) {
        this.hasRenewal = hasRenewal;
    }

    public Integer getSigned() {
        return signed;
    }

    public void setSigned(Integer signed) {
        this.signed = signed;
    }

    public Integer getContTerminated() {
        return contTerminated;
    }

    public void setContTerminated(Integer contTerminated) {
        this.contTerminated = contTerminated;
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

    public String getContDeclare() {
        return contDeclare;
    }

    public void setContDeclare(String contDeclare) {
        this.contDeclare = contDeclare == null ? null : contDeclare.trim();
    }

    public Date getOgContStartTime() {
        return ogContStartTime;
    }

    public void setOgContStartTime(Date ogContStartTime) {
        this.ogContStartTime = ogContStartTime;
    }

    public Date getOgContEndTime() {
        return ogContEndTime;
    }

    public void setOgContEndTime(Date ogContEndTime) {
        this.ogContEndTime = ogContEndTime;
    }

    public List<ContractDepositPlanVO> getContractDepositPlanVos() {
        return contractDepositPlanVos;
    }

    public void setContractDepositPlanVos(List<ContractDepositPlanVO> contractDepositPlanVos) {
        this.contractDepositPlanVos = contractDepositPlanVos;
    }

    public List<ContractIncomeSetVO> getContractReceiptVos() {
        return contractReceiptVos;
    }

    public void setContractReceiptVos(List<ContractIncomeSetVO> contractReceiptVos) {
        this.contractReceiptVos = contractReceiptVos;
    }

    public List<ContractEnergyPlanVO> getAdcEnergyBillingTypeVos() {
        return adcEnergyBillingTypeVos;
    }

    public void setAdcEnergyBillingTypeVos(List<ContractEnergyPlanVO> adcEnergyBillingTypeVos) {
        this.adcEnergyBillingTypeVos = adcEnergyBillingTypeVos;
    }

    public List<Long> getDeleteEnergyIds() {
        return deleteEnergyIds;
    }

    public void setDeleteEnergyIds(List<Long> deleteEnergyIds) {
        this.deleteEnergyIds = deleteEnergyIds;
    }

    public List<ContractDetailVO> getContractDetailVoList() {
        return contractDetailVoList;
    }

    public void setContractDetailVoList(List<ContractDetailVO> contractDetailVoList) {
        this.contractDetailVoList = contractDetailVoList;
    }

    public List<Long> getDeleteContractDetailIds() {
        return deleteContractDetailIds;
    }

    public void setDeleteContractDetailIds(List<Long> deleteContractDetailIds) {
        this.deleteContractDetailIds = deleteContractDetailIds;
    }

    public List<ContractAttach> getContractAttachVoList() {
        return contractAttachVoList;
    }

    public void setContractAttachVoList(List<ContractAttach> contractAttachVoList) {
        this.contractAttachVoList = contractAttachVoList;
    }

    public List<Long> getDeleteContractAttacheIds() {
        return deleteContractAttacheIds;
    }

    public void setDeleteContractAttacheIds(List<Long> deleteContractAttacheIds) {
        this.deleteContractAttacheIds = deleteContractAttacheIds;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getOgContName() {
        return ogContName;
    }

    public void setOgContName(String ogContName) {
        this.ogContName = ogContName;
    }

    public String getContTypeName() {
        return contTypeName;
    }

    public void setContTypeName(String contTypeName) {
        this.contTypeName = contTypeName;
    }

    public String getContStatusName() {
        return contStatusName;
    }

    public void setContStatusName(String contStatusName) {
        this.contStatusName = contStatusName;
    }

    public String getResTypeName() {
        return resTypeName;
    }

    public void setResTypeName(String resTypeName) {
        this.resTypeName = resTypeName;
    }

    public boolean isCreateContMan() {
        return createContMan;
    }

    public void setCreateContMan(boolean createContMan) {
        this.createContMan = createContMan;
    }

    public String getViolateTypeValue() {
        return violateTypeValue;
    }

    public void setViolateTypeValue(String violateTypeValue) {
        this.violateTypeValue = violateTypeValue;
    }
}