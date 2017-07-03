package cn.zhouyafeng.summer.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 数组工具类，对Apache ArrayUtils进行封闭
 *
 * @author https://github.com/yaphone
 * @date 2017年7月3日 上午11:52:55
 * @version 1.0
 */
public final class ArrayUtil {
    
    /**
     * 判断数组是否为空
     *
     * @date 2017年7月3日 上午11:54:21
     * @param array
     * @return boolean
     */
    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }
    
    /**
     * 判断数组是否非空
     *
     * @date 2017年7月3日 上午11:55:36
     * @param array
     * @return boolean
     */
    public static boolean isNotEmpty(Object[] array){
        return ArrayUtils.isNotEmpty(array);
    }

}
