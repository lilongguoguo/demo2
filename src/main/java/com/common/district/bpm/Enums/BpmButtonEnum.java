package com.common.district.bpm.Enums;

import com.google.common.base.Optional;

public enum BpmButtonEnum {

    EDIT("编辑",1),
    VIEW("查看",2),
    SUBMIT_APPROVAL("提交审批",3),
    PROOFS("校稿",4),
    NOPROOFS("不校稿",5),
    CONSULT("协商",6),
    CONFIRM_REPLY("确认回复",7),
    ADOPT("通过",8),
    REJECT("驳回",9),
    RETRACT("撤回",10),
    DISCARD("废弃",11);

    private String key;
    private Integer value;

    public static String getKeyByValue(int value) {
        for (BpmButtonEnum buttonEnum : BpmButtonEnum.values()) {
            if (buttonEnum.getValue() == value) {
                return buttonEnum.getKey();
            }
        }
        return null;
    }

    BpmButtonEnum( String key ,Integer value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
