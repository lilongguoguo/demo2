package com.common.district.ad.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReceivablesBill {
    private Long rbId;

    private String rbNo;

    private Long contId;

    private String contNo;

    private String contName;

    private String companyId;

    private String companyName;

    private String proId;

    private String proName;

    private String costType;

    private String costTypeName;

    private String cusId;

    private String cusName;

    private Integer rbStatus;

    private String costMonth;

    private Date rbDate;

    private BigDecimal rbAmount;

    private BigDecimal rbDerateAmount;

    private BigDecimal afterRbAmount;

    private BigDecimal violateAmount;

    private BigDecimal violateDerateAmount;

    private BigDecimal afterViolateAmount;

    private Integer rbDerateRegister;

    private String operatorId;

    private String operatorName;

    private String createUserId;

    private String createUserName;

    private Date createTime;

    private Integer isFreeze;

    private Integer isBillDerate;

    private Integer isViolateDerate;

    private String freezeReason;

    private Date freezeTime;

    private String freezeUserId;

    private Integer dataSourceType;

    private Long dataSourceId;

    private String organId;

    private Integer del;

    private List<ReceivablesBillDerateRelation> receivablesBillDerateList;

    private List<ReceiptRecordRelation> receiptRecordsList;

    private List<RefundAmount> refundAmountList;

    private List<ViolateDerateRelation> violateDerateRelationssList;

    public Long getRbId() {
        return rbId;
    }

    public void setRbId(Long rbId) {
        this.rbId = rbId;
    }

    public String getRbNo() {
        return rbNo;
    }

    public void setRbNo(String rbNo) {
        this.rbNo = rbNo == null ? null : rbNo.trim();
    }

    public Long getContId() {
        return contId;
    }

    public void setContId(Long contId) {
        this.contId = contId;
    }

    public String getContNo() {
        return contNo;
    }

    public void setContNo(String contNo) {
        this.contNo = contNo == null ? null : contNo.trim();
    }

    public String getContName() {
        return contName;
    }

    public void setContName(String contName) {
        this.contName = contName == null ? null : contName.trim();
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

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId == null ? null : proId.trim();
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName == null ? null : proName.trim();
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType == null ? null : costType.trim();
    }

    public String getCostTypeName() {
        return costTypeName;
    }

    public void setCostTypeName(String costTypeName) {
        this.costTypeName = costTypeName == null ? null : costTypeName.trim();
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId == null ? null : cusId.trim();
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName == null ? null : cusName.trim();
    }

    public Integer getRbStatus() {
        return rbStatus;
    }

    public void setRbStatus(Integer rbStatus) {
        this.rbStatus = rbStatus;
    }

    public String getCostMonth() {
        return costMonth;
    }

    public void setCostMonth(String costMonth) {
        this.costMonth = costMonth == null ? null : costMonth.trim();
    }

    public Date getRbDate() {
        return rbDate;
    }

    public void setRbDate(Date rbDate) {
        this.rbDate = rbDate;
    }

    public BigDecimal getRbAmount() {
        return rbAmount;
    }

    public void setRbAmount(BigDecimal rbAmount) {
        this.rbAmount = rbAmount;
    }

    public BigDecimal getRbDerateAmount() {
        return rbDerateAmount;
    }

    public void setRbDerateAmount(BigDecimal rbDerateAmount) {
        this.rbDerateAmount = rbDerateAmount;
    }

    public BigDecimal getAfterRbAmount() {
        return afterRbAmount;
    }

    public void setAfterRbAmount(BigDecimal afterRbAmount) {
        this.afterRbAmount = afterRbAmount;
    }

    public BigDecimal getViolateAmount() {
        return violateAmount;
    }

    public void setViolateAmount(BigDecimal violateAmount) {
        this.violateAmount = violateAmount;
    }

    public BigDecimal getViolateDerateAmount() {
        return violateDerateAmount;
    }

    public void setViolateDerateAmount(BigDecimal violateDerateAmount) {
        this.violateDerateAmount = violateDerateAmount;
    }

    public BigDecimal getAfterViolateAmount() {
        return afterViolateAmount;
    }

    public void setAfterViolateAmount(BigDecimal afterViolateAmount) {
        this.afterViolateAmount = afterViolateAmount;
    }

    public Integer getRbDerateRegister() {
        return rbDerateRegister;
    }

    public void setRbDerateRegister(Integer rbDerateRegister) {
        this.rbDerateRegister = rbDerateRegister;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
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

    public Integer getIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(Integer isFreeze) {
        this.isFreeze = isFreeze;
    }

    public Integer getIsBillDerate() {
        return isBillDerate;
    }

    public void setIsBillDerate(Integer isBillDerate) {
        this.isBillDerate = isBillDerate;
    }

    public Integer getIsViolateDerate() {
        return isViolateDerate;
    }

    public void setIsViolateDerate(Integer isViolateDerate) {
        this.isViolateDerate = isViolateDerate;
    }

    public String getFreezeReason() {
        return freezeReason;
    }

    public void setFreezeReason(String freezeReason) {
        this.freezeReason = freezeReason == null ? null : freezeReason.trim();
    }

    public Date getFreezeTime() {
        return freezeTime;
    }

    public void setFreezeTime(Date freezeTime) {
        this.freezeTime = freezeTime;
    }

    public String getFreezeUserId() {
        return freezeUserId;
    }

    public void setFreezeUserId(String freezeUserId) {
        this.freezeUserId = freezeUserId == null ? null : freezeUserId.trim();
    }

    public Integer getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(Integer dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    public Long getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(Long dataSourceId) {
        this.dataSourceId = dataSourceId;
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

    public List<ReceivablesBillDerateRelation> getReceivablesBillDerateList() {
        return receivablesBillDerateList;
    }

    public void setReceivablesBillDerateList(List<ReceivablesBillDerateRelation> receivablesBillDerateList) {
        this.receivablesBillDerateList = receivablesBillDerateList;
    }

    public List<ReceiptRecordRelation> getReceiptRecordsList() {
        return receiptRecordsList;
    }

    public void setReceiptRecordsList(List<ReceiptRecordRelation> receiptRecordsList) {
        this.receiptRecordsList = receiptRecordsList;
    }

    public List<RefundAmount> getRefundAmountList() {
        return refundAmountList;
    }

    public void setRefundAmountList(List<RefundAmount> refundAmountList) {
        this.refundAmountList = refundAmountList;
    }

    public List<ViolateDerateRelation> getViolateDerateRelationssList() {
        return violateDerateRelationssList;
    }

    public void setViolateDerateRelationssList(List<ViolateDerateRelation> violateDerateRelationssList) {
        this.violateDerateRelationssList = violateDerateRelationssList;
    }

    public class ReceivablesBillBuilder{
        private List<ReceivablesBillDerateRelation> receivablesBillDerateList;

        private List<ReceiptRecordRelation> receiptRecordsList;

        private List<RefundAmount> refundAmountList;

        private List<ViolateDerateRelation> violateDeratesList;
        public  ReceivablesBillBuilder withReceivablesBillDerateList(List<ReceivablesBillDerateRelation> lst){
            this.receivablesBillDerateList=lst;
            return this;
        }
        public  ReceivablesBillBuilder withReceiptRecordList(List<ReceiptRecordRelation> lst){
            this.receiptRecordsList=lst;
            return this;
        }
        public  ReceivablesBillBuilder withRefundAmountList(List<RefundAmount> lst){
            this.refundAmountList=lst;
            return this;
        }
        public  ReceivablesBillBuilder withViolateDerate(List<ViolateDerateRelation> lst){
            this.violateDeratesList=lst;
            return this;
        }
        public  ReceivablesBill build(){
            ReceivablesBill.this.setReceiptRecordsList(null==receiptRecordsList?new ArrayList<ReceiptRecordRelation>(){{this.add(new ReceiptRecordRelation(){{this.setReceiptRecord(new ReceiptRecord());}});}}:receiptRecordsList);
            ReceivablesBill.this.setReceivablesBillDerateList(null==receivablesBillDerateList?new ArrayList<ReceivablesBillDerateRelation>(){{this.add(new ReceivablesBillDerateRelation(){{this.setBillDerate(new ReceivablesBillDerate());}});}}:receivablesBillDerateList);
            ReceivablesBill.this.setRefundAmountList(null==refundAmountList?new ArrayList<RefundAmount>(){{this.add(new RefundAmount());}}:refundAmountList);
            ReceivablesBill.this.setViolateDerateRelationssList(null==violateDeratesList?new ArrayList<ViolateDerateRelation>(){{this.add(new ViolateDerateRelation(){{this.setViolateDerate(new ViolateDerate());}});}}:violateDeratesList);
            return ReceivablesBill.this;
        }
    }
}