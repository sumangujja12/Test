package com.multibrand.bo;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import com.multibrand.domain.AllAlertsResponse;
import com.multibrand.exception.OAMException;
import com.multibrand.helper.ContentHelper;
import com.multibrand.service.AdodeAnalyticService;
import com.multibrand.service.ProfileService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.EnvMessageReader;
import com.multibrand.vo.request.ContractInfoRequest;
import com.multibrand.vo.response.ContractOfferPlanContentResponse;
import com.multibrand.vo.response.GetContractInfoResponse;
import com.multibrand.vo.response.MonthlyUsageResponse;
import com.multibrand.vo.response.MonthlyUsageResponseList;

/**
 * Handle Reuest to get the contents from the Rest Content service
 * 
 * @author SMarimuthu
 *
 */
@Component
public class ContentBO extends BaseBO implements Constants {

	/** Object of ProfileBO class. */
	@Autowired
	private ProfileService profileService;

	@Autowired
	private ContentHelper contentHelper;
	
	@Autowired
	private HistoryBO historyBO;
	
	@Autowired
	protected EnvMessageReader envMessageReader;
	
	@Autowired
	private TaskExecutor taskExecutor;

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	public ContractOfferPlanContentResponse getMultiBrandPlanOffers(ContractInfoRequest request, String sessionId) {

		logger.info("::::::::::: Entering in to the ContractOfferPlanContentResponse Method :::::::::::");
		String templateReportSuite = envMessageReader.getMessage(TEMPLATE_REPORTSUITE);
		String messageIdMsg = envMessageReader.getMessage("adobe.messageId.message");
		ContractOfferPlanContentResponse response = new ContractOfferPlanContentResponse();
		 Map<String, String>  adobeValueMap = null;
		try {
			if (contentHelper.handleValidationContentRequest(request, response)) {
				return response;
			}
			GetContractInfoResponse contractInfoResponse = profileService.getContractInfo(request.getAccountNumber(),
					request.getBpNumber(), request.getEsid(), request.getContractId(), request.getLanguageCode(),
					request.getCompanyCode(), sessionId);
			
			/*** call get getContractInfoParallel NRGWS details  **/
			AllAlertsResponse allRequestResponse = profileService.getContractInfoParallel(contentHelper.getContractInfoParallelRequest(request), sessionId);
			Set<String> offerCode = new TreeSet<String>();
			offerCode = contentHelper.getContractOffer(contractInfoResponse, allRequestResponse,response);
			contentHelper.getOfferContent(offerCode,response,request);
			response.getCurrentPlan().setAverageMonthlyPlanUsage(String.valueOf(getAverageMonthlyBilling(request, sessionId)));
			
			if(offerCode != null && offerCode.size() > 0 && StringUtils.isNotBlank(request.getMessageId())) {
				adobeValueMap = CommonUtil.getAdopeValueMap(request.getAccountNumber(), request.getMessageId(), request.getContractId(),
						request.getBpNumber(), request.getOsType(), templateReportSuite,
						"",GET_PLAN_OFFER,messageIdMsg);
				callAdodeAnalytics(adobeValueMap);
			} else if(StringUtils.isNotBlank(request.getMessageId())) {
				
				adobeValueMap = CommonUtil.getAdopeValueMap(request.getAccountNumber(), request.getMessageId(), request.getContractId(),
						request.getBpNumber(), request.getOsType(), templateReportSuite,
						"NO Offer Code",GET_PLAN_OFFER,messageIdMsg);
				callAdodeAnalytics(adobeValueMap);
			}
				
			
		} catch (RemoteException e) {
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			if (StringUtils.isNotBlank(request.getMessageId())) {
				adobeValueMap = CommonUtil.getAdopeValueMap(request.getAccountNumber(), request.getMessageId(),
						request.getContractId(), request.getBpNumber(), request.getOsType(), templateReportSuite,
						response.getErrorDescription(), GET_PLAN_OFFER,messageIdMsg);
				callAdodeAnalytics(adobeValueMap);
			}
			throw new OAMException(200, e.getMessage(), response);
		} catch (Exception e) {
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			if (StringUtils.isNotBlank(request.getMessageId())) {
				adobeValueMap = CommonUtil.getAdopeValueMap(request.getAccountNumber(), request.getMessageId(),
						request.getContractId(), request.getBpNumber(), request.getOsType(), templateReportSuite,
						response.getErrorDescription(), GET_PLAN_OFFER,messageIdMsg);
				callAdodeAnalytics(adobeValueMap);
			}
			throw new OAMException(200, e.getMessage(), response);
		}

		logger.info("::::::::::: Exiting ContractOfferPlanContentResponse Method :::::::::::");

		return response;
	}
	/**
	 * This method is responsible for getting average monthly bill amount
	 * @param contractInfo
	 * @param sessionId
	 * @return
	 */
	public double getAverageMonthlyBilling(ContractInfoRequest contractInfo, String sessionId) {
		double avgUsage = 0;
		String currentDate = new SimpleDateFormat(MM_dd_yyyy).format(Calendar.getInstance().getTime());
		MonthlyUsageResponseList monthlyUsage = historyBO.getMonthlyUsageDetails(contractInfo.getAccountNumber(),
				contractInfo.getContractId(), contractInfo.getEsid(), contractInfo.getZoneId(), currentDate,
				contractInfo.getCompanyCode(), sessionId);
		List<MonthlyUsageResponse> monthlyUsageList = monthlyUsage.getMonthlyUsageResponse();
		if (!monthlyUsageList.isEmpty() && monthlyUsageList.size() > 0) {
			double totMontlyUsageKwh = 0;
			int numberOfMonths = 0;
			for (MonthlyUsageResponse usage : monthlyUsageList) {
				Double monthlyUsageKwh = Double.parseDouble(usage.getTotalUsageMonth());
				logger.info("ContractId="+contractInfo.getContractId()+"monthlyUsageKwh"+String.valueOf(monthlyUsageKwh));
				logger.info("ContractId="+contractInfo.getContractId()+"yearlyUsageKwh"+String.valueOf(usage.getTotalUsageYear()));
				if (monthlyUsageKwh > 0.00) {
					totMontlyUsageKwh += monthlyUsageKwh;
					numberOfMonths++;
				}
			}
			if(totMontlyUsageKwh==0 || numberOfMonths==0){
				avgUsage = 0;
			} else {
				avgUsage = totMontlyUsageKwh / numberOfMonths;
			}
			logger.info("ContractId="+contractInfo.getContractId()+"totalMonthlyUsageKwh-"+String.valueOf(totMontlyUsageKwh));
		}
		
		return avgUsage;
	}
	
	/**
	 * @author SMarimuthu
	 * @param request
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	private Map<String, String> getNoOfTreeServerd(ContractInfoRequest request, String sessionId) throws Exception {
		GetContractInfoResponse contractInfoResponse = profileService.getContractInfo(request.getAccountNumber(),
				request.getBpNumber(), request.getEsid(), request.getContractId(), request.getLanguageCode(),
				request.getCompanyCode(), sessionId);
		if (contractInfoResponse == null || contractInfoResponse.getEligibleOffersList() == null) {
			return new LinkedHashMap<String, String>();
		}
		return contentHelper.getContractNoOfTrees(contractInfoResponse.getEligibleOffersList());
	}
	
	public void callAdodeAnalytics(Map<String, String> adobeValueMap) { 
		StringBuffer strBuffer = new StringBuffer("");
		strBuffer.append(envMessageReader.getMessage(ADOBE_ANALYTIC_TEMPLATE_URL));
		strBuffer.append(envMessageReader.getMessage(TEMPLATE_URL_QUERY_LIST_PARAMETER_ONE));
		strBuffer.append(envMessageReader.getMessage(TEMPLATE_URL_QUERY_LIST_PARAMETER_TWO));
		String url = CommonUtil.substituteVariables(strBuffer.toString(), adobeValueMap);
		logger.info("Tracking URL for the Customer {} -{}", adobeValueMap.get(PARAMETER_VARIABLE_CONTRACTID), url);
		//url = CommonUtil.encodeValue(url);
		logger.info("Encoded URL for the Customer {} -{}", adobeValueMap.get(PARAMETER_VARIABLE_CONTRACTID), url);
		Map<String,String> inputJson = new LinkedHashMap<String,String>();
		AdodeAnalyticService analyticalService = new AdodeAnalyticService(
				envMessageReader.getMessage(Constants.IOT_POST_URL), url, inputJson);
		taskExecutor.execute(analyticalService);
	}
	
}
