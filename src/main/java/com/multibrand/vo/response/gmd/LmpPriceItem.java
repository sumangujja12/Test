package com.multibrand.vo.response.gmd;

import java.math.BigDecimal;

public class LmpPriceItem {

	private String zzone;
	private BigDecimal profvalue;
	private String profdate;
	private String proftime;

	public String getZzone() {
		return zzone;
	}

	public void setZzone(String zzone) {
		this.zzone = zzone;
	}

	public BigDecimal getProfvalue() {
		return profvalue;
	}

	public void setProfvalue(BigDecimal profvalue) {
		this.profvalue = profvalue;
	}

	public String getProfdate() {
		return profdate;
	}

	public void setProfdate(String profdate) {
		this.profdate = profdate;
	}

	public String getProftime() {
		return proftime;
	}

	public void setProftime(String proftime) {
		this.proftime = proftime;
	}

}
