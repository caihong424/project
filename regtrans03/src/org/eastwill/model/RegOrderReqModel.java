package org.eastwill.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("REQ")
public class RegOrderReqModel {
	@XStreamAlias("ORDER_ID")
	private String orderId;
	
	@XStreamAlias("HOSP_PATIENT_ID")
	private String hospPatientId;
	
	@XStreamAlias("CHANNEL_ID")
	private String channelId;
	
	@XStreamAlias("IS_REG")
	private String isReg;
	
	@XStreamAlias("REG_ID")
	private String regId;
	
	@XStreamAlias("REG_LEVEL")
	private String regLevel;
	
	@XStreamAlias("HOS_ID")
	private String hosId;
	
	@XStreamAlias("DEPT_ID") 
	private String deptId;
	
	@XStreamAlias("DOCTOR_ID")
	private String doctorId;
	
	@XStreamAlias("REG_DATE")
	private String regDate;
	
	@XStreamAlias("TIME_FLAG")
	private String timeFlag;
	
	@XStreamAlias("BEGIN_TIME")
	private String beginTime;
	
	@XStreamAlias("END_TIME")
	private String endTime;
	
	@XStreamAlias("REG_FEE")
	private String regFee;
	
	@XStreamAlias("TREAT_FEE")
	private String treatFee;
	
	@XStreamAlias("REG_TYPE")
	private String regType;
	
	@XStreamAlias("IDCARD_TYPE")
	private String idcardType;
	
	@XStreamAlias("IDCARD_NO")
	private String idcardNo;
	
	@XStreamAlias("CARD_TYPE")
	private String cardType;
	
	@XStreamAlias("CARD_NO")
	private String cardNo;
	
	@XStreamAlias("NAME")
	private String name;
	
	@XStreamAlias("SEX")
	private String sex;
	
	@XStreamAlias("BIRTHDAY")
	private String birthday;
	
	@XStreamAlias("ADDRESS")
	private String address;
	
	@XStreamAlias("MOBILE")
	private String mobile;
	
	@XStreamAlias("OPER_IDCARD_TYPE")
	private String operIdcardType;
	
	@XStreamAlias("OPER_IDCARD_NO")
	private String operIdcardNo;
	
	@XStreamAlias("OPER_NAME")
	private String operName;
	
	@XStreamAlias("OPER_MOBILE")
	private String operMobile;
	
	@XStreamAlias("AGENT_ID")
	private String agentId;
	
	@XStreamAlias("ORDER_TIME")
	private String orderTime;

	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getHospPatientId() {
		return hospPatientId;
	}

	public void setHospPatientId(String hospPatientId) {
		this.hospPatientId = hospPatientId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getIsReg() {
		return isReg;
	}

	public void setIsReg(String isReg) {
		this.isReg = isReg;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegLevel() {
		return regLevel;
	}

	public void setRegLevel(String regLevel) {
		this.regLevel = regLevel;
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

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getTimeFlag() {
		return timeFlag;
	}

	public void setTimeFlag(String timeFlag) {
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

	public String getRegFee() {
		return regFee;
	}

	public void setRegFee(String regFee) {
		this.regFee = regFee;
	}

	public String getTreatFee() {
		return treatFee;
	}

	public void setTreatFee(String treatFee) {
		this.treatFee = treatFee;
	}

	public String getRegType() {
		return regType;
	}

	public void setRegType(String regType) {
		this.regType = regType;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOperIdcardType() {
		return operIdcardType;
	}

	public void setOperIdcardType(String operIdcardType) {
		this.operIdcardType = operIdcardType;
	}

	public String getOperIdcardNo() {
		return operIdcardNo;
	}

	public void setOperIdcardNo(String operIdcardNo) {
		this.operIdcardNo = operIdcardNo;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getOperMobile() {
		return operMobile;
	}

	public void setOperMobile(String operMobile) {
		this.operMobile = operMobile;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}	
}
