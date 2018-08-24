package org.eastwill.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("REQ")
public class PayDetailReqModel {
	@XStreamAlias("HOS_ID") 
	private String hosId;
	
	@XStreamAlias("HOSP_PATIENT_ID")
	private String hospPatientId;
	
	@XStreamAlias("HOSP_SEQUENCE")
	private String hospSequence;

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getHospPatientId() {
		return hospPatientId;
	}

	public void setHospPatientId(String hospPatientId) {
		this.hospPatientId = hospPatientId;
	}

	public String getHospSequence() {
		return hospSequence;
	}

	public void setHospSequence(String hospSequence) {
		this.hospSequence = hospSequence;
	}
}
