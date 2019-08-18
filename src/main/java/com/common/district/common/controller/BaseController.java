package com.common.district.common.controller;

import com.common.district.common.constants.SysConstants;
import com.common.district.common.utils.JsonUtils;
import com.common.district.common.utils.RedisUtil;
import com.common.district.org.model.Department;
import com.common.district.org.model.LoginUser;
import net.sf.json.JSONObject;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
public class BaseController {

	protected int pageNo = 1;
	public static int pageSize = 10;
//	protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
	public static String URL404 = "common/404";
	private final static String PARAM_PAGE_NO = "pageNo";
	protected String pageSizeName = "pageSize";

	/**
	 * 往Request里带值
	 *
	 * @param request
	 * @param key
	 * @param value
	 */
	protected static void setValue2Request(HttpServletRequest request, String key, Object value) {
		request.setAttribute(key, value);
	}

	/**
	 * [获取session]
	 *
	 * @param request
	 * @return
	 */
	public static HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		BaseController.pageSize = pageSize;
	}

	/**
	 * 返回根路径
	 */
	public String getBasePath(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		String scheme = request.getScheme();
		if (!scheme.equals("https") && url.indexOf("https://") > -1) {
			scheme = "https";
		}
		String path = request.getContextPath();
		String port = "" + request.getServerPort();
		String basePath = scheme + "://" + request.getServerName();
		if (!port.equals("80")) {
			basePath += ":" + request.getServerPort();
		}
		basePath += path;
		return basePath;
	}

	/**
	 * 返回工程名
	 */
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}

	/**
	 * 获取用登录户信息
	 * @param request
	 * @return
	 */
	protected static LoginUser getLoginInfo(HttpServletRequest request) {
		// 临时赋值，后面会进行token验证，获取key，根据key值获取session中的用户信息
		LoginUser loginUser = new LoginUser();
		loginUser.setUserId("GQ2019080200014");
		loginUser.setUserName("赵六");
		loginUser.setOriginId("ZH201908020001");
		Department dep = new Department();
		dep.setDeptId("201908011758");
		dep.setDeptName("北京物业公司");
		dep.setOrganizationCode("0003");
		List<Department> companys = Lists.newArrayList();
		companys.add(dep);
		loginUser.setCompanys(companys);

//		String userKey = request.getHeader(SysConstants.PDE_U_KEY_TOKEN);
//		Object objUser = RedisUtil.get(userKey);
//		JSONObject jsonUser = JsonUtils.toJson(objUser.toString());
//		LoginUser user = JsonUtils.fromJson(objUser.toString(),LoginUser.class);
		return loginUser;
	}
}
