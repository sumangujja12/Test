package com.multibrand.vo.response.historyResponse;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;
import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name="schedulePaymentResponse")
@Component
public class SchedulePaymentResponse extends GenericResponse{
	
	private List<PaymentDO> pendingPayments;
	private String lastPaymentDate;
	
	public List<PaymentDO> getPendingPayments() {
		return pendingPayments;
	}
	public void setPendingPayments(List<PaymentDO> pendingPayments) {
		this.pendingPayments = pendingPayments;
	}
	public String getLastPaymentDate() {
		return lastPaymentDate;
	}
	public void setLastPaymentDate(String lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}
	

}
