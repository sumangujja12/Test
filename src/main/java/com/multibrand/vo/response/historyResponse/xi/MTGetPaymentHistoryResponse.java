package com.multibrand.vo.response.historyResponse.xi;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author ahanda1
 * Main Response POJO for XI payment history call. To be used with JAXB for marshalling and unmarshalling
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MT_getPaymentHistory_Response", namespace="http://reliant.com/xi/GreenMountain")
@XmlRootElement(name="MT_getPaymentHistory_Response", namespace="http://reliant.com/xi/GreenMountain")
public class MTGetPaymentHistoryResponse {
	@XmlElement(name = "Payment_History_Response")
    protected PaymentHistoryResponse paymentHistoryResponse;

	public PaymentHistoryResponse getPaymentHistoryResponse() {
		return paymentHistoryResponse;
	}

	public void setPaymentHistoryResponse(
			PaymentHistoryResponse paymentHistoryResponse) {
		this.paymentHistoryResponse = paymentHistoryResponse;
	}

	
	

}
