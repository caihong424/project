package org.eastwill.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("REQ")
public class RegPayReqModel {
	@XStreamAlias("HOS_ID")
	private String hosId;
	
	@XStreamAlias("ORDER_ID")
	private String orderId;
	
	@XStreamAlias("HOSP_ORDER_ID")
	private String hospOrderId;
	
	@XStreamAlias("SERIAL_NUM")
	private String serialNum;
	
	@XStreamAlias("PAY_DATE")
	private String payDate;
	
	@XStreamAlias("PAY_TIME")
	private String payTime;
	
	@XStreamAlias("PAY_CHANNEL_ID")
	private String payChannelId;
	
	@XStreamAlias("PAY_TOTAL_FEE")
	private String payTotalFee;
	
	@XStreamAlias("PAY_COPE_FEE")
	private String payCopeFee;
	
	@XStreamAlias("PAY_FEE")
	private String payFee;
	
	@XStreamAlias("PAY_RES_CODE")
	private String payResCode;
	
	@XStreamAlias("PAY_RES_DESC")
	private String payResDesc;
	
	@XStreamAlias("MERCHANT_ID")
	private String merchantId;
	
	@XStreamAlias("TERMINAL_ID")
	private String terminalId;
	
	@XStreamAlias("BANK_NO")
	private String bankNo;
	
	@XStreamAlias("PAY_ACCOUNT")
	private String payAccount;

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getHospOrderId() {
		return hospOrderId;
	}

	public void setHospOrderId(String hospOrderId) {
		this.hospOrderId = hospOrderId;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getPayChannelId() {
		return payChannelId;
	}

	public void setPayChannelId(String payChannelId) {
		this.payChannelId = payChannelId;
	}

	public String getPayTotalFee() {
		return payTotalFee;
	}

	public void setPayTotalFee(String payTotalFee) {
		this.payTotalFee = payTotalFee;
	}

	public String getPayCopeFee() {
		return payCopeFee;
	}

	public void setPayCopeFee(String payCopeFee) {
		this.payCopeFee = payCopeFee;
	}

	public String getPayFee() {
		return payFee;
	}

	public void setPayFee(String payFee) {
		this.payFee = payFee;
	}

	public String getPayResCode() {
		return payResCode;
	}

	public void setPayResCode(String payResCode) {
		this.payResCode = payResCode;
	}

	public String getPayResDesc() {
		return payResDesc;
	}

	public void setPayResDesc(String payResDesc) {
		this.payResDesc = payResDesc;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
		
}
