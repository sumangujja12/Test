package com.multibrand.bo;

import java.rmi.RemoteException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.domain.AllAlertsResponse;
import com.multibrand.exception.OAMException;
import com.multibrand.helper.ContentHelper;
import com.multibrand.service.ProfileService;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.ContractInfoRequest;
import com.multibrand.vo.response.ContractOffer;
import com.multibrand.vo.response.ContractOfferPlanContentResponse;
import com.multibrand.vo.response.GetContractInfoResponse;

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

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	public ContractOfferPlanContentResponse getMultiBrandPlanOffers(ContractInfoRequest request, String sessionId) {

		logger.info("::::::::::: Entering in to the ContractOfferPlanContentResponse Method :::::::::::");

		ContractOfferPlanContentResponse response = new ContractOfferPlanContentResponse();

		try {
			 /*** call get getContractInfoParallel NRGWS details  **/
			AllAlertsResponse allRequestResponse = profileService.getContractInfoParallel(contentHelper.getContractInfoParallelRequest(request), sessionId);
			String[] offerCode = null;
			offerCode = contentHelper.getContractOffer(allRequestResponse,response);
			contentHelper.getOfferContent(offerCode,response,request);
			Map<String, String> noOfTrees = getNoOfTreeServerd(request, sessionId);
			for(ContractOffer contractOffer: response.getPlans()) {
				contractOffer.setNumberOfTreesSaved(noOfTrees.get(contractOffer.getOfferCode()));
			}
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
	
	
	
	
	
	

}
