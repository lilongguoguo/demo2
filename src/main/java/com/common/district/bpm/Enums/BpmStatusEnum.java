package com.common.district.bpm.Enums;

/**
 * bpm 审批状态枚举类
 */
public enum BpmStatusEnum {

    DRAFT("草稿", 100),
    APPROVEING("审批中", 200),
    CONSULT("协商", 300),
    APPROVED("已审批", 400),
    REJECT("已驳回", 500),
    DISCARD("废弃", 600);
    private String key;
    private Integer value;

    BpmStatusEnum(String key, Integer value) {
        this.key = key;
        this.value = value;
    }
    public static String getKeyByValue(int value) {
        for (BpmStatusEnum statusEnum : BpmStatusEnum.values()) {
            if (statusEnum.getValue() == value) {
                return statusEnum.getKey();
            }
        }
        return null;
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
