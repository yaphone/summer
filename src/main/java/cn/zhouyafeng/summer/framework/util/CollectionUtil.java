package cn.zhouyafeng.summer.framework.util;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

/**
 * 集合工具类, 对Appache Commons 相关类进行封装
 * 
 * @author https://github.com/yaphone
 * @date 创建时间：2017年7月1日 下午4:13:43
 * @version 1.0
 *
 */
public final class CollectionUtil {
	/**
	 * 判断collection是否为空
	 * 
	 * @date 2017年7月1日 下午4:16:04
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return CollectionUtils.isEmpty(collection);
	}

	/**
	 * 判断collection是否非空
	 * 
	 * @date 2017年7月1日 下午4:16:52
	 * @param collection
	 * @return
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		return !isEmpty(collection);
	}

	/**
	 * 判断Map是否为空
	 * 
	 * @date 2017年7月1日 下午4:18:52
	 * @param map
	 * @return
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return MapUtils.isEmpty(map);
	}

	/**
	 * 判断map是否非空
	 * 
	 * @date 2017年7月1日 下午4:19:40
	 * @param map
	 * @return
	 */
	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}
}
