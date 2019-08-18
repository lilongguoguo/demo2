package com.common.district.res.enums;

/**
 * @Author: tanwei
 * @Description:
 * @Date: Create in 10:06 2019/8/13
 */
public enum BusinessTypeEnum {
    RESIDENTIAL(1,"社区住宅"),

    BUSINESS(2,"社区商业"),

    OFFICEBUILDING(3,"写字楼");

    private int code;

    private String val;

    BusinessTypeEnum(int code, String val) {
        this.code = code;
        this.val = val;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public static String getVla(int deptType) {
        for (BusinessTypeEnum businessTypeEnum : BusinessTypeEnum.values()) {
            if (businessTypeEnum.getCode() == deptType) {
                return businessTypeEnum.getVal();
            }
        }
        return null;
    }
}
