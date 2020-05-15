package com.multibrand.vo.response.billingResponse;

import java.util.LinkedList;
import java.util.List;

import com.multibrand.vo.response.GenericResponse;

public class DPPExtensionCheckResponse extends GenericResponse {

	private String totalDppAmount;
	private DppValueVO dppAmountToBePaid;
	private List<DppValueVO> dppValue = new LinkedList<>();
	private List<DppInstPlanDetailsDTO> dppInstPlanDetailsList = new LinkedList<>();
	private boolean isDppPlanEligible;
	private boolean isDppPlanPending;
	private boolean isDppPlanActive;
	/**
	 * @return the totalDppAmount
	 */
	public String getTotalDppAmount() {
		return totalDppAmount;
	}
	/**
	 * @param totalDppAmount the totalDppAmount to set
	 */
	public void setTotalDppAmount(String totalDppAmount) {
		this.totalDppAmount = totalDppAmount;
	}
	/**
	 * @return the dppAmountToBePaid
	 */
	public DppValueVO getDppAmountToBePaid() {
		return dppAmountToBePaid;
	}
	/**
	 * @param dppAmountToBePaid the dppAmountToBePaid to set
	 */
	public void setDppAmountToBePaid(DppValueVO dppAmountToBePaid) {
		this.dppAmountToBePaid = dppAmountToBePaid;
	}
	/**
	 * @return the dppValue
	 */
	public List<DppValueVO> getDppValue() {
		return dppValue;
	}
	/**
	 * @param dppValue the dppValue to set
	 */
	public void setDppValue(List<DppValueVO> dppValue) {
		this.dppValue = dppValue;
	}
	/**
	 * @return the dppInstPlanDetailsList
	 */
	public List<DppInstPlanDetailsDTO> getDppInstPlanDetailsList() {
		return dppInstPlanDetailsList;
	}
	/**
	 * @param dppInstPlanDetailsList the dppInstPlanDetailsList to set
	 */
	public void setDppInstPlanDetailsList(List<DppInstPlanDetailsDTO> dppInstPlanDetailsList) {
		this.dppInstPlanDetailsList = dppInstPlanDetailsList;
	}
	/**
	 * @return the isDppPlanEligible
	 */
	public boolean isDppPlanEligible() {
		return isDppPlanEligible;
	}
	/**
	 * @param isDppPlanEligible the isDppPlanEligible to set
	 */
	public void setDppPlanEligible(boolean isDppPlanEligible) {
		this.isDppPlanEligible = isDppPlanEligible;
	}
	/**
	 * @return the isDppPlanPending
	 */
	public boolean isDppPlanPending() {
		return isDppPlanPending;
	}
	/**
	 * @param isDppPlanPending the isDppPlanPending to set
	 */
	public void setDppPlanPending(boolean isDppPlanPending) {
		this.isDppPlanPending = isDppPlanPending;
	}
	/**
	 * @return the isDppPlanActive
	 */
	public boolean isDppPlanActive() {
		return isDppPlanActive;
	}
	/**
	 * @param isDppPlanActive the isDppPlanActive to set
	 */
	public void setDppPlanActive(boolean isDppPlanActive) {
		this.isDppPlanActive = isDppPlanActive;
	}
	
	

}

