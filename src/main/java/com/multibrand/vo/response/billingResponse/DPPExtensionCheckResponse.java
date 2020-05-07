package com.multibrand.vo.response.billingResponse;

import java.util.LinkedList;
import java.util.List;
import com.multibrand.vo.response.GenericResponse;

public class DPPExtensionCheckResponse extends GenericResponse {
	
	private boolean isPaymentExtension = false;
	private List<DppValueVO> dppValue = new LinkedList<DppValueVO>();
	private List<DppInstPlanDetailsDTO> dppInstPlanDetailsList = new LinkedList<DppInstPlanDetailsDTO>();
	private String dpplanActive = "";
	private String dpplanEligible = "";
	private String dppplanPending = "";
	private String amount ="";
	private String dppDescription ="";
	
	/**
	 * @return the isPaymentExtension
	 */
	public boolean isPaymentExtension() {
		return isPaymentExtension;
	}
	/**
	 * @param isPaymentExtension the isPaymentExtension to set
	 */
	public void setPaymentExtension(boolean isPaymentExtension) {
		this.isPaymentExtension = isPaymentExtension;
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
	 * @return the dpplanActive
	 */
	public String getDpplanActive() {
		return dpplanActive;
	}
	/**
	 * @param dpplanActive the dpplanActive to set
	 */
	public void setDpplanActive(String dpplanActive) {
		this.dpplanActive = dpplanActive;
	}
	/**
	 * @return the dpplanEligible
	 */
	public String getDpplanEligible() {
		return dpplanEligible;
	}
	/**
	 * @param dpplanEligible the dpplanEligible to set
	 */
	public void setDpplanEligible(String dpplanEligible) {
		this.dpplanEligible = dpplanEligible;
	}
	/**
	 * @return the dppplanPending
	 */
	public String getDppplanPending() {
		return dppplanPending;
	}
	/**
	 * @param dppplanPending the dppplanPending to set
	 */
	public void setDppplanPending(String dppplanPending) {
		this.dppplanPending = dppplanPending;
	}
	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * @return the dppDescription
	 */
	public String getDppDescription() {
		return dppDescription;
	}
	/**
	 * @param dppDescription the dppDescription to set
	 */
	public void setDppDescription(String dppDescription) {
		this.dppDescription = dppDescription;
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
}
