package com.common.district.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

public class FtpUtil2 {
	private static final Logger logger = LoggerFactory.getLogger(FtpUtil2.class.getClass());
	private FTPClient ftpClient;
	private boolean is_connected;

	public FtpUtil2() {
		ftpClient = new FTPClient();
		ftpClient.setControlEncoding("UTF-8");
		ftpClient.enterLocalPassiveMode();
		is_connected = false;
	}

	public FtpUtil2(int defaultTimeoutSecond, int connectTimeoutSecond, int dataTimeoutSecond) {
		ftpClient = new FTPClient();
		is_connected = false;
		ftpClient.setControlEncoding("UTF-8");
		ftpClient.enterLocalPassiveMode();
		ftpClient.setDefaultTimeout(defaultTimeoutSecond * 1000);
		ftpClient.setConnectTimeout(connectTimeoutSecond * 1000);
		ftpClient.setDataTimeout(dataTimeoutSecond * 1000);
	}

	/**
	 * 获取FTPClient对象
	 *
	 * @param ftpHost
	 *            FTP主机服务器
	 * @param ftpPassword
	 *            FTP 登录密码
	 * @param ftpUserName
	 *            FTP登录用户名
	 * @param ftpPort
	 *            FTP端口 默认为21
	 * @return
	 */
	public static FTPClient getFTPClient(String ftpHost, String ftpUserName, String ftpPassword, int ftpPort) {
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient = new FTPClient();
			// 切换到上传目录
			ftpClient.setControlEncoding("gbk");
			ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
			ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
			if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				logger.info("未连接到FTP，用户名或密码错误。");
				ftpClient.disconnect();
			}
		} catch (SocketException e) {
			e.printStackTrace();
			logger.info("FTP的IP地址可能错误，请正确配置。");
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("FTP的端口错误,请正确配置。");
		}
		return ftpClient;
	}

	/**
	 * 下载文件
	 *
	 * @param hostname
	 *            FTP服务器地址
	 * @param port
	 *            FTP服务器端口号
	 * @param username
	 *            FTP登录帐号
	 * @param password
	 *            FTP登录密码
	 * @param remoteFilePath
	 *            FTP服务器文件路劲
	 * @param downloadFileName
	 *            下载提示的文件名称
	 * @return
	 * @throws IOException
	 */
	public static boolean downloadFile(HttpServletResponse response, String hostname, int port, String username,
									   String password, String remoteFilePath, String downloadFileName) throws IOException {
		boolean flag = false;
		FTPClient ftpClient = new FTPClient();
		ftpClient.setControlEncoding("GBK");
		// 连接FTP服务器
		ftpClient.connect(hostname, port);
		// 登录FTP服务器
		ftpClient.login(username, password);
		// 验证FTP服务器是否登录成功
		int replyCode = ftpClient.getReplyCode();
		if (!FTPReply.isPositiveCompletion(replyCode)) {
			return flag;
		}

		// int lastPathSeparatorIndex = originfilename.lastIndexOf("/");
		// String remotePathName = originfilename.substring(0,
		// lastPathSeparatorIndex);
		// String remoteFileName =
		// originfilename.substring(lastPathSeparatorIndex + 1);
		// ftpClient.changeWorkingDirectory(remotePathName);

		// 如果不设置文件类型，本地测试没问题，但到服务器的tomcat里，文件下载会少一个字节
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + URLEncoder.encode(downloadFileName, "UTF-8"));
		OutputStream responseOutputStream = response.getOutputStream();
		String extName = downloadFileName.substring(downloadFileName.lastIndexOf('.') + 1);
		if ("jpg".equalsIgnoreCase(extName) || "png".equalsIgnoreCase(extName)) {
			ByteArrayOutputStream temp = new ByteArrayOutputStream();
			flag = ftpClient.retrieveFile(remoteFilePath, temp);
			if (!flag) {
				logger.debug("文件{}下载失败", remoteFilePath);
				response.setStatus(HttpStatus.NOT_FOUND.value());
			} else {
				InputStream inputStream = new ByteArrayInputStream(temp.toByteArray());
				//ImageUtils.pressText(inputStream, extName, responseOutputStream);
				inputStream.close();
			}
			temp.close();
		} else {
			flag = ftpClient.retrieveFile(remoteFilePath, responseOutputStream);
			if (!flag) {
				logger.debug("文件{}下载失败", remoteFilePath);
				response.setStatus(HttpStatus.NOT_FOUND.value());
			}
		}
		responseOutputStream.close();
		ftpClient.logout();
		return flag;
	}
	public static Boolean downloadFtpFile(HttpServletResponse response, String hostname, int port, String username,
										  String password, String remoteFilePath, String downloadFileName,String name) {

		boolean flag = false;

		try {
			FTPClient ftpClient = new FTPClient();
			ftpClient.setControlEncoding("GBK");
			// 连接FTP服务器
			ftpClient.connect(hostname, port);
			// 登录FTP服务器
			ftpClient.login(username, password);
			// 验证FTP服务器是否登录成功
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				return flag;
			}
			// ftpClient.setControlEncoding("UTF-8"); // 中文支持
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory(remoteFilePath);
			response.setContentType("application/x-download");
			String newName =URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20").replaceAll("%28", "\\(").replaceAll("%29", "\\)").replaceAll("%3B", ";").replaceAll("%40", "@").replaceAll("%23", "\\#").replaceAll("%26", "\\&");
			if(!"".equals(name)){
				response.setHeader("Content-Disposition",
						"attachment;filename=" + newName);

			}else{
				response.setHeader("Content-Disposition",
						"attachment;filename=" + URLEncoder.encode(downloadFileName, "UTF-8"));
			}
			// File localFile = new File("D://" + File.separatorChar + downloadFileName);
			// OutputStream os = new FileOutputStream(localFile);
			OutputStream os = response.getOutputStream();
			flag = ftpClient.retrieveFile(downloadFileName, os);
			os.close();
			ftpClient.logout();

		} catch (FileNotFoundException e) {
			logger.error("没有找到" + remoteFilePath + "文件");
			e.printStackTrace();
		} catch (SocketException e) {
			logger.error("连接FTP失败.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("文件读取错误。");
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Description: 向FTP服务器上传文件
	 *
	 * @param ftpHost
	 *            FTP服务器hostname
	 * @param ftpUserName
	 *            账号
	 * @param ftpPassword
	 *            密码
	 * @param ftpPort
	 *            端口
	 * @param ftpPath
	 *            FTP服务器中文件所在路径 格式： ftptest/aa
	 * @param fileName
	 *            ftp文件名称
	 * @param input
	 *            文件流
	 * @param
	 *
	 * @return 成功返回true，否则返回false
	 */
	public static boolean uploadFile(String ftpHost, String ftpUserName, String ftpPassword, int ftpPort,
									 String ftpPath, String fileName, InputStream input, String filePath) {
		boolean flag = false;
		FTPClient ftpClient = null;
		try {
			ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				return flag;
			}

			if (!ftpClient.changeWorkingDirectory(ftpPath + filePath)) {
				// 如果目录不存在创建目录
				String[] dirs = filePath.split("/");
				String tempPath = ftpPath;
				for (String dir : dirs) {
					if (null == dir || "".equals(dir))
						continue;
					tempPath += "/" + dir;
					if (!ftpClient.changeWorkingDirectory(tempPath)) {
						if (!ftpClient.makeDirectory(tempPath)) {
							return flag;
						} else {
							ftpClient.changeWorkingDirectory(tempPath);
						}
					}
				}
			}

			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			ftpClient.storeFile(fileName, input);
			input.close();
			ftpClient.logout();
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("ftp上传文件异常");
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return flag;
	}

	/***
	 * 批量上传
	 *
	 * @param uploadFile
	 * @param ftpHost
	 * @param ftpUserName
	 * @param ftpPassword
	 * @param ftpPort
	 * @param basePath
	 *            ftp路径
	 * @param filePath
	 * @return
	 */
	public static boolean uploadFileList(MultipartFile[] uploadFile, String ftpHost, String ftpUserName,
										 String ftpPassword, int ftpPort, String basePath, String filePath) {
		FTPClient ftpClient = null;
		boolean flag = false;
		try {
			ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				return flag;
			}
			if (!ftpClient.changeWorkingDirectory(basePath + filePath)) {
				// 如果目录不存在创建目录
				String[] dirs = filePath.split("/");
				String tempPath = basePath;
				for (String dir : dirs) {
					if (null == dir || "".equals(dir))
						continue;
					tempPath += "/" + dir;
					if (!ftpClient.changeWorkingDirectory(tempPath)) {
						if (!ftpClient.makeDirectory(tempPath)) {
							return false;
						} else {
							ftpClient.changeWorkingDirectory(tempPath);
						}
					}
				}
			}
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			if (uploadFile != null && uploadFile.length > 0) {
				for (int i = 0; i < uploadFile.length; i++) {
					MultipartFile file = uploadFile[i];
					// 一个未上传成功则跳出
					if (!saveFile(file, ftpClient)) {
						flag = false;
						break;
					}
				}
			}
			ftpClient.logout();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return flag;
	}

	/***
	 *
	 * @return
	 */
	public static String generateSuffix(String fileName) {
		int i = fileName.lastIndexOf(".");
		String prefix = fileName.substring(0, i);
		String suffix = fileName.substring(i);
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return new StringBuffer().append(prefix).append(uuid).append(suffix).toString();
	}

	private static boolean saveFile(MultipartFile file, FTPClient client) {
		boolean success = false;
		InputStream inStream = null;
		try {
			String fileName = new String(file.getOriginalFilename());

			inStream = file.getInputStream();
			success = client.storeFile(fileName, inStream);
			if (success == true) {
				return success;
			}
		} catch (Exception e) {
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
				}
			}
		}
		return success;
	}

	/**
	 * 删除文件
	 *
	 * @param hostname
	 *            FTP服务器地址
	 * @param port
	 *            FTP服务器端口号
	 * @param username
	 *            FTP登录帐号
	 * @param password
	 *            FTP登录密码
	 * @param filepath
	 *            FTP服务器保存目录
	 * @param filename
	 *            要删除的文件名称
	 * @return
	 */
	public static boolean deleteFile(String hostname, int port, String username, String password, String filepath,
									 String filename) {
		boolean flag = false;
		FTPClient ftpClient = getFTPClient(hostname, username, password, port);
		try {
			// 验证FTP服务器是否登录成功
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				return flag;
			}
			// 切换FTP目录
			if (ftpClient.changeWorkingDirectory(filepath)) {
				ftpClient.deleteFile(filename);
				flag = true;
			}
			ftpClient.logout();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {

				}
			}
		}
		return flag;
	}

	public static boolean downloadZipFile(HttpServletResponse response, HttpServletRequest request, String hostname,
										  int port, String username, String password, List<String> directoriesPaths, List<String> filePaths,
										  List<String> fileNames,String rarName) throws Exception {
		boolean flag = false;
		ZipOutputStream out = null;
		String zipName = rarName + ".zip";
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(zipName, "UTF-8"));
		// 获取到zip输出流
		out = new ZipOutputStream(response.getOutputStream());
		out.setEncoding("GBK");
		FTPClient ftpClient = null;
		// 目录
		if (directoriesPaths != null && directoriesPaths.size() > 0) {
			List<String> fileList = new ArrayList<>();
			for (int i = 0; i < directoriesPaths.size(); i++) {

				String path = directoriesPaths.get(i);
				// 循环必须重新建立连接
				ftpClient = getFTPClient(hostname, username, password, port);
				ftpClient.enterLocalPassiveMode();
				FTPFile[] listFiles = ftpClient.listFiles(path);

				for (int j = 0; j < listFiles.length; j++) {
					String filename = listFiles[j].getName();
					fileList.add(filename);
				}
				for (String fileName : fileList) {
					// 循环必须重新建立连接
					ftpClient = getFTPClient(hostname, username, password, port);
					InputStream is = ftpClient.retrieveFileStream(path + fileName);
					doCompress(is, fileName, out);
					response.flushBuffer();
				}
			}
		}
		int k = 1;
		if (filePaths != null && filePaths.size() > 0) {
			// 重复名+编号
			List<String> list = new ArrayList<>();
			for (int i = 0; i < fileNames.size(); i++) {
				String fileName = fileNames.get(i);
				if (list.contains(fileName)) {
					String[] name = fileName.split("\\.");
					String fileN = name[0] + k + "." + name[1];
					fileNames.set(i, fileN);
					k++;
				} else {
					list.add(fileName);
				}

			}
			for (int i = 0; i < filePaths.size(); i++) {
				String filePathName = filePaths.get(i);
				// 循环必须重新建立连接
				ftpClient = getFTPClient(hostname, username, password, port);
				ftpClient.enterLocalPassiveMode();
				InputStream is = ftpClient.retrieveFileStream(filePathName);
				doCompress(is, fileNames.get(i), out);
				response.flushBuffer();
			}
		}

		if (out != null) {
			out.close();
		}
		return flag;
	}

	private static void doCompress(InputStream fis, String fileName, ZipOutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		out.putNextEntry(new ZipEntry(fileName));
		int len = 0;
		// 读取文件的内容,打包到zip文件
		while ((len = fis.read(buffer)) > 0) {
			out.write(buffer, 0, len);
			out.flush();
		}
		out.closeEntry();
		fis.close();
	}

	/***
	 * 获取指定文件夹下的所有文件名
	 * @param ftpHost
	 * @param ftpUserName
	 * @param ftpPassword
	 * @param ftpPort
	 * @param path
	 * @return
	 */
	public static List<String> getFileList(String ftpHost, String ftpUserName, String ftpPassword, int ftpPort,
										   String path) {
		FTPClient ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
		List<String> fileNameList = new ArrayList<String>();
		try {
			FTPFile[] listFiles = ftpClient.listFiles(path);
			for (FTPFile file : listFiles) {
				if (file.isFile()) {
					fileNameList.add(file.getName());
					System.out.println("----"+file.getName());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileNameList;
	}
}
