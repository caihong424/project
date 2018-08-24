package org.eastwill.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("REQ")
public class DocPlanReqModel {
	@XStreamAlias("HOS_ID")
	private String hosId;
	
	@XStreamAlias("DEPT_ID")
	private String deptId;
	
	@XStreamAlias("DOCTOR_ID")
	private String doctorId;
	
	@XStreamAlias("START_DATE")
	private String startDate;
	
	@XStreamAlias("END_DATE")
	private String EndDate;

	@XStreamAlias("TIME_FLAG")
	private String TimeFlag;
	
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}	
	
	public String getTimeFlag() {
		return TimeFlag;
	}

	public void setTimeFlag(String timeFlag) {
		TimeFlag = timeFlag;
	}

}
