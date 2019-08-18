package com.common.district.org.model;

import com.common.district.res.model.Company;

import java.util.List;

public class LoginUser extends User {
    // 部门信息
    private List<Department> departments;
    // 公司信息
    private List<Department> companys;

    private List<Menu> menus;

    private List<Role> roleList;

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Department> getCompanys() {
        return companys;
    }

    public void setCompanys(List<Department> companys) {
        this.companys = companys;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
