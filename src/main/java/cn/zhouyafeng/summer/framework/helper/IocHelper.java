package cn.zhouyafeng.summer.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;

import cn.zhouyafeng.summer.framework.annotation.Inject;
import cn.zhouyafeng.summer.framework.util.ArrayUtil;
import cn.zhouyafeng.summer.framework.util.BeanHelper;
import cn.zhouyafeng.summer.framework.util.CollectionUtil;
import cn.zhouyafeng.summer.framework.util.ReflectionUtil;

/**
 * 依赖注入助手类
 * <p>
 * 控制不是由开发者决定，而是反转给框架进行管理，称之为依赖注入（DI），那么如何来管理依赖注入呢？
 * 最简单的方式，先通过BeanHelper获取BeanMap，它记录了类与对象的映射关系，然后遍历这个映射关系，取出
 * Bean类与Bean实例，进而通过反射获取类中所有的成员变量。继续遍历这些成员变量，在循环中判断当前成员变量是否带有Inject注解，
 * 若带有Inject注解，则从BeanMap中根据Bean类取出Bean实例。最后能过ReflectionUtil#setField方法来
 * 修改当前成员变量的值。
 * </P>
 *
 * @author https://github.com/yaphone
 * @date 2017年7月3日 上午11:38:13
 * @version 1.0
 *
 */
public final class IocHelper {
    static{
        //Bean类与Bean实例的映射关系
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if(CollectionUtil.isNotEmpty(beanMap)){
            //遍历BeanMap
            for(Map.Entry<Class<?>, Object> beanEntry: beanMap.entrySet()){
                //从BeanMap中获取Bean类与Bean实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                //获取Bean类定义的所有成员变量（Bean Field）
                Field[] beanFields = beanClass.getDeclaredFields();
                if(ArrayUtil.isNotEmpty(beanFields)){
                    //遍历beanFields
                    for(Field beanField: beanFields){
                        //判断当前BeanField是否带有Inject注解
                        if(beanField.isAnnotationPresent(Inject.class)){
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if(beanFieldInstance != null){
                                //通过反射初始化BeanField值
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
                
            }
        }
    }
}
