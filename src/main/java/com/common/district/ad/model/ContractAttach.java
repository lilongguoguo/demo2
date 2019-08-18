package com.common.district.ad.model;

import java.util.Date;

public class ContractAttach {
    private Long contAttachId;

    private Long contId;

    private Byte attachTypeId;

    private String contAttachName;

    private String contAttachLink;

    private String contAttachDeclare;

    private Date createDate;

    private Date updateTime;

    private Integer del;

    public Long getContAttachId() {
        return contAttachId;
    }

    public void setContAttachId(Long contAttachId) {
        this.contAttachId = contAttachId;
    }

    public Long getContId() {
        return contId;
    }

    public void setContId(Long contId) {
        this.contId = contId;
    }

    public Byte getAttachTypeId() {
        return attachTypeId;
    }

    public void setAttachTypeId(Byte attachTypeId) {
        this.attachTypeId = attachTypeId;
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

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}