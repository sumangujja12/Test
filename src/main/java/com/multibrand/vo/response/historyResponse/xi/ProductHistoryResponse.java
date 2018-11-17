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
 * Response POJO for XI product history call. To be used with JAXB for marshalling and unmarshalling
 * MTGetProductHistoryResponse will contain object of this class
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductHistory_Response", namespace="http://reliant.com/xi/GreenMountain")
@XmlRootElement(name="ProductHistory_Response", namespace="http://reliant.com/xi/GreenMountain")
public class ProductHistoryResponse {
	
	@XmlElement(name = "row")
    protected List<RowProductHistory> row;

	public List<RowProductHistory> getRow() {
		return row;
	}

	public void setRow(List<RowProductHistory> row) {
		this.row = row;
	}

}
