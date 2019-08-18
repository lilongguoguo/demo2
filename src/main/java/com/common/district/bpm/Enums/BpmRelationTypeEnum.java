package com.common.district.bpm.Enums;

/**
 * bpm 业务类型枚举类
 */
public enum BpmRelationTypeEnum {

    HT("合同", 1),
    YSJM("应收减免", 2),
    WYJJM("违约金减免", 3),
    SK("收款", 4),
    TK("退款", 5);

    private String key;
    private Integer value;

    BpmRelationTypeEnum(String key, Integer value) {
        this.key = key;
        this.value = value;
    }
    public static String getKeyByValue(int value) {
        for (BpmRelationTypeEnum statusEnum : BpmRelationTypeEnum.values()) {
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
