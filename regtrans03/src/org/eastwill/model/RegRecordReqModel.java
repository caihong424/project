package org.eastwill.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("REQ")
public class RegRecordReqModel {
	@XStreamAlias("HOS_ID")
	private String hosId;
	
	@XStreamAlias("ORDER_ID")
	private String orderId;
	
	@XStreamAlias("HOSP_ORDER_ID")
	private String hospOrderId;
	
	@XStreamAlias("BEGIN_DATE")
	private String beginDate;
	
	@XStreamAlias("END_DATE")
	private String endDate;
	
	@XStreamAlias("PAGE_CURRENT")
	private String pageCurrent;
	
	@XStreamAlias("PAGE_SIZE")
	private String pageSize;

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

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPageCurrent() {
		return pageCurrent;
	}

	public void setPageCurrent(String pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
}
