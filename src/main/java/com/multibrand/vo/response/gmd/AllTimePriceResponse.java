package com.multibrand.vo.response.gmd;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import com.multibrand.vo.response.GenericResponse;

@XmlRootElement
@XmlSeeAlso({AllTimePriceResponse.class})
public class AllTimePriceResponse extends GenericResponse {

    private BigDecimal avgAllTimePrice;

	/**
	 * @return the avgAllTimePrice
	 */
	public BigDecimal getAvgAllTimePrice() {
		return avgAllTimePrice;
	}

	/**
	 * @param avgAllTimePrice the avgAllTimePrice to set
	 */
	public void setAvgAllTimePrice(BigDecimal avgAllTimePrice) {
		this.avgAllTimePrice = avgAllTimePrice;
	}

}
