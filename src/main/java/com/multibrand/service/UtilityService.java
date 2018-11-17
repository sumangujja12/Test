package com.multibrand.service;

import java.net.URL;

import javax.xml.rpc.ServiceException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;

import com.multibrand.exception.UtilityServiceException;
import com.multibrand.util.CommonUtil;
import com.nrg.utility.webservices.UtilityWebServices;
import com.nrg.utility.webservices.UtilityWebServicesPortBindingStub;
import com.nrg.utility.webservices.UtilityWebServicesResponse;
import com.nrg.utility.webservices.UtilityWebServicesService;
import com.nrg.utility.webservices.UtilityWebServicesServiceLocator;

/**
 * 
 * @author ahanda1
 * 
 */
@Service
public class UtilityService extends BaseAbstractService {

	private Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	// public static final String METHOD_SYNCHRONIZE_LOGGING = "logTransaction";

	public String logTransaction(String xmlRequest) throws Exception {

		return utilityService(CommonUtil.getGuid(), "LoggingService",
				xmlRequest, null);
	}

	/*
	 * public String sendReliantEmail(String xmlRequest) throws Exception{
	 * 
	 * return utilityService(CommonUtil.getGuid(), EMAIL_SERIVE,xmlRequest,
	 * null); }
	 */
	public String logTransaction(String xmlRequest, String endPointUrl)
			throws Exception {

		return utilityService(CommonUtil.getGuid(), "LoggingService",
				xmlRequest, endPointUrl);
	}

	private String utilityService(String txnID, String serviceName,
			String xmlRequest, String endPointURL) throws Exception {

		UtilityWebServicesResponse response = null;
		logger.info("Start-[utilityService]");

		try {
			if (endPointURL != null) {

				UtilityWebServicesService service = new UtilityWebServicesServiceLocator();

				URL portURL = new URL(endPointURL);

				UtilityWebServices port = service
						.getUtilityWebServicesPort(portURL);
				response = port.submitTransactions(txnID, serviceName,
						xmlRequest);

			} else {
				UtilityWebServices utilitySvcs = getUtilityServiceProxy();
				response = utilitySvcs.submitTransactions(txnID, serviceName,
						xmlRequest);
			}
			if (response == null) {
				throw new Exception(" Utility Service Return null Object");
			} else {
				logger.info("The Response from the Utility Service::::"
						+ response.getServiceResponse());

			}
		} catch (Exception e) {
			logger.error("Error in [Utility Service] :" + e.getMessage());
			logger.error("Cause :: " + e.getCause());

			throw new Exception("[Utility Service] Failed :", e);
		}
		logger.info("END - [Utility Service]");

		return response.getServiceResponse();
	}

	public UtilityWebServices getUtilityServiceProxy() throws ServiceException {
		return (UtilityWebServices) getServiceProxy(
				UtilityWebServicesPortBindingStub.class,
				UTILITY_SERVICE_ENDPOINT_URL);

	}

	public String sendReliantEmail(String xmlRequest) throws UtilityServiceException {

		try {
			return utilityService(getGuid(), EMAIL_SERVICE, xmlRequest, null);
		} catch (Exception e) {
			throw new UtilityServiceException(e);
		}
	}

	public String getGuid() {

		try {
			return CommonUtil.getGuid();
		} catch (Exception e) {
			logger.error("error in getting the guid:::::" + e);
			return "";
		}

	}

}