package com.multibrand.vo.response.billingResponse;



import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;


import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name="SwapEligibility")
@Component
public class CheckSwapEligibilityResponse extends GenericResponse {

	private GMEContractAccountDO contractAccountDO;
	
	private String yearlyTreesAbsorbed;

	public GMEContractAccountDO getContractAccountDO() {
		return contractAccountDO;
	}

	public void setContractAccountDO(GMEContractAccountDO contractAccountDO) {
		this.contractAccountDO = contractAccountDO;
	}


	public String getYearlyTreesAbsorbed() {
		return yearlyTreesAbsorbed;
	}

	public void setYearlyTreesAbsorbed(String yearlyTreesAbsorbed) {
		this.yearlyTreesAbsorbed = yearlyTreesAbsorbed;
	}
	
}
