package org.eastwill.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("REQ")
public class LockRegReqModel {
	@XStreamAlias("LOCK_ID")
	private String lockId;
	
	@XStreamAlias("CHANNEL_ID")
	private String channelId;
	
	@XStreamAlias("REG_ID")
	private String regId;
	
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
	
	@XStreamAlias("AGENT_ID")
	private String agentId;

	
	public String getLockId() {
		return lockId;
	}

	public void setLockId(String lockId) {
		this.lockId = lockId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
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

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}	
}
