package com.multibrand.resources;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

import com.multibrand.exception.OEException;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;

/**
 * 
 * 
 * @author jyogapa1
 * 
 */
@Component
public class BaseResource implements Constants {

	/**
	 * 
	 */
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	/**
	 * Validates API input parameters required checks.
	 * 
	 * <p>
	 * List of constraints to be checked.
	 * </p>
	 * <ul>
	 * <li>1. Not null for required parameters
	 * <ul>
	 * 
	 * @param mandatoryParamMap
	 * @return Response
	 * @throws OEException
	 * 
	 * @author jyogapa1 (Jenith)
	 */
	@Deprecated
	protected Response validateParameters(Map<String, Object> mandatoryParamMap)
			throws OEException {

		String METHOD_NAME = "BaseResource: validateParameters(..)";
		logger.debug("Start:" + METHOD_NAME);

		Response errors = null;

		errors = this.validateParameters(mandatoryParamMap, null, null, null);

		return errors;
	}

	/**
	 * Validates API input parameters required and checks size equal to the
	 * number specified in size param.
	 * 
	 * <p>
	 * List of constraints to be checked.
	 * </p>
	 * <ul>
	 * <li>1. Not null for required parameters
	 * <li>2. Size must be equal to the specified number
	 * <ul>
	 * 
	 * @param mandatoryParamMap
	 * @param sizeParamMap
	 * @param size
	 * @return Response
	 * @throws OEException
	 * 
	 * @author jyogapa1 (Jenith)
	 */
	@Deprecated
	protected Response validateParameters(
			Map<String, Object> mandatoryParamMap,
			Map<String, Object> sizeParamMap, Integer size) throws OEException {

		String METHOD_NAME = "BaseResource: validateParameters(..)";
		logger.debug("Start:" + METHOD_NAME);

		Response errors = null;

		errors = this.validateParameters(mandatoryParamMap, sizeParamMap, size,
				size);

		return errors;
	}

	/**
	 * Validates API input parameters required and checks size boundaries
	 * between the given minimum and maximum.
	 * 
	 * <p>
	 * List of constraints to be checked.
	 * </p>
	 * <ul>
	 * <li>1. Not null for required parameters
	 * <li>2. Size must be between the specified boundaries included
	 * <ul>
	 * 
	 * @param mandatoryParamMap
	 * @param sizeParamMap
	 * @param min
	 * @param max
	 * @return response
	 * @throws OEException
	 * 
	 * @author jyogapa1 (Jenith)
	 */
	@SuppressWarnings("unused")
	@Deprecated
	protected Response validateParameters(
			Map<String, Object> mandatoryParamMap,
			Map<String, Object> sizeParamMap, Integer min, Integer max)
			throws OEException {

		String METHOD_NAME = "BaseResource: validateParameters(..)";
		logger.debug("Start:" + METHOD_NAME);

		Response errors = null;
		Boolean isRequestValid = null;

		errors = this.validateParametersEmptyConstraint(mandatoryParamMap);

		if (errors == null && sizeParamMap != null && sizeParamMap.size() > 0) {

			errors = this.validateParametersCustomConstraint(
					VALIDATION_CONSTRAINTS.SIZE.toString(), min, max,
					sizeParamMap);
		}

		logger.debug("End:" + METHOD_NAME);

		return errors;
	}

	/**
	 * Validates API input parameters should not be empty as follow.
	 * 
	 * <p>
	 * List of constraints to be checked.
	 * </p>
	 * <ul>
	 * <li>1. Not null for required parameters
	 * <ul>
	 * 
	 * @param mandatoryParamMap
	 * @return response
	 * @throws OEException
	 * 
	 * @author jyogapa1 (Jenith)
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private Response validateParametersEmptyConstraint(
			Map<String, Object> mandatoryParamMap) throws OEException {

		String METHOD_NAME = "BaseResource: validateParametersEmptyConstraint(..)";
		logger.debug("Start:" + METHOD_NAME);

		Response errors = null;
		Boolean isRequestValid = null;

		HashMap<String, Object> mandatoryParamCheckResponse = CommonUtil
				.checkMandatoryParam(mandatoryParamMap);

		String resultCode = (String) mandatoryParamCheckResponse
				.get("resultCode");

		if (StringUtils.isNotBlank(resultCode)
				&& resultCode.equalsIgnoreCase(Constants.SUCCESS_CODE)) {

			isRequestValid = BOOLEAN_TRUE;
		} else {

			String errorDesc = (String) mandatoryParamCheckResponse
					.get("errorDesc");
			if (StringUtils.isNotBlank(errorDesc)) {
				errors = CommonUtil
						.buildNotValidResponse(resultCode, errorDesc);
			} else {
				errors = CommonUtil.buildNotValidResponse(resultCode,
						Constants.STATUS_CODE_ASK);
			}
			logger.info("Inside validateParametersBasicConstraint:: errorDesc is "
					+ errorDesc);

			isRequestValid = BOOLEAN_FALSE;

		}

		logger.debug("End:" + METHOD_NAME);

		return errors;
	}

	/**
	 * 
	 * Validates API input custom parameters as follow.
	 * 
	 * <p>
	 * List of custom constraints to be checked.
	 * </p>
	 * <ul>
	 * <li>1. The size of the field or property is evaluated and must match the
	 * specified boundaries.
	 * <ul>
	 * 
	 * @param constraintType
	 * @param paramMap
	 * @return Response
	 * @throws OEException
	 * 
	 * @author jyogapa1 (Jenith)
	 */
	@Deprecated
	private Response validateParametersCustomConstraint(String constraintType,
			Integer min, Integer max, Map<String, Object> paramMap)
			throws OEException {

		String METHOD_NAME = "BaseResource: validateParametersCustomConstraint(..)";
		logger.debug("Start:" + METHOD_NAME);

		Response errors = null;

		switch (VALIDATION_CONSTRAINTS.valueOf(constraintType)) {

		case SIZE:
			errors = this.validateParametersSizeConstraint(paramMap, min, max);
			break;

		default:
		}

		logger.debug("End:" + METHOD_NAME);

		return errors;
	}

	/**
	 * 
	 * Validates API input parameters size as follow.
	 * 
	 * <p>
	 * List of constraints to be checked.
	 * </p>
	 * <ul>
	 * <li>1. The size of the field or property is evaluated and must match the
	 * specified boundaries.
	 * <ul>
	 * 
	 * @param sizeParamMap
	 * @return Response
	 * @throws OEException
	 * 
	 * @author jyogapa1 (Jenith)
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private Response validateParametersSizeConstraint(
			Map<String, Object> sizeParamMap, Integer min, Integer max)
			throws OEException {

		String METHOD_NAME = "BaseResource: validateParametersSizeConstraint(..)";
		logger.debug("Start:" + METHOD_NAME);

		Response errors = null;
		Boolean isRequestValid = null;

		HashMap<String, Object> mandatoryParamCheckResponse = CommonUtil
				.checkSizeParam(sizeParamMap, min, max);

		String resultCode = (String) mandatoryParamCheckResponse
				.get("resultCode");

		if (StringUtils.isNotBlank(resultCode)
				&& resultCode.equalsIgnoreCase(Constants.SUCCESS_CODE)) {

			isRequestValid = BOOLEAN_TRUE;
		} else {

			String errorDesc = (String) mandatoryParamCheckResponse
					.get("errorDesc");
			if (StringUtils.isNotBlank(errorDesc)) {
				errors = CommonUtil
						.buildNotValidResponse(resultCode, errorDesc);
			} else {
				errors = CommonUtil.buildNotValidResponse(resultCode,
						Constants.STATUS_CODE_ASK);
			}
			logger.info("Inside validateParametersSizeConstraint:: errorDesc is "
					+ errorDesc);

			isRequestValid = BOOLEAN_FALSE;

		}

		logger.debug("End:" + METHOD_NAME);

		return errors;
	}

}