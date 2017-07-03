package cn.zhouyafeng.summer.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 视图对象
 *<p>
 *由于视图中是可以包含模型数据的，因此在View中包含了视图路径与该视图中所需的模型数据，模型数据是一个Map类型的"键值对",
 *可在视图中根据模型的键名获取键值。
 *</p>
 *
 * @author https://github.com/yaphone
 * @date 2017年7月3日 下午3:01:53
 * @version 1.0
 *
 */
public class View {
    /**
     * 视图路径
     */
    private String path;
    
    /**
     * 模型数据
     */
    private Map<String, Object> model;
    
    public View(String path) {
        this.path = path;
        model = new HashMap<String, Object>();
    }
    
    public View addModel(String key, Object value){
        model.put(key, value);
        return this;
    }
    
    public String getPath(){
        return path;
    }
    
    public Map<String, Object> getModel(){
        return model;
    }
}
