package com.common.district.ad.model;

import java.util.Date;

public class ContractSupAttach {
    private Long contAttachId;

    private Long supId;

    private String contAttachName;

    private String contAttachLink;

    private String contAttachDeclare;

    private Date createDate;

    private Date updateTime;

    private Integer isDel;

    public Long getContAttachId() {
        return contAttachId;
    }

    public void setContAttachId(Long contAttachId) {
        this.contAttachId = contAttachId;
    }

    public Long getSupId() {
        return supId;
    }

    public void setSupId(Long supId) {
        this.supId = supId;
    }

    public String getContAttachName() {
        return contAttachName;
    }

    public void setContAttachName(String contAttachName) {
        this.contAttachName = contAttachName == null ? null : contAttachName.trim();
    }

    public String getContAttachLink() {
        return contAttachLink;
    }

    public void setContAttachLink(String contAttachLink) {
        this.contAttachLink = contAttachLink == null ? null : contAttachLink.trim();
    }

    public String getContAttachDeclare() {
        return contAttachDeclare;
    }

    public void setContAttachDeclare(String contAttachDeclare) {
        this.contAttachDeclare = contAttachDeclare == null ? null : contAttachDeclare.trim();
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

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}