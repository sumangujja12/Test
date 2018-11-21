package com.multibrand.vo.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class AgentPartnerDTO {
  
	
	@SerializedName("results")
    private List<AgentPartnerDTOResult> results;

	public List<AgentPartnerDTOResult> getResults() {
		return results;
	}

	public void setResults(List<AgentPartnerDTOResult> results) {
		this.results = results;
	}

}