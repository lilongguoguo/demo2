package com.common.district.org.vo;

import com.common.district.org.model.Menu;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

/**
 * 添加或修改角色配置相关菜单和功能
 */
public class BusRoleVo {

    private Boolean onOff;//判断是添加还是修改

    private String roleId;//角色id

    private String roleName;//角色名

    private Integer accessRange;//数据范围，集团，地区，个人

    private Long menuId;

    private String menuCode;

    private String menuName;

    private String menuLink;

    private Integer menuLevel;

    private Long parentId;

    private Integer del;

    private Integer leftMenu;

    private String remark;

    private Integer sort;

    private String createUserId;

    private Date createTime;

    private String updateUserId;

    private Date updateTime;

    private String organId;

    private Integer status;//状态，1启用0禁用

    private List<BusRoleVo> children = new ArrayList();//子类菜单集合

    private List<Long> checkedNode = new ArrayList();// 默认选中的节点(选中的菜单功能)

    private List<Long> expandedNode = new ArrayList();//默认打开的节点

    private List<Menu> devices = new ArrayList();//功能集合（例如：合同查看，合同审批）

    private List<Menu> checkedDevices = new ArrayList();//功能集合（选中的回显页面，用于修改）

    private List<String> checkedDevicesString = new ArrayList<>();//功能集合，用于插入

    public List<String> getCheckedDevicesString() {
        return checkedDevicesString;
    }

    public void setCheckedDevicesString(List<String> checkedDevicesString) {
        this.checkedDevicesString = checkedDevicesString;
    }

    public List<Menu> getCheckedDevices() {
        return checkedDevices;
    }

    public void setCheckedDevices(List<Menu> checkedDevices) {
        this.checkedDevices = checkedDevices;
    }

    public List<Menu> getDevices() {
        return devices;
    }

    public void setDevices(List<Menu> devices) {
        this.devices = devices;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Long> getExpandedNode() {
        return expandedNode;
    }

    public void setExpandedNode(List<Long> expandedNode) {
        this.expandedNode = expandedNode;
    }

    public Integer getAccessRange() {
        return accessRange;
    }

    public void setAccessRange(Integer accessRange) {
        this.accessRange = accessRange;
    }

    public List<Long> getCheckedNode() {
        return checkedNode;
    }

    public void setCheckedNode(List<Long> checkedNode) {
        this.checkedNode = checkedNode;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getOnOff() {
        return onOff;
    }

    public void setOnOff(Boolean onOff) {
        this.onOff = onOff;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuLink() {
        return menuLink;
    }

    public void setMenuLink(String menuLink) {
        this.menuLink = menuLink;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public Integer getLeftMenu() {
        return leftMenu;
    }

    public void setLeftMenu(Integer leftMenu) {
        this.leftMenu = leftMenu;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
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
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public List<BusRoleVo> getChildren() {
        return children;
    }

    public void setChildren(List<BusRoleVo> children) {
        this.children = children;
    }
}
