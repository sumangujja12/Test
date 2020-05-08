package com.multibrand.vo.response.billingResponse;

import java.util.LinkedList;
import java.util.List;

import com.multibrand.vo.response.GenericResponse;

public class DPPExtensionCheckResponse extends GenericResponse {

	private String totalDppAmount;
	private DppValueVO dppAmountToBePaid;
	private List<DppValueVO> dppValue = new LinkedList<>();
	private List<DppInstPlanDetailsDTO> dppInstPlanDetailsList = new LinkedList<>();
	private boolean isDppEligible;
	private boolean isDppPending;
	
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
	 * @return the isDppEligible
	 */
	public boolean isDppEligible() {
		return isDppEligible;
	}
	/**
	 * @param isDppEligible the isDppEligible to set
	 */
	public void setDppEligible(boolean isDppEligible) {
		this.isDppEligible = isDppEligible;
	}
	/**
	 * @return the isDppPending
	 */
	public boolean isDppPending() {
		return isDppPending;
	}
	/**
	 * @param isDppPending the isDppPending to set
	 */
	public void setDppPending(boolean isDppPending) {
		this.isDppPending = isDppPending;
	}
	
}

