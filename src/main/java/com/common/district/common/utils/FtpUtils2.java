package com.common.district.common.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Ftp地址：47.97.158.160 fxmftp  Fs20171010!$
 * 上传，带{}为可变，根据需求动态补充，其他为固定
 * 目录：/fxmftp/fsf/{项目}/{年}/{月}/{日}/{文件}
 * 例：/fxmftp/fsf/loupan/2019/07/10/989289282.jpg
 */
public class FtpUtils2 {
    public static final String FILE_SERVER = "http://files.fxmsoft.com";
    public static final String SERVER_PRE = "/data/ftp";
    public static final String FILE_PATH_FSF = "/fsf";
    public static final String FILE_PATH_fXM = "/fxmftp";

    public static final String FILE_PATH = FILE_PATH_fXM + FILE_PATH_FSF;
    /**
     * ftp服务器地址
     **/
    private static String HOST_NAME = "47.97.158.160";
    /**
     * ftp服务器端口号默认为21
     **/
    private static Integer PORT = 21;
    /**
     * ftp登录账号
     **/
    private static String USERNAME = "fxmftp";
    /**
     * ftp登录密码
     **/
    private static String PASSWORD = "Fs20171010!$";

    public FTPClient ftpClient = null;

    public static SimpleDateFormat formatYear;
    public static SimpleDateFormat formatMonth;
    public static SimpleDateFormat formatDay;

    static {
        formatYear = new SimpleDateFormat("yyyy");
        formatMonth = new SimpleDateFormat("MM");
        formatDay = new SimpleDateFormat("dd");
    }

    /**
     * 初始化ftp服务器
     */
    public void initFtpClient() {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        try {
            System.out.println("connecting...ftp服务器:" + HOST_NAME + ":" + PORT);
            ftpClient.connect(HOST_NAME, PORT); //连接ftp服务器
            ftpClient.login(USERNAME, PASSWORD); //登录ftp服务器
            int replyCode = ftpClient.getReplyCode(); //是否成功登录服务器
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("connect failed...ftp服务器:" + HOST_NAME + ":" + PORT);
            }
            System.out.println("connect successfu...ftp服务器:" + HOST_NAME + ":" + PORT);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     *
     * @param pathname       ftp服务保存地址
     * @param fileName       上传到ftp的文件名
     * @param originfilename 待上传文件的名称（绝对地址） *
     * @return
     */
    public boolean uploadFile(String pathname, String fileName, String originfilename) {
        boolean flag = false;
        InputStream inputStream = null;
        try {
            System.out.println("开始上传文件");
            inputStream = new FileInputStream(new File(originfilename));
            initFtpClient();
            ftpClient.sendCommand("OPTS UTF8", "ON");
            ftpClient.enterLocalPassiveMode();
            ftpClient.setControlEncoding("utf-8");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.setFileTransferMode(FTPClient.BINARY_FILE_TYPE);
            ftpClient.setBufferSize(1024*1024);
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            CreateDirecroty(pathname);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.storeFile(fileName, bis);
            inputStream.close();
            ftpClient.logout();
            flag = true;
            System.out.println("上传文件成功");
        } catch (Exception e) {
            System.out.println("上传文件失败");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * 上传文件
     *
     * @param pathname    ftp服务保存地址
     * @param fileName    上传到ftp的文件名
     * @param inputStream 输入文件流
     * @return
     */
    public boolean uploadFile(String pathname, String fileName, InputStream inputStream) {
        boolean flag = false;
        try {
            System.out.println("开始上传文件");
            initFtpClient();
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            CreateDirecroty(pathname);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
            System.out.println("上传文件成功");
        } catch (Exception e) {
            System.out.println("上传文件失败");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    //改变目录路径
    public boolean changeWorkingDirectory(String directory) {
        boolean flag = true;
        try {
            flag = ftpClient.changeWorkingDirectory(directory);
            if (flag) {
                System.out.println("进入文件夹" + directory + " 成功！");

            } else {
                System.out.println("进入文件夹" + directory + " 失败！开始创建文件夹");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return flag;
    }

    //创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
    public boolean CreateDirecroty(String remote) throws IOException {
        boolean success = true;
        String directory = remote + "/";
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory))) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            String path = "";
            String paths = "";
            while (true) {
                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                path = path + "/" + subDirectory;
                if (!existFile(path)) {
                    if (makeDirectory(subDirectory)) {
                        changeWorkingDirectory(subDirectory);
                    } else {
                        System.out.println("创建目录[" + subDirectory + "]失败");
                        changeWorkingDirectory(subDirectory);
                    }
                } else {
                    changeWorkingDirectory(subDirectory);
                }

                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

    //判断ftp服务器文件是否存在
    public boolean existFile(String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }

    //创建目录
    public boolean makeDirectory(String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                System.out.println("创建文件夹" + dir + " 成功！");

            } else {
                System.out.println("创建文件夹" + dir + " 失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 下载文件 *
     *
     * @param pathname  FTP服务器文件目录 *
     * @param filename  文件名称 *
     * @param localpath 下载后的文件路径 *
     * @return
     */
    public boolean downloadFile(String pathname, String filename, String localpath) {
        boolean flag = false;
        OutputStream os = null;
        try {
            System.out.println("开始下载文件");
            initFtpClient();
            ftpClient.enterLocalPassiveMode();
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for (FTPFile file : ftpFiles) {
                if (filename.equalsIgnoreCase(file.getName())) {
                    File localFile = new File(localpath + "/" + file.getName());
                    os = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(file.getName(), os);
                    os.close();
                }
            }
            ftpClient.logout();
            flag = true;
            System.out.println("下载文件成功");
        } catch (Exception e) {
            System.out.println("下载文件失败");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    public boolean downloadFile(String pathname, String filename, HttpServletResponse response) {
        boolean flag = false;
        try {
            System.out.println("开始下载文件");
            initFtpClient();
            ftpClient.enterLocalPassiveMode();
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for (FTPFile file : ftpFiles) {
                if (filename.equalsIgnoreCase(file.getName())) {
                    ftpClient.retrieveFile(file.getName(), response.getOutputStream());
                }
            }
            ftpClient.logout();
            flag = true;
            System.out.println("下载文件成功");
        } catch (Exception e) {
            System.out.println("下载文件失败");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 删除文件 *
     *
     * @param pathname FTP服务器保存目录 *
     * @param filename 要删除的文件名称 *
     * @return
     */
    public boolean deleteFile(String pathname, String filename) {
        boolean flag = false;
        try {
            System.out.println("开始删除文件");
            initFtpClient();
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.dele(filename);
            ftpClient.logout();
            flag = true;
            System.out.println("删除文件成功");
        } catch (Exception e) {
            System.out.println("删除文件失败");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /*public static void main(String[] args) {
        FtpUtils2 ftp = new FtpUtils2();
        try {
            ftp.initFtpClient();
            //ftp.loginFtpClient();
            //boolean b = ftp.CreateDirecroty("/fxmftp/fsf/puberp/position");
            boolean b = ftp.uploadFile("/fxmftp/fsf/puberp/position", "错误数据.txt", "C:/Users/pc/Downloads/错误数据.txt");
            //ftp.downloadFile("/fxmftp/fsf/puberp/position","错误数据.txt","C:/Users/pc/Downloads");
            //http://files.fxmsoft.com/fsf/puberp/position/123.txt
            //System.out.println(b);
            //ftp.deleteFile("/fxmftp/fsf/puberp/position","123.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //ftp.uploadFile("ftpFile/data", "123.docx", "E://123.docx");
//        ftp.downloadFile("ftpFile/data", "123.docx", "F://");
        *//*try {
            Date date = new Date();
            String savePath = "/gongqv/"
                    + FtpUtils2.formatYear.format(date) + "/"
                    + FtpUtils2.formatMonth.format(date) + "/"
                    + FtpUtils2.formatDay.format(date);
            String upPath = FILE_PATH + savePath;
            ftp.uploadFile(upPath, "树荫图.jpg", new FileInputStream(new File("C://Users/admin/Pictures/树荫图.jpg")));
        }catch (Exception e){
            e.printStackTrace();
        }*//*
//        String path="http://files.fxmsoft.com/fsf/gongqv/2019/07/18/1563443069736中单.jpg";
//        String finalPath=SERVER_PRE+FtpUtils.FILE_PATH_fXM+"/"+path.substring(path.indexOf(FILE_SERVER)+FILE_SERVER.length()+1,path.lastIndexOf("/"));
//        String serverFileName=path.substring(path.lastIndexOf("/")+1);
//        ftp.downloadFile(finalPath, serverFileName,"F://龙湖/sql");
//        System.out.println("ok");
    }*/
}
