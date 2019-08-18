package com.common.district.org.vo;

import org.joda.time.Instant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 部门扩展实体类
 */
public class DepartmentVo implements Serializable{

    private String deptId;

    private String deptName;

    private Integer deptType;

    private String deptFlag;

    private String parentId;

    private String topDeptId;

    private String originId;

    private Integer status;

    private String createUserId;

    private Date createTime;

    private String updateUserId;

    private Date updateTime;

    private Integer del;

    private String delUserId;

    private Date delTime;

    private String organizationCode;

    private String remark;

    private String parentName;

    private List<DepartmentVo> children = new ArrayList<>();//子类部门

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<DepartmentVo> getChildren() {
        return children;
    }

    public void setChildren(List<DepartmentVo> children) {
        this.children = children;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public Integer getDeptType() {
        return deptType;
    }

    public void setDeptType(Integer deptType) {
        this.deptType = deptType;
    }

    public String getDeptFlag() {
        return deptFlag;
    }

    public void setDeptFlag(String deptFlag) {
        this.deptFlag = deptFlag == null ? null : deptFlag.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getTopDeptId() {
        return topDeptId;
    }

    public void setTopDeptId(String topDeptId) {
        this.topDeptId = topDeptId == null ? null : topDeptId.trim();
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
}
