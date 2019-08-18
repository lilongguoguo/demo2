package com.common.district.org.controller;

import com.common.district.common.SysUtil.RespData;
import com.common.district.common.constants.SysConstants;
import com.common.district.common.utils.*;
import com.common.district.org.model.LoginUser;
import com.common.district.org.service.UserService;
import com.common.district.org.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "login")
@Api(description = "用户验证",produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "verify", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户登录",httpMethod = "POST")
    public RespData<LoginUser> verify(@RequestBody LoginVo loginVo,
                                      HttpServletRequest request,
                                      HttpServletResponse response){
        RespData<LoginUser> respData = new RespData<>();
        if(loginVo == null || StringUtils.isBlank(loginVo.getUserName()) || StringUtils.isBlank(loginVo.getPassword())){
            respData.setResult(false);
            respData.setErrorMessage("用户名和密码不能为空！");
            respData.setErrorCode(SysConstants.LOGIN_UNAME_OR_PASSWD_EMPTY);
            return respData;
        }
        try {
            respData = this.userService.verify(loginVo.getUserName(), loginVo.getPassword());
            // 登录成功，设置登录状态
            if(respData.getResult() && respData.getData() != null){
                String uKey = TokenUtil.Create();
                String userJson = JsonUtils.toJson(respData.getData());
                RedisUtil.set(uKey,userJson);
                CookieUtil.setCookie(SysConstants.PDE_U_KEY_TOKEN, uKey, -1, response, "localhost", "/");
                CookieUtil.setCookie(SysConstants.PDE_AUTHENTICATE_TOKEN, SysConstants.TOKEN_KEY, -1, response, "localhost", "/");
                respData.setData(null);
            }
        }catch (Exception e){
            log.error("系统登录异常->:",e);
            respData.setResult(false);
            respData.setErrorMessage("系统登录异常，请联系管理员！");
            respData.setErrorCode(SysConstants.LOGIN_SYS_EXCEPTION);
        }
        return respData;
    }
}
