package com.common.district.common.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemConfig {

	private static Properties prop = null;

	static  {
		prop = new Properties();
		try {
			InputStream inStream = SystemConfig.class.getClassLoader().getResourceAsStream("config.properties");
			prop.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 项目根路径*/
	private static String baseUrl;

	public static String getBaseUrl() {
		if (prop != null) {
			baseUrl = prop.getProperty("baseUrl");
		}
		return baseUrl;
	}
	private static String domain;

	public static String getDomain() {
		if (prop != null) {
			domain = prop.getProperty("domain");
		}
		return domain;
	}
	/** 项目根路径*/
	private static String loginUrl;

	public static String getLoginUrl() {
		if (prop != null) {
			loginUrl = prop.getProperty("loginUrl");
		}
		return loginUrl;
	}
}