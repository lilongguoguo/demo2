package com.common.district.org.controller;

import com.common.district.org.model.User;
import com.common.district.org.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("welcome")
@Api( description = "用户信息")
public class Welcome {
    @Autowired
    private UserService userService;

    @RequestMapping("welcome")
    @ResponseBody
    @ApiOperation(value = "value-test")
    public User welcome(){
        User user = userService.selectByPrimaryKey("1");
        System.out.println(user.toString());
        return user;
    }

    @RequestMapping("user-list")
    @ResponseBody
    @ApiOperation(value = "获取用户示例数据")
    public List<User> getUserList(){
        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setUserName("张三");
        user1.setUserId("U1");
        user1.setLoginId("zhangsan");
        User user2 = new User();
        user2.setUserName("李四");
        user2.setUserId("U2");
        user2.setLoginId("lishi");
        User user3 = new User();
        user3.setUserName("王五");
        user3.setUserId("U3");
        user3.setLoginId("wangwu");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return list;
    }


    @RequestMapping("toWelcome")
    public ModelAndView toWelcome(){
        User user = userService.selectByPrimaryKey("1");
        System.out.println(user.toString());
        return new ModelAndView("welcome");
    }
}
