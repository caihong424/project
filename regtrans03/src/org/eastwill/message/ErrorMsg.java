package org.eastwill.message;

/**
 * 错误代码表
 * 
 * @author 王畅
 * @version 创建时间：2015-4-29 下午02:53:03
 */
public enum ErrorMsg {

	CLIENT_INPUT_ERROR(-4, "客户端输入错误"),
	/**
	 * 交易成功
	 */
	DEAL_SUCCESS(0, "交易成功"),
	/**
	 * 用户名不正确
	 */
	USER_NOT_EXIST(1, "用户名不正确"),
	/**
	 * 签名不正确
	 */
	SIGN_NOT_RIGHT(2, "签名不正确"),
	DATE_NOT_RIGHT(3, "日期格式不正确");

	private int resCode;
	private String resMsg;

	private ErrorMsg(int resCode, String resMsg) {
		this.resCode = resCode;
		this.resMsg = resMsg;
	}

	public int getResCode() {
		return resCode;
	}

	public void setResCode(int resCode) {
		this.resCode = resCode;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

}
