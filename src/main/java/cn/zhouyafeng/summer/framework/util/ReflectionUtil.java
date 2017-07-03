package cn.zhouyafeng.summer.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 反射工具类
 *
 * @author https://github.com/yaphone
 * @date 2017年7月3日 上午10:33:55
 * @version 1.0
 *
 */
public class ReflectionUtil {
    private static Logger LOG = LoggerFactory.getLogger(ReflectionUtil.class);
    
    /**
     * 创建实例
     *
     * @date 2017年7月3日 上午10:45:35
     * @param cls
     * @return Object
     */
    public static Object newInstance(Class<?> cls){
        Object instance;
        try{
            instance = cls.newInstance();
        }catch(Exception e){
            LOG.error("new instance failure", e);
            throw new RuntimeException(e);
        }
        return instance;
    }
    
    /**
     * 调用方法
     *
     * @date 2017年7月3日 上午10:50:30
     * @param obj
     * @param method
     * @param args
     * @return Object
     */
    public static Object invokeMethod(Object obj, Method method, Object... args){
        Object result;
        try{
            method.setAccessible(true);
            result = method.invoke(obj, args);
        }catch (Exception e) {
            LOG.error("invoke method failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }
    
    public static void setField(Object obj, Field field, Object value){
        try{
            field.setAccessible(true);
            field.set(obj, value);
        }catch(Exception e){
            LOG.error("set field failure", e);
            throw new RuntimeException(e);
        }
    }

}
