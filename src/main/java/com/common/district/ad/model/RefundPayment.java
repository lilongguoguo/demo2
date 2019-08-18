package com.common.district.ad.model;

import java.util.Date;

public class RefundPayment {
    private Long riId;

    private Long raId;

    private String incomeBankName;

    private String incomeCusBankName;

    private String incomeCusName;

    private String incomeCusAccount;

    private Integer nomOrGov;

    private Integer payType;

    private String createUserId;

    private String createUserName;

    private Date createTime;

    private Integer del;

    public Long getRiId() {
        return riId;
    }

    public void setRiId(Long riId) {
        this.riId = riId;
    }

    public Long getRaId() {
        return raId;
    }

    public void setRaId(Long raId) {
        this.raId = raId;
    }

    public String getIncomeBankName() {
        return incomeBankName;
    }

    public void setIncomeBankName(String incomeBankName) {
        this.incomeBankName = incomeBankName == null ? null : incomeBankName.trim();
    }

    public String getIncomeCusBankName() {
        return incomeCusBankName;
    }

    public void setIncomeCusBankName(String incomeCusBankName) {
        this.incomeCusBankName = incomeCusBankName == null ? null : incomeCusBankName.trim();
    }

    public String getIncomeCusName() {
        return incomeCusName;
    }

    public void setIncomeCusName(String incomeCusName) {
        this.incomeCusName = incomeCusName == null ? null : incomeCusName.trim();
    }

    public String getIncomeCusAccount() {
        return incomeCusAccount;
    }

    public void setIncomeCusAccount(String incomeCusAccount) {
        this.incomeCusAccount = incomeCusAccount == null ? null : incomeCusAccount.trim();
    }

    public Integer getNomOrGov() {
        return nomOrGov;
    }

    public void setNomOrGov(Integer nomOrGov) {
        this.nomOrGov = nomOrGov;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
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
}