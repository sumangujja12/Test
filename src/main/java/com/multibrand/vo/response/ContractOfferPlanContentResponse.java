package com.multibrand.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component
@XmlRootElement(name="ContractOfferPlanContentResponse")
public class ContractOfferPlanContentResponse extends GenericResponse {
		
	private ContractOffer currentPlan;
	private List<ContractOffer> plans;
	private ServiceAddressDO serviceAddress;
	private boolean renewalOffers = false;
	private boolean swapOffers = false;
	private String swapPendingDate ="";
	private boolean pendingSwap = false;
	private String yearlyTreesAbsorbed = "";

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

	/**
	 * @return the serviceAddress
	 */
	public ServiceAddressDO getServiceAddress() {
		return serviceAddress;
	}

	/**
	 * @param serviceAddress the serviceAddress to set
	 */
	public void setServiceAddress(ServiceAddressDO serviceAddress) {
		this.serviceAddress = serviceAddress;
	}

	/**
	 * @return the renewalOffers
	 */
	public boolean isRenewalOffers() {
		return renewalOffers;
	}

	/**
	 * @param renewalOffers the renewalOffers to set
	 */
	public void setRenewalOffers(boolean renewalOffers) {
		this.renewalOffers = renewalOffers;
	}

	/**
	 * @return the swapOffers
	 */
	public boolean isSwapOffers() {
		return swapOffers;
	}

	/**
	 * @param swapOffers the swapOffers to set
	 */
	public void setSwapOffers(boolean swapOffers) {
		this.swapOffers = swapOffers;
	}

	/**
	 * @return the swapPendingDate
	 */
	public String getSwapPendingDate() {
		return swapPendingDate;
	}

	/**
	 * @param swapPendingDate the swapPendingDate to set
	 */
	public void setSwapPendingDate(String swapPendingDate) {
		this.swapPendingDate = swapPendingDate;
	}

	/**
	 * @return the pendingSwap
	 */
	public boolean isPendingSwap() {
		return pendingSwap;
	}

	/**
	 * @param pendingSwap the pendingSwap to set
	 */
	public void setPendingSwap(boolean pendingSwap) {
		this.pendingSwap = pendingSwap;
	}

	/**
	 * @return the yearlyTreesAbsorbed
	 */
	public String getYearlyTreesAbsorbed() {
		return yearlyTreesAbsorbed;
	}

	/**
	 * @param yearlyTreesAbsorbed the yearlyTreesAbsorbed to set
	 */
	public void setYearlyTreesAbsorbed(String yearlyTreesAbsorbed) {
		this.yearlyTreesAbsorbed = yearlyTreesAbsorbed;
	}
	
}
