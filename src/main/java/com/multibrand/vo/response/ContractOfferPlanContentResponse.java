package com.multibrand.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component
@XmlRootElement(name="ContractOfferPlanContentResponse")
public class ContractOfferPlanContentResponse extends GenericResponse {
		
	private List<ContractOffer> contractOffer;
	
	
	/**
	 * @return the contractOffer
	 */
	public List<ContractOffer> getContractOffer() {
		return contractOffer;
	}
	/**
	 * @param contractOffer the contractOffer to set
	 */
	public void setContractOffer(List<ContractOffer> contractOffer) {
		this.contractOffer = contractOffer;
	}
	
	
	
	
}
