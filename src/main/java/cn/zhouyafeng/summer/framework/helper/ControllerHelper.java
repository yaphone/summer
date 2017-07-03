package cn.zhouyafeng.summer.framework.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.zhouyafeng.summer.framework.annotation.Action;
import cn.zhouyafeng.summer.framework.bean.Handler;
import cn.zhouyafeng.summer.framework.bean.Request;
import cn.zhouyafeng.summer.framework.util.ArrayUtil;
import cn.zhouyafeng.summer.framework.util.CollectionUtil;

/**
 * 控制器助手类
 * <p>
 * 通过ClassHelper，可以获取所有定义了Controller注解的类，通过反射可以获取该类中所有带Action注解的方法，获取Action注解中的请求
 * 表达式，进而获取请求方法的请求路径，封装一个请求对象（Request）与处理对象（Handler），最后将Request与Handler建立一个映射关系
 * 存入一个ActionMap，并提供一个可根据请求方法与请求路径获取处理对象的方法。
 * </p>
 *
 * @author https://github.com/yaphone
 * @date 2017年7月3日 下午2:02:57
 * @version 1.0
 */
public class ControllerHelper {
    // 用于存放请求与处理器的映射关系
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        // 获取所有的Controller类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)) {
            // 遍历ControllerSet
            for (Class<?> controllerClass : controllerClassSet) {
                // 获取Controller中定义的方法
                Method[] methods = controllerClass.getDeclaredMethods();
                // 遍历methods，获取带有Action注解的方法，获取URL映射规则
                for (Method method : methods) {
                    if (method.isAnnotationPresent(Action.class)) {
                        // 从Action注解中获取URL映射规则
                        Action action = method.getAnnotation(Action.class);
                        String mapping = action.value();
                        // 验证URL映射规则
                        if (mapping.matches("\\w+:/\\w*")) {
                            String[] array = mapping.split(":");
                            if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                // 获取请求方法与请求路径
                                String requestMethod = array[0];
                                String requestPath = array[1];
                                Request request = new Request(requestMethod, requestPath);
                                Handler handler = new Handler(controllerClass, method);

                                // 更新ACTION_MAP
                                ACTION_MAP.put(request, handler);
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * 获取Handler
     *
     * @date 2017年7月3日 下午2:27:26
     * @param requestMethod
     * @param requestPath
     * @return Handler
     */
    public static Handler getHandler(String requestMethod, String requestPath){
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }

}
