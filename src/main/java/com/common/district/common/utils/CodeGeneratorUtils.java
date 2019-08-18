package com.common.district.common.utils;


import java.util.Date;

public class CodeGeneratorUtils {

    private static final java.text.DecimalFormat format = new java.text.DecimalFormat("0000");
    private static final java.text.DecimalFormat format2 = new java.text.DecimalFormat("00000");


	/**
	 * String str = CodeGeneratorUtils.getNextNumber(jedisPool,key);
     * str >> "00001"
	 * @description:根据键获取下一编号
	 * key:键
	 */
	public static String getNextNumber(String key) {
        Long serialNumber = RedisUtil.incr(key);
        if(serialNumber == null){
            throw new RuntimeException("生成编码失败，incr 操作失败，key："+key);
        }
        return format2.format(serialNumber);
	}

    /**
     * 获取合同编号
     * @param key
     * @return
     */
    public static String getContractCode(String key) {
        String currentDateStr = DateUtil.formatDate(new Date(),"yyyyMMdd");
        String contractKey = key.concat(currentDateStr);
        //如果是新增的key，则设置过期时间为一天
        Long contractSerialNumber = RedisUtil.increx(key, 24 * 60 * 60);
        if(contractSerialNumber == null){
            throw new RuntimeException("生成合同编码，increx 操作失败，key："+contractKey);
        }
        return contractKey.concat(format2.format(contractSerialNumber));
    }

}
