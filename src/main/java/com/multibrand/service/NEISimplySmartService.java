package com.multibrand.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.multibrand.dto.request.NEIPaypalPaymentRequest;
import com.multibrand.dto.response.NEIPaypalPaymentResponse;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.nrg.cxfstubs.nei.paypal.ZEISUNEIPAYPALPAYMENTResponse;
import com.nrg.cxfstubs.nei.paypal.ZEISUNEIPAYPALPAYMENT_Type;

@Service
public class NEISimplySmartService extends BaseAbstractService {

	@Autowired
	@Qualifier("webServiceTemplateForNeiPaypalPayment")
	private WebServiceTemplate webServiceTemplateForNeiPaypalPayment;

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

	public NEIPaypalPaymentResponse paypalPayment(NEIPaypalPaymentRequest paypalPaymentRequest, String sessionId)
			throws Exception {

		logger.debug("{} START: NEISimplySmartService:paypalPayment::::::::::::::::::::Start:", sessionId);

		long startTime = CommonUtil.getStartTime();
		Long endTime = null;
		String companyCode = "NEI";
		ZEISUNEIPAYPALPAYMENTResponse zResVruPaypalPaymentResponse = null;
		NEIPaypalPaymentResponse paymentResponse = null;

		try {
			logger.debug("{}:****NEISimplySmartService:paypalPayment: Request:{}", sessionId, paypalPaymentRequest);
			com.nrg.cxfstubs.nei.paypal.ObjectFactory factory = new com.nrg.cxfstubs.nei.paypal.ObjectFactory();

			ZEISUNEIPAYPALPAYMENT_Type wsRequest = factory.createZEISUNEIPAYPALPAYMENT_Type();
			

			wsRequest.setIMUSERNAME(paypalPaymentRequest.getUsername());
			wsRequest.setIPAYMENT(paypalPaymentRequest.getPayment());
			wsRequest.setIPPALAUTH(paypalPaymentRequest.getPpalauth());
			wsRequest.setISSID(paypalPaymentRequest.getSsId());

			startTime = CommonUtil.getStartTime();
			logger.debug("{}:****NEISimplySmartService:paypalPayment: CCS ENDPOINT {}", webServiceTemplateForNeiPaypalPayment.getDefaultUri());
			zResVruPaypalPaymentResponse = (ZEISUNEIPAYPALPAYMENTResponse) webServiceTemplateForNeiPaypalPayment
					.marshalSendAndReceive(wsRequest);

			endTime = Calendar.getInstance().getTimeInMillis();
			paymentResponse = new NEIPaypalPaymentResponse(zResVruPaypalPaymentResponse);
			logger.debug("{}:****NEISimplySmartService:paypalPayment: PaypalpaymentResponse: {}", sessionId, paymentResponse);
			logger.debug("Time taken by service is ={}", (endTime - startTime));
		} catch (Exception ex) {
			logger.error(String.format("%s :Exception in PaypalPaymentService:paypalBillPayment:", sessionId), ex);

			utilityloggerHelper.logTransaction("NEISimplySmartService:paypalPayment", false, paypalPaymentRequest,
					ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}
		logger.debug("{} END: NEISimplySmartService:paypalPayment::::::::::::::::::::Start:", sessionId);

		return paymentResponse;

	}

}
