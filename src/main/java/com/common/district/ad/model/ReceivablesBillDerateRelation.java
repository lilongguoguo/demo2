package com.common.district.ad.model;

import java.math.BigDecimal;

public class ReceivablesBillDerateRelation {
    private Long id;

    private Long rbdId;

    private Long rbId;

    private BigDecimal rbDerateAmount;

    private BigDecimal rbActualAmount;

    private Integer del;

    private String remark;

    private ReceivablesBillDerate billDerate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRbdId() {
        return rbdId;
    }

    public void setRbdId(Long rbdId) {
        this.rbdId = rbdId;
    }

    public Long getRbId() {
        return rbId;
    }

    public void setRbId(Long rbId) {
        this.rbId = rbId;
    }

    public BigDecimal getRbDerateAmount() {
        return rbDerateAmount;
    }

    public void setRbDerateAmount(BigDecimal rbDerateAmount) {
        this.rbDerateAmount = rbDerateAmount;
    }

    public BigDecimal getRbActualAmount() {
        return rbActualAmount;
    }

    public void setRbActualAmount(BigDecimal rbActualAmount) {
        this.rbActualAmount = rbActualAmount;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public ReceivablesBillDerate getBillDerate() {
        return billDerate;
    }

    public void setBillDerate(ReceivablesBillDerate billDerate) {
        this.billDerate = billDerate;
    }
}