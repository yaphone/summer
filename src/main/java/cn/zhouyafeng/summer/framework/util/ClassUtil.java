package cn.zhouyafeng.summer.framework.util;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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
		Class<?> cls;
		try {
			cls = Class.forName(className, isInitalized, getClassLoader());
		} catch (ClassNotFoundException e) {
			LOG.error("load class failure", e);
			throw new RuntimeException(e);
		}
		return cls;
	}

	/**
	 * 获取指定包名下的所有类
	 * <p>
	 * 需要根据包名转换为文件路径，读取class文件或jar包，获取指定的类名去加载类
	 * </p>
	 * 
	 * @date 2017年7月2日 下午6:59:51
	 * @param packageName
	 * @return
	 */
	public static Set<Class<?>> getClassSet(String packageName) {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		try {
			Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				if (url != null) {
					String protocol = url.getProtocol();
					if (protocol.equals("file")) {
						String packagePath = url.getPath().replaceAll("%20", " ");
						addClass(classSet, packagePath, packageName);
					} else if (protocol.equals("jar")) {
						JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
						if (jarURLConnection != null) {
							JarFile jarFile = jarURLConnection.getJarFile();
							if (jarFile != null) {
								Enumeration<JarEntry> jarEntries = jarFile.entries();
								while (jarEntries.hasMoreElements()) {
									JarEntry jarEntry = jarEntries.nextElement();
									String jarEntryName = jarEntry.getName();
									if (jarEntryName.endsWith(".class")) {
										String className = jarEntryName.substring(0, jarEntryName.lastIndexOf("."))
												.replaceAll("/", ".");
										doAddClass(classSet, className);
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("get class set failure", e);
			throw new RuntimeException(e);
		}
		return classSet;
	}

	private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
		File[] files = new File(packagePath).listFiles(new FileFilter() {

			@Override
			public boolean accept(File file) {
				return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
			}
		});
		for (File file : files) {
			String fileName = file.getName();
			if (file.isFile()) {
				String className = fileName.substring(0, fileName.lastIndexOf("."));
				if (StringUtil.isNotEmpty(packageName)) {
					className = packageName + "." + className;
				}
				doAddClass(classSet, className);
			} else {
				String subPackagePath = fileName;
				if (StringUtil.isNotEmpty(packagePath)) {
					subPackagePath = packagePath + "/" + subPackagePath;
				}
				String subPackageName = fileName;
				if (StringUtil.isNotEmpty(packageName)) {
					subPackageName = packageName + "." + subPackageName;
				}
				addClass(classSet, subPackagePath, subPackageName);
			}
		}
	}

	private static void doAddClass(Set<Class<?>> classSet, String className) {
		Class<?> cls = loadClass(className, false); // isInitalized设置为false以提高加载效率
		classSet.add(cls);
	}
}
