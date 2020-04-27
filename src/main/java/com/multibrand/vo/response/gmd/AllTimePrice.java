package com.multibrand.vo.response.gmd;

import javax.xml.bind.annotation.XmlRootElement;
import com.multibrand.vo.response.UsageInterface;

@XmlRootElement(name = "allTimePrice")
public class AllTimePrice implements UsageInterface
{

	private String avgAllTimePrice;

	/**
	 * @return the avgAllTimePrice
	 */
	public String getAvgAllTimePrice() {
		return avgAllTimePrice;
	}

	/**
	 * @param avgAllTimePrice the avgAllTimePrice to set
	 */
	public void setAvgAllTimePrice(String avgAllTimePrice) {
		this.avgAllTimePrice = avgAllTimePrice;
	}
	
	
	
	
}
