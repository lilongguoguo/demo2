package com.common.district.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	/**
	 * 设置Cookie
	 *
	 * @param key    要设置的Cookie名字
	 * @param value  要设置的Cookie值
	 * @param maxAge 设置Cookie的有效期
	 * @param res    HttpServletResponse对象
	 * @param domain 设置Cookie的domain
	 * @param path   设置Cookie的path
	 * @author LiZhe
	 */
	public static void setCookie(String key, String value, int maxAge,HttpServletResponse res, String domain,
								 String path) {
		if (null == key || key.trim().equals("") || res == null) {
			return;
		}

		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(4 * 60 * 60);
		if (!StringUtils.isBlank(domain)) {
			cookie.setDomain(domain);
		}
		if (!StringUtils.isBlank(path)) {
			cookie.setPath(path);
		}
		res.addCookie(cookie);
	}


	/**
	 * 获取Cookie
	 *
	 * @author LiZhe
	 * @param key 要获取的Cookie名字
	 * @param req HttpServletRequest对象
	 * @return
	 */
	public static String getCookie(String key, HttpServletRequest req)
	{
		if (null == key || key.trim().equals("") || req == null)
		{
			return null;
		}

		Cookie[] cookies = req.getCookies();

		if (cookies != null)
		{
			for (int i = 0; i < cookies.length; i++)
			{
				if (key.equals(cookies[i].getName()))
				{
					return cookies[i].getValue();
				}
			}
		}
		return null;
	}
}
