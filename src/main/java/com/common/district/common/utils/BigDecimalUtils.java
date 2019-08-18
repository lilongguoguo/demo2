package com.common.district.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;


public class BigDecimalUtils {

    public static final int DEFAULT_SCALE = 16;
    public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;
    public static BigDecimal FINANCE_MONEY_RADIX = new BigDecimal(1000);

    /**
     * 加法运算
     * @param adds 加数
     * @return 和
     */
    public static BigDecimal add(double... adds) {
        if (adds == null || adds.length == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = BigDecimal.ZERO;
        for (double a : adds) {
            result = result.add(BigDecimal.valueOf(a));
        }
        return result;
    }

    /**
     * 减法运算
     * @param subs 第一个为被减数，后面全部为减数
     * @return 差
     */
    public static BigDecimal sub(double... subs) {
        if (subs == null || subs.length == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = BigDecimal.valueOf(subs[0]);
        for (int n = 1, max = subs.length; n < max; n ++) {
            result = result.subtract(BigDecimal.valueOf(subs[n]));
        }
        return result;
    }

    /**
     * 乘积运算
     * @param muls 乘数
     * @return 积
     */
    public static BigDecimal mul(double... muls) {
        if (muls == null || muls.length == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = BigDecimal.valueOf(1);
        for (double a : muls) {
            result = result.multiply(BigDecimal.valueOf(a));
        }
        return result;
    }

    /**
     * 除法运算
     * @param divs 第一个为被除数，后面的为除数
     * @return 商
     */
    public static BigDecimal div(double... divs) {
        if (divs == null || divs.length == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = BigDecimal.valueOf(divs[0]);
        for (int n = 1, max = divs.length; n < max; n ++) {
            result = result.divide(BigDecimal.valueOf(divs[n]), DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
        }
        return result;
    }

    /**
     * 加法运算
     * @param adds 加数
     * @return 和
     */
    public static BigDecimal add(BigDecimal... adds) {
        if (adds == null || adds.length == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = BigDecimal.ZERO;
        for (BigDecimal a : adds) {
            result = result.add(checkAndSetDefault(a));
        }
        return result;
    }

    /**
     * 减法运算
     * @param subs 第一个为被减数，后面全部为减数
     * @return 差
     */
    public static BigDecimal sub(BigDecimal... subs) {
        if (subs == null || subs.length == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = checkAndSetDefault(subs[0]);
        for (int n = 1, max = subs.length; n < max; n ++) {
            result = result.subtract(checkAndSetDefault(subs[n]));
        }
        return result;
    }

    /**
     * 乘积运算
     * @param muls 乘数
     * @return 积
     */
    public static BigDecimal mul(BigDecimal... muls) {
        if (muls == null || muls.length == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = BigDecimal.ONE;
        for (BigDecimal a : muls) {
            result = result.multiply(checkAndSetDefault(a));
        }
        return result;
    }

    /**
     * 除法运算
     * @param divs 第一个为被除数，后面的为除数
     * @return 商
     */
    public static BigDecimal div(BigDecimal... divs) {
        if (divs == null || divs.length == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = divs[0];
        for (int n = 1, max = divs.length; n < max; n ++) {
            result = result.divide(divs[n], DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
        }
        return result;
    }

    /**
     * null类型判断
     * @param b
     * @return
     */
    public static BigDecimal checkAndSetDefault(BigDecimal b) {
        if(b == null) {
            return BigDecimal.ZERO;
        }
        b = b.setScale(2,BigDecimal.ROUND_HALF_UP);
        return b;
    }

    /**
     * 保留小数
     * @param val 原始值
     * @param scale 保留小数位
     * @param roundingMode roundingMode
     * @return 结果
     */
    public static double scale(BigDecimal val, int scale, RoundingMode roundingMode) {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(scale);
        decimalFormat.setRoundingMode(roundingMode);
        return Double.valueOf(decimalFormat.format(val));
    }

    public static BigDecimal min(BigDecimal... mins){
        if (mins == null || mins.length == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = mins[0];
        for (int n = 1, max = mins.length; n < max; n ++) {
            result = result.min(mins[n]);
        }

        return result;
    }
    public static void main(String[] args) {
//        System.out.println(add(Long.MAX_VALUE));
//        System.out.println(Long.MAX_VALUE);
//        System.out.println(BigDecimal.valueOf(Long.MAX_VALUE).longValue());
//        System.out.println(BigDecimal.valueOf((double)Long.MAX_VALUE).longValue());
//        System.out.println(Double.MAX_VALUE > Long.MAX_VALUE);
//        System.out.println(scale(BigDecimal.valueOf(0.888), 2, RoundingMode.FLOOR));
        System.out.println(min(BigDecimal.valueOf(0.683), BigDecimal.valueOf(0.988),BigDecimal.valueOf(0.688)));
/*        System.out.println(scale(div(1, 3), 2, RoundingMode.UP));*/
    }
}
