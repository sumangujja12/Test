package com.multibrand.bo;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.dao.ContentDao;
import com.multibrand.domain.AllAlertsResponse;
import com.multibrand.exception.OAMException;
import com.multibrand.helper.ContentHelper;
import com.multibrand.service.ProfileService;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.ContractInfoRequest;
import com.multibrand.vo.request.MaintenanceScheduleRequest;
import com.multibrand.vo.response.ContractOfferPlanContentResponse;
import com.multibrand.vo.response.GetContractInfoResponse;
import com.multibrand.vo.response.MonthlyUsageResponse;
import com.multibrand.vo.response.MonthlyUsageResponseList;
import com.multibrand.vo.response.contentResponse.MaintenanceSchedule;
import com.multibrand.vo.response.contentResponse.MaintenanceScheduleResponse;

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
	ContentDao contentDao;

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	public ContractOfferPlanContentResponse getMultiBrandPlanOffers(ContractInfoRequest request, String sessionId) {

		logger.info("::::::::::: Entering in to the ContractOfferPlanContentResponse Method :::::::::::");

		ContractOfferPlanContentResponse response = new ContractOfferPlanContentResponse();

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
		} catch (RemoteException e) {
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		} catch (Exception e) {
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
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
	
	public MaintenanceScheduleResponse getMaintenanceSchedule(MaintenanceScheduleRequest request) {
		List<MaintenanceSchedule> maintenanceSchedules = new ArrayList<MaintenanceSchedule>();
		MaintenanceScheduleResponse response = new MaintenanceScheduleResponse();
		try {
			logger.info("STARTED ::::::::::::::: ContentBo ::::: getMaintenanceSchedule");
			maintenanceSchedules = contentDao.getMaintenanceSchedule(request);
			if (maintenanceSchedules.size() > 0) {
				response.setMaintenanceSchedules(maintenanceSchedules);
				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(MSG_SUCCESS);
				logger.info("SUCCESS ::::::::::::::: ContentBo ::::: getMaintenanceSchedule");
			} else {
				response.setResultCode(RESULT_CODE_NO_DATA);
				response.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
				logger.info("NODATA ::::::::::::::: ContentBo ::::: getMaintenanceSchedule");
			}
		} catch (Exception e) {
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			logger.info("Exception Occured ::::::::::::::: ContentBo ::::: getMaintenanceSchedule" + e);
		}
		return response;
	}
}
