package com.common.district.org.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    private String userId;

    private String loginId;

    private String userName;

    private String passwd;

    private Integer sex;

    private String phone;

    private String email;

    private String headUrl;

    private String cardNo;

    private String originId;

    private Integer status;

    private String createUserId;

    private Date createTime;

    private String updateUserId;

    private Date updateTime;

    private Integer del;

    private String delUserId;

    private Date delTime;

    private List<Role> roleList = new ArrayList<>();

    private List<Department> departmentList = new ArrayList<>();

    private List<String> roleNameList = new ArrayList<>();//所有的角色名

    private List<String> deptNameList = new ArrayList<>();//所有的部门名

    private String roleName ;//角色姓名(以分号隔开)

    private String deptName;//部门姓名（以分号隔开）

    private Integer cp;

    private Integer ps;

    private String statusStr;//状态字符串

    public List<String> getRoleNameList() {
        return roleNameList;
    }

    public void setRoleNameList(List<String> roleNameList) {
        this.roleNameList = roleNameList;
    }

    public List<String> getDeptNameList() {
        return deptNameList;
    }

    public void setDeptNameList(List<String> deptNameList) {
        this.deptNameList = deptNameList;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public Integer getCp() {
        return cp;
    }

    public Integer getPs() {
        return ps;
    }

    public void setPs(Integer ps) {
        this.ps = ps;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId == null ? null : loginId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl == null ? null : headUrl.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId == null ? null : originId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public String getDelUserId() {
        return delUserId;
    }

    public void setDelUserId(String delUserId) {
        this.delUserId = delUserId == null ? null : delUserId.trim();
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", loginId='" + loginId + '\'' +
                ", userName='" + userName + '\'' +
                ", passwd='" + passwd + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", originId='" + originId + '\'' +
                ", status=" + status +
                ", createUserId='" + createUserId + '\'' +
                ", createTime=" + createTime +
                ", updateUserId='" + updateUserId + '\'' +
                ", updateTime=" + updateTime +
                ", del=" + del +
                ", delUserId='" + delUserId + '\'' +
                ", delTime=" + delTime +
                '}';
    }
}