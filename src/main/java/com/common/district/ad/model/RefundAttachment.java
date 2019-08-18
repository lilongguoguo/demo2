package com.common.district.ad.model;

import java.util.Date;

public class RefundAttachment {
    private Long ratId;

    private Long raId;

    private Integer attachTypeId;

    private String attachName;

    private String attachLink;

    private String remark;

    private String createUserId;

    private String createUserName;

    private Date createTime;

    private Date updateTime;

    private Integer del;

    public Long getRatId() {
        return ratId;
    }

    public void setRatId(Long ratId) {
        this.ratId = ratId;
    }

    public Long getRaId() {
        return raId;
    }

    public void setRaId(Long raId) {
        this.raId = raId;
    }

    public Integer getAttachTypeId() {
        return attachTypeId;
    }

    public void setAttachTypeId(Integer attachTypeId) {
        this.attachTypeId = attachTypeId;
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName == null ? null : attachName.trim();
    }

    public String getAttachLink() {
        return attachLink;
    }

    public void setAttachLink(String attachLink) {
        this.attachLink = attachLink == null ? null : attachLink.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
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

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}