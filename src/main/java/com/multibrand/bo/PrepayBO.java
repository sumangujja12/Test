package com.multibrand.bo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.domain.BalanceUsageRange;
import com.multibrand.domain.CheckPrepayOfferRequest;
import com.multibrand.domain.CheckPrepayOfferResponse;
import com.multibrand.domain.PpdApiDocReadRequest;
import com.multibrand.domain.PpdApiDocReadResponse;
import com.multibrand.domain.PpdApiDocUpdateRequest;
import com.multibrand.domain.PpdApiDocUpdateResponse;
import com.multibrand.domain.SsBalanceAndUsageDataRequest;
import com.multibrand.domain.SsBalanceAndUsageDataResponse;
import com.multibrand.domain.StartTollTagMonitorRequest;
import com.multibrand.domain.StartTollTagMonitorResponse;
import com.multibrand.dto.request.TemperatureRequest;
import com.multibrand.dto.request.UsageHistoryRequest;
import com.multibrand.dto.response.DailyTemperatureResponse;
import com.multibrand.dto.response.Temperature;
import com.multibrand.exception.OAMException;
import com.multibrand.service.BillingService;
import com.multibrand.service.OfferService;
import com.multibrand.service.SSService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.JavaBeanUtil;
import com.multibrand.vo.request.CheckIfPrepayOfferRequest;
import com.multibrand.vo.response.CheckIfPrepayOfferResponse;
import com.multibrand.vo.response.DayTemperatureData;
import com.multibrand.vo.response.SSBalanceAndUsageResponse;
import com.multibrand.vo.response.StartTollTagResponse;
import com.multibrand.vo.response.TemperaturedataResponse;
import com.multibrand.vo.response.billingResponse.PrepayDocReadResponse;
import com.multibrand.vo.response.billingResponse.PrepayDocUpdateResponse;



/**
 * 
 * @author dkrishn1
 * This Class is used to handle all GME related operations
 * 
 */
@Component
public class PrepayBO implements Constants {
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	@Autowired
	private OfferService offerService;
	
	@Autowired
	private BillingService billingService;
	
	@Autowired
	private SSService ssService;
	/**
	 * 
	 * @param checkIfPrepayOfferRequest
	 * @return
	 * @throws ServiceException
	 */
	public CheckIfPrepayOfferResponse checkPrepayOffer(CheckIfPrepayOfferRequest checkIfPrepayOfferRequest, String sessionID) {
		logger.debug("PrepayBO.checkPrepayOffer : Entering the method:" );
		
		CheckPrepayOfferResponse checkPrepayOfferResponse = new CheckPrepayOfferResponse();
		CheckIfPrepayOfferResponse response = new CheckIfPrepayOfferResponse();
		CheckPrepayOfferRequest checkPrepayOfferRequest = new CheckPrepayOfferRequest();
	
		try{
			checkPrepayOfferRequest.setBrandId(checkIfPrepayOfferRequest.getBrandId());
			checkPrepayOfferRequest.setCompanyCode(checkIfPrepayOfferRequest.getCompanyCode());
			checkPrepayOfferRequest.setOfferCode(checkIfPrepayOfferRequest.getOfferCode());
			
			checkPrepayOfferResponse = ssService.checkPrepayOffer(checkPrepayOfferRequest, sessionID);
			if(checkPrepayOfferResponse!= null)
			{
				if(null==checkPrepayOfferResponse.getPrepayOfferFlag())
				{
					logger.debug("PrepayBO.checkPrepayOffer() checkPrepayOfferResponse.getPrepayOfferFlag() is NULL");
					response.setResultCode(RESULT_CODE_CCS_ERROR);
					response.setResultDescription(RESULT_DESCRIPTION_CCS_EXCEPTION);
					throw new OAMException(200, RESULT_DESCRIPTION_CCS_EXCEPTION, response);
				}
				response.setPrepayOfferFlag(checkPrepayOfferResponse.getPrepayOfferFlag()); // valid values are X or EMPTY Where 'X' indicated 'Prepay' ad "" indicated PostPay
			}
			
			if(checkPrepayOfferResponse != null && StringUtils.isNotBlank(checkPrepayOfferResponse.getErrorMessage())){
				logger.debug("PrepayBO.checkPrepayOffer() Error Message:" + checkPrepayOfferResponse.getErrorMessage());
				logger.debug("PrepayBO.checkPrepayOffer() Error Code:" + checkPrepayOfferResponse.getErrorCode());
			}
		}catch(Exception e) {
			logger.error("PrepayBO.checkPrepayOffer() in PrepayBO "+e.getMessage()); 
			response.setResultCode(RESULT_CODE_CCS_ERROR);
			response.setResultDescription(RESULT_DESCRIPTION_CCS_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		
		logger.debug("PrepayBO.checkPrepayOffer() : End : :checkPrepayOfferResponse: "+checkPrepayOfferResponse);
		return response; 
	}
	
	
	/**
	 * 
	 * @param accountNumber
	 * @param fromDate
	 * @param toDate
	 * @param billToDate
	 * @param esid
	 * @param language
	 * @param noOfMonths
	 * @return
	 */
	public SSBalanceAndUsageResponse getBalanceAndUsageData(String accountNumber, 
			String fromDate,String toDate,
			String billToDate,String esid,
			String language,String noOfMonths,
			String companyCode,String brandName)
	{
		
		
		SsBalanceAndUsageDataRequest request = new SsBalanceAndUsageDataRequest();
		BalanceUsageRange balanceUsageRange = new BalanceUsageRange();
		SSBalanceAndUsageResponse response = new SSBalanceAndUsageResponse();
		
		SsBalanceAndUsageDataResponse ssBalanceUsageResponse = null;
	
		try{
			try {
				balanceUsageRange.setFromDate(CommonUtil.changeDateFormat(fromDate, "MM/dd/yyyy" ,"yyyy-MM-dd"));		
				balanceUsageRange.setToDate(CommonUtil.changeDateFormat(toDate, "MM/dd/yyyy" ,"yyyy-MM-dd"));				
			} catch (Exception e) {
				throw new Exception(" *** Invalid Date format *** ");				
			}
			
			request.setContractAccNumber(accountNumber);
			request.setEsid(esid);
			request.setLang(language);
			request.setNoOfMonths(noOfMonths);
			request.setBillToDate(CommonUtil.changeDateFormat(billToDate,"MM/dd/yyyy" ,"yyyy-MM-dd"));
			request.setBalanceUsageRange(balanceUsageRange);
			request.setCompanyCode(companyCode);
			request.setBrandId(brandName);			
			
			 try
			 {
				 ssBalanceUsageResponse = offerService.getBalanceAndUsageData(request);
				 
			 }
			 catch(Exception e){
					logger.error("getBalanceAndUsageData() in PrepayBO "+e.getMessage()); 
					response.setResultCode(RESULT_CODE_CCS_ERROR);
					response.setResultDescription(RESULT_DESCRIPTION_CCS_EXCEPTION);
					throw new OAMException(200, e.getMessage(), response);
				}
			
			 if(ssBalanceUsageResponse!= null)
			 {
					logger.info("Error message Array length "+ssBalanceUsageResponse.getMsg().length);
					if(ssBalanceUsageResponse.getMsg().length==0){
						if(ssBalanceUsageResponse.getDailyEstimatedData().length > 0){
							logger.info(" **** Daily estimated data available from CCS *** size ["+ssBalanceUsageResponse.getDailyEstimatedData().length+"]");
						}
						JavaBeanUtil.copy(ssBalanceUsageResponse, response);
						response.setResultCode(RESULT_CODE_SUCCESS);
						response.setResultDescription(MSG_SUCCESS);
					}else
					{
						response.setResultCode(RESULT_CODE_NO_DATA);
						response.setResultDescription(RESULT_CODE_NO_MATCH_DESCRIPTION);
						logger.info("Error Message ");
						
						for(mc_style.functions.soap.sap.document.sap_com.Bapiret2 err:ssBalanceUsageResponse.getMsg()){
							logger.info("Err Id "+err.getId());
							logger.info("Err Message "+err.getMessage());
						}
					}
			 }
			
		}catch(Exception e){
			logger.error("getBalanceAndUsageData() in PrepayBO "+e.getMessage()); 
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		
	    return response;
	}

	/**
	 * @author mshukla1
	 * @param caNumber
	 * @return
	 */
    public PrepayDocReadResponse prepayDocRead(String accountNumber,String companyCode,String brandName){
		
		
		PpdApiDocReadRequest request = new PpdApiDocReadRequest();
		request.setCa(accountNumber);
		
		PpdApiDocReadResponse ppDresponse=null;
		PrepayDocReadResponse response = new PrepayDocReadResponse();
		
		try {
			ppDresponse = billingService.prepayDocRead(request);
			System.out.println("ppDresponse tostring*"+ppDresponse.toString());
			System.out.println("ppDresponse tostring*"+ppDresponse.getContractAccountNumber());
			System.out.println("ppDresponse tostring*"+ppDresponse.getContractData(0).getPARTNER_GUID());
			System.out.println("ppDresponse tostring*"+ppDresponse.getContractData(0).getPPD_CONTRACT_ACC());
			
		}
		catch(Exception e){
			logger.error("prepayDocRead() in PrepayBO "+e.getMessage()); 
			response.setResultCode(RESULT_CODE_CCS_ERROR);
			response.setResultDescription(RESULT_DESCRIPTION_CCS_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		try{
			if(ppDresponse!= null)
			{
				if(StringUtils.isNotEmpty(ppDresponse.getErrCode()))
				{
					response.setResultCode(RESULT_CODE_NO_DATA);
					response.setResultDescription(RESULT_CODE_NO_MATCH_DESCRIPTION);
					
					logger.info("Error Code "+ppDresponse.getErrCode());
					logger.info("Error Message "+ppDresponse.getErrMessage());
				}
				else{
					JavaBeanUtil.copy(ppDresponse, response);
					response.setResultCode(RESULT_CODE_SUCCESS);
					response.setResultDescription(MSG_SUCCESS);
				}
			}
		}catch(Exception ex) {
			logger.error("prepayDocRead() in PrepayBO "+ex.getMessage()); 
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, ex.getMessage(), response);
		}
		
		return response;
	}
    
    /**
     * @author mshukla1
     * @param accountNumber
     * @param ppdId
     * @param reloadAmount
     * @param serviceEndDate
     * @param serviceStartDate
     * @param status
     * @param thresHoldAmt
     * @param tollTagEndDate
     * @param tollTagStartDate
     * @param uuid
     * @param companyCode
     * @param brandName
     * @return
     */
    public PrepayDocUpdateResponse prepayDocUpdate(String accountNumber,
			String ppdId, String reloadAmount, String serviceEndDate,
			String serviceStartDate, String status, String thresHoldAmt,
			String tollTagEndDate, String tollTagStartDate, String uuid,
			String companyCode,String brandName) {
		
    	
		PrepayDocUpdateResponse response = new PrepayDocUpdateResponse();
        PpdApiDocUpdateResponse ppdDocUpdateResponse = null;
        
		PpdApiDocUpdateRequest request = new PpdApiDocUpdateRequest();
	
		try{
			request.setContractAccountNumber(accountNumber);
			request.setPPDId(ppdId);
			
			if(reloadAmount!=null && StringUtils.isNotEmpty(reloadAmount))
			   request.setReloadAmount(Double.parseDouble(reloadAmount));
			
			request.setStatus(status);
			
			if(thresHoldAmt!=null && StringUtils.isNotEmpty(thresHoldAmt))
			   request.setThresHoldAmt(Double.parseDouble(thresHoldAmt));
			
			request.setUUID(uuid);
			request.setServiceEndDate(CommonUtil.getDatefromString(serviceEndDate));
			request.setServiceStartDate(CommonUtil.getDatefromString(serviceStartDate));
			request.setTollTagEndDate(CommonUtil.getDatefromString(tollTagEndDate));
			request.setTollTagStartDate(CommonUtil.getDatefromString(tollTagStartDate));
			try{
			ppdDocUpdateResponse = billingService.prepayDocUpdate(request);
			}
			catch(Exception e){
				logger.error("prepayDocUpdate() in PrepayBO "+e.getMessage()); 
				response.setResultCode(RESULT_CODE_CCS_ERROR);
				response.setResultDescription(RESULT_DESCRIPTION_CCS_EXCEPTION);
				throw new OAMException(200, e.getMessage(), response);
			}
			
			if(ppdDocUpdateResponse!= null)
			{
				logger.info("Response from NRGWS ");
				logger.info("Err code "+ ppdDocUpdateResponse.getErrCode());
				logger.info("Err Message "+ppdDocUpdateResponse.getErrMessage());
				logger.info("Response Status "+ ppdDocUpdateResponse.isResponseStatus());
				
				if(ppdDocUpdateResponse.isResponseStatus()){
					response.setResponseStatus(ppdDocUpdateResponse.isResponseStatus());
					response.setResultCode(RESULT_CODE_SUCCESS);
					response.setResultDescription(MSG_SUCCESS);
				}else
				{
					response.setResponseStatus(ppdDocUpdateResponse.isResponseStatus());
					response.setResultCode(RESULT_CODE_NO_DATA);
					response.setResultDescription(RESULT_CODE_NO_MATCH_DESCRIPTION);
				}
			}
			
		}catch(Exception e){
			logger.error("prepayDocUpdate() in PrepayBO " + e.getMessage());
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		
		return response;
	}
    
    public StartTollTagResponse startTollTagMonitor(String paymentMode,String contractAccount,
    		                                        String brandId,String companyCode,String sessionId){
    	
    	StartTollTagResponse response = new StartTollTagResponse();
    	StartTollTagMonitorResponse startTollTagresponse = null;
    	StartTollTagMonitorRequest request = new StartTollTagMonitorRequest();
    	logger.info("PrepayBO.startTollTagMonitor():::::: paymentMode "+paymentMode +" scontractAccount:::: "+contractAccount);
    	if((StringUtils.isNotBlank(paymentMode) && StringUtils.isNotBlank(contractAccount) )
    			&&(paymentMode.equals(PAYMENT_MODE_CC) ||paymentMode.equals(PAYMENT_MODE_BANK) )){
    		
    		request.setBrandId(brandId);
    		request.setCompanyCode(companyCode);
    		request.setContractAccountNumber(contractAccount);
    		request.setPaymentMode(paymentMode);
    		
    		try {
				startTollTagresponse = ssService.startTollTag(request, sessionId);
				logger.info("Response from NRGWS ::: returnCode "+startTollTagresponse.getReturnCode()+":: returnMessage "+startTollTagresponse.getReturnMessage());
				
				if(StringUtils.isNotBlank(startTollTagresponse.getErrorCode())){
					
					response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		    		response.setResultDescription(startTollTagresponse.getErrorCode());
				}
				else{
					 if(startTollTagresponse.getReturnCode().equals(SUCCESS_CODE))
					 {
						 response.setResultCode(RESULT_CODE_SUCCESS);
				    	 response.setResultDescription(startTollTagresponse.getReturnMessage());
					 }
					 else
					 {
						 response.setResultCode(startTollTagresponse.getReturnCode());
				    	 response.setResultDescription(startTollTagresponse.getReturnMessage());
					 }
				}
			} catch (ServiceException e) {
				logger.error("ServiceException::: PrepayBO.startTollTagMonitor()::::" +e.getMessage());
				response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			}
    		
    	}else
    	{
    		logger.info("Invalid Request :::: contractAccount "+contractAccount +"::: paymentMode "+paymentMode);
    		response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
    		response.setResultDescription(INVALID_REQ);
    	}
    	
    	return response;
    }
    
  
	/**
	 * @param tmpRequest
	 * @return
	 */
	public TemperaturedataResponse getTemperatureData(TemperatureRequest tmpRequest) {
		
		UsageHistoryRequest request = new UsageHistoryRequest();
		TemperaturedataResponse response = new TemperaturedataResponse();
		DailyTemperatureResponse tempResponse = null;
	
		try {
			request.setContractAccNumber(tmpRequest.getContractAccountNumber());
			request.setContractId(tmpRequest.getContractId());
			request.setEsiid(tmpRequest.getEsid());
			request.setFromDate(CommonUtil.changeDateFormat(tmpRequest.getFromDate(), "yyyy-MM-dd", "MM/dd/yyyy"));		
			request.setToDate(CommonUtil.changeDateFormat(tmpRequest.getToDate(), "yyyy-MM-dd", "MM/dd/yyyy"));		
			
			 try {
				 tempResponse = ssService.getTemperatureData(request);
			 }
			 catch(Exception e) {
					logger.error(" PrepayBO - getTemperatureData() ::: "+e.getMessage()); 
					response.setResultCode(RESULT_CODE_CCS_ERROR);
					response.setResultDescription(RESULT_DESCRIPTION_CCS_EXCEPTION);
					throw new OAMException(200, e.getMessage(), response);
			 }
			 
			 if(null != tempResponse ) {
				 logger.info(" PrepayBO - getTemperatureData() ::: tempResponse not null ");
				 if(null != tempResponse.getTempList() && tempResponse.getTempList().size() > 0) {
					 logger.info(" PrepayBO - getTemperatureData() :::  **** Daily temperature data available from from service *** size ["+tempResponse.getTempList().size()+"]");
					 //JavaBeanUtil.copy(tempResponse, response);
					 //response.setDayTemperatureDataList(dayTemperatureDataList);tempResponse.getDayTemperatureDataResponse()
					 List<DayTemperatureData> dayTemperatureDataList = new ArrayList<DayTemperatureData>();
					 DayTemperatureData temp = null;
					 for(Temperature tempData:tempResponse.getTempList()) {
						 temp = new DayTemperatureData();
						 temp.setDay(tempData.getActualDay());
						 temp.setDayHighTemp(tempData.getTempHigh());
						 temp.setDayLowTemp(tempData.getTempLow());
						 dayTemperatureDataList.add(temp);
					 }
					 
					 response.setDayTemperatureDataList(dayTemperatureDataList);
					 response.setResultCode(RESULT_CODE_SUCCESS);
					 response.setResultDescription(MSG_SUCCESS);
				 } else {
					 response.setResultCode(RESULT_CODE_NO_DATA);
					 response.setResultDescription(RESULT_CODE_NO_MATCH_DESCRIPTION);
					 logger.info(" PrepayBO - getTemperatureData() ::: Error Message "+tempResponse.getErrorMessage());
				 }
			 }
		} catch(Exception e) {
			logger.error(" PrepayBO - getTemperatureData() ::: "+e.getMessage()); 
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		
	    return response;
	}
	
}
