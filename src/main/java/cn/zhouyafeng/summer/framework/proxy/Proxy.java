package cn.zhouyafeng.summer.framework.proxy;

/**
 * 代理接口
 * 
 * @author https://github.com/yaphone
 * @date 创建时间：2017年7月8日 下午11:17:56
 * @version 1.0
 *
 */
public interface Proxy {
	Object doProxy(ProxyChain proxyChain) throws Throwable;

}
