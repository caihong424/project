package org.eastwill.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 网络测试XML入参对应实体类(节点定义只能比XML多,不能少.否则解析异常)
 * 
 * @author 王畅
 * @version 创建时间：2015-4-28 下午05:30:46
 */
@XStreamAlias("ROOT")
public class NetTestRequstModel {

	@XStreamAlias("LOG_NO")
	private String logNo;

	@XStreamAlias("FUN_CODE")
	private String funCode;

	@XStreamAlias("USER_ID")
	private String userId;

	@XStreamAlias("SIGN_TYPE")
	private String signType;

	@XStreamAlias("SIGN")
	private String sign;

	@XStreamAlias("REQ_ENCRYPTED")
	private String reqEncrypted;

	public String getLogNo() {
		return logNo;
	}

	public void setLogNo(String logNo) {
		this.logNo = logNo;
	}

	public String getFunCode() {
		return funCode;
	}

	public void setFunCode(String funCode) {
		this.funCode = funCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getReqEncrypted() {
		return reqEncrypted;
	}

	public void setReqEncrypted(String reqEncrypted) {
		this.reqEncrypted = reqEncrypted;
	}
}
