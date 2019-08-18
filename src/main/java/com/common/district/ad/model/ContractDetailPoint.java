package com.common.district.ad.model;

import java.util.Date;

public class ContractDetailPoint {
    private Long contDetailPointId;

    private Long contId;

    private Long contDetailId;

    private Long positionId;

    private Date createDate;

    private Date updateTime;

    public Long getContDetailPointId() {
        return contDetailPointId;
    }

    public void setContDetailPointId(Long contDetailPointId) {
        this.contDetailPointId = contDetailPointId;
    }

    public Long getContId() {
        return contId;
    }

    public void setContId(Long contId) {
        this.contId = contId;
    }

    public Long getContDetailId() {
        return contDetailId;
    }

    public void setContDetailId(Long contDetailId) {
        this.contDetailId = contDetailId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}