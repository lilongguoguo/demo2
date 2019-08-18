package com.common.district.common.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具 主要对 StringUtils 的一些方法进行重写,达到更方便的使用
 */
@SuppressWarnings("restriction")
public class StringUtils extends org.apache.commons.lang.StringUtils {

	/**
	 * 一次性判断多个或单个对象为空。
	 *
	 * @param objects
	 * @return 只要有一个元素为Blank，则返回true
	 */
	public static boolean isBlank(Object... objects) {
		Boolean result = false;
		for (Object object : objects) {
			if (null == object || "".equals(object.toString().trim()) || "null".equals(object.toString().trim())) {
				result = true;
				break;
			}
		}
		return result;
	}

	public static String getRandom(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			// 输出字母还是数字
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 字符串
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 取得大写字母还是小写字母
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val.toLowerCase();
	}

	/**
	 * 一次性判断多个或单个对象不为空。
	 *
	 * @param objects
	 * @author zhou-baicheng
	 * @return 只要有一个元素不为Blank，则返回true
	 */
	public static boolean isNotBlank(Object... objects) {
		return !isBlank(objects);
	}

	public static boolean isBlank(String... objects) {
		Object[] object = objects;
		return isBlank(object);
	}

	public static boolean isNotBlank(String... objects) {
		Object[] object = objects;
		return !isBlank(object);
	}

	public static boolean isBlank(String str) {
		Object object = str;
		return isBlank(object);
	}

	public static boolean isNotBlank(String str) {
		Object object = str;
		return !isBlank(object);
	}

	/**
	 * 判断一个字符串在数组中存在几个
	 *
	 * @param baseStr
	 * @param strings
	 * @return
	 */
	public static int indexOf(String baseStr, String[] strings) {

		if (null == baseStr || baseStr.length() == 0 || null == strings)
			return 0;

		int i = 0;
		for (String string : strings) {
			boolean result = baseStr.equals(string);
			i = result ? ++i : i;
		}
		return i;
	}

	/**
	 * 判断一个字符串是否为JSONObject,是返回JSONObject,不是返回null
	 *
	 * @param args
	 * @return
	 */
	public static JSONObject isJSONObject(String args) {
		JSONObject result = null;
		if (isBlank(args)) {
			return result;
		}
		try {
			return JSONObject.fromObject(args.trim());
		} catch (Exception e) {
			return result;
		}
	}

	/**
	 * 判断一个字符串是否为JSONArray,是返回JSONArray,不是返回null
	 *
	 * @param args
	 * @return
	 */
	public static JSONArray isJSONArray(Object args) {
		JSONArray result = new JSONArray();
		if (isBlank(args)) {
			return null;
		}
		if (args instanceof JSONArray) {

			JSONArray arr = (JSONArray) args;
			for (Object json : arr) {
				if (json != null && json instanceof JSONObject) {
					result.add(json);
					continue;
				} else {
					result.add(JSONObject.fromObject(json));
				}
			}
			return result;
		} else {
			return null;
		}

	}

	public static String trimToEmpty(Object str) {
		return (isBlank(str) ? "" : str.toString().trim());
	}

	/**
	 * 将 Strig 进行 BASE64 编码
	 *
	 * @param str
	 *            [要编码的字符串]
	 * @param bf
	 *            [true|false,true:去掉结尾补充的'=',false:不做处理]
	 * @return
	 */
	public static String getBASE64(String str, boolean... bf) {
		if (StringUtils.isBlank(str))
			return null;
		String base64 = new sun.misc.BASE64Encoder().encode(str.getBytes());
		// 去掉 '='
		if (isBlank(bf) && bf[0]) {
			base64 = base64.replaceAll("=", "");
		}
		return base64;
	}

	/** 将 BASE64 编码的字符串 s 进行解码 **/
	public static String getStrByBASE64(String s) {
		if (isBlank(s))
			return "";
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 把Map转换成get请求参数类型,如 {"name"=20,"age"=30} 转换后变成 name=20&age=30
	 *
	 * @param map
	 * @return
	 */
	public static String mapToGet(Map<? extends Object, ? extends Object> map) {
		String result = "";
		if (map == null || map.size() == 0) {
			return result;
		}
		Set<? extends Object> keys = map.keySet();
		for (Object key : keys) {
			result += ((String) key + "=" + (String) map.get(key) + "&");
		}

		return isBlank(result) ? result : result.substring(0, result.length() - 1);
	}

	/**
	 * 把一串参数字符串,转换成Map 如"?a=3&b=4" 转换为Map{a=3,b=4}
	 *
	 * @param args
	 * @return
	 */
	public static Map<String, ? extends Object> getToMap(String args) {
		if (isBlank(args)) {
			return null;
		}
		args = args.trim();
		// 如果是?开头,把?去掉
		if (args.startsWith("?")) {
			args = args.substring(1, args.length());
		}
		String[] argsArray = args.split("&");

		Map<String, Object> result = new HashMap<String, Object>();
		for (String ag : argsArray) {
			if (!isBlank(ag) && ag.indexOf("=") > 0) {

				String[] keyValue = ag.split("=");
				// 如果value或者key值里包含 "="号,以第一个"="号为主 ,如 name=0=3
				// 转换后,{"name":"0=3"}, 如果不满足需求,请勿修改,自行解决.

				String key = keyValue[0];
				String value = "";
				for (int i = 1; i < keyValue.length; i++) {
					value += keyValue[i] + "=";
				}
				value = value.length() > 0 ? value.substring(0, value.length() - 1) : value;
				result.put(key, value);
			}
		}

		return result;
	}

	/**
	 * 转换成Unicode
	 *
	 * @param str
	 * @return
	 */
	public static String toUnicode(String str) {
		String as[] = new String[str.length()];
		String s1 = "";
		for (int i = 0; i < str.length(); i++) {
			int v = str.charAt(i);
			if (v >= 19968 && v <= 171941) {
				as[i] = Integer.toHexString(str.charAt(i) & 0xffff);
				s1 = s1 + "\\u" + as[i];
			} else {
				s1 = s1 + str.charAt(i);
			}
		}
		return s1;
	}

	/**
	 * 合并数据
	 *
	 * @param v
	 * @return
	 */
	public static String merge(Object... v) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < v.length; i++) {
			sb.append(v[i]);
		}
		return sb.toString();
	}

	/**
	 * 字符串转urlcode
	 *
	 * @param value
	 * @return
	 */
	public static String strToUrlcode(String value) {
		try {
			value = java.net.URLEncoder.encode(value, "utf-8");
			return value;
		} catch (UnsupportedEncodingException e) {
			LoggerUtils.error(StringUtils.class, "字符串转换为URLCode失败,value:" + value, e);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * urlcode转字符串
	 *
	 * @param value
	 * @return
	 */
	public static String urlcodeToStr(String value) {
		try {
			value = java.net.URLDecoder.decode(value, "utf-8");
			return value;
		} catch (UnsupportedEncodingException e) {
			LoggerUtils.error(StringUtils.class, "URLCode转换为字符串失败;value:" + value, e);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 判断字符串是否包含汉字
	 *
	 * @param txt
	 * @return
	 */
	public static Boolean containsCN(String txt) {
		if (isBlank(txt)) {
			return false;
		}
		for (int i = 0; i < txt.length(); i++) {

			String bb = txt.substring(i, i + 1);

			boolean cc = Pattern.matches("[\u4E00-\u9FA5]", bb);
			if (cc)
				return cc;
		}
		return false;
	}

	/**
	 * 去掉HTML代码
	 *
	 * @param news
	 * @return
	 */
	public static String removeHtml(String news) {
		String s = news.replaceAll("amp;", "").replaceAll("<", "<").replaceAll(">", ">");

		Pattern pattern = Pattern.compile("<(span)?\\sstyle.*?style>|(span)?\\sstyle=.*?>", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(s);
		String str = matcher.replaceAll("");

		Pattern pattern2 = Pattern.compile("(<[^>]+>)", Pattern.DOTALL);
		Matcher matcher2 = pattern2.matcher(str);
		String strhttp = matcher2.replaceAll(" ");

		String regEx = "(((http|https|ftp)(\\s)*((\\:)|：))(\\s)*(//|//)(\\s)*)?" + "([\\sa-zA-Z0-9(\\.|．)(\\s)*\\-]+((\\:)|(:)[\\sa-zA-Z0-9(\\.|．)&%\\$\\-]+)*@(\\s)*)?" + "("
				+ "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])" + "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)"
				+ "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)" + "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])"
				+ "|([\\sa-zA-Z0-9\\-]+(\\.|．)(\\s)*)*[\\sa-zA-Z0-9\\-]+(\\.|．)(\\s)*[\\sa-zA-Z]*" + ")" + "((\\s)*(\\:)|(：)(\\s)*[0-9]+)?"
				+ "(/(\\s)*[^/][\\sa-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*";
		Pattern p1 = Pattern.compile(regEx, Pattern.DOTALL);
		Matcher matchhttp = p1.matcher(strhttp);
		String strnew = matchhttp.replaceAll("").replaceAll("(if[\\s]*\\(|else|elseif[\\s]*\\().*?;", " ");

		Pattern patterncomma = Pattern.compile("(&[^;]+;)", Pattern.DOTALL);
		Matcher matchercomma = patterncomma.matcher(strnew);
		String strout = matchercomma.replaceAll(" ");
		String answer = strout.replaceAll("[\\pP‘’“”]", " ").replaceAll("\r", " ").replaceAll("\n", " ").replaceAll("\\s", " ").replaceAll("　", "");

		return answer;
	}

	/**
	 * 把数组的空数据去掉
	 *
	 * @param array
	 * @return
	 */
	public static List<String> array2Empty(String[] array) {
		List<String> list = new ArrayList<String>();
		for (String string : array) {
			if (StringUtils.isNotBlank(string)) {
				list.add(string);
			}
		}
		return list;
	}

	/**
	 * 把数组转换成set
	 *
	 * @param array
	 * @return
	 */
	public static Set<?> array2Set(Object[] array) {
		Set<Object> set = new TreeSet<Object>();
		for (Object id : array) {
			if (null != id) {
				set.add(id);
			}
		}
		return set;
	}

	/**
	 * serializable toString
	 * @param serializable
	 * @return
	 */
	public static String toString(Serializable serializable) {
		if (null == serializable) {
			return null;
		}
		try {
			return (String) serializable;
		} catch (Exception e) {
			return serializable.toString();
		}
	}

	/**
	 * 随机产生一个32位的UUID字符串
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 字符串为空、null转换为"",否则返回本身
	 *
	 * @param obj
	 * @return
	 */
	public static String stringIsnullOrEmptyConvert(String obj) {
		if (StringUtils.isBlank(obj)) {
			return "";
		}
		return obj.trim();
	}

	/**
	 * 将ISO编码改为UTF-8
	 * @param str ISO字符串
	 * @return UTF-8编码
	 */
	public static String convertISO8859ToUTF8(String str){
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return str;
		}
	}

	/**
	 *
	 * @方法描述：  返回首字母
	 * @创建人：Kevin
	 * @创建时间：2017年3月2日 下午4:37:50   
	 * @修改人：Kevin
	 * @修改时间：2017年3月2日 下午4:37:50   
	 * @修改备注：
	 * @param： @param strChinese 字符串
	 * @param： @param bUpCase 是否大写
	 * @param： @return
	 */
	public static String getPYIndexStr(String strChinese, boolean bUpCase) {
		try {
			StringBuffer buffer = new StringBuffer();
			byte b[] = strChinese.getBytes("GBK");// 把中文转化成byte数组
			for (int i = 0; i < b.length; i++) {
				if ((b[i] & 255) > 128) {
					int char1 = b[i++] & 255;
					char1 <<= 8;// 左移运算符用“<<”表示，是将运算符左边的对象，向左移动运算符右边指定的位数，并且在低位补零。其实，向左移n位，就相当于乘上2的n次方
					int chart = char1 + (b[i] & 255);
					buffer.append(getPYIndexChar((char) chart, bUpCase));
					continue;
				}
				char c = (char) b[i];
				if (!Character.isJavaIdentifierPart(c))// 确定指定字符是否可以是Java标识符中首字符以外的部分。
					c = 'A';
				buffer.append(c);
			}
			return buffer.toString();
		} catch (Exception e) {
			//System.out.println((new StringBuilder()).append("\u53D6\u4E2D\u6587\u62FC\u97F3\u6709\u9519").append(e.getMessage()).toString());
		}
		return null;
	}

	/**
	 * @方法描述： 返回首字母
	 * @创建人：Kevin
	 * @创建时间：2017年3月2日 下午4:39:21   
	 * @修改人：Kevin
	 * @修改时间：2017年3月2日 下午4:39:21   
	 * @修改备注：
	 * @param： @param strChinese 字符集
	 * @param： @param bUpCase 是否大写
	 * @param： @return
	 */
	private static char getPYIndexChar(char strChinese, boolean bUpCase) {
		int charGBK = strChinese;
		char result;
		if (charGBK >= 45217 && charGBK <= 45252)
			result = 'A';
		else if (charGBK >= 45253 && charGBK <= 45760)
			result = 'B';
		else if (charGBK >= 45761 && charGBK <= 46317)
			result = 'C';
		else if (charGBK >= 46318 && charGBK <= 46825)
			result = 'D';
		else if (charGBK >= 46826 && charGBK <= 47009)
			result = 'E';
		else if (charGBK >= 47010 && charGBK <= 47296)
			result = 'F';
		else if (charGBK >= 47297 && charGBK <= 47613)
			result = 'G';
		else if (charGBK >= 47614 && charGBK <= 48118)
			result = 'H';
		else if (charGBK >= 48119 && charGBK <= 49061)
			result = 'J';
		else if (charGBK >= 49062 && charGBK <= 49323)
			result = 'K';
		else if (charGBK >= 49324 && charGBK <= 49895)
			result = 'L';
		else if (charGBK >= 49896 && charGBK <= 50370)
			result = 'M';
		else if (charGBK >= 50371 && charGBK <= 50613)
			result = 'N';
		else if (charGBK >= 50614 && charGBK <= 50621)
			result = 'O';
		else if (charGBK >= 50622 && charGBK <= 50905)
			result = 'P';
		else if (charGBK >= 50906 && charGBK <= 51386)
			result = 'Q';
		else if (charGBK >= 51387 && charGBK <= 51445)
			result = 'R';
		else if (charGBK >= 51446 && charGBK <= 52217)
			result = 'S';
		else if (charGBK >= 52218 && charGBK <= 52697)
			result = 'T';
		else if (charGBK >= 52698 && charGBK <= 52979)
			result = 'W';
		else if (charGBK >= 52980 && charGBK <= 53688)
			result = 'X';
		else if (charGBK >= 53689 && charGBK <= 54480)
			result = 'Y';
		else if (charGBK >= 54481 && charGBK <= 55289)
			result = 'Z';
		else
			result = (char) (65 + (new Random()).nextInt(25));
		if (!bUpCase)
			result = Character.toLowerCase(result);
		return result;
	}

	/**
	 * 返回枚举属性成员集合 
	 * @param enumType
	 * @return Map<Integer, String>
	 */
	public static <E extends Enum<E>> Map<Integer, String> getMap(Class<E> enumType){
		Map<Integer, String> map = new HashMap<Integer, String>();
		E[] enums = enumType.getEnumConstants();
		for(Enum<E> e : enums){
			map.put(e.ordinal(), e.toString());
		}
		return map;
	}
	/**房型
	 * */
	public static String convertHouseTypeToName(String houseType){
		String rs = "1";
		switch (houseType) {
			case "1":
				rs = "一居室";
				break;
			case "2":
				rs = "二居室";
				break;
			case "3":
				rs = "三居室";
				break;
			case "4":
				rs = "四居室";
				break;
			case "5":
				rs = "五居室";
				break;
			case "6":
				rs = "五居以上";
				break;
			default:
				rs = "一居室";
				break;
		}

		return rs;
	}
	/**房型
	 * */
	public static String convertHouseType(String houseType){
		String rs = "一居室";
		switch (houseType) {
			case "一居室":
				rs = "1";
				break;
			case "二居室":
				rs = "2";
				break;
			case "三居室":
				rs = "3";
				break;
			case "四居室":
				rs = "4";
				break;
			case "五居室":
				rs = "5";
				break;
			case "五居以上":
				rs = "6";
				break;
			default:
				rs = "1";
				break;
		}

		return rs;
	}
	/**
	 * 根据导入文件的房间状态，转换成数据库中字段需要的integer类型
	 * liuy at 2018-11-18
	 * @param rootState
	 * @return
	 */

	public static int convertRoomState(String rootState) {
		Integer rs = 0;
		switch (rootState) {
			case "在售":
				rs = 1;
				break;
			case "我司认购":
				rs = 2;
				break;
			case "我司签约":
				rs = 3;
				break;
			case "已结佣":
				rs = 4;
				break;
			case "暂不售":
				rs = 5;
				break;
			default:
				break;
		}

		return rs;
	}

	public static String convertSellingPoint(Integer sellingPoint){
		String rs = "";
		switch (sellingPoint) {
			case 1:
				rs = "高佣";
				break;
			case 2:
				rs = "主推";
				break;
			case 3:
				rs = "特价";
				break;
			default:
				break;
		}
		return rs;
	}

	public static Integer convertSellingPointToValue(String sellingPoint){
		Integer rs = 0;
		if(sellingPoint != null && sellingPoint != ""){
			sellingPoint = sellingPoint.trim();
		}
		switch (sellingPoint) {
			case "高佣":
				rs = 1;
				break;
			case "主推":
				rs = 2;
				break;
			case "特价":
				rs = 3;
				break;
			default:
				break;
		}
		return rs;
	}

	public static String convertRoomStateToName(Integer rootState) {
		String  rs = "在售";
		switch (rootState) {
			case 1:
				rs = "在售";
				break;
			case 2:
				rs = "我司认购";
				break;
			case 3:
				rs ="我司签约";
				break;
			case 4:
				rs = "已结佣";
				break;
			case 5:
				rs = "暂不售";
				break;
			default:
				break;
		}

		return rs;
	}

	public static String convertTowards(String toward) {
		String rs = "";
		switch (toward) {
			case "东":
				rs = "1";
				break;
			case "南":
				rs = "3";
				break;
			case "西":
				rs = "6";
				break;
			case "北":
				rs = "8";
				break;
			case "东南":
				rs = "2";
				break;
			case "西南":
				rs = "4";
				break;
			case "南北":
				rs = "5";
				break;
			case "西北":
				rs = "7";
				break;
			case "东北":
				rs = "9";
				break;
			case "东西":
				rs = "10";
				break;
			default:
				rs= "1";
				break;
		}

		return rs;
	}
	public static String convertTowardsToName(String toward) {
		String  rs ="";
		switch (toward){
			case "1":
				rs = "东";
				break;
			case "3":
				rs = "南";
				break;
			case "6":
				rs = "西";
				break;
			case "8":
				rs = "北";
				break;
			case "2":
				rs = "东南";
				break;
			case "4":
				rs = "西南";
				break;
			case "5":
				rs = "南北";
				break;
			case "7":
				rs = "西北";
				break;
			case "9":
				rs = "东北";
				break;
			case "10":
				rs = "东西";
				break;
			default:
				rs = "东";
				break;
		}
		return rs;
	}

	public static String getStringByObj(Object obj) {
		if (obj == null || "".equals((obj + "").trim()) || "undefined".equals((obj + "").trim()) || "null".equals((obj + "").trim())) {
			return "";
		}
		return trim(obj + "");
	}

	public static String getStringByObj(Object obj,String defaultValue) {
		String temp=getStringByObj(obj);
		if("".equals(temp)){
			temp=defaultValue;
		}
		return temp;
	}
}
