package org.eastwill.message;

/**
 * 返回报文格式
 * 
 * @author 王畅
 * @version 创建时间：2015-4-29 下午04:34:16
 */
public class ResponseMsg {

	/**
	 * 测试网络报文格式
	 */
	public final static String NET_WORK_MESSAGE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ROOT><RETURN_CODE>{0}</RETURN_CODE><RETURN_MSG>{1}</RETURN_MSG><SIGN_TYPE>{2}</SIGN_TYPE><SIGN>{3}</SIGN><RES_ENCRYPTED>{4}</RES_ENCRYPTED></ROOT>";

}
