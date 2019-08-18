package com.common.district.ad.model;

public class ReceiptRecordRelation {
    private Long rrrId;

    private Long rrId;

    private Long rbId;

    private Integer del;

    private ReceiptRecord  receiptRecord;

    public Long getRrrId() {
        return rrrId;
    }

    public void setRrrId(Long rrrId) {
        this.rrrId = rrrId;
    }

    public Long getRrId() {
        return rrId;
    }

    public void setRrId(Long rrId) {
        this.rrId = rrId;
    }

    public Long getRbId() {
        return rbId;
    }

    public void setRbId(Long rbId) {
        this.rbId = rbId;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public ReceiptRecord getReceiptRecord() {
        return receiptRecord;
    }

    public void setReceiptRecord(ReceiptRecord receiptRecord) {
        this.receiptRecord = receiptRecord;
    }
}