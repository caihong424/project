package org.eastwill.utils;

/**
 * 组装报文类
 * 
 * @author 王畅
 * @version 创建时间：2015-4-29 下午03:03:13
 */
public class Sign {

	/**
	 * 获取请求参数SIGN值
	 * 
	 * @author 王畅
	 * @param fun_code
	 * @param user_id
	 * @param req_Encrypted
	 * @param key
	 * @return
	 */
	public static String GetRequestSign(String fun_code, String user_id, String req_Encrypted, String key) {
		String p = "FUN_CODE=" + fun_code + "&" + "REQ_ENCRYPTED=" + req_Encrypted + "&" + "USER_ID=" + user_id + "&" + "KEY=" + key;
		return Md5Utils.md5(p);

	}

	/**
	 * 返回参数串签名
	 * 
	 * @author 王畅
	 * @param code
	 * @param msg
	 * @param res_encrypted
	 * @param key
	 * @return
	 */
	public static String GetResponseSign(int code, String msg, String res_encrypted, String key) {
		String p = "RES_ENCRYPTED=" + res_encrypted + "&" + "RETURN_CODE=" + code + "&" + "RETURN_MSG=" + msg + "&" + "KEY" + key;
		return Md5Utils.md5(p);
	}
}
