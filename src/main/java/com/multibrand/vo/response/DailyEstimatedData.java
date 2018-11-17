package com.multibrand.vo.response;

import java.math.BigDecimal;

public class DailyEstimatedData {

	
	private BigDecimal billAmount;
	private String billDate;	
	private BigDecimal zusage;
	/*
	private String aedat;
	private String aenam;
	private String bukrs;
	private String erdat;
	private String ername;
	private String extUi;
	private String mandt;
	private String processFlag;
	private BigDecimal simCustChgF;
	private BigDecimal simCustChgV;
	private BigDecimal simEnergyChgF;
	private BigDecimal simEnergyChgV;
	private BigDecimal simIncentiveF;
	private BigDecimal simIncentiveV;
	private BigDecimal simLidaChgF;
	private BigDecimal simLidaChgV;
	private BigDecimal simOtherChgF;
	private BigDecimal simOtherChgV;
	private BigDecimal simPenaltyV;
	private BigDecimal simPenaltyF;
	private BigDecimal simReccTdspF;
	private BigDecimal simReccTdspV;
	private BigDecimal simTaxesF;
	private BigDecimal simTaxesV;
	private String tariftyp;
	private String timeChanged;
	private String timeCreated;
	private String vertrag;*/
	
	/**
	 * @return the billAmount
	 */
	public BigDecimal getBillAmount() {
		return billAmount;
	}
	/**
	 * @param billAmount the billAmount to set
	 */
	public void setBillAmount(BigDecimal billAmount) {
		this.billAmount = billAmount;
	}
	/**
	 * @return the billDate
	 */
	public String getBillDate() {
		return billDate;
	}
	/**
	 * @param billDate the billDate to set
	 */
	public void setBillDate(String billDate) {
		if(null != billDate && billDate.length() > 0)
			this.billDate = billDate.replaceAll("-", "");
		else
			this.billDate = billDate;
	}
	/**
	 * @return the zusage
	 */
	public BigDecimal getZusage() {
		return zusage;
	}
	/**
	 * @param zusage the zusage to set
	 */
	public void setZusage(BigDecimal zusage) {
		this.zusage = zusage;
	}
	
}
