package com.common.district.bpm.model;

import java.util.Date;

public class BpmApproveRecords {
    private Long id;

    private Long instanceId;

    private String approveUserName;

    private String approveUserId;

    private String noteId;

    private String noteName;

    private String approveContent;

    private String approveAction;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Long instanceId) {
        this.instanceId = instanceId;
    }

    public String getApproveUserName() {
        return approveUserName;
    }

    public void setApproveUserName(String approveUserName) {
        this.approveUserName = approveUserName == null ? null : approveUserName.trim();
    }

    public String getApproveUserId() {
        return approveUserId;
    }

    public void setApproveUserId(String approveUserId) {
        this.approveUserId = approveUserId;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId == null ? null : noteId.trim();
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName == null ? null : noteName.trim();
    }

    public String getApproveContent() {
        return approveContent;
    }

    public void setApproveContent(String approveContent) {
        this.approveContent = approveContent == null ? null : approveContent.trim();
    }

    public String getApproveAction() {
        return approveAction;
    }

    public void setApproveAction(String approveAction) {
        this.approveAction = approveAction == null ? null : approveAction.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}