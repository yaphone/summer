package cn.zhouyafeng.summer.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * JSON工具类，用于处理JSON与POJO对象之前的转换
 *
 * @author https://github.com/yaphone
 * @date 2017年7月3日 下午4:07:38
 * @version 1.0
 *
 */
public class JsonUtil {
    private static Logger LOG = LoggerFactory.getLogger(JsonUtil.class);
    
    /**
     * 将POJO转为Json对象
     *
     * @date 2017年7月3日 下午4:12:31
     * @param obj
     * @return String
     */
    public static <T> String toJson(T obj){
        String json;
        try{
            json = JSON.toJSONString(obj);
        }catch(Exception e){
            LOG.error("convert POJO to JSON failure", e);
            throw new RuntimeException(e);
        }
        return json;
    }
    
    /**
     * 将Json字符串转为Objs
     *
     * @date 2017年7月3日 下午4:20:59
     * @param jsonStr
     * @param type
     * @return T
     */
    public static <T> T jsonToObj(String jsonStr, Class<T> type){
        T obj;
        try{
            JSON json = JSON.parseObject(jsonStr);
            obj = JSON.toJavaObject(json, type);
        }catch(Exception e){
            LOG.error("convert JSON to POJO failure", e);
            throw new RuntimeException(e);
        }
        return obj;
    }

}
