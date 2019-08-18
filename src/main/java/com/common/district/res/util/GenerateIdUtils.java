package com.common.district.res.util;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @Author: tanwei
 * @Description:
 * @Date: Create in 19:10 2019/7/29
 */
public class GenerateIdUtils {
    public static String getId(String prefix){
        StringBuilder stringBuilder =new StringBuilder();
        Random random =new Random();

        String i = random.nextInt(10)+"";

        Calendar Cld = Calendar.getInstance();
        String YY = Cld.get(Calendar.YEAR)+"";//年
        String MM = Cld.get(Calendar.MONTH)<9 ? "0"+(Cld.get(Calendar.MONTH)+1) : (Cld.get(Calendar.MONTH)+1)+"";//月
        String DD = Cld.get(Calendar.DATE)+"";//日
        String HH = Cld.get(Calendar.HOUR_OF_DAY)+"";//时
        String mm = Cld.get(Calendar.MINUTE)+"";//分
        String SS = Cld.get(Calendar.SECOND)+"";//秒
        String MI = Cld.get(Calendar.MILLISECOND)+"";//毫秒
        stringBuilder.append(prefix)
                .append(YY)
                .append(MM)
                .append(DD)
                .append(HH)
                .append(mm)
                .append(SS)
                .append(MI)
                .append(i);
        return stringBuilder.toString();
    }

    /*public static void main(String[] args) {
        String zylx = getId("ZYLX");
        System.out.println(zylx);
    }*/
}
