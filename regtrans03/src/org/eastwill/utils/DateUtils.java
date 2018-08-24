package org.eastwill.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 日期工具类
 * 
 * @author 王畅
 * @version 创建时间：2015-4-18 上午11:48:59
 */
public class DateUtils {

	/**
	 * 获取当前日期(年月日时分秒)
	 * 
	 * @author 王畅
	 * @return
	 */
	public static String getDateTime() {
		Calendar currentTime = Calendar.getInstance();
		SimpleDateFormat formate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formate.format(currentTime.getTime());
	}

	/**
	 * 获取年-月-日
	 * 
	 * @author 王畅
	 * @return
	 */
	public static String getDate() {
		Calendar currentTime = Calendar.getInstance();
		SimpleDateFormat formate = new java.text.SimpleDateFormat("yyyy-MM-dd");
		return formate.format(currentTime.getTime());
	}

	/**
	 * 获取时-分-秒
	 * 
	 * @author 王畅
	 * @return
	 */
	public static String getTime() {
		Calendar currentTime = Calendar.getInstance();
		SimpleDateFormat formate = new java.text.SimpleDateFormat("HH:mm:ss");
		return formate.format(currentTime.getTime());
	}
}
