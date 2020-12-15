package com.multibrand.vo.response.gmd;

import java.math.BigDecimal;
import java.util.List;


public class GmdMdDailyStmtResponse {

	
    private String fromDate;
    private String toDate;
    private BigDecimal cUsage;
    private BigDecimal billingAmount;
    private BigDecimal useChrg;
    private BigDecimal solarFee;
    private BigDecimal anclServ;
    private BigDecimal tduDely;
    private BigDecimal servQual;
    private BigDecimal membershipFee;
    private BigDecimal tax;
    private BigDecimal anciFee;
    private BigDecimal isoFee;
    private BigDecimal rucLrs;
    private BigDecimal cardRev;
    private BigDecimal loss;
    private BigDecimal cUsageAdj;
    private BigDecimal tdspAdj;
    List<GmdMdDailyStmtItemResponse> stmtDailyDaywise;
    
    
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the cUsage
	 */
	public BigDecimal getcUsage() {
		return cUsage;
	}
	/**
	 * @param cUsage the cUsage to set
	 */
	public void setcUsage(BigDecimal cUsage) {
		this.cUsage = cUsage;
	}
	/**
	 * @return the billingAmount
	 */
	public BigDecimal getBillingAmount() {
		return billingAmount;
	}
	/**
	 * @param billingAmount the billingAmount to set
	 */
	public void setBillingAmount(BigDecimal billingAmount) {
		this.billingAmount = billingAmount;
	}
	/**
	 * @return the useChrg
	 */
	public BigDecimal getUseChrg() {
		return useChrg;
	}
	/**
	 * @param useChrg the useChrg to set
	 */
	public void setUseChrg(BigDecimal useChrg) {
		this.useChrg = useChrg;
	}
	/**
	 * @return the solarFee
	 */
	public BigDecimal getSolarFee() {
		return solarFee;
	}
	/**
	 * @param solarFee the solarFee to set
	 */
	public void setSolarFee(BigDecimal solarFee) {
		this.solarFee = solarFee;
	}
	/**
	 * @return the anclServ
	 */
	public BigDecimal getAnclServ() {
		return anclServ;
	}
	/**
	 * @param anclServ the anclServ to set
	 */
	public void setAnclServ(BigDecimal anclServ) {
		this.anclServ = anclServ;
	}
	/**
	 * @return the tduDely
	 */
	public BigDecimal getTduDely() {
		return tduDely;
	}
	/**
	 * @param tduDely the tduDely to set
	 */
	public void setTduDely(BigDecimal tduDely) {
		this.tduDely = tduDely;
	}
	/**
	 * @return the servQual
	 */
	public BigDecimal getServQual() {
		return servQual;
	}
	/**
	 * @param servQual the servQual to set
	 */
	public void setServQual(BigDecimal servQual) {
		this.servQual = servQual;
	}
	/**
	 * @return the membershipFee
	 */
	public BigDecimal getMembershipFee() {
		return membershipFee;
	}
	/**
	 * @param membershipFee the membershipFee to set
	 */
	public void setMembershipFee(BigDecimal membershipFee) {
		this.membershipFee = membershipFee;
	}
	/**
	 * @return the tax
	 */
	public BigDecimal getTax() {
		return tax;
	}
	/**
	 * @param tax the tax to set
	 */
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	/**
	 * @return the anciFee
	 */
	public BigDecimal getAnciFee() {
		return anciFee;
	}
	/**
	 * @param anciFee the anciFee to set
	 */
	public void setAnciFee(BigDecimal anciFee) {
		this.anciFee = anciFee;
	}
	/**
	 * @return the isoFee
	 */
	public BigDecimal getIsoFee() {
		return isoFee;
	}
	/**
	 * @param isoFee the isoFee to set
	 */
	public void setIsoFee(BigDecimal isoFee) {
		this.isoFee = isoFee;
	}
	/**
	 * @return the rucLrs
	 */
	public BigDecimal getRucLrs() {
		return rucLrs;
	}
	/**
	 * @param rucLrs the rucLrs to set
	 */
	public void setRucLrs(BigDecimal rucLrs) {
		this.rucLrs = rucLrs;
	}
	/**
	 * @return the cardRev
	 */
	public BigDecimal getCardRev() {
		return cardRev;
	}
	/**
	 * @param cardRev the cardRev to set
	 */
	public void setCardRev(BigDecimal cardRev) {
		this.cardRev = cardRev;
	}
	/**
	 * @return the loss
	 */
	public BigDecimal getLoss() {
		return loss;
	}
	/**
	 * @param loss the loss to set
	 */
	public void setLoss(BigDecimal loss) {
		this.loss = loss;
	}
	/**
	 * @return the cUsageAdj
	 */
	public BigDecimal getcUsageAdj() {
		return cUsageAdj;
	}
	/**
	 * @param cUsageAdj the cUsageAdj to set
	 */
	public void setcUsageAdj(BigDecimal cUsageAdj) {
		this.cUsageAdj = cUsageAdj;
	}
	/**
	 * @return the tdspAdj
	 */
	public BigDecimal getTdspAdj() {
		return tdspAdj;
	}
	/**
	 * @param tdspAdj the tdspAdj to set
	 */
	public void setTdspAdj(BigDecimal tdspAdj) {
		this.tdspAdj = tdspAdj;
	}
	/**
	 * @return the stmtDailyDaywise
	 */
	public List<GmdMdDailyStmtItemResponse> getStmtDailyDaywise() {
		return stmtDailyDaywise;
	}
	/**
	 * @param stmtDailyDaywise the stmtDailyDaywise to set
	 */
	public void setStmtDailyDaywise(List<GmdMdDailyStmtItemResponse> stmtDailyDaywise) {
		this.stmtDailyDaywise = stmtDailyDaywise;
	}    
}
