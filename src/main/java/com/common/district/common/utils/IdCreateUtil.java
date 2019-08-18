package com.common.district.common.utils;

import com.common.district.common.constants.ConstantsEnum;
import org.apache.tools.ant.taskdefs.Sleep;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class IdCreateUtil {
    private static SimpleDateFormat format;
    static{
        format=new SimpleDateFormat("yyyyMMddHHmmssS");
    }
    public static String create(ConstantsEnum.IdCreatePreEnum pre){
        synchronized (IdCreateUtil.class){
            try{Thread.sleep(1L);}catch (Exception e){e.printStackTrace();}
            return pre.getPre()+format.format(new Date(System.currentTimeMillis()))+new Random().nextInt(10);
        }
    }
    public static void main(String[] args) {
        for(int i=0;i<1000;i++) {
            new Thread() {
                @Override
                public void run() {
                    System.out.println(IdCreateUtil.create(ConstantsEnum.IdCreatePreEnum.CONTRACT));
                }
            }.start();
        }
    }
}
