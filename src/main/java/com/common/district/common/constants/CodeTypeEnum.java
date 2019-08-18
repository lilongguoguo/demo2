package com.common.district.common.constants;

/**
 * 编号枚举
 *
 * @author wanglu
 */
public enum CodeTypeEnum {
    WY_CONTRACT("HT", "物业合同"),
    POSITION("DWBH", "点位编号");

    private String code;
    private String name;

    CodeTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
