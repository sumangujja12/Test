package com.multibrand.vo.response;

/**
 * Start | PBI 52935 | MBAR: Sprint 17 -ERCOT ESID LOOKUP REST IMPL | Jyothi | 9/21/2020
 */

public class RepOfRecord {
 
    public boolean flag; 
    public String roRStartDate;
    
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getRoRStartDate() {
		return roRStartDate;
	}
	public void setRoRStartDate(String roRStartDate) {
		this.roRStartDate = roRStartDate;
	}
}
