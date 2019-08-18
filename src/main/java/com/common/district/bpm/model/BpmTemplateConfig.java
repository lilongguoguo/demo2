package com.common.district.bpm.model;

import java.util.Date;
import java.util.List;

public class BpmTemplateConfig {
    private Long id;

    private Long templateId;

    private String noteName;

    private String noteCode;

    private Integer noteSort;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName == null ? null : noteName.trim();
    }

    public String getNoteCode() {
        return noteCode;
    }

    public void setNoteCode(String noteCode) {
        this.noteCode = noteCode == null ? null : noteCode.trim();
    }

    public Integer getNoteSort() {
        return noteSort;
    }

    public void setNoteSort(Integer noteSort) {
        this.noteSort = noteSort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}