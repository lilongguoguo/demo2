package com.common.district.common.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.Date;

/**
 * Description：FTP工具类 Created by 席红蕾
 */
public class FTPUtil {

	private FTPClient ftpClient;
	private String strIp;
	private int intPort;
	private String user;
	private String password;

	//private static Logger logger = Logger.getLogger(FTPUtil.class.getName());

	/**
	 *  Ftp构造函数
	 */
	public FTPUtil(String strIp, int intPort, String user, String Password) {
		this.strIp = strIp;
		this.intPort = intPort;
		this.user = user;
		this.password = Password;
		this.ftpClient = new FTPClient();
	}

	/**
	 * @return 判断是否登入成功
	 * @throws IOException
	 */
	public boolean ftpLogin() throws IOException {
		boolean isLogin = false;
		FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
		this.ftpClient.setControlEncoding("GBK");
		this.ftpClient.configure(config);

		try {
			if (this.intPort > 0) {
				this.ftpClient.connect(this.strIp, this.intPort);
			} else {
				this.ftpClient.connect(this.strIp);
			}
			// FTP服务器连接回答
			int reply = this.ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				this.ftpClient.disconnect();
				System.out.println("登录FTP服务失败！");
				return isLogin;
			}
			this.ftpClient.login(this.user, this.password);
			// 设置传输协议
			this.ftpClient.enterLocalPassiveMode();
			this.ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			System.out.println("恭喜" + this.user + "成功登陆FTP服务器");
			isLogin = true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(this.user + "登录FTP服务失败！" + e.getMessage());
		}
		return isLogin;
	}

	/**
	 * @退出关闭服务器链接
	 */
	public void ftpLogOut() {
		if (null != this.ftpClient && this.ftpClient.isConnected()) {
			try {
				boolean reuslt = this.ftpClient.logout();// 退出FTP服务器
				if (reuslt) {
					System.out.println("成功退出服务器");
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("退出FTP服务器异常！" + e.getMessage());
			} finally {
				try {
					this.ftpClient.disconnect();// 关闭FTP服务器的连接
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("关闭FTP服务器的连接异常！");
				}
			}
		}
	}

	/***
	 * 上传Ftp文件
	 * @param localFile当地文件
	 * @param romotUpLoadePath上传服务器路径- 应该以/结束
	 */
	public boolean uploadFile(File localFile, String romotUpLoadePath) {
		BufferedInputStream inStream = null;
		boolean success = false;
		try {
			this.ftpClient.changeWorkingDirectory(romotUpLoadePath);// 改变工作路径
			inStream = new BufferedInputStream(new FileInputStream(localFile));
			System.out.println(localFile.getName() + "开始上传.....");
			success = this.ftpClient.storeFile(localFile.getName(), inStream);
			if (success == true) {
				System.out.println(localFile.getName() + "上传成功");
				return success;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(localFile + "未找到");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return success;
	}

	/***
	 * @上传文件夹
	 * @param localDir当地文件夹
	 * @param remoteDir Ftp 服务器路径 以目录"/"结束
	 */
	public boolean ftpUpload(String localDir, String remoteDir) {
		File src = new File(localDir);
		try {
			this.ftpClient.makeDirectory(remoteDir);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(remoteDir + "目录创建失败");
		}
		File[] allFile = src.listFiles();
		for (int currentFile = 0; currentFile < allFile.length; currentFile++) {
			if (!allFile[currentFile].isDirectory()) {
				String srcName = allFile[currentFile].getPath().toString();
				uploadFile(new File(srcName), remoteDir);
			}
		}
		for (int currentFile = 0; currentFile < allFile.length; currentFile++) {
			if (allFile[currentFile].isDirectory()) {
				// 递归
				ftpUpload(allFile[currentFile].getPath().toString(), remoteDir);
			}
		}
		return true;
	}

	/***
	 * @下载文件夹
	 * @param localDir 本地地址
	 * @param remoteDir   远程文件夹
	 * @param containStr 文件名中包含的字符串 如果为""或null 则为下载全部
	 */
	public boolean ftpDownload(String localDir, String remoteDir, String containStr) {
		try {
			this.ftpClient.setRemoteVerificationEnabled(false);
			this.ftpClient.enterLocalPassiveMode();
			System.out.println("下载文件");
			boolean isDir = this.ftpClient.changeWorkingDirectory(remoteDir);
			localDir = localDir + "/";
			File local = new File(localDir);
			if (!local.exists()) {
				local.mkdirs();
			}
			if (isDir) {
				FTPFile[] files = this.ftpClient.listFiles();
				for (FTPFile f : files) {
					if (containStr != null && !containStr.equals("")) {
						if (f.getName().indexOf(containStr) > 0) {
							System.out.println(new Date() + "  下载    "+f.getName()+"  成功");
							File localFile = new File(localDir + f.getName());
							OutputStream ios = new FileOutputStream(localFile);
							ftpClient.retrieveFile(f.getName(), ios);
							ios.close();
						}
					} else {
						System.out.println(new Date() + "    下载    "+f.getName()+"  成功");
						File localFile = new File(localDir + f.getName());
						OutputStream ios = new FileOutputStream(localFile);
						ftpClient.retrieveFile(f.getName(), ios);
						ios.close();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("下载文件夹失败");
			return false;
		}
		return true;
	}

	// FtpClient的Set 和 Get 函数
	public FTPClient getFtpClient() {
		return ftpClient;
	}

	public void setFtpClient(FTPClient ftpClient) {
		this.ftpClient = ftpClient;
	}

	public static void main(String[] args) throws IOException {
		/*Properties pro = new Properties();
		pro.load(FTPUtil.class.getResourceAsStream("/ftpfile.properties"));
		System.out.println(pro.getProperty("ftp.ip"));
		System.out.println(pro.getProperty("ftp.port"));
		System.out.println(pro.getProperty("ftp.username"));
		System.out.println(pro.getProperty("ftp.password"));
		System.out.println(pro.getProperty("ftp.serverpath"));*/
		//FTPUtil ftp = new FTPUtil("10.37.253.33", 21, "zhangbaolong", "qCloud@2017");
		//ftp.ftpLogin();
		// 上传文件夹
		//ftp.ftpUpload("d://tmp/", "/data/gap/");
		// 下载文件夹
		//ftp.ftpDownload("d:/tmp/", "/data/gap","2017-03-17");
		//ftp.ftpLogOut();
		/*FTPUtil ftp = new FTPUtil("47.97.158.160", 21, "fxmftp", "Fs20171010!$");
		ftp.ftpLogin();
		ftp.ftpDownload("C:/Users/pc/Desktop/files/pub/", "/fxmftp/fsf/2019/06/02",null);
		ftp.ftpLogOut();*/
	}
}
