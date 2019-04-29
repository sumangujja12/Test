package com.multibrand.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component
@XmlRootElement(name="ContractOfferPlanContentResponse")
public class ContractOfferPlanContentResponse extends GenericResponse {
		
	private ContractOffer currentPlan;
	private List<ContractOffer> plans;

	/**
	 * @return the plans
	 */
	public List<ContractOffer> getPlans() {
		return plans;
	}

	/**
	 * @param plans the plans to set
	 */
	public void setPlans(List<ContractOffer> plans) {
		this.plans = plans;
	}

	/**
	 * @return the currentPlan
	 */
	public ContractOffer getCurrentPlan() {
		return currentPlan;
	}

	/**
	 * @param currentPlan the currentPlan to set
	 */
	public void setCurrentPlan(ContractOffer currentPlan) {
		this.currentPlan = currentPlan;
	}
	
	
	
	
	
	
	
	
	
	
}
