package com.common.district.common.utils;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 配置文件工具类
 * Created by ShiJiaLei on 2018/9/10.
 */
public class PropertyUtil {

    private static Map<String, FileProperties> propertiesMap = Maps.newHashMap();
    
    public static String getAttribute(String propertyName) {
        return getAttribute(propertyName, "common.properties");
    }

    public static String getAttribute(String propertyName, String propertyFile) {
        return getAttribute(propertyName, propertyFile, "");
    }

    /**
      * 获取配置文件属性的值
      *
      * Author: ShiJiaLei
      * Date: 2018/9/10
      * Params: [propertyName, propertyFile, defaultValue]
      * Return: java.lang.String
      */
    public static String getAttribute(String propertyName, String propertyFile, String defaultValue) {
        try {
            if (propertiesMap.containsKey(propertyFile)) {
                return propertiesMap.get(propertyFile).getProperty(propertyName);
            } else {
                FileProperties fileProperties = new FileProperties("/" + propertyFile);
                propertiesMap.put(propertyFile, fileProperties);
                return fileProperties.getProperty(propertyName);
            }
        } catch (Exception e) {
        }
        return defaultValue;
    }

}
