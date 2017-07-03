package cn.zhouyafeng.summer.framework.helper;

import java.util.HashSet;
import java.util.Set;

import cn.zhouyafeng.summer.framework.annotation.Controller;
import cn.zhouyafeng.summer.framework.annotation.Service;
import cn.zhouyafeng.summer.framework.util.ClassUtil;

/**
 * 类操作助手类
 * <p>
 * 在summer.properties中指定了summer.framework.app.base_package，它是整个应用的基础包名，
 * 通过ClassUtil加载的类都需要基于该基础包名。ClassHelper用于获取包名下所有的类、包名下所有的Controller类、Service类。
 * 此外， 还可以将带有Controller注解与带有Service注解的类所产生
 * 的对象，理解为Summer框架所管理的Bean，因此有必要在ClassHelper类中增加一个获取应用包下所有Bean类的方法 。 </>
 * 
 * @author https://github.com/yaphone
 * @date 创建时间：2017年7月2日 下午11:43:26
 * @version 1.0
 *
 */
public final class ClassHelper {

	/**
	 * 类集合，用于存放所加载的类
	 */
	private static final Set<Class<?>> CLASS_SET;

	static {
		String basePackage = ConfigHelper.getAppBasePackage();
		CLASS_SET = ClassUtil.getClassSet(basePackage);
	}

	/**
	 * 获取应用包名下所有的类
	 * 
	 * @date 2017年7月3日 上午12:06:14
	 * @return
	 */
	public static Set<Class<?>> getClassSet() {
		return CLASS_SET;
	}

	/**
	 * 获取应用包下所有的Service类
	 * 
	 * @date 2017年7月3日 上午12:07:15
	 * @return
	 */
	public static Set<Class<?>> getServiceClassSet() {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> cls : CLASS_SET) {
			if (cls.isAnnotationPresent(Service.class)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}

	/**
	 * 获取应用包下所有的Controller类
	 * 
	 * @date 2017年7月3日 上午12:10:21
	 * @return
	 */
	public static Set<Class<?>> getControllerClassSet() {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> cls : CLASS_SET) {
			if (cls.isAnnotationPresent(Controller.class)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}

	/**
	 * 获取应用包名下所有的Bean类，包括Controller、Service类
	 * 
	 * @date 2017年7月3日 上午12:13:06
	 * @return
	 */
	public static Set<Class<?>> getBeanClassSet() {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		classSet.addAll(getControllerClassSet());
		classSet.addAll(getServiceClassSet());
		return classSet;
	}

}
