package org.eastwill.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("REQ")
public class RegRefundReqModel {
	@XStreamAlias("HOS_ID")
	private String hosId;
	
	@XStreamAlias("ORDER_ID")
	private String orderId;
	
	@XStreamAlias("HOSP_ORDER_ID")
	private String hospOrderId;
	
	@XStreamAlias("REFUND_ID")
	private String refundId;
	
	@XStreamAlias("REFUND_SERIAL_NUM")
	private String refundSerialNum;
	
	@XStreamAlias("TOTAL_FEE")
	private String totalFee;
	
	@XStreamAlias("REFUND_FEE")
	private String refundFee;
	
	@XStreamAlias("REFUND_DATE")
	private String refundDate;
	
	@XStreamAlias("REFUND_TIME")
	private String refundTime;
	
	@XStreamAlias("REFUND_RES_CODE")
	private String refundResCode;
	
	@XStreamAlias("REFUND_RES_DESC")
	private String refundResDesc;
	
	@XStreamAlias("REFUND_REMARK")
	private String refundRemark;

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

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getRefundSerialNum() {
		return refundSerialNum;
	}

	public void setRefundSerialNum(String refundSerialNum) {
		this.refundSerialNum = refundSerialNum;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(String refundFee) {
		this.refundFee = refundFee;
	}

	public String getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(String refundDate) {
		this.refundDate = refundDate;
	}

	public String getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}

	public String getRefundResCode() {
		return refundResCode;
	}

	public void setRefundResCode(String refundResCode) {
		this.refundResCode = refundResCode;
	}

	public String getRefundResDesc() {
		return refundResDesc;
	}

	public void setRefundResDesc(String refundResDesc) {
		this.refundResDesc = refundResDesc;
	}

	public String getRefundRemark() {
		return refundRemark;
	}

	public void setRefundRemark(String refundRemark) {
		this.refundRemark = refundRemark;
	}
	
	
}
