package com.multibrand.dto;

import java.io.Serializable;

import com.multibrand.util.CommonUtil;

/**
 * 
 * @author vsood30
 *
 */
public class PrepayDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1246787483841106985L;
	private String threshholdAmount;
	private String refillAmount;
	private String totalToday;
	private String ppStartServiceDate;
	private PaymentDTO ppAutopayDTO;
	
	public String getThreshholdAmount() {
		return threshholdAmount;
	}
	public void setThreshholdAmount(String threshholdAmount) {
		this.threshholdAmount = threshholdAmount;
	}
	public String getRefillAmount() {
		return refillAmount;
	}
	public void setRefillAmount(String refillAmount) {
		this.refillAmount = refillAmount;
	}
	public String getTotalToday() {
		return totalToday;
	}
	public void setTotalToday(String totalToday) {
		this.totalToday = totalToday;
	}
	public String getPpStartServiceDate() {
		return ppStartServiceDate;
	}
	public void setPpStartServiceDate(String ppStartServiceDate) {
		this.ppStartServiceDate = ppStartServiceDate;
	}
	public PaymentDTO getPpAutopayDTO() {
		return ppAutopayDTO;
	}
	public void setPpAutopayDTO(PaymentDTO ppAutopayDTO) {
		this.ppAutopayDTO = ppAutopayDTO;
	}
	
	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}
	
}
