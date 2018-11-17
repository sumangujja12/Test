package com.multibrand.vo.response.historyResponse.xi;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author ahanda1
 * Response POJO for XI InvoiceList call. To be used with JAXB for marshalling and unmarshalling
 * MTGetInvoiceListResponse will contain object of this class
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvoiceList_Response", namespace="http://reliant.com/xi/GreenMountain")
@XmlRootElement(name="InvoiceList_Response", namespace="http://reliant.com/xi/GreenMountain")
public class InvoiceListResponse {

	@XmlElement(name = "row")
    protected List<RowInvoiceList> row;

	public List<RowInvoiceList> getRow() {
		return row;
	}

	public void setRow(List<RowInvoiceList> row) {
		this.row = row;
	}
	
}
