package com.common.district.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* @Description: 字符串帮助类
* 
* @Author:z.l
* @Date: 2018/1/11 11:42
*
*/
public class StringToolsUtil {

    private static final Logger logger = LoggerFactory.getLogger(StringToolsUtil.class);

    /**
     * 对传入的字符串进行处理之后返回List
     * @param param 传入的字符串
     * @param splitParam 按照什么字符串分隔,不传则认为是","
     */
    public static<T> List<T> strToList(String param, String... splitParam){
        List<T> resultList = null;
        if(StringUtils.isEmpty(param)){
            logger.error("传入的字符串为空");
            return resultList;
        }
        resultList = new ArrayList<T>();
        if(splitParam.length < 1){
            resultList = (List<T>) Arrays.asList(param.split(","));
        }else{
            resultList = (List<T>) Arrays.asList(param.split(splitParam[0]));
        }
        return resultList;
    }

    /**
     * 判断字符串是否全部为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * 生成 7位 SAAS租户号段编码
     *
     * @param sSrc
     * @return
     */
    public static String saasCodeGenerateForSeven(String sSrc) throws Exception {
        // 默认七位
        int defaultLenth = 7;
        String iReturn = "" ;
        String defaultStr = "0000000";
        if(sSrc == null){
            return iReturn;
        }
        if (sSrc.length() > defaultLenth){
            return iReturn;
        }else if (sSrc.length() == defaultLenth){
            iReturn = sSrc;
        }else if(sSrc.length() < defaultLenth){
            iReturn = defaultStr.substring(0,(defaultLenth - sSrc.length())) + sSrc;
        }
        return iReturn;
    }

}
