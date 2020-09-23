package com.multibrand.vo.response;
/**
 * Start | PBI 52935 | MBAR: Sprint 17 -ERCOT ESID LOOKUP REST IMPL | Jyothi | 9/21/2020
 */
public class MeterRead {

    public String calculationDate;
    public boolean flag;
    public String code;
    public boolean settlementAMSIndicator;
    
	public String getCalculationDate() {
		return calculationDate;
	}
	public void setCalculationDate(String calculationDate) {
		this.calculationDate = calculationDate;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isSettlementAMSIndicator() {
		return settlementAMSIndicator;
	}
	public void setSettlementAMSIndicator(boolean settlementAMSIndicator) {
		this.settlementAMSIndicator = settlementAMSIndicator;
	}    
}
