package com.multibrand.vo.response.historyResponse.xi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author ahanda1
 * Main Response POJO for XI invoice list call. To be used with JAXB for marshalling and unmarshalling
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MT_get_InvoiceList_Response", namespace="http://reliant.com/xi/GreenMountain")
@XmlRootElement(name="MT_get_InvoiceList_Response", namespace="http://reliant.com/xi/GreenMountain")
public class MTGetInvoiceListResponse {

	@XmlElement(name = "InvoiceList_Response")
    protected InvoiceListResponse invoiceListResponse;

	public InvoiceListResponse getInvoiceListResponse() {
		return invoiceListResponse;
	}

	public void setInvoiceListResponse(
			InvoiceListResponse invoiceListResponse) {
		this.invoiceListResponse = invoiceListResponse;
	}
}
