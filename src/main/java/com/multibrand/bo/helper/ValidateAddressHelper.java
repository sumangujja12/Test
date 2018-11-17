package com.multibrand.bo.helper;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.multibrand.dto.request.ValidateAddressRequest;
import com.multibrand.dto.response.ValidateAddressResponse;
import com.multibrand.exception.OEException;
import com.multibrand.util.Constants;
import com.multibrand.util.LoggerUtil;
import com.multibrand.web.i18n.WebI18nMessageSource;
import com.reliant.domain.AddressValidateRequest;
import com.reliant.domain.AddressValidateResponse;

/**
 * This class contains the Business related helper methods for intermediate
 * calls request creation to other BO, Proxy layers, response data handlers and
 * other supported methods for BO class.
 * 
 * @author jyogapa1
 * 
 * @version 1.0
 * 
 */
@Component
public class ValidateAddressHelper implements Constants  {

	LoggerUtil logger = LoggerUtil.getInstance("ValidateAddressHelper");
	
	@Resource(name = "webI18nMessageSource")
	protected WebI18nMessageSource msgSource;

	/**
	 * @author jyogapa1 (Jenith)
	 * 
	 * Handles address validation error.
	 * 
	 * @param responseDto
	 * @param addressValidateResponse
	 */
	public void handleAddressValidationError(
			ValidateAddressResponse responseDto,
			AddressValidateResponse addressValidateResponse) {
	
			responseDto.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			responseDto.setResultDescription(addressValidateResponse
					.getErrorCode());
			responseDto.setStatusCode(STATUS_CODE_ASK);
	}
	
	/**
	 * @author jyogapa1 (Jenith)
	 * 
	 * Handles address validation success and match flag.
	 * 
	 * @param responseDto
	 * @param addressValidateResponse
	 * @throws OEException
	 */
	public void handleAddressValidationSuccess(
			ValidateAddressResponse responseDto,
			AddressValidateResponse addressValidateResponse) throws OEException{
		
		String matchStatus = addressValidateResponse.getMatchStatusFlag();

		if (matchStatus != null) {

			// Find the match status ('complete', 'partial' or 'no match')
			this.processAddressMatchStatus(responseDto, matchStatus);
		
			// Find the specific call status in case of 'Partial match' or 'No match'
			if (StringUtils.equalsIgnoreCase(matchStatus, ADDRESS_MATCH.PARTIAL_MATCH.toString())
					|| StringUtils.equalsIgnoreCase(matchStatus, ADDRESS_MATCH.NO_MATCH.toString())) {

				String callStatus = addressValidateResponse.getStatusValue();
				
				this.processAddressCallStatus(responseDto, callStatus);
			}
			
		} else {
			throw new OEException(
					"Match Status Flag is empty! Throwing error explicitly.");
		}
	
	}

	/**
	 * @author jyogapa1 (Jenith)
	 * 
	 *  Finds the Complete match , Partial match or Not matched valid
	 *  address from match status.
	 * 
	 * @param responseDto
	 * @param matchStatus
	 */
	private void processAddressMatchStatus(
			ValidateAddressResponse responseDto, String matchStatus) {

		switch (ADDRESS_MATCH.valueOf(matchStatus)) {

		case COMPLETE_MATCH:
			responseDto.setResultCode(RESULT_CODE_SUCCESS);
			responseDto.setStatusCode(STATUS_CODE_CONTINUE);
			responseDto.setMessageCode(ADDRESS_MATCH_MESSAGE.COMPLETE_MATCH
					.toString());
			break;
		case PARTIAL_MATCH:
			responseDto.setResultCode(RESULT_CODE_SUCCESS);
			responseDto.setStatusCode(STATUS_CODE_ASK);
			responseDto.setMessageCode(ADDRESS_MATCH_MESSAGE.PARTIAL_MATCH
					.toString());
			break;
		case NO_MATCH:
			responseDto.setResultCode(RESULT_CODE_SUCCESS);
			responseDto.setStatusCode(STATUS_CODE_ASK);
			responseDto.setMessageCode(ADDRESS_MATCH_MESSAGE.NO_MATCH
					.toString());
			break;

		default:
		}

	}

	/**
	 * 
	 * @author jyogapa1 (Jenith)
	 * 
	 * Finds the Missing Apt , Invalid Apt or Not valid address from
	 * call status.
	 * 
	 * 
	 * @param responseDto
	 * @param callStatus
	 */
	private void processAddressCallStatus(
			ValidateAddressResponse responseDto, String callStatus) {
		
		if (callStatus != null) {
			
			switch (ADDRESS_MATCH.valueOf(callStatus)) {

			case INVALID_APT:
				responseDto.setResultCode(RESULT_CODE_SUCCESS);
				responseDto.setStatusCode(STATUS_CODE_ASK);
				responseDto.setMessageCode(ADDRESS_MATCH_MESSAGE.INCORRECT_UNIT
						.toString());
				responseDto.setMessageText(getMessage(ADDRESS_VALIDATION_INCORRECT_UNIT_MSG));
				break;
			case MISSING_APT:
				responseDto.setResultCode(RESULT_CODE_SUCCESS);
				responseDto.setStatusCode(STATUS_CODE_ASK);
				responseDto.setMessageCode(ADDRESS_MATCH_MESSAGE.UNIT_NEEDED
						.toString());
				responseDto.setMessageText(getMessage(ADDRESS_VALIDATION_UNIT_NEEDED_MSG));
				break;
			case NOT_VALID_ADDRESS:
				responseDto.setResultCode(RESULT_CODE_SUCCESS);
				responseDto.setStatusCode(STATUS_CODE_ASK);
				responseDto
						.setMessageCode(ADDRESS_MATCH_MESSAGE.UNVERIFIED_ADDRESS
								.toString());
				responseDto.setMessageText(getMessage(ADDRESS_VALIDATION_UNVERIFIED_ADDRESS_MSG));
				break;
			case SYS_ERROR:
				responseDto.setResultCode(RESULT_CODE_SUCCESS);
				responseDto.setStatusCode(STATUS_CODE_ASK);
				responseDto
						.setMessageCode(ADDRESS_MATCH_MESSAGE.UNVERIFIED_ADDRESS
								.toString());
				responseDto.setMessageText(getMessage(ADDRESS_VALIDATION_UNVERIFIED_ADDRESS_MSG));
				break;
			default:
			}
		}
	}
	
	/**
	 * @author jyogapa1 (Jenith)
	 * 
	 * Creates address validate request for NRGWS
	 * 
	 * 
	 * @return
	 */
	public AddressValidateRequest createAddressValidateRequest(
			ValidateAddressRequest validateAddressRequestDTO) {

		AddressValidateRequest addressValidateRequest = new AddressValidateRequest();

		addressValidateRequest.setAptNumber(validateAddressRequestDTO
				.getAptNum());
		addressValidateRequest.setCity(validateAddressRequestDTO.getCity());
		addressValidateRequest.setCountry(COUNTRY_US);
		addressValidateRequest.setState(validateAddressRequestDTO.getState());
		addressValidateRequest.setStreetName(validateAddressRequestDTO
				.getStreetName());
		addressValidateRequest.setStreetNum(validateAddressRequestDTO
				.getStreetNum());
		addressValidateRequest.setZipCode(validateAddressRequestDTO
				.getZipCode());
		addressValidateRequest.setPOBox(validateAddressRequestDTO
				.getPoBox());

		return addressValidateRequest;
	}
	
	/**
	 * @author jyogapa1 (Jenith)
	 * 
	 * Populates validate address response from NRGWS.
	 * 
	 * 
	 * @return
	 */
	public AddressValidateRequest populateValidateAddressResponse(ValidateAddressResponse responseDto,
			AddressValidateResponse addressValidateResponse) {

		AddressValidateRequest addressValidateRequest = new AddressValidateRequest();

		responseDto.setAptNum(addressValidateResponse.getAptNumber());
		responseDto.setCity(addressValidateResponse.getCity());
		responseDto.setCountry(COUNTRY_US);
		responseDto.setState(addressValidateResponse.getState());
		responseDto.setStreetName(addressValidateResponse
				.getStreetName());
		responseDto.setStreetNum(addressValidateResponse
				.getStreetNum());
		responseDto.setZipCode(addressValidateResponse
				.getZipCode());
		responseDto.setPoBox(addressValidateResponse
				.getPOBox());

		return addressValidateRequest;
	}
	
	/**
	 * 
	 * @author jyogapa1 (Jenith)
	 * 
	 * Populates validate address response from NRGREST inpu.
	 * 
	 * 
	 * @return
	 */
	public AddressValidateRequest populateValidateAddressResponseSameAsRequest(ValidateAddressResponse responseDto,
			ValidateAddressRequest requestDto) {

		AddressValidateRequest addressValidateRequest = new AddressValidateRequest();

		responseDto.setAptNum(requestDto.getAptNum());
		responseDto.setCity(requestDto.getCity());
		responseDto.setCountry(COUNTRY_US);
		responseDto.setState(requestDto.getState());
		responseDto.setStreetName(requestDto
				.getStreetName());
		responseDto.setStreetNum(requestDto
				.getStreetNum());
		responseDto.setZipCode(requestDto
				.getZipCode());
		responseDto.setPoBox(requestDto
				.getPoBox());

		return addressValidateRequest;
	}

	/**
	 * Gets a value from the message properties file named webI18nMessageSource
	 * for the given message property key.
	 * 
	 * @param messageKey
	 * @return
	 */
	private String getMessage(String messageKey) {
		return msgSource.getMessage(messageKey);
	}

}