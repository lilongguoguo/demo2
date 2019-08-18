package com.common.district.common;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Ftp配置信息
 * 
 * @author tanwei
 *
 */
public class FtpConfig {

	Properties prop = null;

	public FtpConfig() {
		prop = new Properties();
		try {
			InputStream inStream = FtpConfig.class.getClassLoader().getResourceAsStream("ftpConfig.properties");
			prop.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ftp地址
	 */
	private String ftphostname;
	/**
	 * ftp端口
	 */
	private String ftpport;
	/**
	 * 用户名
	 */
	private String ftpusername;
	/**
	 * 密码
	 */
	private String ftppassword;
	/**
	 * ftp目录
	 */
	private String filedir;
	/**
	 * ftp目录
	 */
	private String filepath;
	/**
	 * 点位导入上传目录
	 */
	private String positiondir;
	/**
	 * 文件访问目录
	 */
	private String filedowndir;

	public String getFtphostname() {
		if (prop != null) {
			ftphostname = prop.getProperty("ftphostname");
		}
		return ftphostname;
	}

	public String getFtpport() {
		if (prop != null) {
			ftpport = prop.getProperty("ftpport");
		}
		return ftpport;
	}

	public String getFtpusername() {
		if (prop != null) {
			ftpusername = prop.getProperty("ftpusername");
		}
		return ftpusername;
	}

	public String getFtppassword() {
		if (prop != null) {
			ftppassword = prop.getProperty("ftppassword");
		}
		return ftppassword;
	}

	public String getFiledir() {
		if (prop != null) {
			filedir = prop.getProperty("filedir");
		}
		return filedir;
	}

	public String getFilepath() {
		if (prop != null) {
			filepath = prop.getProperty("filepath");
		}
		return filepath;
	}

	public String getPositiondir() {
		if (prop != null) {
			positiondir = prop.getProperty("positiondir");
		}
		return positiondir;
	}

	public String getFiledowndir() {
		if (prop != null) {
			filedowndir = prop.getProperty("filedowndir");
		}
		return filedowndir;
	}
}