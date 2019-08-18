package com.common.district.common.constants;

import com.google.common.base.Optional;

/**
 * 合同状态
 * @author houys
 */
public enum ContractStatusEnum {

    DRAFT(1000, "草稿"),
    INITIATE_APPROVAL(1100, "发起审批"),
    APPROVING(2000, "审批中"),
    CONSULT(2100, "协商"),
    APPROVE(3000, "已审批"),
    ARCHIVE(4000, "已归档"),
    OVERRULED(5000, "已驳回"),
    TERMINATE(6000, "已终止");

    private Integer code;
    private String name;

    /**
     * get contract status name by contract status
     * @author: houys
     * @date: 2017/12/15 18:23
     * @modified By:
     * @param code
     * @return: com.google.common.base.Optional<java.lang.String>
     */
    public static Optional<String> getContractStatusNameByCode(Integer code){
        Optional<String> nameOptional = Optional.absent();
        for(ContractStatusEnum contractStatusEnum : values()){
            if(contractStatusEnum.getCode()/1000 == code/1000){
                nameOptional = Optional.of(contractStatusEnum.getName());;
                break;
            }
        }
        return nameOptional;
    }

    public static String getStatusNameByCode(Integer code){
        String statusName = null;
        for(ContractStatusEnum contractStatusEnum : values()){
            if(contractStatusEnum.getCode().equals(code)){
                statusName = contractStatusEnum.getName();
                break;
            }
        }
        return statusName;
    }

    ContractStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
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
