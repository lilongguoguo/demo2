package com.common.district.res.model;

import java.util.Date;

public class Dicts {
    private Long id;

    private String dictsName;

    private Long dictsCode;

    private Long dictsParentCdoe;

    private String dictsType;

    private String dictsTypeDesp;

    private Date createTime;

    private String createUserId;

    private Date updateTime;

    private String updateuserId;

    private String organId;

    private Integer del;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDictsName() {
        return dictsName;
    }

    public void setDictsName(String dictsName) {
        this.dictsName = dictsName == null ? null : dictsName.trim();
    }

    public Long getDictsCode() {
        return dictsCode;
    }

    public void setDictsCode(Long dictsCode) {
        this.dictsCode = dictsCode;
    }

    public Long getDictsParentCdoe() {
        return dictsParentCdoe;
    }

    public void setDictsParentCdoe(Long dictsParentCdoe) {
        this.dictsParentCdoe = dictsParentCdoe;
    }

    public String getDictsType() {
        return dictsType;
    }

    public void setDictsType(String dictsType) {
        this.dictsType = dictsType == null ? null : dictsType.trim();
    }

    public String getDictsTypeDesp() {
        return dictsTypeDesp;
    }

    public void setDictsTypeDesp(String dictsTypeDesp) {
        this.dictsTypeDesp = dictsTypeDesp == null ? null : dictsTypeDesp.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateuserId() {
        return updateuserId;
    }

    public void setUpdateuserId(String updateuserId) {
        this.updateuserId = updateuserId == null ? null : updateuserId.trim();
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