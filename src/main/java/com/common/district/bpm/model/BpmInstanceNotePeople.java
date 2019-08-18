package com.common.district.bpm.model;

public class BpmInstanceNotePeople {
    private Long id;

    private Long instanceNoteId;

    private String userName;

    private String userId;

    private Integer finish;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInstanceNoteId() {
        return instanceNoteId;
    }

    public void setInstanceNoteId(Long instanceNoteId) {
        this.instanceNoteId = instanceNoteId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getFinish() {
        return finish;
    }

    public void setFinish(Integer finish) {
        this.finish = finish;
    }
}