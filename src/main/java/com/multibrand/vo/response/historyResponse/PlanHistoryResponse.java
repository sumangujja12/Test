package com.multibrand.vo.response.historyResponse;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name="PlanHistoryResponse")
public class PlanHistoryResponse extends GenericResponse{

	private PlanHistory[] planHistory;

	public PlanHistory[] getPlanHistory() {
		return planHistory;
	}

	public void setPlanHistory(PlanHistory[] planHistory) {
		this.planHistory = planHistory;
	}
}
