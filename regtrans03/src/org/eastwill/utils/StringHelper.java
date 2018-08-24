package org.eastwill.utils;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

/**
 * 字符串处理类
 * 
 * @author 王畅
 * @version 创建时间：2015-4-30 上午10:36:49
 */
public class StringHelper {

	/**
	 * 根据分隔符获取字符串
	 * 
	 * @author 王畅
	 * @param map
	 * @param split
	 * @return
	 */
	public static String convertMapToString(Map<String, String> map) {
		if (map == null)
			return null;
		// 第二步：把所有参数名和参数值串在一起
		StringBuilder sb = new StringBuilder("");
		for (Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
				sb.append(key).append("=").append(value).append("&");
			}
		}
		return sb.toString().substring(0, sb.toString().length() - 1);
	}
}
