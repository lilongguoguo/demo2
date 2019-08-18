package com.common.district.ad.model;

import java.math.BigDecimal;

public class RefundRelation {
    private Long arrId;

    private Long rrId;

    private Long raId;

    private BigDecimal raAmount;

    private BigDecimal raReceivablesAmount;

    private BigDecimal raViolateAmount;

    private Integer del;

    public Long getArrId() {
        return arrId;
    }

    public void setArrId(Long arrId) {
        this.arrId = arrId;
    }

    public Long getRrId() {
        return rrId;
    }

    public void setRrId(Long rrId) {
        this.rrId = rrId;
    }

    public Long getRaId() {
        return raId;
    }

    public void setRaId(Long raId) {
        this.raId = raId;
    }

    public BigDecimal getRaAmount() {
        return raAmount;
    }

    public void setRaAmount(BigDecimal raAmount) {
        this.raAmount = raAmount;
    }

    public BigDecimal getRaReceivablesAmount() {
        return raReceivablesAmount;
    }

    public void setRaReceivablesAmount(BigDecimal raReceivablesAmount) {
        this.raReceivablesAmount = raReceivablesAmount;
    }

    public BigDecimal getRaViolateAmount() {
        return raViolateAmount;
    }

    public void setRaViolateAmount(BigDecimal raViolateAmount) {
        this.raViolateAmount = raViolateAmount;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}