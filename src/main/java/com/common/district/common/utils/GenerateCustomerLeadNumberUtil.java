package com.common.district.common.utils;

import java.text.DecimalFormat;

/**
 * @方法描述： 生成带看编号工具类
 * @创建人：任建峰
 * @创建时间：2018年4月25日 上午10:34:52
 */
public class GenerateCustomerLeadNumberUtil {
	private static final String REGULAR = "^(0+)";
	private static final String RENT_ZD = "ZD";
	private static final String BUY_MD = "MD";
	private static final String DEFAULTVALU = "";
	private static final String INITVALE0 = "0000000";
	private static final String INITVALE = "0000001";

	/**
     * @方法描述： 生成带看编号
     * @创建人：任建峰
     * @创建时间：2018年4月25日 上午10:34:52
     */
    public static  String getLeadNumber(Integer buyRent,String leadNumber ) {
        // 序列号
        String serialNumber = DEFAULTVALU;
        if (buyRent == 1) {
            if (StringUtils.isBlank(leadNumber)) {
                serialNumber = INITVALE;
            } else {
                String CHAR_FORMAT = INITVALE0;
                String maxSerialNO = leadNumber.substring(BUY_MD.length()).replaceAll(REGULAR, DEFAULTVALU);
                Integer maxIntSerialID = Integer.parseInt(maxSerialNO);
                maxIntSerialID++;
                DecimalFormat df = new DecimalFormat(CHAR_FORMAT);
                serialNumber = df.format(maxIntSerialID);
            }
            return StringUtils.merge(BUY_MD, serialNumber);
        } else {
            if (StringUtils.isBlank(leadNumber)) {
                serialNumber = INITVALE;
            } else {
                String CHAR_FORMAT = INITVALE0;
                String maxSerialNO = leadNumber.substring(RENT_ZD.length()).replaceAll(REGULAR, DEFAULTVALU);
                Integer maxIntSerialID = Integer.parseInt(maxSerialNO);
                maxIntSerialID++;
                DecimalFormat df = new DecimalFormat(CHAR_FORMAT);
                serialNumber = df.format(maxIntSerialID);
            }
            return StringUtils.merge(RENT_ZD, serialNumber);
        }
    }
    public static void main(String[] args) {
    	getLeadNumber(1,"MD0000003");
	}
}
