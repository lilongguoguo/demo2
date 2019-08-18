package com.common.district.org.vo;

import java.io.Serializable;

public class ResDeptVo implements Serializable {
    /*   //机构名称
       deptName:"",
       //上级机构id
       parentId:"",
       //上级机构名
       parentName:"",
       //机构编码
       coding:"",
       //部门类型
       deptType:"",
       //备注
       customerName:""*/
    private String deptId;
    private String deptName;
    private String parentId;
    private String parentName;
    private String coding;
    private Integer deptType;
    private String customerName;
    private Boolean onOff;//开关，判断页面传过来是修改还是添加

    public Boolean getOnOff() {
        return onOff;
    }

    public void setOnOff(Boolean onOff) {
        this.onOff = onOff;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getCoding() {
        return coding;
    }

    public void setCoding(String coding) {
        this.coding = coding;
    }

    public Integer getDeptType() {
        return deptType;
    }

    public void setDeptType(Integer deptType) {
        this.deptType = deptType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
