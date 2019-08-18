package com.common.district.common.constants;

/**
 * 系统常量
 * @author XIHONGLEI
 * @date 2019-08-12
 */
public class SysConstants {
    /* 系统编码 Token */
    public static final String PDE_AUTHENTICATE_TOKEN = "X-Authentication-Token";

    /* 用户登录状态 Token */
    public static final String PDE_U_KEY_TOKEN = "X-UKey-Token";

    /* 系统标识编码 */
    public static final String TOKEN_KEY = "PIxOQnLyM+KkLkA0I+yd9$ImbIuq97eRkiTud3g0IV3qPOLgYSeDdoJhb3Joo8Rte";

    public static final String LOGIN_VERIFY = "/login/verify";
    public static final String GET_VERIFY_CODE = "/login/getVerify";

    /* Swagger地址 */
    public static final String SWAGGER_PATH = "swagger-ui.html";
    public static final String SWAGGER_PATH_OTHER = "/webjars/springfox-swagger-ui";
    public static final String SWAGGER_PATH_OTHER_2 = "/swagger-resources";
    public static final String SWAGGER_PATH_DOC = "/api-docs";

    /* 登录超时 */
    public static final int LOGIN_TIMEOUT = 1001;
    /* 用户名密码为空 */
    public static final int LOGIN_UNAME_OR_PASSWD_EMPTY = 1002;
    /* 用户不存在 */
    public static final int LOGIN_USER_UNDEFINE = 1003;
    /* 用户密码错误 */
    public static final int LOGIN_USER_PWD_ERROR = 1004;
    /* 系统异常 */
    public static final int LOGIN_SYS_EXCEPTION = 1005;
}
