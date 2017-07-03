package cn.zhouyafeng.summer.framework.bean;

import java.lang.reflect.Method;

/**
 * 封闭Action消息
 *
 * @author https://github.com/yaphone
 * @date 2017年7月3日 下午1:57:19
 * @version 1.0
 *
 */
public class Handler {
    /**
     * Controller 类
     */
    private Class<?> controllerClass;
    
    /**
     * Action方法
     */
    private Method actionMethod;
    
    public Handler(Class<?> controllerClass, Method actionMethod){
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }
    
    public Class<?> getControllerClass(){
        return controllerClass;
    }
    
    public Method getActionMethod(){
        return actionMethod;
    }
}
