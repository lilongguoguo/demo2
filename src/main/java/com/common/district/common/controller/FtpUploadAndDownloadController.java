package com.common.district.common.controller;

import com.common.district.common.RespData;
import com.common.district.common.utils.FtpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ftp文件上传
 * @author fc
 */
@Controller
@RequestMapping("file")
public class FtpUploadAndDownloadController {
    public static final Lock lock=new ReentrantLock();
    @RequestMapping("fileUpload")
    @ResponseBody
    public RespData fileUpload(HttpServletRequest request, @RequestParam("file") CommonsMultipartFile[] files)throws Exception {
        lock.lock();
        RespData jr = new RespData();
        List<Map> upFiles = new ArrayList<>();
        Date date = new Date();
        String savePath="/gongqv/"
                + FtpUtils.formatYear.format(date)+"/"
                +FtpUtils.formatMonth.format(date)+"/"
                +FtpUtils.formatDay.format(date);
        String path= FtpUtils.FILE_PATH+savePath;
        String downPath=FtpUtils.FILE_PATH_FSF+savePath;
        FtpUtils ftpUtils=new FtpUtils();
        if(files != null && files.length > 0) {
            for(CommonsMultipartFile upfile : files) {
                String originalFilename = upfile.getOriginalFilename();
                String fileType = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

                String newFileName=System.currentTimeMillis()+"."+fileType;
                boolean isSuccess = ftpUtils.uploadFile(path, newFileName, upfile.getInputStream());
                if(!isSuccess){
                    jr.setCode(RespData.FAIL_CODE);
                    jr.setMessage("上传异常");
                }
                Map<String,Object> map=new ConcurrentHashMap<>();
                map.put("path",FtpUtils.FILE_SERVER+downPath+"/"+newFileName);
                map.put("newName",newFileName);
                map.put("type",fileType);
                map.put("size",convertFileSize(upfile.getSize()));
                map.put("oriName",originalFilename);
                map.put("ipdloadTime",new Date());
                upFiles.add(map);
            }
        }
        lock.unlock();
        jr.setData(upFiles);
        jr.setCode(RespData.SUCCESS_CODE);
        jr.setMessage("上传成功");
        return jr;
    }
    @RequestMapping("down")
    @ResponseBody
    public void fileDownList(HttpServletRequest request,
                             HttpServletResponse response,
                             String path)throws Exception {
        String finalPath=FtpUtils.SERVER_PRE+FtpUtils.FILE_PATH_fXM+"/"+path.substring(path.indexOf(FtpUtils.FILE_SERVER)+FtpUtils.FILE_SERVER.length()+1,path.lastIndexOf("/"));
        String serverFileName=path.substring(path.lastIndexOf("/")+1);
        FtpUtils ftp=new FtpUtils();
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(serverFileName, "UTF-8"));
        response.setHeader("Cache-Control", "no-cache");
        ftp.downloadFile(finalPath, serverFileName,response);


    }

    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * 文件大小转换成可显示的Mb,Gb和kb方法
     * @param size 文件大小
     * @return 转换后的大小
     */
    public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else {
            return String.format("%d B", size);
        }
    }
}
