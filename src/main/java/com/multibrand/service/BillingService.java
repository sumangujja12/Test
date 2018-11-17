package com.multibrand.service;

import java.math.BigDecimal;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.ws.BindingProvider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multibrand.domain.ActEbillRequest;
import com.multibrand.domain.ActEbillResponse;
import com.multibrand.domain.AmbCheckRequest;
import com.multibrand.domain.AmbCheckResponse;
import com.multibrand.domain.AmbSignupRequest;
import com.multibrand.domain.AmbSignupResponse;
import com.multibrand.domain.BankCCInfoRequest;
import com.multibrand.domain.BankCCInfoResponse;
import com.multibrand.domain.BankInfoUpdateRequest;
import com.multibrand.domain.BankInfoUpdateResponse;
import com.multibrand.domain.BillingDomain;
import com.multibrand.domain.BillingDomainPortBindingStub;
import com.multibrand.domain.CcInfoUpdateRequest;
import com.multibrand.domain.CcInfoUpdateResponse;
import com.multibrand.domain.DeActEbillRequest;
import com.multibrand.domain.DeActEbillResponse;
import com.multibrand.domain.GetArRequest;
import com.multibrand.domain.GetArResponse;
import com.multibrand.domain.PpdApiDocReadRequest;
import com.multibrand.domain.PpdApiDocReadResponse;
import com.multibrand.domain.PpdApiDocUpdateRequest;
import com.multibrand.domain.PpdApiDocUpdateResponse;
import com.multibrand.domain.SSDomain;
import com.multibrand.domain.SSDomainPortBindingStub;
import com.multibrand.domain.UpdPaperBillRequest;
import com.multibrand.domain.UpdPaperBillResponse;
import com.multibrand.dto.request.BillCourtesyCreditActivityRequest;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.XmlUtil;
import com.multibrand.vo.response.BillCourtesyCreditActivityResponse;
import com.multibrand.vo.response.billingResponse.BillInfoResponse;
import com.nrg.cxfstubs.billinfo.ZEISUGETBILLRELATEDINFO_Service;
import com.nrg.cxfstubs.billinfo.ZesBillRelatedInfo;

/**
 * 
 * @author Kdeshmu1
 *
 * This class is responsible for fetching information from Redbull Service BillingDomain 
 */

@Service
public class BillingService extends BaseAbstractService {
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	/**
	 * This will return BillingDomainProxy and set EndPoint URL
	 * @return BillingDomainProxy The BillingDomainProxy Object
	 */
	protected BillingDomain getBillingDomainProxy(){
        
		return (BillingDomain) getServiceProxy(BillingDomainPortBindingStub.class,
				BILLING_SERVICE_ENDPOINT_URL);
	}
	
	/**
	 * This will return SSDomainProxy
	*/
	protected SSDomain getSSDomainProxy() {
		
		return (SSDomain) getServiceProxy(SSDomainPortBindingStub.class, SS_SERVICE_ENDPOINT_URL);
		
	}
	/**
	 * @author Kdeshmu1
	 * @param getArRequest
	 * @return
	 * @throws Exception 
	 */
	public GetArResponse getAR(GetArRequest getArRequest, String companyCode, String sessionId) throws Exception{
		
		BillingDomain proxy = getBillingDomainProxy();

        long startTime = CommonUtil.getStartTime();
		GetArResponse response = null;
		try{
		response = proxy.getAR(getArRequest);
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(getArRequest));
			logger.error(ex);
			utilityloggerHelper.logTransaction("getAR", false, getArRequest,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(getArRequest));
			logger.error(ex);
			utilityloggerHelper.logTransaction("getAR", false, getArRequest,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}
		utilityloggerHelper.logTransaction("getAR", false, getArRequest,response, response.getStrErrorText(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(getArRequest));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
	}
	
	/**
	 * @author Kdeshmu1
	 * @param ActEbillRequest
	 * @return
	 * @throws Exception 
	 */
	public ActEbillResponse activateEbill(ActEbillRequest request, String companyCode, String sessionId) throws Exception{
		
		BillingDomain proxy = getBillingDomainProxy();
		long startTime = CommonUtil.getStartTime();
		ActEbillResponse response = null;
		try{
			response= proxy.activateEbill(request);
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("activateEbill", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("activateEbill", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}
		utilityloggerHelper.logTransaction("activateEbill", false, request,response, response.getStrErrorText(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
	}
	
	/**
	 * @author Kdeshmu1
	 * @param DeActEbillRequest
	 * @return
	 * @throws Exception 
	 */
	public DeActEbillResponse deactivateEbill(DeActEbillRequest request, String companyCode, String sessionId) throws Exception{
		
		BillingDomain proxy = getBillingDomainProxy();
		long startTime = CommonUtil.getStartTime();
		DeActEbillResponse response = null;
		try{
			response = proxy.deactivateEbill(request);
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("deactivateEbill", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}catch(Exception ex){
			logger.info(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("deactivateEbill", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}
		utilityloggerHelper.logTransaction("deactivateEbill", false, request,response, response.getStrErrorText(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
	}
	/**
	 * @author kdeshmu1
	 * @param accountNumber
	 * @param bp
	 * @param co
	 * @return
	 * @throws Exception 
	 */
	public BillInfoResponse billInfo(String accountNumber,String bp, String co, String sessionId, String companyCode)throws Exception
	{
		BillInfoResponse response = new BillInfoResponse();
		
		String request = "accountNumber="+accountNumber+",bpid="+bp+",contractNumber="+co;
		
		String imCa = CommonUtil.paddedCa(accountNumber);
		URL url = ZEISUGETBILLRELATEDINFO_Service.class.getResource("Z_E_ISU_GET_BILL_RELATED_INFO-RPM.wsdl");
		
		ZEISUGETBILLRELATEDINFO_Service port = new ZEISUGETBILLRELATEDINFO_Service(url);
		
		com.nrg.cxfstubs.billinfo.ZEISUGETBILLRELATEDINFO stub = port.getZEISUGETBILLRELATEDINFO();
		
		BindingProvider binding = (BindingProvider)stub;
		
		binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,this.envMessageReader.getMessage(CCS_USER_NAME));
		
		binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,this.envMessageReader.getMessage(CCS_PASSWORD));
		
		binding.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,this.envMessageReader.getMessage(CCS_GET_BILL_INFO_JNDINAME));
		
		long startTime = CommonUtil.getStartTime();
		javax.xml.ws.Holder<com.nrg.cxfstubs.billinfo.ZesBillRelatedInfo> hZesBillRelatedInfo = new javax.xml.ws.Holder<com.nrg.cxfstubs.billinfo.ZesBillRelatedInfo>();
		javax.xml.ws.Holder<java.lang.String> hString =new javax.xml.ws.Holder<String>();
	       
		try{
			stub.zeIsuGetBillRelatedInfo(bp, co, imCa, hZesBillRelatedInfo,hString);
		}catch(Exception ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction("getBillInfo", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			throw ex;
		}
		
		ZesBillRelatedInfo htZesBillRelatedInfo = hZesBillRelatedInfo.value;
		BigDecimal unBilledAmount = htZesBillRelatedInfo.getUnbilledAmt();
				
		String hReturnCode = hString.value;
		if(hReturnCode!=null && hReturnCode.equalsIgnoreCase("1"))
		{
			response.setResultCode(RESULT_CODE_NO_DATA);
			response.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
		}
		else
		{
			logger.info("unBilledAmount "+unBilledAmount);
			response.setResultCode(RESULT_CODE_SUCCESS);
			response.setResultDescription(MSG_SUCCESS);
			response.setUnBilledAmount(unBilledAmount.toString());
		}
		utilityloggerHelper.logTransaction("getBillInfo", false, request,response, response.getResultCode(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
	}	
	
	/**
	 * @author smuruga1
	 * @param request
	 * @param companyCode
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public UpdPaperBillResponse updatePaperBill(UpdPaperBillRequest request, String companyCode, String sessionId) throws Exception{
		
		BillingDomain proxy = getBillingDomainProxy();
		long startTime = CommonUtil.getStartTime();
		UpdPaperBillResponse response = null;
		try{
			response= proxy.updatePaperBill(request);
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("activateEbill", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("activateEbill", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}
		utilityloggerHelper.logTransaction("activateEbill", false, request,response, response.getStrErrorText(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
	}
	
	/**
	 * @author ahanda1
	 * @param bpid
	 * @param companyCode
	 * @param brandName
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public BankCCInfoResponse getBankCCInfo(BankCCInfoRequest request)throws Exception
	{
	// UTILITY AND INFO LOGGING IS DONE IN BO LAYER FOR THIS CALL AS ITS RESPONSE WILL ALOT OF DISCRETE DATA LIKE BANK ACCOUNT NO. AND CREDIT CARD NO.S
		BillingDomain proxy = getBillingDomainProxy();
		BankCCInfoResponse response = null;
		try{
			response= proxy.getBankCCInfo(request);
		}catch(RemoteException ex){
			throw ex;
		}catch(Exception ex){
			throw ex;
		}
		return response;
		
	}	

	/**
	 * for updating bank ac info based upon bpid
	 * @param bpid
	 * @param bankAcNo
	 * @param bankRoutingNo
	 * @param updateFlag
	 * @param companyCode
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public BankInfoUpdateResponse updateBankInfo(BankInfoUpdateRequest request, String companyCode, String sessionId)throws Exception
	{
		
		BillingDomain proxy = getBillingDomainProxy();
		long startTime = CommonUtil.getStartTime();
		BankInfoUpdateResponse response = null;
		try{
			response= proxy.updateBankInfo(request);
			
			//masking the bank account number before logging 
			request.setBankAccountNumber(CommonUtil.maskBankAccountNo(request.getBankAccountNumber()));
			
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("updateBankInfo", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("updateBankInfo", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}
		utilityloggerHelper.logTransaction("updateBankInfo", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
		
	}	

	
	/**
	 * @author ahanda1
	 * @param request
	 * @param companyCode
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public CcInfoUpdateResponse updateCCInfo(CcInfoUpdateRequest request, String companyCode, String sessionId)throws Exception
	{
		
		BillingDomain proxy = getBillingDomainProxy();
		long startTime = CommonUtil.getStartTime();
		CcInfoUpdateResponse response = null;
		try{
			response= proxy.updateCCInfo(request);
			
			//masking the bank account number before logging 
			request.setCcNumber(CommonUtil.maskCCNo(request.getCcNumber()));
			
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("updateCCInfo", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("updateCCInfo", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}
		utilityloggerHelper.logTransaction("updateCCInfo", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
		
	}
	
	/**
	 * @author mshukla1
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public PpdApiDocReadResponse prepayDocRead(PpdApiDocReadRequest request) throws Exception {
		
		PpdApiDocReadResponse response=null;
		SSDomain ssdomainProxy = getSSDomainProxy();
		
		try{
			response  = ssdomainProxy.ppdApiDocRead(request);
		}catch(RemoteException re){
			logger.info(XmlUtil.pojoToXML(request));
			logger.error(re);
			throw re;
		}catch (Exception e) {
			logger.info(XmlUtil.pojoToXML(request));
			logger.error(e);
			throw e;
		}
		return response;
	}
	
	/**
	 * Prepay Api Doc update call
	 * @param request
	 * @return
	 * @throws Exception
	 */
    public PpdApiDocUpdateResponse prepayDocUpdate(PpdApiDocUpdateRequest request)throws Exception{
		
		PpdApiDocUpdateResponse response = null;
		SSDomain ssdomainProxy = getSSDomainProxy();
		
		try{
			response  = ssdomainProxy.ppdApiDocUpdate(request);
		}catch(RemoteException re){
			logger.info(XmlUtil.pojoToXML(request));
			logger.error(re);
			throw re;
		}catch (Exception e) {
			logger.info(XmlUtil.pojoToXML(request));
			logger.error(e);
			throw e;
		}
		return response;
	}
    
    /**
     * 
     * @return
     * @throws Exception 
     */
    public AmbCheckResponse ambeligibilityCheck(AmbCheckRequest request, String sessionId, String companyCode) throws Exception {
    	BillingDomain proxy = getBillingDomainProxy();
		long startTime = CommonUtil.getStartTime();
		AmbCheckResponse response = null;
		try{
			response= proxy.eligibilityCheck(request);
						
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("ambeligibilityCheck", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("ambeligibilityCheck", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}
		utilityloggerHelper.logTransaction("ambeligibilityCheck", false, request,response, response.getErrMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
    }
    
    /**
     * 
     * @param request
     * @param sessionId
     * @param companyCode
     * @return
     * @throws Exception
     */
    public AmbSignupResponse saveAMBSignUp(AmbSignupRequest request, String sessionId, String companyCode) throws Exception {
    	BillingDomain proxy = getBillingDomainProxy();
		long startTime = CommonUtil.getStartTime();
		AmbSignupResponse response = null;
		try{
			response= proxy.saveAMBSignup(request);
						
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("saveAMBSingup", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("saveAMBSingup", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}
		utilityloggerHelper.logTransaction("saveAMBSingup", false, request,response, response.getErrMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
    }
	public BillCourtesyCreditActivityResponse courtesyCreditActivity(BillCourtesyCreditActivityRequest request, String sessionId) {
		logger.info("PaymentService.courtesyCreditActivity :: START");
		
		BillingDomain proxy = getBillingDomainProxy();
		com.multibrand.domain.CourtesyCreditActivityResponse response=null;
		BillCourtesyCreditActivityResponse newResp = new BillCourtesyCreditActivityResponse();
		
		long startTime = CommonUtil.getStartTime();
		
		try{
			
			com.multibrand.domain.CourtesyCreditActivityRequest newrequest = new com.multibrand.domain.CourtesyCreditActivityRequest();
			
			newrequest.setStrBusinessPartnerId(request.getBpNumber());
			newrequest.setStrCaNumber(request.getCaNumber());
			newrequest.setStrCourtesyCreditCode(request.getCourtesyCreditCode());
			newrequest.setStrNotes(request.getNotes());
			newrequest.setStrUAN(request.getUan());
			newrequest.setStrUrl(request.getUrl());
		
			response = proxy.billCourtesyCreditActivityLog(newrequest);
			
			if(null != response.getErrorCode()) {
				
				if ( null != response.getErrorCode() && response.getErrorCode().equalsIgnoreCase(COURTESY_CREDIT_SUCCESS_CODE)) {
					newResp.setResultCode(SUCCESS_CODE);
					newResp.setResultDescription(MSG_SUCCESS);
				} else if ( null != response.getErrorCode() && response.getErrorCode().equalsIgnoreCase(COURTESY_CREDIT_FAILURE_CODE)) { 
					newResp.setResultCode(FAILURE_CODE_01);
					newResp.setResultDescription(COURTESY_CREDIT_RES_CODE_DESC);
				}
				
			}
			
			if(response.getErrorMessage()!=null)newResp.setResultDescription(response.getErrorMessage());
			
			if(response.getReturnObjectNo()!=null)newResp.setReturnObjectNo(response.getReturnObjectNo());
			
			utilityloggerHelper.logTransaction("courtesyCreditActivity", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, "");
			if(logger.isDebugEnabled()){
				logger.debug(XmlUtil.pojoToXML(request));
				logger.debug(XmlUtil.pojoToXML(response));
			}
		}catch(RemoteException ex){
			newResp.setResultCode(FAILURE_CODE_01);
			newResp.setResultDescription(COURTESY_CREDIT_RES_CODE_DESC);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("courtesyCreditActivity", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, "");
		}
		catch(Exception ex){
			newResp.setResultCode(FAILURE_CODE_01);
			newResp.setResultDescription(COURTESY_CREDIT_RES_CODE_DESC);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("courtesyCreditActivity", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, "");
		}
		logger.info("PaymentService.courtesyCreditActivity :: END");
		return newResp;
	}  

}
