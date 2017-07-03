package cn.zhouyafeng.summer.framework.bean;

import java.util.Map;

import cn.zhouyafeng.summer.framework.util.CastUtil;

/**
 * 请求参数对象，用来保存从HttpServletRequest中获取到的请求参数
 *
 * @author https://github.com/yaphone
 * @date 2017年7月3日 下午2:52:37
 * @version 1.0
 *
 */
public class Param {
    private Map<String, Object> paramMap;
    
    public Param(Map<String, Object> paramMap){
        this.paramMap = paramMap;
    }
    
    /**
     * 根据参数名获取long型参数
     *
     * @date 2017年7月3日 下午2:56:47
     * @param name
     * @return long
     */
    public long getLong(String name){
        return CastUtil.castLong(paramMap.get(name));
    }
    
    /**
     * 获取所有字段信息
     *
     * @date 2017年7月3日 下午2:57:41
     * @return Map<String,Object>
     */
    public Map<String, Object> getMap(){
        return paramMap;
    }

}
