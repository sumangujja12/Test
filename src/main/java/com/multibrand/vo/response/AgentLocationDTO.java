package com.multibrand.vo.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class AgentLocationDTO {
  
	@SerializedName("results")
    private List<AgentLocationDTOResult> resultss;

	public List<AgentLocationDTOResult> getResultss() {
		return resultss;
	}

	public void setResultss(List<AgentLocationDTOResult> resultss) {
		this.resultss = resultss;
	}
	
	

}