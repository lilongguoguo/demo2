package com.common.district.common.filter;

import com.common.district.common.constants.SysConstants;
import com.common.district.common.utils.RedisUtil;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 跨域拦截器
 */
public class CROSFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(CROSFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String reqUrl = getCurrentUrl(request);
        logger.info("当前请求链接->:{}",reqUrl);
        // 如果是登录验证或验证码，则跳过验证
        if(reqUrl.contains(SysConstants.LOGIN_VERIFY)
                || reqUrl.contains(SysConstants.GET_VERIFY_CODE)
                || reqUrl.contains(SysConstants.SWAGGER_PATH)
                || reqUrl.contains(SysConstants.SWAGGER_PATH_OTHER)
                || reqUrl.contains(SysConstants.SWAGGER_PATH_OTHER_2)
                || reqUrl.contains(SysConstants.SWAGGER_PATH_DOC)){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String authToken = request.getHeader(SysConstants.PDE_AUTHENTICATE_TOKEN);
        String userKey = request.getHeader(SysConstants.PDE_U_KEY_TOKEN);
        logger.info("当前authToken->:{}",authToken);
        logger.info("userKey->:{}",userKey);
        // 如果系统Token和用户Key为空，则返回错误
        if(StringUtils.isBlank(authToken)
                || StringUtils.isBlank(userKey)
                || !SysConstants.TOKEN_KEY.equals(authToken)){
            logger.info("用户登录状态校验未通过->:{}","Token为空 | UKey为空 | Token不正确");
            return;
        }
        Object logonUser = RedisUtil.get(userKey);
        if(logonUser == null
                || "".equals(logonUser.toString())
                || "{}".equals(logonUser.toString())){
            response.setStatus(SysConstants.LOGIN_TIMEOUT);
            logger.info("用户登录状态校验未通过->:{}","Redis中未获取到用户状态");
            return;
        }

        // 获取请求的参数
        String paraName = null;  //参数名称
        String paraValue = null;  //值
        Enumeration<String> parameters = request.getParameterNames();
        while(parameters.hasMoreElements()) {
            paraName = parameters.nextElement();
            paraValue = request.getParameter(paraName);
        }
/*        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Content-Type");
        response.setHeader("Access-Control-Expose-Headers", "*");
        response.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");*/
        // 浏览器是会先发一次options请求，如果请求通过，则继续发送正式的post请求
        // 配置options的请求返回
        /*if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpStatus.SC_OK);
            response.getWriter().write("OPTIONS returns OK");
            return;
        }*/
//        String referer = request.getHeader("Referer");
//        if(!referer.startsWith("http://"+SystemConfig.getDomain())||
//                !referer.startsWith("https://"+SystemConfig.getDomain())){
//            response.sendRedirect(SystemConfig.getLoginUrl());
//        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
    public String getCurrentUrl(HttpServletRequest request){
        String param = request.getQueryString();
        StringBuffer url = request.getRequestURL();
        if(StringUtils.isNotBlank(request.getHeader("X-Forwarded-Proto")) && request.getHeader("X-Forwarded-Proto").contains("https") && url.indexOf("http://") == 0){
            url = url.replace(0,4, "https");
        }
        String currentUrl = StringUtils.isBlank(param)?url.toString():url.append("?").append(request.getQueryString()).toString();
        return currentUrl;
    }
}
