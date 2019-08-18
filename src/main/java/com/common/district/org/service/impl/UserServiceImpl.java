package com.common.district.org.service.impl;

import com.common.district.common.SysUtil.RespData;
import com.common.district.common.constants.SysConstants;
import com.common.district.common.utils.MD5Util;
import com.common.district.common.utils.SHA256Util;
import com.common.district.org.dao.DepartmentMapper;
import com.common.district.org.dao.RoleMapper;
import com.common.district.org.dao.UserMapper;
import com.common.district.org.model.Department;
import com.common.district.org.model.LoginUser;
import com.common.district.org.model.Role;
import com.common.district.org.model.User;
import com.common.district.org.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public User selectByPrimaryKey(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public RespData<LoginUser> verify(String userName, String passwd) {
        RespData<LoginUser> respData = new RespData<>();
        User user = this.userMapper.findUserByLoginId(userName);
        if(user == null || user.getUserId() == null){
            respData.setResult(false);
            respData.setErrorCode(SysConstants.LOGIN_USER_UNDEFINE);
            respData.setErrorMessage("该用户不存在！");
            return respData;
        }
        //验证登录
        String cipherText = SHA256Util.encode(MD5Util.encode(passwd));
        if(!cipherText.equals(user.getPasswd())){
            respData.setResult(false);
            respData.setErrorCode(SysConstants.LOGIN_USER_PWD_ERROR);
            respData.setErrorMessage("用户名密码错误！");
            return respData;
        }
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(user,loginUser);
        //获取用户角色
        List<Role> roleList = this.roleMapper.getUserRoleListByUserId(user.getUserId());
        loginUser.setRoleList(roleList);
        //获取部门
        List<Department> deptList = this.departmentMapper.getUserDeptListByUserId(user.getUserId());
        loginUser.setDepartments(deptList);
        //获取菜单权限
        respData.setResult(true);
        respData.setData(loginUser);
        return respData;
    }
    @Override
    public List<User> getUserListByUserName(String userName,String organId,Integer cp,Integer ps) {
        return userMapper.getUserListByUserName(userName,organId,cp,ps);
    }

    @Override
    public Integer getUserSizeByUserName(String userName,String organId) {
        return userMapper.getUserSizeByUserName(userName,organId);
    }
}
