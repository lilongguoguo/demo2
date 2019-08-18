package com.common.district.ad.model;

import java.math.BigDecimal;

public class ViolateDerateRelation {
    private Long id;

    private Long rvbId;

    private Long rbId;

    private BigDecimal violateDerateAmount;

    private BigDecimal violateActualAmount;

    private Integer del;

    private ViolateDerate violateDerate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRvbId() {
        return rvbId;
    }

    public void setRvbId(Long rvbId) {
        this.rvbId = rvbId;
    }

    public Long getRbId() {
        return rbId;
    }

    public void setRbId(Long rbId) {
        this.rbId = rbId;
    }

    public BigDecimal getViolateDerateAmount() {
        return violateDerateAmount;
    }

    public void setViolateDerateAmount(BigDecimal violateDerateAmount) {
        this.violateDerateAmount = violateDerateAmount;
    }

    public BigDecimal getViolateActualAmount() {
        return violateActualAmount;
    }

    public void setViolateActualAmount(BigDecimal violateActualAmount) {
        this.violateActualAmount = violateActualAmount;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public ViolateDerate getViolateDerate() {
        return violateDerate;
    }

    public void setViolateDerate(ViolateDerate violateDerate) {
        this.violateDerate = violateDerate;
    }
}