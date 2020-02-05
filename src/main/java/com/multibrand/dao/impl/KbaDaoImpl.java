package com.multibrand.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.multibrand.dao.KbaDAO;
import com.multibrand.domain.KbaErrorDTO;
import com.multibrand.domain.KbaQuestionResponse;
import com.multibrand.domain.KbaResponseReasonDTO;
import com.multibrand.dto.KBAErrorDTO;
import com.multibrand.dto.KBAResponseReasonDTO;
import com.multibrand.dto.KBASubmitResultsDTO;
import com.multibrand.util.Constants;
import com.multibrand.util.DBConstants;
import com.multibrand.dao.AbstractSpringDAO;

@Repository("kbaDAO")
public class KbaDaoImpl extends AbstractSpringDAO implements KbaDAO, Constants {

	@Autowired
	@Qualifier("choiceJdbcTemplate")
	private JdbcTemplate choicedbcTemplate;

	@Autowired(required = true)
	public KbaDaoImpl(@Qualifier("choiceJdbcTemplate") JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		init(KbaDaoImpl.class);
	}

	public boolean addKbaDetails(KbaQuestionResponse kbaQuestionResponse) throws Exception {
		logger.debug("KbaDaoImpl::addKbaDetails: Entering the method");
		int result = 0;

		try {
			String addKbaDetailsQuery = getSqlMessage().getMessage(DBConstants.OE_ADD_KBA_DETAILS_QUERY, null, null);
			String errorCodeList = StringUtils.EMPTY;
			String errorMsgList = StringUtils.EMPTY;
			String reasonCodeList = StringUtils.EMPTY;

			errorCodeList = getKBAErrorList(kbaQuestionResponse);
			errorMsgList = getKBAMsgList(kbaQuestionResponse);
			reasonCodeList = getKBAReasonCodeList(kbaQuestionResponse);

			if (kbaQuestionResponse.getQuestionList() != null && kbaQuestionResponse.getQuestionList().length > 0) {

				result = getJdbcTemplate().update(addKbaDetailsQuery, kbaQuestionResponse.getTransactionKey(),
						StringUtils.EMPTY, kbaQuestionResponse.getQuestionList().length, StringUtils.EMPTY,
						StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, kbaQuestionResponse.getReturnCode(),
						kbaQuestionResponse.getReturnMessage(), errorCodeList, errorMsgList, StringUtils.EMPTY);
			} else if ((kbaQuestionResponse.getQuestionList() != null
					|| kbaQuestionResponse.getQuestionList().length == 0)
					&& null != kbaQuestionResponse.getKbaSubmitAnswerResponseOutput() && (StringUtils
							.isNotEmpty(kbaQuestionResponse.getKbaSubmitAnswerResponseOutput().getTransactionKey()))) {

				result = getJdbcTemplate().update(addKbaDetailsQuery,
						kbaQuestionResponse.getKbaSubmitAnswerResponseOutput().getTransactionKey(),
						kbaQuestionResponse.getKbaSubmitAnswerResponseOutput().getDecision(), "0",
						kbaQuestionResponse.getKbaSubmitAnswerResponseOutput().getIdentityScore(),
						kbaQuestionResponse.getKbaSubmitAnswerResponseOutput().getOverallScore(),
						kbaQuestionResponse.getKbaSubmitAnswerResponseOutput().getInteractiveQscore(),
						kbaQuestionResponse.getKbaSubmitAnswerResponseOutput().getFraudlevel(),
						kbaQuestionResponse.getReturnCode(), kbaQuestionResponse.getReturnMessage(), errorCodeList,
						errorMsgList, reasonCodeList);

			}

			if (result > 0) {

				return true;
			} else {
				return false;
			}

		} catch (DataAccessException exception) {
			logger.error("addKbaDetails insert Failed " + exception);
			throw new Exception(exception);
		}
	}

	/**
	 * Start: OE : Sprint3 : 14064 - Create New KBA Question API :Kdeshmu1
	 * 
	 * @param kbaQuestionResponse
	 * @return
	 */
	private String getKBAErrorList(KbaQuestionResponse kbaQuestionResponse) {
		String errorCodeList = StringUtils.EMPTY;
		if (kbaQuestionResponse.getErrorList() != null) {
			for (KbaErrorDTO errorDTO : kbaQuestionResponse.getErrorList()) {

				errorCodeList = errorCodeList + errorDTO.getErrorCode() + Constants.DELIMETER_PIPE;
			}
			if (errorCodeList.endsWith(Constants.DELIMETER_PIPE)) {
				errorCodeList = errorCodeList.substring(0, errorCodeList.length() - 1);
			}
		}
		if (StringUtils.isNotBlank(errorCodeList) && errorCodeList.length() > 5) {
			errorCodeList = errorCodeList.substring(0, 4);
		}

		return errorCodeList;
	}

	/**
	 * Start: OE : Sprint3 : 14064 - Create New KBA Question API :Kdeshmu1
	 * 
	 * @author Kdeshmu1
	 * @param kbaQuestionResponse
	 * @return
	 */

	private String getKBAMsgList(KbaQuestionResponse kbaQuestionResponse) {
		String errorMsgList = StringUtils.EMPTY;
		if (kbaQuestionResponse.getErrorList() != null) {
			for (KbaErrorDTO errorDTO : kbaQuestionResponse.getErrorList()) {

				errorMsgList = errorMsgList + errorDTO.getErrorMsg() + Constants.DELIMETER_PIPE;
			}

			if (errorMsgList.endsWith(Constants.DELIMETER_PIPE)) {
				errorMsgList = errorMsgList.substring(0, errorMsgList.length() - 1);
			}

			if (errorMsgList.length() > 256) {
				errorMsgList = errorMsgList.substring(0, 255);
			}

		}
		return errorMsgList;
	}

	/**
	 * Start: OE : Sprint3 : 14064 - Create New KBA Question API :Kdeshmu1
	 * 
	 * @author Kdeshmu1
	 * @param kbaQuestionResponse
	 * @return
	 */
	private String getKBAReasonCodeList(KbaQuestionResponse kbaQuestionResponse) {
		String reasonCodeList = StringUtils.EMPTY;
		if (kbaQuestionResponse.getKbaSubmitAnswerResponseOutput().getKbaReasonList() != null) {
			for (KbaResponseReasonDTO reasonDTO : kbaQuestionResponse.getKbaSubmitAnswerResponseOutput()
					.getKbaReasonList()) {

				reasonCodeList = reasonCodeList + reasonDTO.getReasonCode() + Constants.DELIMETER_PIPE;
			}
			if (reasonCodeList.endsWith(Constants.DELIMETER_PIPE)) {
				reasonCodeList = reasonCodeList.substring(0, reasonCodeList.length() - 1);
			}

			if (reasonCodeList.length() > 256) {
				reasonCodeList = reasonCodeList.substring(0, 255);
			}

		}
		return reasonCodeList;
	}

	@Override
	public boolean updateKbaDetails(KBASubmitResultsDTO kBASubmitResultsDTO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("KbaDaoImpl::updateKbaDetails: Entering the method");
		String reason_code_list = null;
		String error_code_list = null;
		String error_msg_list = null;
		Long startTime = Calendar.getInstance().getTimeInMillis();
		int result = 0;
		try {
			String updateKbaDetailsQuery = getSqlMessage().getMessage(DBConstants.OE_UPDATE_KBA_DETAILS_QUERY, null,
					null);
			error_code_list = getErrorCodeList(kBASubmitResultsDTO);
			if (StringUtils.isNotBlank(error_code_list) && error_code_list.length() > 5) {
				error_code_list = error_code_list.substring(0, 4);
			}
			error_msg_list = getErrorMsgList(kBASubmitResultsDTO);
			reason_code_list = getKBAReasonCodeList(kBASubmitResultsDTO);
			if(null != kBASubmitResultsDTO.getKbaAnswerResponseDTO()){
			result = getJdbcTemplate().update(updateKbaDetailsQuery,
					kBASubmitResultsDTO.getKbaAnswerResponseDTO().getDecision(),
					kBASubmitResultsDTO.getKbaAnswerResponseDTO().getIdentityScore(),
					kBASubmitResultsDTO.getKbaAnswerResponseDTO().getOverallScore(),
					kBASubmitResultsDTO.getKbaAnswerResponseDTO().getInteractiveQscore(),
					kBASubmitResultsDTO.getKbaAnswerResponseDTO().getFraudlevel(), kBASubmitResultsDTO.getReturnCode(),
					kBASubmitResultsDTO.getReturnMessage(), error_code_list, error_msg_list, reason_code_list,
					kBASubmitResultsDTO.getKbaAnswerResponseDTO().getTransactionKey());
			}
			Long endTime = Calendar.getInstance().getTimeInMillis();
			logger.info(OE_SPRING_CALL_LOG_STATEMENT + EMPTY + "updateKbaDetails" + endTime + startTime);

			if (result > 0) {
				return true;
			} else {
				return false;
			}

		} catch (DataAccessException exception) {
			logger.error("updateKbaDetails insert Failed " + exception);
			throw new Exception(exception);
		}
	}

	/**
	 * Start: OE : Sprint3 : 14065 - Create New KBA Answers API :asingh
	 * 
	 * @author asingh
	 * @param KBASubmitResultsDTO
	 * @return
	 */
	private String getKBAReasonCodeList(KBASubmitResultsDTO kBASubmitResultsDTO) {
		String reasonCodeList = StringUtils.EMPTY;
		if(null != kBASubmitResultsDTO.getKbaAnswerResponseDTO()){
		if (kBASubmitResultsDTO.getKbaAnswerResponseDTO().getKbaReasonList() != null) {
			for (KBAResponseReasonDTO reasonDTO : kBASubmitResultsDTO.getKbaAnswerResponseDTO().getKbaReasonList()) {

				reasonCodeList = reasonCodeList + reasonDTO.getReasonCode() + Constants.DELIMETER_PIPE;
			}
			if (reasonCodeList.endsWith(Constants.DELIMETER_PIPE)) {
				reasonCodeList = reasonCodeList.substring(0, reasonCodeList.length() - 1);
			}

			if (reasonCodeList.length() > 256) {
				reasonCodeList = reasonCodeList.substring(0, 255);
			}

		}
	}
		return reasonCodeList;
	}

	/**
	 * Start: OE : Sprint3 : 14065 - Create New KBA Answers API :asingh
	 * 
	 * @author asingh
	 * @param kbaQuestionResponse
	 * @return
	 */

	private String getErrorMsgList(KBASubmitResultsDTO kbaSubmitResultsDTO) {
		String errorMsgList = StringUtils.EMPTY;
		if (kbaSubmitResultsDTO.getErrorList() != null) {
			for (KBAErrorDTO errorDTO : kbaSubmitResultsDTO.getErrorList()) {

				errorMsgList = errorMsgList + errorDTO.getErrorMsg() + Constants.DELIMETER_PIPE;
			}

			if (errorMsgList.endsWith(Constants.DELIMETER_PIPE)) {
				errorMsgList = errorMsgList.substring(0, errorMsgList.length() - 1);
			}

			if (errorMsgList.length() > 256) {
				errorMsgList = errorMsgList.substring(0, 255);
			}

		}
		return errorMsgList;
	}

	public String getErrorCodeList(KBASubmitResultsDTO kbaSubmitResultsDTO) {
		String errorCodeList = StringUtils.EMPTY;
		if (kbaSubmitResultsDTO.getErrorList() != null) {
			for (KBAErrorDTO cartItem : kbaSubmitResultsDTO.getErrorList()) {
				errorCodeList = errorCodeList + cartItem.getErrorCode() + Constants.DELIMETER_PIPE;
			}
			if (errorCodeList.endsWith(Constants.DELIMETER_PIPE)) {
				errorCodeList = errorCodeList.substring(0, errorCodeList.length() - 1);
			}
		}
		if (errorCodeList.length() > 40) {
			errorCodeList = errorCodeList.substring(0, 39);
		}
		return errorCodeList;
	}
}
