package com.common.district.res.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tanwei
 * @Description:处理业态映射
 * @Date: Create in 11:45 2019/5/31
 */
public enum ImportLogNoEnum {
    INSTANCE;
    private Map<Integer, String> map = new HashMap<>();
    private ImportLogNoEnum() {
        map.put(0, "A");
        map.put(1, "B");
        map.put(2, "C");
        map.put(3, "D");
        map.put(4, "E");
        map.put(5, "F");
        map.put(6, "G");
        map.put(7, "H");
        map.put(8, "I");
        map.put(9, "J");
        map.put(10, "K");
        map.put(11, "L");
        map.put(12, "M");
        map.put(13, "N");
        map.put(14, "O");
        map.put(15, "P");
        map.put(16, "Q");
        map.put(17, "R");
        map.put(18, "S");
        map.put(19, "T");
        map.put(20, "U");
        map.put(21, "V");
        map.put(22, "W");
        map.put(23, "X");
        map.put(24, "Y");
        map.put(25, "Z");
    }

    public String getLogNo(Integer count) throws NullPointerException {
        int num=count+10000;

        int shang=num/90000;
        int yu=num%90000;

        if(yu<10000){
            String s= yu+"";
            String s1 = autoGenericCode(s, 4);
            return map.get(shang)+"1"+s1;
        }else{
            String s= yu+"";
            String s1 = autoGenericCode(s, 5);
            return map.get(shang)+s1;
        }
    }
    /**
     * 不够位数的在前面补0，保留num的长度位数字
     * @param code
     * @return
     */
    private String autoGenericCode(String code, int num) {
        String result = "";
        result = String.format("%0" + num + "d", Integer.parseInt(code) + 1);
        return result;
    }

}
