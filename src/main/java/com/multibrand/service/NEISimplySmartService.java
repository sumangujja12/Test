package com.multibrand.service;

import java.net.URL;
import java.util.Calendar;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multibrand.dto.request.NEIPaypalPaymentRequest;
import com.multibrand.dto.response.NEIPaypalPaymentResponse;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.nrg.cxfstubs.nei.paypal.ZEISUNEIPAYPALPAYMENT;
import com.nrg.cxfstubs.nei.paypal.ZEISUNEIPAYPALPAYMENT_Service;

@Service
public class NEISimplySmartService extends BaseAbstractService {

	private static final String CCS_NEI_PAYPAL = "CCS_NEI_PAYPAL";

	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	ZEISUNEIPAYPALPAYMENT stub;

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
		NEIPaypalPaymentResponse paymentResponse = null;

		try {
			logger.debug("{}:****NEISimplySmartService:paypalPayment: Request:{}", sessionId, paypalPaymentRequest);
			String endpoint = envMessageReader.getMessage(CCS_NEI_PAYPAL)	;

			stub = getPaypalPaymentCCSStub(endpoint);
			
			//Holder<Bapiret2> exBapiret2Resp =new Holder<Bapiret2>();
			Holder<String> eOTBDID =new Holder<String>();
			Holder<String> xcode =new Holder<String>();
			
			startTime = CommonUtil.getStartTime();
			logger.debug("{}:****NEISimplySmartService:paypalPayment: CCS ENDPOINT {}", sessionId, endpoint);
			stub.zEISUNEIPAYPALPAYMENT(paypalPaymentRequest.getUsername(),
									   paypalPaymentRequest.getPayment(),
									   paypalPaymentRequest.getPpalauth(),
									   paypalPaymentRequest.getSsId(),
									   eOTBDID,
									   xcode
									   
					);
			
			endTime = Calendar.getInstance().getTimeInMillis();
			paymentResponse = new NEIPaypalPaymentResponse();
			paymentResponse.setEOTBDID(eOTBDID.value);
			paymentResponse.setXCODE(xcode.value);
			paymentResponse.loadMessage();
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
	
	public ZEISUNEIPAYPALPAYMENT  getPaypalPaymentCCSStub (String endpoint) {
		URL url = ZEISUNEIPAYPALPAYMENT_Service.class.getResource("Z_E_ISU_NEI_PAYPAL_PAYMENT.wsdl");
		
		ZEISUNEIPAYPALPAYMENT_Service port = new ZEISUNEIPAYPALPAYMENT_Service(url);
		stub = port.getZEISUNEIPAYPALPAYMENT();
        BindingProvider binding = (BindingProvider) stub;
        binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,this.envMessageReader.getMessage(CCS_USER_NAME));
		binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,this.envMessageReader.getMessage(CCS_PSD));
		binding.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,endpoint);
		return stub;
	}

}
