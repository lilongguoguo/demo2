package com.common.district.res.model;

import java.util.Date;

public class Region {
    private String dictRegionId;

    private String dictRegionCode;

    private Integer dictRegionType;

    private String dictRegionName;

    private String dictRegionFullName;

    private String parentId;

    private Integer treeLevel;

    private Integer status;

    private Integer sort;

    private String createUserId;

    private Date createTime;

    private String updateUserId;

    private Date updateTime;

    private Integer del;

    public String getDictRegionId() {
        return dictRegionId;
    }

    public void setDictRegionId(String dictRegionId) {
        this.dictRegionId = dictRegionId == null ? null : dictRegionId.trim();
    }

    public String getDictRegionCode() {
        return dictRegionCode;
    }

    public void setDictRegionCode(String dictRegionCode) {
        this.dictRegionCode = dictRegionCode == null ? null : dictRegionCode.trim();
    }

    public Integer getDictRegionType() {
        return dictRegionType;
    }

    public void setDictRegionType(Integer dictRegionType) {
        this.dictRegionType = dictRegionType;
    }

    public String getDictRegionName() {
        return dictRegionName;
    }

    public void setDictRegionName(String dictRegionName) {
        this.dictRegionName = dictRegionName == null ? null : dictRegionName.trim();
    }

    public String getDictRegionFullName() {
        return dictRegionFullName;
    }

    public void setDictRegionFullName(String dictRegionFullName) {
        this.dictRegionFullName = dictRegionFullName == null ? null : dictRegionFullName.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public Integer getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}