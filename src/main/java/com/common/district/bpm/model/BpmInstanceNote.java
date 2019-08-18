package com.common.district.bpm.model;

public class BpmInstanceNote {
    private Long id;

    private Long instanceId;

    private Integer noteSort;

    private String noteName;

    private String noteCode;

    private Integer active;

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

    public Integer getNoteSort() {
        return noteSort;
    }

    public void setNoteSort(Integer noteSort) {
        this.noteSort = noteSort;
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

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}