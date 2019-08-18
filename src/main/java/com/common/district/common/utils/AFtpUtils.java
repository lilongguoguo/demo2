package com.common.district.common.utils;

import com.common.district.common.FtpConfig;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * @Author: tanwei
 * @Description:
 * @Date: Create in 13:50 2019/8/3
 */
public class AFtpUtils {
    public static FTPClient ftpClient = null;

    /**
     * 初始化ftp服务器
     */
    public static void initFtpClient() {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        try {
            FtpConfig config =new FtpConfig();

            String ftphostname = config.getFtphostname();
            String ftpport = config.getFtpport();
            String ftpusername = config.getFtpusername();
            String ftppassword = config.getFtppassword();
            System.out.println("connecting...ftp服务器:" + ftphostname + ":" + ftpport);
            ftpClient.connect(ftphostname, Integer.valueOf(ftpport)); //连接ftp服务器
            ftpClient.login(ftpusername, ftppassword); //登录ftp服务器
            int replyCode = ftpClient.getReplyCode(); //是否成功登录服务器
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("connect failed...ftp服务器:" + ftphostname + ":" + ftpport);
            }
            System.out.println("connect successfu...ftp服务器:" + ftphostname + ":" + ftpport);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @退出关闭服务器链接
     */
    public static void ftpLogOut() {
        if (null != ftpClient && ftpClient.isConnected()) {
            try {
                boolean reuslt = ftpClient.logout();// 退出FTP服务器
                if (reuslt) {
                    System.out.println("成功退出服务器");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("退出FTP服务器异常！" + e.getMessage());
            } finally {
                try {
                    ftpClient.disconnect();// 关闭FTP服务器的连接
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("关闭FTP服务器的连接异常！");
                }
            }
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
    public static boolean uploadFile(String pathname, String fileName, String originfilename) {
        boolean flag = false;
        InputStream inputStream = null;
        try {
            System.out.println("开始上传文件");
            inputStream = new FileInputStream(new File(originfilename));
            initFtpClient();
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            createDirecroty(pathname);
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

    /**
     * 上传文件
     *
     * @param pathname    ftp服务保存地址
     * @param fileName    上传到ftp的文件名
     * @param inputStream 输入文件流
     * @return
     */
    public static boolean uploadFile(String pathname, String fileName, InputStream inputStream) {
        boolean flag = false;
        try {
            System.out.println("开始上传文件");
            initFtpClient();
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            createDirecroty(pathname);
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

    //创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
    public static boolean createDirecroty(String remote) throws IOException {
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

    //创建目录
    public static boolean makeDirectory(String dir) {
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

    //判断ftp服务器文件是否存在
    public static boolean existFile(String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }
    //改变目录路径
    public static boolean changeWorkingDirectory(String directory) {
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

    /**
     * 删除文件 *
     *
     * @param pathname FTP服务器保存目录 *
     * @param filename 要删除的文件名称 *
     * @return
     */
    public static boolean deleteFile(String pathname, String filename) {
        boolean flag = false;
        try {
            System.out.println("开始删除文件");
            initFtpClient();
            //切换FTP目录
            boolean b = ftpClient.changeWorkingDirectory(pathname);
            int dele = ftpClient.dele(filename);
            ftpClient.logout();
            flag = true;
            System.out.println("删除文件成功"+b+" "+dele);
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

    public static String uploadPosition(String fileName,String filePath){
        FtpConfig ftpConfig =new FtpConfig();
        String filedir = ftpConfig.getFiledir();
        String filepath = ftpConfig.getFilepath();
        String positiondir = ftpConfig.getPositiondir();
        String pathname=filedir+filepath+positiondir;
        String filedowndir = ftpConfig.getFiledowndir();
        String httpUrl=filedowndir+filepath+positiondir+"/"+fileName;

        AFtpUtils.uploadFile(pathname, fileName, filePath);

        return httpUrl;
    }

    /*public static void main(String[] args) {
        FtpConfig ftpConfig =new FtpConfig();
        String filedir = ftpConfig.getFiledir();
        String filepath = ftpConfig.getFilepath();
        String positiondir = ftpConfig.getPositiondir();
        String pathname=filedir+filepath+positiondir;
        String filedowndir = ftpConfig.getFiledowndir();
        String filename="345.txt";
        String httpUrl=filedowndir+filepath+positiondir+"/"+filename;
        //AFtpUtils.uploadFile(pathname, filename, "C:/Users/pc/Downloads/123.txt");
        AFtpUtils.deleteFile(pathname,filename);
        System.out.println(httpUrl);
    }*/
}
