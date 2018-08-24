package org.eastwill.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("REQ")
public class PayListReqModel {
	@XStreamAlias("HOS_ID")
	private String hosId;
	
	@XStreamAlias("HOSP_PATIENT_ID")
	private String hospPatientId;
	
	@XStreamAlias("IDCARD_TYPE")
	private String idcardType;
	
	@XStreamAlias("IDCARD_NO")
	private String idcardNo;
	
	@XStreamAlias("CARD_TYPE") 
	private String cardType;
	
	@XStreamAlias("CARD_NO")
	private String cardNo;
	
	@XStreamAlias("PATIENT_NAME")
	private String patientName;
	
	@XStreamAlias("PATIENT_SEX")
	private String patientSex;
	
	@XStreamAlias("PATIENT_AGE")
	private String patientAge;
	
	@XStreamAlias("QUERY_TYPE") 
	private String queryType;
	
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

	public String getIdcardType() {
		return idcardType;
	}

	public void setIdcardType(String idcardType) {
		this.idcardType = idcardType;
	}

	public String getIdcardNo() {
		return idcardNo;
	}

	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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
}
