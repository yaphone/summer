package cn.zhouyafeng.summer.framework.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取属性文件工具类
 * 
 * @author https://github.com/yaphone
 * @version 1.0
 * @date 2017/7/1 15:19
 */
public final class PropsUtil {
	private static Logger LOG = LoggerFactory.getLogger(PropsUtil.class);

	/**
	 * 加载属性文件
	 * 
	 * @date 2017年7月1日 下午3:37:39
	 * @param fileName
	 * @return
	 */
	public static Properties loadProps(String fileName) {
		Properties props = null;
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			if (is == null) {
				throw new FileNotFoundException(fileName + " file is not found");
			}
			props = new Properties();
			props.load(is);
		} catch (IOException e) {
			LOG.error("load properties file failure", e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					LOG.error("close input steam failure", e);
				}
			}
		}
		return props;
	}

	/**
	 * 获取字符型属性，默认值为空字符串
	 * 
	 * @date 2017年7月1日 下午3:39:21
	 * @param props
	 * @param key
	 * @return
	 */
	public static String getString(Properties props, String key) {
		return getString(props, key, "");
	}

	/**
	 * 获取字符型属性，可指定默认值
	 * 
	 * @date 2017年7月1日 下午3:39:49
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getString(Properties props, String key, String defaultValue) {
		String value = defaultValue;
		if (props.containsKey(key)) {
			value = props.getProperty(key);
		}
		return value;
	}

	/**
	 * 获取int型属性，默认值 为0
	 * 
	 * @date 2017年7月1日 下午3:43:01
	 * @param props
	 * @param key
	 * @return
	 */
	public static int getInt(Properties props, String key) {
		return getInt(props, key, 0);
	}

	/**
	 * 获取int型属性，可提供默认值
	 * 
	 * @date 2017年7月1日 下午4:25:50
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getInt(Properties props, String key, int defaultValue) {
		int value = defaultValue;
		if (props.containsKey(key)) {
			value = CastUtil.castInt(props.getProperty(key));
		}
		return value;
	}

	/**
	 * 获取bool型值，默认值为false
	 * 
	 * @date 2017年7月1日 下午4:27:33
	 * @param props
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(Properties props, String key) {
		return getBoolean(props, key, false);
	}

	/**
	 * 获取bool型值，可提供默认值
	 * 
	 * @date 2017年7月1日 下午4:28:16
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBoolean(Properties props, String key, Boolean defaultValue) {
		boolean value = defaultValue;
		if (props.contains(key)) {
			value = CastUtil.castBoolean(props.getProperty(key));
		}
		return value;
	}

}
