package com.multibrand.vo.response;

/**
 * Start | PBI 52935 | MBAR: Sprint 17 -ERCOT ESID LOOKUP REST IMPL | Jyothi | 9/21/2020
 */

public class CSA {

    private String CsaStartDate;
    private String Duns;
    
	public String getCsaStartDate() {
		return CsaStartDate;
	}
	public void setCsaStartDate(String csaStartDate) {
		CsaStartDate = csaStartDate;
	}
	public String getDuns() {
		return Duns;
	}
	public void setDuns(String duns) {
		Duns = duns;
	}
    	
}
