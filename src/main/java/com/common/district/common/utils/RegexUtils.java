package com.common.district.common.utils;

import java.util.regex.Pattern;

/**
 * @author zxx
 * @date 2017/10/26.
 */
public class RegexUtils {
    private static final Pattern number = Pattern.compile("^\\d+(\\.\\d+)?$");
    private static final Pattern zero = Pattern.compile("^0+(\\.0+)?$");

    private static final Pattern positiveInteger = Pattern.compile("^[1-9]\\d*$");

    /**
     * 判断字符串是否为非负浮点数，包括整数和小数
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        return number.matcher(str).matches();
    }

    public static boolean isZero(String str){
        return zero.matcher(str).matches();
    }

    public static boolean isPositiveInteger(String str){
        return positiveInteger.matcher(str).matches();
    }




    public static void main(String[] args) {
        System.out.println( isNumber("00000.000000"));
        System.out.println( isNumber("0"));
        System.out.println( isNumber("10.1"));
        System.out.println( isNumber("10"));
        System.out.println( isNumber("10a"));


    }
}
