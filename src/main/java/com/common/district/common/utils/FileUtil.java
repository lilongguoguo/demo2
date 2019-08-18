package com.common.district.common.utils;

import java.io.File;
import java.io.IOException;

/**
 * 文件和文件夹相关类
 * @author liuy
 *
 */
public class FileUtil {

	// 判断文件是否存在
	public static void judeFileExists(File file) {

	    if (file.exists()) {
	        System.out.println("file exists");
	    } else {
	        System.out.println("file not exists, create it ...");
	        try {
	            file.createNewFile();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }

	}
	// 判断文件夹是否存在
	public static void judeDirExists(File file) {

	    if (file.exists()) {
	        if (file.isDirectory()) {
	            System.out.println("dir exists");
	        } else {
	            System.out.println("the same name file exists, can not create dir");
	        }
	    } else {
	        System.out.println("dir not exists, create it ...");
	        file.mkdir();
	    }

	}
}
