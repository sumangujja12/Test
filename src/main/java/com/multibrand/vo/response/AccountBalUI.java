package com.multibrand.vo.response;

public class AccountBalUI {

	private ActBalSummaryS actBalSummaryS;
	
	private ActBalDetailTt actBalDetailTt[];

	@javax.xml.bind.annotation.XmlElement(name="monthlySummary")
	public ActBalSummaryS getActBalSummaryS() {
		return actBalSummaryS;
	}

	public void setActBalSummaryS(ActBalSummaryS actBalSummaryS) {
		this.actBalSummaryS = actBalSummaryS;
	}

	@javax.xml.bind.annotation.XmlElement(name="accountActivityDetails")
	public ActBalDetailTt[] getActBalDetailTt() {
		return actBalDetailTt;
	}

	public void setActBalDetailTt(ActBalDetailTt[] actBalDetailTt) {
		this.actBalDetailTt = actBalDetailTt;
	}

}