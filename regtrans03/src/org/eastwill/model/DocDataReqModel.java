package org.eastwill.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("REQ")
public class DocDataReqModel {
	@XStreamAlias("HOS_ID")
	private String hosId;
	
	@XStreamAlias("AREA_NAME")
	private String areaName;
	
	@XStreamAlias("DEPT_ID")
	private String deptId;
	
	@XStreamAlias("DOCTOR_ID")
	private String doctorId;
	
	@XStreamAlias("COUNT_DATE")
	private String countDate;
	
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

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	public String getCountDate() {
		return countDate;
	}

	public void setCountDate(String countDate) {
		this.countDate = countDate;
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
