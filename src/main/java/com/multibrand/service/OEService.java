package com.multibrand.service;

import java.rmi.RemoteException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multibrand.domain.BpMatchCCSRequest;
import com.multibrand.domain.BpMatchCCSResponse;
import com.multibrand.domain.OEDomain;
import com.multibrand.domain.OEDomainPortBindingStub;
import com.multibrand.domain.OetdspRequest;
import com.multibrand.domain.OfferPricingRequest;
import com.multibrand.domain.PermitCheckRequest;
import com.multibrand.domain.PermitCheckResponse;
import com.multibrand.domain.PromoOfferRequest;
import com.multibrand.domain.PromoOfferResponse;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.XmlUtil;

/**
 * 
 * @author vsood30
 *
 * This class is responsible for fetching information from Redbull Service OEDomain 
 */

@Service
public class OEService extends BaseAbstractService {
	
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	/**
	 * This will return SwapDomainProxy and set EndPoint URL
	 * @return swapDomainProxy The SwapDomainProxy Object
	 */
	protected OEDomain getOEDomainProxy(){
		return (OEDomain) getServiceProxy(OEDomainPortBindingStub.class, OE_SERVICE_ENDPOINT_URL);
	}
	
	/**
	 * getOfferPricingFromCCS CCS service call
	 * @param offerPricingRequest OfferPricingRequest
	 * @return PromoOfferResponse
	 * @throws RemoteException
	 */
	public PromoOfferResponse getOfferPricingFromCCS(OfferPricingRequest offerPricingRequest) throws RemoteException{
		logger.debug("OEService.getOfferPricingFromCCS() Start");
		PromoOfferResponse promoOfferResponse = null;
		try {
			promoOfferResponse = null;
			OEDomain proxy = getOEDomainProxy();
			promoOfferResponse=proxy.getOfferPricingFromCCS(offerPricingRequest);
		} catch (Exception e) {
			logger.error("getOfferPricingFromCCS : Exception while fetching Offers:", e);
		}	
		logger.debug("OEService.getOfferPricingFromCCS() End");
		return promoOfferResponse; 
	}
	
	/**
	 * getPromoOffers CCS service call
	 * @param promoOfferRequest PromoOfferRequest
	 * @return PromoOfferResponse
	 * @throws RemoteException
	 */
	public PromoOfferResponse getOfferWithPricingFromCCS(PromoOfferRequest promoOfferRequest,String companyCode, String sessionId) throws RemoteException {
		logger.debug("OEService.getOfferWithPricingFromCCS() Start");
		PromoOfferResponse promoOfferResponse = null;
		try {
			OEDomain proxy = getOEDomainProxy();
			long startTime = CommonUtil.getStartTime();
			promoOfferResponse=proxy.getPromoOffers(promoOfferRequest);
			 this.utilityloggerHelper.logTransaction("getOfferWithPricingFromCCS", false, promoOfferRequest, promoOfferResponse, promoOfferResponse.getStrErrMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			 if(logger.isDebugEnabled()){
				 logger.debug(XmlUtil.pojoToXML(promoOfferRequest));
				 logger.debug(XmlUtil.pojoToXML(promoOfferResponse));
			 }
		} catch (Exception e) {
			logger.error("getOfferWithPricingFromCCS : Exception while fetching Offers:", e);
		}	
		logger.debug("OEService.getOfferWithPricingFromCCS() End");
		return promoOfferResponse;
	}
	
	
	/**
	   * @author ahanda1
	   * @param request
	   * @param sessionId
	   * @return
	 * @throws Exception 
	   */
	  public String getTDSPSpecificCalendarDates(OetdspRequest request, String sessionId) throws Exception
	  {
	    this.logger.debug("OEService.getTDSPSpecificCalendarDates start");
	    String response = null;
	    
	        OEDomain proxyclient = getOEDomainProxy();
	        long startTime = CommonUtil.getStartTime();
	 try{
	        response = proxyclient.getTDSPSpecificCalendarDates(request);
	        this.utilityloggerHelper.logTransaction("getTDSPSpecificCalendarDates", false, request, response,"", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getStrCompanyCode());
	        if(logger.isDebugEnabled()){
		        logger.debug(XmlUtil.pojoToXML(request));
		    	logger.debug(XmlUtil.pojoToXML(response));
	        }
	    }
	 catch (RemoteException e) {
	    	this.logger.error("OEService.getTDSPSpecificCalendarDates : Exception while getting data from ccs:", e);
	    	this.utilityloggerHelper.logTransaction("getTDSPSpecificCalendarDates", false, request, e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getStrCompanyCode());
	    	if(logger.isDebugEnabled())
	    		logger.debug(XmlUtil.pojoToXML(request));
	        throw e;// it is required to throw exception back to BO layer for proper response generation
	    }
	    catch (Exception e) {
	    	this.logger.error("OEService.getTDSPSpecificCalendarDates : Exception while getting data from ccs:", e);
	    	this.utilityloggerHelper.logTransaction("getTDSPSpecificCalendarDates", false, request, e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getStrCompanyCode());
	    	if(logger.isDebugEnabled())
	    		logger.debug(XmlUtil.pojoToXML(request));
	        throw e;// it is required to throw exception back to BO layer for proper response generation
	    }

	    this.logger.debug("OEService.getTDSPSpecificCalendarDates end");
	    return response;
	  } 
	
	  
	  /**
	   * @author ahanda1
	   * @param request
	   * @param sessionId
	   * @return
	 * @throws Exception 
	   */
	  public PermitCheckResponse checkPermitRequirment(PermitCheckRequest request, String sessionId) throws Exception
	  {
	    this.logger.debug("OEService.checkPermitRequirment start");
	    PermitCheckResponse response = null;
	   
	        OEDomain proxyclient = getOEDomainProxy();
	        long startTime = CommonUtil.getStartTime();
	try
	    {	 
	        response = proxyclient.checkPermitRequirment(request);
	        this.utilityloggerHelper.logTransaction("checkPermitRequirment", false, request, response, response.getStrErrMsg(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getStrCompanyCode());
	        logger.debug(XmlUtil.pojoToXML(request));
	    	logger.debug(XmlUtil.pojoToXML(response));
	    }
	   catch (RemoteException e) {
    	this.logger.error("OEService.checkPermitRequirment : Exception while getting data from ccs:", e);
    	this.utilityloggerHelper.logTransaction("checkPermitRequirment", false, request, e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getStrCompanyCode());
        logger.debug(XmlUtil.pojoToXML(request));
        throw e;// it is required to throw exception back to BO layer for proper response generation
    }
	    catch (Exception e) {
	    	this.logger.error("OEService.checkPermitRequirment : Exception while getting data from ccs:", e);
	    	this.utilityloggerHelper.logTransaction("checkPermitRequirment", false, request, e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getStrCompanyCode());
	        logger.debug(XmlUtil.pojoToXML(request));
	        throw e;// it is required to throw exception back to BO layer for proper response generation
	    }

	    this.logger.debug("OEService.checkPermitRequirment end");
	    return response;
	  } 
	  
	  
	  
	  /**
		 * Method to make getBPMatchStatusFromCCS service call from OEDomain web service
		 * @param oeSignupDTO
		 * @return BpMatchCCSResponse
		 * @throws ServiceException
		 */
		public BpMatchCCSResponse getBPMatchStatusFromCCS(BpMatchCCSRequest request) throws Exception 
		{
			BpMatchCCSResponse bpMatchCCSResponse = null ;
			try {
				OEDomain proxyclient = getOEServiceProxy();
				bpMatchCCSResponse = proxyclient.getBPMatchStatusFromCCS(request);
			} catch (Exception e) {
				logger.error("error while executing getBPMatchStatusFromCCS() method from OEDomain web service");
				throw new Exception("Exception in ValidationService:getBPMatchStatusFromCCS():", e);
			} 
			
			logger.debug("ValidationService::getBPMatchStatusFromCCS() Response :"+bpMatchCCSResponse);
			return bpMatchCCSResponse;
		}
		
		
		/**
		 * This will return OEDomain and set EndPoint URL
		 * @return proxy The OEDomain Object
		 */
		protected OEDomain getOEServiceProxy() throws Exception{
			return (OEDomain) getServiceProxy(OEDomainPortBindingStub.class,
					OE_DOMAIN_END_POINT_URL_JNDI_NAME);
		}
		
	  
}
