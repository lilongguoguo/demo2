package com.common.district.common.utils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Map操作工具类
 * @author chenhuan
 * @createDate 2017年6月22日 下午7:46:14
 * @version 1.0
 */
public class MapUtil {
	/**
	 * 
	 * @Description: Map按value排序
	 * @param map
	 * @return Map<K,V>
	 * @author chenhuan
	 * @date 2017年6月22日 下午7:47:47
	 */
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
	/**
	 * 对象转map
	 * 
	 * @param obj
	 * @return Map<String, Object> 
	 * @throws 
	 * @author 任建峰
	 *	       2018年3月5日下午4:37:21
	 */
	public static Map<String, Object> obj2Map(Object obj) {  
		  
        Map<String, Object> map = new HashMap<String, Object>();  
        // System.out.println(obj.getClass());  
        // 获取f对象对应类中的所有属性域  
        Field[] fields = obj.getClass().getDeclaredFields();  
        for (int i = 0, len = fields.length; i < len; i++) {  
            String varName = fields[i].getName();  
            //varName = varName.toLowerCase();//将key置为小写，默认为对象的属性
            try {  
                // 获取原来的访问控制权限  
                boolean accessFlag = fields[i].isAccessible();  
                // 修改访问控制权限  
                fields[i].setAccessible(true);  
                // 获取在对象f中属性fields[i]对应的对象中的变量  
                Object o = fields[i].get(obj);  
                if (o != null)  
                    map.put(varName, o.toString());  
                //System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);  
                // 恢复访问控制权限  
                fields[i].setAccessible(accessFlag);  
            } catch (IllegalArgumentException ex) {  
                ex.printStackTrace();  
            } catch (IllegalAccessException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return map;  
    }  
}
