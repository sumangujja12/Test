package com.multibrand.bo;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import com.multibrand.util.LoggerUtil;
import com.multibrand.util.Token;
import com.multibrand.vo.request.TokenRequestVO;
import com.multibrand.vo.response.TokenizedResponse;

@Component
public class TokenBO extends BaseBO {
	
	LoggerUtil logger = LoggerUtil.getInstance("NRGREST_LOGGER");
	
	
	/**This method will validate and call token server.
	 * @param request
	 * 			Hold the inputs for the token server
	 * @return TokenizedResponse
	 * 			holds tokenized or de-tokenized value.
	 */
	public String getTokenResponse(TokenRequestVO request) {

		logger.debug("getTokenResponse :::::::: Start");

		TokenizedResponse tokenizedResponse = new TokenizedResponse();

		String returnToken = "";
		
		try {
			
			if (StringUtils.isBlank(request.getActionCode())
					|| (!request.getActionCode().equalsIgnoreCase(Token.getDriverLicenceAction())
					    && !request.getActionCode().equalsIgnoreCase(Token.getSsnAction())
					    && !request.getActionCode().equalsIgnoreCase(Token.getSSNDetokenAction())
					    && !request.getActionCode().equalsIgnoreCase(Token.getDriverLicenceDetokenAction())
						&& !request.getActionCode().equalsIgnoreCase(SY)
						&& !request.getActionCode().equalsIgnoreCase(PY)))
			{
				tokenizedResponse.setResultCode(RESULT_CODE_SUCCESS);
				tokenizedResponse.setResultDescription(ACTION_CODE_DETOKNIZED_INVALID);
				tokenizedResponse.setReturnToken(returnToken);
				logger.debug("getTokenResponse :::::::: Ends with invalid action code");
				return ACTION_CODE_DETOKNIZED_INVALID;
			}
			
			if (request.getActionCode().equalsIgnoreCase(Token.getDriverLicenceAction())) {
				returnToken = Token.getDRLToken(request.getNumToBeTokenized());	
				tokenizedResponse.setReturnToken(returnToken);
			} else if (request.getActionCode().equalsIgnoreCase(Token.getSsnAction())) {
				returnToken = Token.getSSNToken(request.getNumToBeTokenized());
				tokenizedResponse.setReturnToken(returnToken);
			} else if (request.getActionCode().equalsIgnoreCase(Token.getSSNDetokenAction())) {
				returnToken = Token.getSSNDeTokenization(request.getNumToBeTokenized());
				tokenizedResponse.setReturnToken(returnToken);
			} else if (request.getActionCode().equalsIgnoreCase(Token.getDriverLicenceDetokenAction())) {
				returnToken = Token.getDriverLicenceDeTokenization(request.getNumToBeTokenized());
				tokenizedResponse.setReturnToken(returnToken);
			} else if (request.getActionCode().equalsIgnoreCase(SY)) { 
                returnToken = Token.getSSNDeTokenization(request.getNumToBeTokenized()); 
                tokenizedResponse.setReturnToken(returnToken); 
            } else if (request.getActionCode().equalsIgnoreCase(PY)) { 
                returnToken = Token.getDriverLicenceDeTokenization(request.getNumToBeTokenized()); 
                tokenizedResponse.setReturnToken(returnToken); 
            }
			
		} catch (Exception e) {
			logger.error(e);
			tokenizedResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			tokenizedResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			returnToken += "\n" + "Fatal transport error: "
					+ e.getMessage();
			//throw new OAMException(200, e.getMessage(), tokenizedResponse);
		}

		logger.debug("getTokenResponse :::::::: End");

		return returnToken;
	}

	/**
	 * Create the Token Request Value Object.
	 * @param actionCode
	 * 		Action code for the token string.
	 * @param numToBeTokenized
	 * 		Text to be tokenized or De-tokenized
	 * @return
	 */
	public TokenRequestVO createTokenRequest(String actionCode, String numToBeTokenized) {
		TokenRequestVO request = new TokenRequestVO();
		request.setActionCode(actionCode);
		request.setNumToBeTokenized(numToBeTokenized);
		return request;
	} 
}
