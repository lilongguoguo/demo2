package com.common.district.bpm.vo;

import java.io.Serializable;
import java.util.Date;

public class RespApproveRecordsVo implements Serializable{

    private String noteName;

    private String approveContent;

    private String approveAction;

    private String approveUserName;

    private String createTime;

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getApproveContent() {
        return approveContent;
    }

    public void setApproveContent(String approveContent) {
        this.approveContent = approveContent;
    }

    public String getApproveAction() {
        return approveAction;
    }

    public void setApproveAction(String approveAction) {
        this.approveAction = approveAction;
    }

    public String getApproveUserName() {
        return approveUserName;
    }

    public void setApproveUserName(String approveUserName) {
        this.approveUserName = approveUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
