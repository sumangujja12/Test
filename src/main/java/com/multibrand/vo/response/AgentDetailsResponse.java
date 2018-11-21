package com.multibrand.vo.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="AgentDetailsResponse")
public class AgentDetailsResponse extends GenericResponse implements Serializable {

	private static final long serialVersionUID = -3671204145307613817L;
	
	@SerializedName("d")
	AgentDetailsResponseOutData agentDetailsResponseOutData;

	public AgentDetailsResponseOutData getAgentDetailsResponseOutData() {
		return agentDetailsResponseOutData;
	}


	public void setAgentDetailsResponseOutData(AgentDetailsResponseOutData agentDetailsResponseOutData) {
		this.agentDetailsResponseOutData = agentDetailsResponseOutData;
	}

	@Override
	public String toString() {
		return "AgentDetailsResponse [agentDetailsResponseOutData="
				+ agentDetailsResponseOutData + "]";
	}
	
}
