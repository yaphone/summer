package cn.zhouyafeng.summer.framework.bean;

/**
 * 返回的数据对象
 *
 * @author https://github.com/yaphone
 * @date 2017年7月3日 下午3:10:15
 * @version 1.0
 *
 */
public class Data {
    
    /**
     * 模型数据
     */
    private Object model;
    
    public Data(Object model) {
        this.model = model;
    }
    
    public Object getModel(){
        return model;
    }
}
