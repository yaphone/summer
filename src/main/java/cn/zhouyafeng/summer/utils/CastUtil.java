package cn.zhouyafeng.summer.utils;

/**
 * 转型操作工具类
 * 
 * @author https://github.com/yaphone
 * @date 创建时间：2017年7月1日 下午3:45:03
 * @version 1.0
 *
 */
public final class CastUtil {

	/**
	 * 转为String型
	 * 
	 * @date 2017年7月1日 下午3:46:33
	 * @param obj
	 * @return
	 */
	public static String castString(Object obj) {
		return CastUtil.castString(obj, "");
	}

	/**
	 * 转为String型，提供默认值
	 * 
	 * @date 2017年7月1日 下午3:48:05
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public static String castString(Object obj, String defaultValue) {
		return obj != null ? String.valueOf(obj) : defaultValue;
	}

	/**
	 * 转为double型
	 * 
	 * @date 2017年7月1日 下午3:52:21
	 * @param obj
	 * @return
	 */
	public static double castDouble(Object obj) {
		return CastUtil.castDouble(obj, 0);
	}

	/**
	 * 转为double型，提供默认值
	 * 
	 * @date 2017年7月1日 下午3:53:08
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public static double castDouble(Object obj, double defaultValue) {
		double value = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				try {
					value = Double.parseDouble(strValue);
				} catch (NumberFormatException e) {
					value = defaultValue;
				}
			}
		}
		return value;
	}

	/**
	 * 转为long型
	 * 
	 * @date 2017年7月1日 下午4:00:51
	 * @param obj
	 * @return
	 */
	public static long castLong(Object obj) {
		return castLong(obj, 0L);
	}

	/**
	 * 转为long型，提供默认值
	 * 
	 * @date 2017年7月1日 下午4:03:24
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public static long castLong(Object obj, long defaultValue) {
		long value = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				try {
					value = Long.parseLong(strValue);
				} catch (NumberFormatException e) {
					value = defaultValue;
				}
			}
		}
		return value;
	}

	/**
	 * 转为int型
	 * 
	 * @date 2017年7月1日 下午4:06:15
	 * @param obj
	 * @return
	 */
	public static int castInt(Object obj) {
		return castInt(obj, 0);
	}

	/**
	 * 转为int型，提供默认值
	 * 
	 * @date 2017年7月1日 下午4:06:43
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public static int castInt(Object obj, int defaultValue) {
		int value = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				try {
					value = Integer.parseInt(strValue);
				} catch (Exception e) {
					value = defaultValue;
				}
			}
		}
		return value;
	}

	/**
	 * 转为boolean型
	 * 
	 * @date 2017年7月1日 下午4:09:19
	 * @param obj
	 * @return
	 */
	public static boolean castBoolean(Object obj) {
		return CastUtil.castBoolean(obj, false);
	}

	/**
	 * 转为boolean型，提供默认值
	 * 
	 * @date 2017年7月1日 下午4:09:56
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public static boolean castBoolean(Object obj, boolean defaultValue) {
		boolean value = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				value = Boolean.parseBoolean(strValue);
			}
		}
		return value;
	}

}
