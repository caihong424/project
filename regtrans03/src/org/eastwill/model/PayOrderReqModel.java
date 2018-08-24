package org.eastwill.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("REQ")
public class PayOrderReqModel {
	@XStreamAlias("HOS_ID")
	private String hosId;
	
	@XStreamAlias("ORDER_ID")
	private String orderId;
	
	@XStreamAlias("HOSP_SEQUENCE")
	private String hospSequence;
	
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
	
	@XStreamAlias("PAY_BEHOOVE_FEE")
	private String payBehooveFee;
	
	@XStreamAlias("PAY_ACTUAL_FEE")
	private String payActualFee;
	
	@XStreamAlias("PAY_MI_FEE")
	private String payMiFee;
	
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
	
	@XStreamAlias("OPERATOR_ID")
	private String operatorId;
	
	@XStreamAlias("RECEIPT_ID")
	private String receiptId;

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

	public String getHospSequence() {
		return hospSequence;
	}

	public void setHospSequence(String hospSequence) {
		this.hospSequence = hospSequence;
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

	public String getPayBehooveFee() {
		return payBehooveFee;
	}

	public void setPayBehooveFee(String payBehooveFee) {
		this.payBehooveFee = payBehooveFee;
	}

	public String getPayActualFee() {
		return payActualFee;
	}

	public void setPayActualFee(String payActualFee) {
		this.payActualFee = payActualFee;
	}

	public String getPayMiFee() {
		return payMiFee;
	}

	public void setPayMiFee(String payMiFee) {
		this.payMiFee = payMiFee;
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

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}
	
}
