package org.eastwill.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("REQ")
public class NetTestRequstParamModel {
	@XStreamAlias("HOS_ID")
	private String hosId;
	
	@XStreamAlias("IP")
	private String ip;

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
