package com.multibrand.service;

import java.net.URL;
import java.rmi.RemoteException;
import javax.xml.ws.BindingProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.multibrand.domain.AutoPayBankRequest;
import com.multibrand.domain.AutoPayBankResponse;
import com.multibrand.domain.AutoPayCCRequest;
import com.multibrand.domain.AutoPayCCResponse;
import com.multibrand.domain.BankDetailsValidationRequest;
import com.multibrand.domain.BankDetailsValidationResponse;
import com.multibrand.domain.BankPaymentInstitutionResponse;
import com.multibrand.domain.CancelOtccPaymentResp;
import com.multibrand.domain.CancelPaymentRequest;
import com.multibrand.domain.CancelPaymentResponse;
import com.multibrand.domain.CancelSchdOtccPaymetReq;
import com.multibrand.domain.DppEligibleRequest;
import com.multibrand.domain.DppEligibleResponse;
import com.multibrand.domain.DppSubmissionRequest;
import com.multibrand.domain.DppSubmissionResponse;
import com.multibrand.domain.PayByBankRequest;
import com.multibrand.domain.PayByBankResponse;
import com.multibrand.domain.PayByCCRequest;
import com.multibrand.domain.PayByCCResponse;
import com.multibrand.domain.PayExtEligibleRequest;
import com.multibrand.domain.PayExtEligibleResponse;
import com.multibrand.domain.PaymentDomain;
import com.multibrand.domain.PaymentDomainPortBindingStub;
import com.multibrand.domain.ScheduleOtccPaymentRequest;
import com.multibrand.domain.ScheduleOtccPaymentResponse;
import com.multibrand.domain.ValidateBankRequest;
import com.multibrand.domain.ValidateBankResponse;
import com.multibrand.domain.ValidateCCRequest;
import com.multibrand.domain.ValidateCCResponse;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.XmlUtil;
import com.multibrand.vo.response.DeEnrollResponse;
import com.nrg.cxfstubs.autopay.ZEISURECURPAYDEENROLL;
import com.nrg.cxfstubs.autopay.ZEISURECURPAYDEENROLL_Service;



@Service
public class PaymentService extends BaseAbstractService {

	
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	/**
	 * This will return PaymentDomainProxy and set EndPoint URL
	 * @return paymentDomainProxy The PaymentDomainProxy Object
	 */
	protected PaymentDomain getPaymentDomainProxy(){
        
		return (PaymentDomain) getServiceProxy(PaymentDomainPortBindingStub.class,
				PAYMENT_SERVICE_ENDPOINT_URL);
	}
	
	
	
	public ValidateBankResponse validateBankDetails(String ca, String bankAccNumber, String bankRoutingNumber, String companyCode, String sessionId,String brandName) throws Exception{
		logger.info("PaymentService.validateBankDetails :: START");
		ValidateBankRequest request = new ValidateBankRequest();
		request.setStrBankAccNumber(bankAccNumber);
		request.setStrBankRoutingNumber(bankRoutingNumber);
		request.setStrCompanyCode(companyCode);
		//START : Changes for Cirro OAM : Kdeshmu1
		if(ca == null || ca.equalsIgnoreCase("")){
			request.setStrCANumber("");
		}else{
			request.setStrCANumber(ca);
		}
		//END : Changes for Cirro OAM : Kdeshmu1
		
		PaymentDomain proxy = getPaymentDomainProxy();
		long startTime = CommonUtil.getStartTime();
		ValidateBankResponse response=null;
		try{
		response = proxy.validateBankDetails(request);
		request.setStrBankAccNumber(CommonUtil.maskBankAccountNo(bankAccNumber));
		utilityloggerHelper.logTransaction("validateBankDetails", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("validateBankDetails", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}
		catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("validateBankDetails", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}
		logger.info("PaymentService.validateBankDetails :: END");
		return response;
	}
	
	
	public AutoPayBankResponse submitBankAutoPay(AutoPayBankRequest request, String companyCode, String sessionId, String brandName) throws Exception{
		logger.info("PaymentService.submitBankAutoPay :: START");
			
		PaymentDomain proxy = getPaymentDomainProxy();
		long startTime = CommonUtil.getStartTime();
		AutoPayBankResponse response = null;
		try{
		response = proxy.submitBankAutoPay(request);
		request.setStrBankAccNumber(CommonUtil.maskBankAccountNo(request.getStrBankAccNumber()));
		utilityloggerHelper.logTransaction("submitBankAutoPay", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("submitBankAutoPay", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("submitBankAutoPay", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}
		logger.info("PaymentService.submitBankAutoPay :: END");
		return response;
	}
	
	
	public ValidateCCResponse validateCCDetails(ValidateCCRequest request, String companyCode, String sessionId,String brandName) throws Exception{
		logger.info("PaymentService.validateCCDetails :: START");

		long startTime = CommonUtil.getStartTime();
		PaymentDomain proxy = getPaymentDomainProxy();
		ValidateCCResponse response = null;
		try{
		response = proxy.validateCCDetails(request);
		request.setStrCCNumber(CommonUtil.maskCCNo(request.getStrCCNumber()));
		request.setStrCVVNumber(CommonUtil.maskCVVCode(request.getStrCVVNumber()));
		utilityloggerHelper.logTransaction("validateCCDetails", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}	
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("validateCCDetails", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("validateCCDetails", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}
		logger.info("PaymentService.validateCCDetails :: END");
		return response;
	}
	
	
	public AutoPayCCResponse submitCCAutoPay(AutoPayCCRequest request, String companyCode, String sessionId,String brandName) throws Exception{
		logger.info("PaymentService.submitCCAutoPay :: START");
		
		PaymentDomain proxy = getPaymentDomainProxy();
		long startTime = CommonUtil.getStartTime();
		AutoPayCCResponse response = null;
		try{
		response= proxy.submitCCAutoPay(request);
		request.setStrCCNumber(CommonUtil.maskCCNo(request.getStrCCNumber()));
		utilityloggerHelper.logTransaction("submitCCAutoPay", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("submitCCAutoPay", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("submitCCAutoPay", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}
		logger.info("PaymentService.submitCCAutoPay :: END");
		return response;
	}
	
	
	public PayByBankResponse submitBankPayment(PayByBankRequest request, String companyCode, String sessionId,String brandName) throws Exception{
		logger.info("PaymentService.submitBankPayment :: START");
		
		PaymentDomain proxy = getPaymentDomainProxy();
		long startTime = CommonUtil.getStartTime();
		PayByBankResponse response = null;
		try{
			response= proxy.submitBankPayment(request);
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("submitBankPayment", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("submitBankPayment", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}
		
		request.setStrBankAccNumber(CommonUtil.maskBankAccountNo(request.getStrBankAccNumber()));
		utilityloggerHelper.logTransaction("submitBankPayment", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		logger.info("PaymentService.submitBankPayment :: END");
		return response;
	}
	
	
	public PayByCCResponse submitCCPayment(PayByCCRequest request, String companyCode, String sessionId,String brandName) throws Exception{
		logger.info("PaymentService.submitCCPayment :: START");
		
		PaymentDomain proxy = getPaymentDomainProxy();
		long startTime = CommonUtil.getStartTime();
		PayByCCResponse response = null;
		try{
			response= proxy.submitCCPayment(request);
		}catch(RemoteException ex){
			request.setStrCCNumber(CommonUtil.maskCCNo(request.getStrCCNumber()));
			request.setStrCVVNumber(CommonUtil.maskCVVCode(request.getStrCVVNumber()));
			logger.error(ex);
			utilityloggerHelper.logTransaction("submitCCPayment", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}catch(Exception ex){
			request.setStrCCNumber(CommonUtil.maskCCNo(request.getStrCCNumber()));
			request.setStrCVVNumber(CommonUtil.maskCVVCode(request.getStrCVVNumber()));
			logger.error(ex);
			utilityloggerHelper.logTransaction("submitCCPayment", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}
		request.setStrCCNumber(CommonUtil.maskCCNo(request.getStrCCNumber()));
		request.setStrCVVNumber(CommonUtil.maskCVVCode(request.getStrCVVNumber()));
		utilityloggerHelper.logTransaction("submitCCPayment", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		logger.info("PaymentService.submitCCPayment :: END");
		return response;
	}
	
	/**
	 * @author kdeshmu1
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public DeEnrollResponse deEnroll(String accountNumber,String companyCode, String sessionId , String brandName)throws Exception
	{
		DeEnrollResponse response = new DeEnrollResponse();
		
		String imCa = CommonUtil.paddedCa(accountNumber);
		String connId = null;
		URL url = ZEISURECURPAYDEENROLL_Service.class
		.getResource("Z_E_ISU_RECUR_PAY_DEENROLL_RPM.wsdl");
		
		ZEISURECURPAYDEENROLL_Service port = new ZEISURECURPAYDEENROLL_Service(url);
		
		ZEISURECURPAYDEENROLL stub = port.getZEISURECURPAYDEENROLL();
		
		BindingProvider binding = (BindingProvider)stub;
		
		binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,this.envMessageReader.getMessage(CCS_USER_NAME));
		
		binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,this.envMessageReader.getMessage(CCS_PSD));
		
		binding.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,this.envMessageReader.getMessage(AUTOPAY_DEENROLL_ENDPOINT_URL_JNDINAME));
		
		long startTime = CommonUtil.getStartTime();
		String request = "connId="+connId+",accountNumber="+accountNumber;
		
		String returnCode = null;
		try{
		returnCode = stub.zeIsuRecurPayDeenroll(connId,accountNumber);
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("deEnroll", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}
		response.setSuccessCode(returnCode);
		
		
		utilityloggerHelper.logTransaction("deEnroll", false, request,response, returnCode, CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		logger.info("AutoPayService - deEnroll ends");
		return response;
	}
	/**
	 * @author kdeshmu1
	 * @param accountNumber
	 * @param companyCode
	 * @param paymentId
	 * @param brandName
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public CancelPaymentResponse doCancelPayment(String accountNumber,
			String companyCode,String paymentId,String brandName,String sessionId) throws Exception{
		logger.info("PaymentService.doCancelPayment :: START");
		
		PaymentDomain proxy = getPaymentDomainProxy();
		long startTime = CommonUtil.getStartTime();
		CancelPaymentResponse response = null;
		CancelPaymentRequest request = new CancelPaymentRequest();
		request.setStrCANumber(accountNumber);
		request.setStrCompanyCode(companyCode);
		request.setStrPaymentID(paymentId);
		try{
		response= proxy.submitCancelPayment(request);
		
		utilityloggerHelper.logTransaction("doCancelPayment", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("doCancelPayment", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("doCancelPayment", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}
		logger.info("PaymentService.doCancelPayment :: END");
		return response;
	}
	
	
	/**
	 * @author ahanda1
	 * @param request
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public ScheduleOtccPaymentResponse scheduleOneTimeCCPayment(ScheduleOtccPaymentRequest request,String sessionId) throws Exception{
		logger.info("PaymentService.scheduleOneTimeCCPayment :: START");
		
		PaymentDomain proxy = getPaymentDomainProxy();
		long startTime = CommonUtil.getStartTime();
		ScheduleOtccPaymentResponse response = null;
		
		try{
		response= proxy.scheduleOneTimeCCPayment(request);
		
		// masking cc number before logging
		request.setTokCCNumber(CommonUtil.maskCCNo(request.getTokCCNumber()));
		
		utilityloggerHelper.logTransaction("scheduleOneTimeCCPayment", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		}catch(RemoteException ex){
			// masking cc number before logging
			request.setTokCCNumber(CommonUtil.maskCCNo(request.getTokCCNumber()));
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("scheduleOneTimeCCPayment", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}catch(Exception ex){
			// masking cc number before logging
			request.setTokCCNumber(CommonUtil.maskCCNo(request.getTokCCNumber()));
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("scheduleOneTimeCCPayment", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}
		logger.info("PaymentService.scheduleOneTimeCCPayment :: END");
		return response;
	}
	
	
	
	/**
	 * @author ahanda1
	 * @param request
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public CancelOtccPaymentResp editCancelOTCCPayment(CancelSchdOtccPaymetReq request,String sessionId) throws Exception{
		logger.info("PaymentService.editCancelOTCCPayment :: START");
		
		PaymentDomain proxy = getPaymentDomainProxy();
		long startTime = CommonUtil.getStartTime();
		CancelOtccPaymentResp response = null;
		
		try{
		response= proxy.editCancelOtccPayment(request);
		
		utilityloggerHelper.logTransaction("editCancelOTCCPayment", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("editCancelOTCCPayment", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("editCancelOTCCPayment", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}
		logger.info("PaymentService.editCancelOTCCPayment :: END");
		return response;
	}
	
	public BankPaymentInstitutionResponse getBankPaymentInstitution(String routingNumber) throws RemoteException{
		logger.info("PaymentService.getBankPaymentInstitution..start");
		
		PaymentDomain proxy = getPaymentDomainProxy();
		BankPaymentInstitutionResponse response = proxy.getBankPaymentInstitution(routingNumber);
		
		logger.info("PaymentService.getBankPaymentInstitution..end");
		return response;
	}
	
	
	public PayExtEligibleResponse getPayExtEligibleResponse(PayExtEligibleRequest request, String sessionId) throws RemoteException {
		logger.info("PaymentService.getPayExtEligibleResponse :: START");
		
		PaymentDomain proxy = getPaymentDomainProxy();
		long startTime = CommonUtil.getStartTime();
		PayExtEligibleResponse response = null;
		
		try{
		response= proxy.payExtEligibilityCheck(request);
		
		utilityloggerHelper.logTransaction("getPayExtEligibleResponse", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("getPayExtEligibleResponse", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("getPayExtEligibleResponse", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}
		logger.info("PaymentService.getPayExtEligibleResponse :: END");
		return response;
	}
	
	
	public DppEligibleResponse getDPPExtEligibleResponse(DppEligibleRequest request, String sessionId) throws RemoteException {
		logger.info("PaymentService.getDPPExtEligibleResponse :: START");
		
		PaymentDomain proxy = getPaymentDomainProxy();
		long startTime = CommonUtil.getStartTime();
		DppEligibleResponse response = null;
		
		try{
		response= proxy.dppEligibilityCheck(request);
		
		utilityloggerHelper.logTransaction("getDPPExtEligibleResponse", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("getDPPExtEligibleResponse", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("getDPPExtEligibleResponse", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}
		logger.info("PaymentService.getDPPExtEligibleResponse :: END");
		return response;
	}
	public DppSubmissionResponse dppSubmit(DppSubmissionRequest request, String sessionId) throws RemoteException {
		logger.info("PaymentService.dppSubmit :: START");
		
		PaymentDomain proxy = getPaymentDomainProxy();
		long startTime = CommonUtil.getStartTime();
		DppSubmissionResponse response = null;
		
		try{
		response= proxy.dppSubmit(request);
		
		utilityloggerHelper.logTransaction("dppSubmit", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("dppSubmit", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("dppSubmit", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}
		logger.info("PaymentService.dppSubmit :: END");
		return response;
	}
	
	public BankDetailsValidationResponse validateBankDetailsGIACT(BankDetailsValidationRequest request) throws RemoteException{
		logger.info("PaymentService.validateBankDetailsGIACT :: START");
			
		long startTime = CommonUtil.getStartTime();
		BankDetailsValidationResponse response = null;
		try{
			PaymentDomain proxy = getPaymentDomainProxy();
			response = proxy.validateBankDetailsGIACT(request);
			request.setBankAccountNumber(CommonUtil.maskBankAccountNo(request.getBankAccountNumber()));
			logger.info("Time taken by validateBankDetailsGIACT  Call is : {}", CommonUtil.getElapsedTime(startTime));
		}catch(Exception ex){
			logger.error("NRGWS Error in validateBankDetailsGIACT Message {}", ex.getMessage());
			throw ex;
		}
		logger.info("PaymentService.validateBankDetailsGIACT :: END");
		return response;
	}
		
}