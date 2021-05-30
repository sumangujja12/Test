package com.multibrand.vo.response.tosResponse;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;
import com.multibrand.vo.response.ContractOffer;
import com.multibrand.vo.response.GenericResponse;

@Component
@XmlRootElement(name="ContractOfferPlanContentResponse")
public class OfferPlanContentResponse extends GenericResponse {
		
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
	
}
