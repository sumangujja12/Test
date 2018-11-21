package com.multibrand.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="AgentDetailsResponseOutData")
public class AgentDetailsResponseOutData {
	
	@SerializedName("results")
	private List<AgentDetailsResponseOutDataResult> result;

	public List<AgentDetailsResponseOutDataResult> getResult() {
		return result;
	}

	public void setResult(List<AgentDetailsResponseOutDataResult> result) {
		this.result = result;
	}

}
