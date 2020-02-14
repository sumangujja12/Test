package com.multibrand.vo.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WseResponse {
	@SerializedName("wse")
	@Expose
	private WseDO wse;
	
	public WseDO getWse() {
	return wse;
	}
	
	public void setWse(WseDO wse) {
	this.wse = wse;
	}
}