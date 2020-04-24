package com.multibrand.vo.response.gmd;

import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;
import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name="GMDPricingResponse")
@Component

public class GMDPricingResponse extends GenericResponse{
	
	private Pricing pricing;

	/**
	 * @return the pricing
	 */
	public Pricing getPricing() {
		return pricing;
	}

	/**
	 * @param pricing the pricing to set
	 */
	public void setPricing(Pricing pricing) {
		this.pricing = pricing;
	}
}
