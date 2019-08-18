package com.common.district.common.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

import sun.misc.BASE64Encoder;
/**
 * Token生成工具
 * @author XIHONGLEI
 * @date 2019-08-13
 */
public class TokenUtil {
    public static String Create(){
        String token = UUID.randomUUID().toString().replaceAll("-", "") + "";
        // 数据指纹 128位长 16个字节 md5
        try{
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] = md.digest(token.getBytes());
            // base64编码--任意二进制编码明文字符
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
}
