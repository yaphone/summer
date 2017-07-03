package cn.zhouyafeng.summer.framework.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.zhouyafeng.summer.framework.helper.ClassHelper;

/**
 * Bean用手类
 * <p>
 * 用于获取summer框架管理的所有Bean类，先调用ClassHelper类的getBeanSet方法，随后循环调用RefletionUtil的
 * newInstance()，根据类来实例化对象，最后将每次创建的对象存放在一个静态Map<Class<?>, Object>中。后面需要获取
 * 该Map，还需要通过该Map的key(类名)去获取所对应的value(Bean对象)。
 * </P>
 * <p>
 * BeanHelper相当于一个"Bean容器"，在BEAN_MAP中存放了Bean类与Bean实例的映射关系，只需要调用getBean方法，传入一个
 * Bean类，就能获取一个Bean实例。
 * </p>
 *
 * @author https://github.com/yaphone
 * @date 2017年7月3日 上午11:10:18
 * @version 1.0
 *
 */
public final class BeanHelper {
    /**
     * BEAN_MAP用于存放Bean映射
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();
    
    static{
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for(Class<?> beanClass: beanClassSet){
            Object obj = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass, obj);
        }
    }
    
    /**
     * 获取Bean映射
     *
     * @date 2017年7月3日 上午11:22:07
     * @return Map<Class<?>,Object>
     */
    public static Map<Class<?>, Object> getBeanMap(){
        return BEAN_MAP;
    }
    
    /**
     * 获取Bean实例
     *
     * @date 2017年7月3日 上午11:23:36
     * @param cls
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls){
        if(!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("can not get bean by class: " + cls);
        }
        return (T) BEAN_MAP.get(cls);
    }
}
