package org.eastwill.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("REQ")
public class UnLockRegReqModel {
	@XStreamAlias("HOS_ID")
	private String hosId;
	
	@XStreamAlias("LOCK_ID")
	private String lockId;

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getLockId() {
		return lockId;
	}

	public void setLockId(String lockId) {
		this.lockId = lockId;
	}
	
	

}
