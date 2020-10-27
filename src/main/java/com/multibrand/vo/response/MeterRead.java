package com.multibrand.vo.response;
/**
 * Start | PBI 52935 | MBAR: Sprint 17 -ERCOT ESID LOOKUP REST IMPL | Jyothi | 9/21/2020
 */
public class MeterRead {

    private String CalculationDate;
    private String Flag;
    private String Code;
    private String SettlementAMSIndicator;
    
	public String getCalculationDate() {
		return CalculationDate;
	}
	public void setCalculationDate(String calculationDate) {
		this.CalculationDate = calculationDate;
	}
	public String getFlag() {
		return Flag;
	}
	public void setFlag(String flag) {
		this.Flag = flag;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		this.Code = code;
	}
	public String getSettlementAMSIndicator() {
		return SettlementAMSIndicator;
	}
	public void setSettlementAMSIndicator(String settlementAMSIndicator) {
		this.SettlementAMSIndicator = settlementAMSIndicator;
	}   
	
}
