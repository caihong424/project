package org.eastwill.model;

import java.util.Date;

public class DocPlanTime {
	private Long seq;
	private String hosId;
	private String deptId;
	private String doctorId;
	private Integer timeFlag;
	private String beginTime;
	private String endTime;
	private Integer total;
	private Integer overCount;
	private String regId;
	private Date regDate;
	
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getHosId() {
		return hosId;
	}
	public void setHosId(String hosId) {
		this.hosId = hosId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public Integer getTimeFlag() {
		return timeFlag;
	}
	public void setTimeFlag(Integer timeFlag) {
		this.timeFlag = timeFlag;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getOverCount() {
		return overCount;
	}
	public void setOverCount(Integer overCount) {
		this.overCount = overCount;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}	
}
