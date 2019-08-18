package com.common.district.ad.model;

import java.util.Date;

public class ContractCostDicts {
    private Long costId;

    private Long parentId;

    private String costCode;

    private String costName;

    private Integer costTypeId;

    private String costTypeName;

    private Date createTime;

    private Date updateTime;

    private String organId;

    private Integer del;

    public Long getCostId() {
        return costId;
    }

    public void setCostId(Long costId) {
        this.costId = costId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCostCode() {
        return costCode;
    }

    public void setCostCode(String costCode) {
        this.costCode = costCode == null ? null : costCode.trim();
    }

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName == null ? null : costName.trim();
    }

    public Integer getCostTypeId() {
        return costTypeId;
    }

    public void setCostTypeId(Integer costTypeId) {
        this.costTypeId = costTypeId;
    }

    public String getCostTypeName() {
        return costTypeName;
    }

    public void setCostTypeName(String costTypeName) {
        this.costTypeName = costTypeName == null ? null : costTypeName.trim();
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
}