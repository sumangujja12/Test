package com.multibrand.vo.response.historyResponse;

import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;
import com.multibrand.vo.response.GenericResponse;


@XmlRootElement(name="PaymentHistory")
@Component
public class PaymentHistoryResponse extends GenericResponse 
{
	private PaymentDO[] paymentDO;

	public PaymentDO[] getPaymentDO() {
		return paymentDO;
	}

	public void setPaymentDO(PaymentDO[] paymentDO) {
		this.paymentDO = paymentDO;
	}	
}
