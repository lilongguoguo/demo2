package com.common.district.ad.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 应收金额校验接收类
 */
public class ReceiptAmountRequestVo implements Serializable {

    private Long rbId;

    private Long rbdId;

    /**
     * 应收减免-应收金额减免校验时用
     */
    private Double receivableDerateAmount;

    /**
     * 违约金减免-违约金减免校验时用
     */
    private Double violateDerateAmount;

    private String remark;

    public Long getRbdId() {
        return rbdId;
    }

    public void setRbdId(Long rbdId) {
        this.rbdId = rbdId;
    }

    public Double getReceivableDerateAmount() {
        return receivableDerateAmount;
    }

    public void setReceivableDerateAmount(Double receivableDerateAmount) {
        this.receivableDerateAmount = receivableDerateAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getRbId() {
        return rbId;
    }

    public void setRbId(Long rbId) {
        this.rbId = rbId;
    }

    public Double getViolateDerateAmount() {
        return violateDerateAmount;
    }

    public void setViolateDerateAmount(Double violateDerateAmount) {
        this.violateDerateAmount = violateDerateAmount;
    }
}
