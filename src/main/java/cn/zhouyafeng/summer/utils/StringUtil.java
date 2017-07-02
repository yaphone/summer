package cn.zhouyafeng.summer.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类，对Appache Commons StringUtils类进行封装
 * 
 * @author https://github.com/yaphone
 * @date 创建时间：2017年7月1日 下午3:54:48
 * @version 1.0
 *
 */
public final class StringUtil {

	/**
	 * 判断字符串是否为空
	 * 
	 * @date 2017年7月1日 下午3:56:42
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str != null) {
			str = str.trim();
		}
		return StringUtils.isEmpty(str);
	}

	/**
	 * 判断字符串是否非空
	 * 
	 * @date 2017年7月1日 下午3:57:54
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

}
