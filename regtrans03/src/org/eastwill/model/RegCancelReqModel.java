package org.eastwill.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("REQ")
public class RegCancelReqModel {
	@XStreamAlias("HOS_ID")
	private String hosId;
	
	@XStreamAlias("ORDER_ID")
	private String orderId;
	
	@XStreamAlias("HOSP_ORDER_ID")
	private String hospOrderId;
	
	@XStreamAlias("CANCEL_DATE")
	private String cancelDate;
	
	@XStreamAlias("CANCEL_REMARK")
	private String cancelRemark;

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

	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getCancelRemark() {
		return cancelRemark;
	}

	public void setCancelRemark(String cancelRemark) {
		this.cancelRemark = cancelRemark;
	}
}
