package com.multibrand.vo.response.gmd;

import java.math.BigDecimal;


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
    
	
	
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	public BigDecimal getAnclServ() {
		return anclServ;
	}
	public void setAnclServ(BigDecimal anclServ) {
		this.anclServ = anclServ;
	}
	public BigDecimal getAnciFee() {
		return anciFee;
	}
	public void setAnciFee(BigDecimal anciFee) {
		this.anciFee = anciFee;
	}
	public BigDecimal getBillingAmount() {
		return billingAmount;
	}
	public void setBillingAmount(BigDecimal billingAmount) {
		this.billingAmount = billingAmount;
	}
	public BigDecimal getCardRev() {
		return cardRev;
	}
	public void setCardRev(BigDecimal cardRev) {
		this.cardRev = cardRev;
	}
	public BigDecimal getcUsage() {
		return cUsage;
	}
	public void setcUsage(BigDecimal cUsage) {
		this.cUsage = cUsage;
	}
	public BigDecimal getUseChrg() {
		return useChrg;
	}
	public void setUseChrg(BigDecimal useChrg) {
		this.useChrg = useChrg;
	}
	public BigDecimal getcUsageAdj() {
		return cUsageAdj;
	}
	public void setcUsageAdj(BigDecimal cUsageAdj) {
		this.cUsageAdj = cUsageAdj;
	}
	public BigDecimal getIsoFee() {
		return isoFee;
	}
	public void setIsoFee(BigDecimal isoFee) {
		this.isoFee = isoFee;
	}
	public BigDecimal getSolarFee() {
		return solarFee;
	}
	public void setSolarFee(BigDecimal solarFee) {
		this.solarFee = solarFee;
	}
	public BigDecimal getTduDely() {
		return tduDely;
	}
	public void setTduDely(BigDecimal tduDely) {
		this.tduDely = tduDely;
	}
	public BigDecimal getServQual() {
		return servQual;
	}
	public void setServQual(BigDecimal servQual) {
		this.servQual = servQual;
	}
	public BigDecimal getMembershipFee() {
		return membershipFee;
	}
	public void setMembershipFee(BigDecimal membershipFee) {
		this.membershipFee = membershipFee;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public BigDecimal getRucLrs() {
		return rucLrs;
	}
	public void setRucLrs(BigDecimal rucLrs) {
		this.rucLrs = rucLrs;
	}
	public BigDecimal getLoss() {
		return loss;
	}
	public void setLoss(BigDecimal loss) {
		this.loss = loss;
	}
	public BigDecimal getTdspAdj() {
		return tdspAdj;
	}
	public void setTdspAdj(BigDecimal tdspAdj) {
		this.tdspAdj = tdspAdj;
	}
	
	
	
    
    
}
