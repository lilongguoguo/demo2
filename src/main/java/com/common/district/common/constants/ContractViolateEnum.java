package com.common.district.common.constants;

import com.google.common.collect.Maps;

import java.util.Map;

public enum ContractViolateEnum {
    WITHOUT(0,"无"),
    FIXED_PROPORTION(1,"固定比例"),
    ContractViolateEnum(2,"固定金额");

    private Integer code;
    private String name;

    ContractViolateEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Map<Integer, String> getContractViolateMap(){
        Map<Integer, String> map = Maps.newHashMapWithExpectedSize(values().length);
        for(ContractViolateEnum contractViolateEnum : values()){
            map.put(contractViolateEnum.code, contractViolateEnum.name);
        }
        return map;
    }
    public static String getNameByCode(Integer code){
        Map<Integer, String> contractViolateMap = getContractViolateMap();
        return contractViolateMap.get(code);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
