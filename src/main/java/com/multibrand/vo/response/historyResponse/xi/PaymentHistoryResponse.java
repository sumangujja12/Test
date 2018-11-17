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
 * Response POJO for XI payment history call. To be used with JAXB for marshalling and unmarshalling
 * MTGetPaymentHistoryResponse will contain object of this class
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Payment_History_Response", namespace="http://reliant.com/xi/GreenMountain")
@XmlRootElement(name="Payment_History_Response", namespace="http://reliant.com/xi/GreenMountain")
public class PaymentHistoryResponse {

	@XmlElement(name = "row")
    protected List<Row> row;

	public List<Row> getRow() {
		return row;
	}

	public void setRow(List<Row> row) {
		this.row = row;
	}
	
	
}
