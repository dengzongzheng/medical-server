
package com.dzz.medical.config.filter;



/**
 * 防 xss攻击接口定义
 * @author dzz
 * @since  2017年05月21 上午11:54
 * @version  1.0.0
 */
public interface IXssFilterService {

    /**
     * 字符过滤
     * @param potentiallyDirtyParameter 参数
     * @return 转码
     */
	 String filterString(String potentiallyDirtyParameter);
}
