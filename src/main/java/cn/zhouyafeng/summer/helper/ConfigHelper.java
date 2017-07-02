package cn.zhouyafeng.summer.helper;

import java.util.Properties;

import cn.zhouyafeng.summer.framework.ConfigConstant;
import cn.zhouyafeng.summer.utils.PropsUtil;

/**
 * 属性文件助手类
 * 
 * @author https://github.com/yaphone
 * @date 创建时间：2017年7月2日 下午6:31:46
 * @version 1.0
 *
 */
public final class ConfigHelper {
	private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

	/**
	 * 获取JDBC驱动
	 * 
	 * @date 2017年7月2日 下午6:37:22
	 * @return
	 */
	public static String getJdbcDriver() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
	}

	/**
	 * 获取JDBC URL
	 * 
	 * @date 2017年7月2日 下午6:38:52
	 * @return
	 */
	public static String getJdbcUrl() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
	}

	/**
	 * 获取JDBC 用户名
	 * 
	 * @date 2017年7月2日 下午6:40:30
	 * @return
	 */
	public static String getJdbcUsername() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
	}

	/**
	 * 获取JDBC密码
	 * 
	 * @date 2017年7月2日 下午6:41:40
	 * @return
	 */
	public static String getJdbcPassword() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
	}

	/**
	 * 获取基础包名
	 * 
	 * @date 2017年7月2日 下午6:43:35
	 * @return
	 */
	public static String getAppBasePackage() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
	}

	/**
	 * 获取应用JSP路径
	 * 
	 * @date 2017年7月2日 下午6:46:14
	 * @return
	 */
	public static String getAppJspPathString() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH, "/WEB-INF/view/");
	}

	/**
	 * 获取APP静态资源路径
	 * 
	 * @date 2017年7月2日 下午6:48:35
	 * @return
	 */
	public static String getAppAssetPath() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH, "/asset/");
	}
}
