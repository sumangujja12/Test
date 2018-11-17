package com.multibrand.service;

import java.rmi.RemoteException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multibrand.domain.PendingSwapRequest;
import com.multibrand.domain.PendingSwapResponseMaster;
import com.multibrand.domain.RolloverPlanDetailsRequest;
import com.multibrand.domain.RolloverPlanDetailsResponse;
import com.multibrand.domain.SwapDomain;
import com.multibrand.domain.SwapDomainPortBindingStub;
import com.multibrand.domain.SwapRequest;
import com.multibrand.domain.SwapResponse;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.XmlUtil;

/**
 * 
 * @author kdeshmu1
 *
 * This class is responsible for fetching information from Redbull Service SwapDomain 
 */

@Service
public class SwapService extends BaseAbstractService {
	Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	/**
	 * This will return SwapDomainProxy and set EndPoint URL
	 * @return swapDomainProxy The SwapDomainProxy Object
	 */
	protected SwapDomain getSwapDomainProxy(){
        
		return (SwapDomain) getServiceProxy(SwapDomainPortBindingStub.class,
				SWAP_SERVICE_ENDPOINT_URL);
		
	}
	
	
	/**
	 * @author Kdeshmu1
	 * @param swapRequest
	 * @return
	 * @throws Exception 
	 */
	public SwapResponse submitSwap(SwapRequest swapRequest, String companyCode, String sessionId) throws Exception{
		
		SwapDomain proxy = getSwapDomainProxy();
		long startTime = CommonUtil.getStartTime();
		SwapResponse response = null;
		try{
			response= proxy.submitSwap(swapRequest);
		}catch(RemoteException ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction("submitSwap", false, swapRequest,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(swapRequest));
			throw ex;
		}
		catch(Exception ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction("submitSwap", false, swapRequest,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(swapRequest));
			throw ex;
		}
		utilityloggerHelper.logTransaction("submitSwap", false, swapRequest,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(swapRequest));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
	}
	
	/**
	 * This method is calling NRGWS service layer
	 * @author mshukla1
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public RolloverPlanDetailsResponse getRolloverPlanDetails(RolloverPlanDetailsRequest request, String companyCode, String sessionId)throws Exception{
		
		SwapDomain proxy =getSwapDomainProxy();
		long startTime = CommonUtil.getStartTime();
		RolloverPlanDetailsResponse response = null;
		try{
			response= proxy.getRolloverPlanDetails(request);
		}catch(RemoteException ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction("getRolloverPlanDetails", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			throw ex;
		}
		catch(Exception ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction("getRolloverPlanDetails", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			throw ex;
		}
		utilityloggerHelper.logTransaction("getRolloverPlanDetails", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
	}
	
	/**
	 * @author kdeshmu1
	 * @param request
	 * @param companyCode
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public PendingSwapResponseMaster pendingSwapDetails(PendingSwapRequest request, String companyCode, String sessionId)throws Exception{
		
		SwapDomain proxy =getSwapDomainProxy();
		long startTime = CommonUtil.getStartTime();
		PendingSwapResponseMaster response = null;
		try{
			logger.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%+11");
			response= proxy.getPendingSwapDetails(request);
			logger.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%+12");
		}catch(RemoteException ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction("pendingSwapDetails", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			throw ex;
		}
		catch(Exception ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction("pendingSwapDetails", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			throw ex;
		}
		utilityloggerHelper.logTransaction("pendingSwapDetails", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
	}
	
}
