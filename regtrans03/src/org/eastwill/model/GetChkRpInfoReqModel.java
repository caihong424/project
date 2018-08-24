package org.eastwill.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("REQ")
public class GetChkRpInfoReqModel {
	@XStreamAlias("HOS_ID")
	private String hosId;
	
	@XStreamAlias("REPORT_ID")
	private String reportId;
	
	@XStreamAlias("HOSP_PATIENT_ID")
	private String hospPatientId;

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getHospPatientId() {
		return hospPatientId;
	}

	public void setHospPatientId(String hospPatientId) {
		this.hospPatientId = hospPatientId;
	}

}
