package com.common.district.bpm.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BpmInstanceNoteVo implements Serializable{

    private Integer noteSort;
    private String noteName;
    private String noteCode;
    private List<BpmUser> userList;

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
        this.noteName = noteName;
    }

    public String getNoteCode() {
        return noteCode;
    }

    public void setNoteCode(String noteCode) {
        this.noteCode = noteCode;
    }

    public List<BpmUser> getUserList() {
        return userList;
    }

    public void setUserList(List<BpmUser> userList) {
        this.userList = userList;
    }
}
