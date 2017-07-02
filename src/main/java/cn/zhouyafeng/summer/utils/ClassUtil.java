package cn.zhouyafeng.summer.utils;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类操作工具
 * 
 * @author https://github.com/yaphone
 * @date 创建时间：2017年7月2日 下午6:52:38
 * @version 1.0
 *
 */
public final class ClassUtil {
	private static Logger LOG = LoggerFactory.getLogger(ClassUtil.class);

	/**
	 * 获取类加载器
	 * 
	 * @date 2017年7月2日 下午6:55:47
	 * @return
	 */
	public static ClassLoader getClassLoader() {
		// 获取当前线程的ClassLoader
		return Thread.currentThread().getContextClassLoader();
	}

	/**
	 * 加载类
	 * 
	 * @date 2017年7月2日 下午6:58:50
	 * @param className
	 * @param isInitalized
	 * @return
	 */
	public static Class<?> loadClass(String className, boolean isInitalized) {
		// TODO
		return null;
	}

	/**
	 * 获取指定包名下的所有类
	 * 
	 * @date 2017年7月2日 下午6:59:51
	 * @param packageName
	 * @return
	 */
	public static Set<Class<?>> getClassSet(String packageName) {
		// TODO
		return null;
	}
}
