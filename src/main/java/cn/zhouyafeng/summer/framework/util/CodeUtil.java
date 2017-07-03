package cn.zhouyafeng.summer.framework.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 编码与解码操作工具类
 *
 * @author https://github.com/yaphone
 * @date 2017年7月3日 下午4:01:05
 * @version 1.0
 *
 */
public class CodeUtil {
    private static final Logger LOG = LoggerFactory.getLogger(CodeUtil.class);
    
    /**
     * 将URL编码
     *
     * @date 2017年7月3日 下午4:03:06
     * @param source
     * @return String
     */
    public static String encodeURL(String source){
        String target;
        try{
            target = URLEncoder.encode(source, "UTF-8");
        }catch(Exception e){
            LOG.error("encode url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }
    
    /**
     * 将URL解码
     *
     * @date 2017年7月3日 下午4:07:15
     * @param source
     * @return String
     */
    public static String decodeURL(String source){
        String target;
        try{
            target = URLDecoder.decode(source, "UTF-8");
        }catch(Exception e){
            LOG.error("decode url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }
}
