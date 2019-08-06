
package com.dzz.medical.config.filter;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Component;


/**
 * 防 xss攻击接口简单实现
 * @author dzz
 * @since  2017年05月21 上午11:54
 * @version  1.0.0
 */
@Component
public class SimpleFilterServiceImpl implements IXssFilterService {

	/**
	 * 
	 * @param potentiallyDirtyParameter 请求参数
	 * @return html转码结果
	 */
	@Override
	public String filterString(final String potentiallyDirtyParameter) {
		if (potentiallyDirtyParameter == null) {
			return null;
		}
        return StringEscapeUtils.escapeHtml4(potentiallyDirtyParameter);
	}

}
