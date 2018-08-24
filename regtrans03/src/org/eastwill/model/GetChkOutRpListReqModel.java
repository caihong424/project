package org.eastwill.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("REQ")
public class GetChkOutRpListReqModel {
	@XStreamAlias("HOS_ID")
	private String hosId;
	
	@XStreamAlias("HOSP_PATIENT_ID")
	private String hospPatientId;
	
	@XStreamAlias("PATIENT_IDCARD_TYPE")
	private String patientIdcardType;
	
	@XStreamAlias("PATIENT_IDCARD_NO")
	private String patientIdcardNo;
	
	@XStreamAlias("PATIENT_CARD_TYPE")
	private String patientCardType;
	
	@XStreamAlias("PATIENT_CARD_NO")
	private String patientCardNo;
	
	@XStreamAlias("PATIENT_NAME")
	private String patientName;
	
	@XStreamAlias("PATIENT_SEX")
	private String patientSex;
	
	@XStreamAlias("PATIENT_AGE")
	private String patientAge;
	
	@XStreamAlias("BEGIN_DATE")
	private String beginDate;
	
	@XStreamAlias("END_DATE")
	private String endDate;

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

	public String getPatientIdcardType() {
		return patientIdcardType;
	}

	public void setPatientIdcardType(String patientIdcardType) {
		this.patientIdcardType = patientIdcardType;
	}

	public String getPatientIdcardNo() {
		return patientIdcardNo;
	}

	public void setPatientIdcardNo(String patientIdcardNo) {
		this.patientIdcardNo = patientIdcardNo;
	}

	public String getPatientCardType() {
		return patientCardType;
	}

	public void setPatientCardType(String patientCardType) {
		this.patientCardType = patientCardType;
	}

	public String getPatientCardNo() {
		return patientCardNo;
	}

	public void setPatientCardNo(String patientCardNo) {
		this.patientCardNo = patientCardNo;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}

	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
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
}
