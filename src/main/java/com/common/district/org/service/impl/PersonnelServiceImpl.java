package com.common.district.org.service.impl;

import com.common.district.common.SysUtil.RespData;
import com.common.district.common.utils.StringUtils;
import com.common.district.org.dao.DepartmentMapper;
import com.common.district.org.dao.RoleMapper;
import com.common.district.org.dao.UserMapper;
import com.common.district.org.model.User;
import com.common.district.org.service.PersonnelService;
import com.common.district.org.vo.UserVo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 人员账号
 */
@Service
public class PersonnelServiceImpl implements PersonnelService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    DepartmentMapper departmentMapper;


    /**
     * 获取人员账号列表
     *
     * @param vo
     * @return
     */
    @Override
    public List getPersonnelList(UserVo vo) {
        User user = inspectUser(vo);
        //组装分页参数
        Integer cpCp = user.getCp();
        user.setCp((cpCp - 1) * user.getPs());
        user.setPs(cpCp * user.getPs());
        List<User> userList = userMapper.getAllRoleDeptUser(user);//查询所有的人员角色部门
        List<String> roleNameList = roleMapper.getRoleNameList(vo.getOriginId());
        List<String> deptNameList = departmentMapper.getDeptNameList(vo.getOriginId());
        if (CollectionUtils.isNotEmpty(userList)) {
            userList.forEach(x -> {
                List<String> roleList = Lists.newArrayList();
                List<String> deptList = Lists.newArrayList();
                x.getRoleList().forEach(y -> {
                    roleList.add(y.getRoleName());
                });
                x.getDepartmentList().forEach(d -> {
                    deptList.add(d.getDeptName());
                });
                x.setRoleName(StringUtils.join(roleList, ";"));
                x.setDeptName(StringUtils.join(deptList, ";"));
                if (StringUtils.isEmpty(x.getUpdateUserId())) {//如果没有修改人，则把修改时间设置为null
                    x.setUpdateTime(null);
                }
                if (null != x.getStatus()) {
                    String Str = x.getStatus() == 1 ? "启用" : x.getStatus() == 0 ? "禁用" : "离职";
                    x.setStatusStr(Str);
                }
                x.setRoleNameList(roleNameList);
                x.setDeptNameList(deptNameList);
            });
        }
        return userList;
    }


    /**
     * 赋值人员账号列表
     *
     * @param vo
     * @return
     */
    public User inspectUser(UserVo vo) {
        User user = new User();
        if (StringUtils.isNotEmpty(vo.getUserName())) {
            user.setUserName(vo.getUserName());//用户名
        }
        if (StringUtils.isNotEmpty(vo.getOriginId())) {
            user.setOriginId(vo.getOriginId());//租户id
        }
        if (StringUtils.isNotEmpty(vo.getRoleName())) {
            user.setRoleName(vo.getRoleName());//角色名
        }
        if (StringUtils.isNotEmpty(vo.getDeptName())) {
            user.setDeptName(vo.getDeptName());//部门
        }
        if (null != vo.getCp()) {
            user.setCp(vo.getCp());
        }
        if (null != vo.getPs()) {
            user.setPs(vo.getPs());
        }
        if (StringUtils.isNotEmpty(vo.getLoginId())) {
            user.setLoginId(vo.getLoginId());//登录账号
        }
        if (null != vo.getStatus()) {
            user.setStatus(vo.getStatus());//状态
        }
        if (StringUtils.isNotEmpty(vo.getStatusStr())) {
            Integer status = "启用".equals(vo.getStatusStr()) ? 1 : "禁用".equals(vo.getStatusStr()) ? 0 : "离职".equals(vo.getStatusStr()) ? 2 : 500;
            if (500 != status){
                user.setStatus(status);
            }
        }
        return user;
    }

    /**
     * 获取人员列表统计数
     *
     * @param vo
     * @return
     */
    @Override
    public Integer getPersonnelListCount(UserVo vo) {
        User user = inspectUser(vo);
        List<User> users = userMapper.getPersonnelListCount(user);
        return users.size();
    }

    /**
     * 部门角色名
     *
     * @param vo
     * @return
     */
    @Override
    public RespData getDeptRoleName(UserVo vo) {
        List list = Lists.newArrayList();
        List<String> roleNameList = roleMapper.getRoleNameList(vo.getOriginId());
        List<String> deptNameList = departmentMapper.getDeptNameList(vo.getOriginId());
        list.add(roleNameList);
        list.add(deptNameList);
        return RespData.success(list);
    }

}
