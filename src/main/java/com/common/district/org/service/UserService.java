package com.common.district.org.service;

import com.common.district.common.SysUtil.RespData;
import com.common.district.org.model.LoginUser;
import com.common.district.org.model.User;

import java.util.List;

public interface UserService {

    User selectByPrimaryKey(String userId);

    RespData<LoginUser> verify(String userName, String passwd);
    /**
     * 根据用户名字查询用户列表信息包含所属的所有部门和角色信息
     * @param userName 用户名 可无
     * @return
     */
    List<User> getUserListByUserName(String userName,String organId,Integer cp,Integer ps);

    Integer getUserSizeByUserName(String userName,String organId);
}
