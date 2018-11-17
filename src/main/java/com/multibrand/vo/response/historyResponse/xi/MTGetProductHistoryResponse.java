package com.multibrand.vo.response.historyResponse.xi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author ahanda1
 * Main Response POJO for XI Product History call. To be used with JAXB for marshalling and unmarshalling
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MT_getProductHistory_Response", namespace="http://reliant.com/xi/GreenMountain")
@XmlRootElement(name="MT_getProductHistory_Response", namespace="http://reliant.com/xi/GreenMountain")
public class MTGetProductHistoryResponse {
	
	@XmlElement(name = "ProductHistory_Response")
    protected ProductHistoryResponse productHistoryResponse;

	public ProductHistoryResponse getProductHistoryResponse() {
		return productHistoryResponse;
	}

	public void setProductHistoryResponse(
			ProductHistoryResponse productHistoryResponse) {
		this.productHistoryResponse = productHistoryResponse;
	}

}
