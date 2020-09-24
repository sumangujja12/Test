package com.multibrand.vo.response;

/**
 * Start | PBI 52935 | MBAR: Sprint 17 -ERCOT ESID LOOKUP REST IMPL | Jyothi | 9/21/2020
 */

public class RepOfRecord {
 
    private String Flag; 
    private String RoRStartDate;
    
	public String getFlag() {
		return Flag;
	}
	public void setFlag(String flag) {
		this.Flag = flag;
	}
	public String getRoRStartDate() {
		return RoRStartDate;
	}
	public void setRoRStartDate(String roRStartDate) {
		this.RoRStartDate = roRStartDate;
	}   
	
}
