package com.common.district.ad.vo;

import com.common.district.common.utils.StringUtils;

public class ReceivablesBillDerateReturnVo {

    private Long rbId;

    private String costType;

    private String createUserName;

    private String createTime;

    private String rbNo;

    private String contNo;

    private Integer status;

    private Integer approveStatus;

    public Long getRbId() {
        return rbId;
    }

    public void setRbId(Long rbId) {
        this.rbId = rbId;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRbNo() {
        return rbNo;
    }

    public void setRbNo(String rbNo) {
        this.rbNo = rbNo;
    }

    public String getContNo() {
        return contNo;
    }

    public void setContNo(String contNo) {
        this.contNo = contNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }
}
