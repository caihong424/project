package org.eastwill.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("REQ")
public class QueryPayRecordReqModel {
	@XStreamAlias("HOS_ID")
	private String hosId;
	
	@XStreamAlias("QUERY_ORDER")
	private String queryOrder;
	
	@XStreamAlias("ORDER_NO")
	private String orderNo;
	
	@XStreamAlias("HOSP_SEQUENCE")
	private String hospSequence;
	
	@XStreamAlias("QUERY_DATE")
	private String queryDate;
	
	@XStreamAlias("QUERY_TYPE")
	private String queryType;
	
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

	public String getQueryOrder() {
		return queryOrder;
	}

	public void setQueryOrder(String queryOrder) {
		this.queryOrder = queryOrder;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getHospSequence() {
		return hospSequence;
	}

	public void setHospSequence(String hospSequence) {
		this.hospSequence = hospSequence;
	}

	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
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
