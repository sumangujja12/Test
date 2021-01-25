package com.multibrand.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.multibrand.dto.request.NEIPaypalPaymentRequest;
import com.multibrand.exception.NRGException;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.nrg.cxfstubs.nei.paypal.ZEISUNEIPAYPALPAYMENTResponse;
import com.nrg.cxfstubs.nei.paypal.ZEISUNEIPAYPALPAYMENT_Type;

@Service
public class NEIPaypalPaymentService extends BaseAbstractService {

	@Autowired
	@Qualifier("webServiceTemplateForPaypalPayment")
	private WebServiceTemplate webServiceTemplateForPaypalPayment;

	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;

	/**
	 * This profile call will do the call to the logging framework
	 * 
	 * @param accountNumber
	 * @param companyCode
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */

	public ZEISUNEIPAYPALPAYMENTResponse paypalBillPayment(NEIPaypalPaymentRequest paypalPaymentRequest, String sessionId)
			throws Exception {

		logger.info("{}:****PaypalPayBillPaymentService:submitPaypalBillPayment::::::::::::::::::::Start:", sessionId);

		long startTime = CommonUtil.getStartTime();
		Long endTime = null;
		String companyCode = "NEI";
		ZEISUNEIPAYPALPAYMENTResponse zResVruPaypalPaymentResponse = null;

		try {
			com.nrg.cxfstubs.nei.paypal.ObjectFactory factory = new com.nrg.cxfstubs.nei.paypal.ObjectFactory();

			ZEISUNEIPAYPALPAYMENT_Type wsRequest = factory.createZEISUNEIPAYPALPAYMENT_Type();

			wsRequest.setIMUSERNAME(paypalPaymentRequest.getUername());
			wsRequest.setIPAYMENT(paypalPaymentRequest.getPayment());
			wsRequest.setIPPALAUTH(paypalPaymentRequest.getPpalauth());
			wsRequest.setISSID(paypalPaymentRequest.getSsId());

			startTime = CommonUtil.getStartTime();
			zResVruPaypalPaymentResponse = (ZEISUNEIPAYPALPAYMENTResponse) webServiceTemplateForPaypalPayment
					.marshalSendAndReceive(wsRequest);

			endTime = Calendar.getInstance().getTimeInMillis();
			logger.info("Time taken by service is ={}", (endTime - startTime));
		} catch (Exception ex) {
			logger.error(String.format("%s :Exception in PaypalPaymentService:paypalBillPayment:", sessionId), ex);

			utilityloggerHelper.logTransaction("PaypalPaymentService:paypalBillPayment", false, paypalPaymentRequest,
					ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}
		logger.info("PaypalPaymentService:paypalBillPayment::::::::::::::::::::End");

		return zResVruPaypalPaymentResponse;

	}

}
