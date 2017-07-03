package cn.zhouyafeng.summer.framework;

import cn.zhouyafeng.summer.framework.helper.ClassHelper;
import cn.zhouyafeng.summer.framework.helper.ControllerHelper;
import cn.zhouyafeng.summer.framework.helper.IocHelper;
import cn.zhouyafeng.summer.framework.util.BeanHelper;
import cn.zhouyafeng.summer.framework.util.ClassUtil;

/**
 * 加载相应的Helper类
 * <p>
 * 通过调用init()方法来加载Helper类。
 * </p>
 *
 * @author https://github.com/yaphone
 * @date 2017年7月3日 下午2:30:56
 * @version 1.0
 *
 */
public class HelperLoader {
    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for(Class<?> cls: classList){
            ClassUtil.loadClass(cls.getName(), false);
        }
    }
}
