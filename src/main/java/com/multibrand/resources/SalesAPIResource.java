
package com.multibrand.resources;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.bo.OEBO;
import com.multibrand.bo.ValidationBO;
import com.multibrand.dto.OESignupDTO;
import com.multibrand.dto.request.AffiliateOfferRequest;
import com.multibrand.dto.request.CreditCheckRequest;
import com.multibrand.dto.request.EnrollmentRequest;
import com.multibrand.dto.request.EsidCalendarRequest;
import com.multibrand.dto.request.EsidRequest;
import com.multibrand.dto.request.GetKBAQuestionsRequest;
import com.multibrand.dto.request.KbaAnswerRequest;
import com.multibrand.dto.request.PerformPosIdAndBpMatchRequest;
import com.multibrand.dto.request.ProspectDataRequest;
import com.multibrand.dto.request.UCCDataRequest;
import com.multibrand.dto.response.AffiliateOfferResponse;
import com.multibrand.dto.response.EnrollmentResponse;
import com.multibrand.dto.response.EsidResponse;
import com.multibrand.dto.response.UCCDataResponse;
import com.multibrand.exception.OEException;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.request.handlers.OERequestHandler;
import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.TokenRequestVO;
import com.multibrand.vo.response.AgentDetailsResponse;
import com.multibrand.vo.response.EsidInfoTdspCalendarResponse;
import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.GetKBAQuestionsResponse;
import com.multibrand.vo.response.KbaAnswerResponse;
import com.multibrand.vo.response.NewCreditScoreResponse;
import com.multibrand.vo.response.PerformPosIdandBpMatchResponse;
import com.multibrand.vo.response.ProspectDataResponse;
import com.multibrand.vo.response.TokenizedResponse;
import com.multibrand.web.i18n.WebI18nMessageSource;

/**
 * This Resource is to handle all the Online Enrollment API calls.
 * 
 * @author NRG Energy
 */
@Component
@Path("/sales")
public class SalesAPIResource extends BaseResource {
	
	/**
	 * 
	 */
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	// ~Autowire entries
	@Autowired
	OERequestHandler oeRequestHandler;

	/** Object of oeBO class. */
	@Autowired
	private OEBO oeBO;

	/** Object of ValidationBO class. */
	@Autowired
	private ValidationBO validationBO;

	@Context
	private HttpServletRequest httpRequest;

	@Resource(name = "webI18nMessageSource")
	private WebI18nMessageSource msgSource;

	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	@GET
	@Path(API_OFFERS)	
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAffiliateOffers(	@QueryParam(value = "companyCode")  @NotBlank(groups = BasicConstraint.class) @Length(max = 4, groups = SizeConstraint.class) String companyCode,
			@QueryParam(value = "brandId")   String brandId,
			@QueryParam(value = "languageCode")   String languageCode,
			@QueryParam(value = "affiliateId") String affiliateId,
			@QueryParam(value = "channelType")  String channelType,
			@QueryParam(value = "transactionType")   String transactionType ,
			@QueryParam(value = "promoCode")  String promoCode,
			@QueryParam(value = "tdspCodeCCS")  String tdspCodeCCS,
			@QueryParam(value = "esid")   String esid ) {
		AffiliateOfferRequest request = new AffiliateOfferRequest();	
		request.setCompanyCode(companyCode);
		request.setBrandId(brandId);
		request.setLanguageCode(languageCode);
		request.setAffiliateId(affiliateId);
		request.setChannelType(channelType);
		request.setTransactionType(transactionType);
		request.setPromoCode(promoCode);
		request.setTdspCodeCCS(tdspCodeCCS);
		request.setEsid(esid);		
		Response response=null;
		try{			

			
			AffiliateOfferResponse offerResponse = oeBO.getAffiliateOffers(request,	httpRequest.getSession(true).getId());
			Response.Status status = offerResponse.getHttpStatus() != null ? offerResponse.getHttpStatus() :Response.Status.OK;
			response = Response.status(status).entity(offerResponse).build();
   		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			// Not logging Offer API calls - vsood
   			//utilityloggerHelper.logSalesAPITransaction(API_GET_AFFILIATE_OFFERS, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
       return response;
	}

	@POST
	@Path(API_POSID)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response performPosidAndBpMatch(
			@Valid PerformPosIdAndBpMatchRequest request) {
		long startTime = CommonUtil.getStartTime();
		Response response = null;
		String dobForPosId=null;
		HashMap<String, Object> mandatoryParamList = null;
		HashMap<String, Object> mandatoryParamCheckResponse = null;
		String resultCode = null;
		String errorDesc = null;
		boolean isValidAge = false;
		AgentDetailsResponse agentDetailsResponse;
		OEBO oeBo = null;
		TokenizedResponse tokenResponse = null;
		Map<String, Object> getPosIdTokenResponse = null;
		OESignupDTO oESignupDTO = new OESignupDTO();
		// Start Validating DOB- Jsingh1
		//Checking if DOB lies in Valid age Range (18-100)
		try{
			try{
				logger.info("inside performPosidAndBpMatch:: formatting DOB to Posid acceptable format");
				
				dobForPosId=CommonUtil.formatDateForNrgws(request.getDob());
				request.setDobForPosId(dobForPosId);
				
				logger.info("inside performPosidAndBpMatch:: dob for posid call is:: "+dobForPosId);
				
				mandatoryParamList = new HashMap<String, Object>();
	
				if (StringUtils.isBlank(request.getBillStreetNum())
						&& StringUtils.isBlank(request.getBillStreetName())) {
					// Either Billing PO box or Billing Street num/name should be supplied
					mandatoryParamList.put("billPOBox",
							request.getBillPOBox());
				} else {
					mandatoryParamList.put("billStreetNum",
							request.getBillStreetNum());
					mandatoryParamList.put("billStreetName",
							request.getBillStreetName());
				}
				if(StringUtils.equalsIgnoreCase(Constants.DSI_AGENT_ID,request.getAffiliateId())){
					mandatoryParamList.put("agentId",
							request.getAgentID());
				}
				mandatoryParamCheckResponse = CommonUtil
				.checkMandatoryParam(mandatoryParamList);
				resultCode = (String) mandatoryParamCheckResponse
				.get("resultCode");
	
				if (StringUtils.isNotBlank(resultCode)
				&& !resultCode.equalsIgnoreCase(Constants.SUCCESS_CODE)) {
	
					errorDesc = (String) mandatoryParamCheckResponse
					.get("errorDesc");
					
					if (StringUtils.isNotBlank(errorDesc)) {
						response = CommonUtil.buildNotValidResponse(resultCode,
						errorDesc);
					} else {
						response  = CommonUtil.buildNotValidResponse(errorDesc,
						Constants.STATUS_CODE_ASK);
					}
					logger.info("Inside performCreditCheck:: errorDesc is " + errorDesc);
				
					return response;
					
				}
				
				isValidAge=validationBO.getValidAge(dobForPosId);
				if( !isValidAge )
				{
					logger.info("inside performPosidAndBpMatch::Invalid Age: Prospect must be at least 18 years old but not "
							+ "over 100 years old or invalid date format");
					PerformPosIdandBpMatchResponse validPosIdResponse= validationBO.getInvalidDOBResponse(request.getAffiliateId(),
							request.getTrackingId());				
					
					response = Response.status(200).entity(validPosIdResponse)
							.build();
					return response;
				}
				//START : OE :Sprint61 :US21009 :Kdeshmu1
				if(StringUtils.isNotBlank(request.getAgentID())){
					agentDetailsResponse=validationBO.validateAgentID(request.getAgentID());
					if(!RESULT_CODE_SUCCESS.equalsIgnoreCase(agentDetailsResponse.getResultCode()))
					{
						logger.info("Agent Id is not valid");
						PerformPosIdandBpMatchResponse validPosIdResponse= validationBO.getInvalidAgentIDResponse(request.getAgentID(),
								request.getTrackingId());				
						
						response = Response.status(200).entity(validPosIdResponse)
								.build();
						return response;
					}else{
						oESignupDTO.setAgentID(request.getAgentID());
						oESignupDTO.setAgentFirstName(agentDetailsResponse.getAgentDetailsResponseOutData().getResult().get(0).getAgentFirstName());
						oESignupDTO.setAgentLastName(agentDetailsResponse.getAgentDetailsResponseOutData().getResult().get(0).getAgentLastName());
						oESignupDTO.setAgentType(agentDetailsResponse.getAgentDetailsResponseOutData().getResult().get(0).getAgentType());
						oESignupDTO.setVendorCode(agentDetailsResponse.getAgentDetailsResponseOutData().getResult().get(0).getAgentVendorCode());
						oESignupDTO.setVendorName(agentDetailsResponse.getAgentDetailsResponseOutData().getResult().get(0).getAgentVendorName());
					}
				}//END : OE :Sprint61 :US21009 :Kdeshmu1  
				
			}
			catch(Exception e)
			{
				logger.info("inside performPosidAndBpMatch :: unable to validate age or Agent ID for the prospect.", e);
			}				
			//End Validating DOB- Jsingh1
			
			oeBo = new OEBO();
			tokenResponse = new TokenizedResponse();
			getPosIdTokenResponse = new HashMap<String, Object>();
			
			// Changing language code to suitable locale
			request.setLanguageCode(CommonUtil
					.localeCode(request.getLanguageCode()));
			logger.info("inside validatePosId::after local change languageCode langauge is :: "
					+ request.getLanguageCode());
			
			getPosIdTokenResponse = oeBo.getPosIdTokenResponse(
					request.getTdl(), request.getSsn(),
					request.getAffiliateId(),
					request.getTrackingId());
			
			if (getPosIdTokenResponse != null) {
				tokenResponse = (TokenizedResponse) getPosIdTokenResponse
						.get("tokenResponse");
				request.setTokenTDL((String) getPosIdTokenResponse
						.get("tokenTdl"));
				request.setTokenSSN((String) getPosIdTokenResponse
						.get("tokenSSN"));
	
				if (tokenResponse.getResultCode().equals(Constants.RESULT_CODE_SUCCESS)
				&& StringUtils.isNotBlank(tokenResponse.getReturnToken())) {
					logger.info("inside performPosidAndBpMatch:: affiliate Id : "
							+ request.getAffiliateId()
							+ ":: got token back.");
					if (!CommonUtil.checkTokenDown(tokenResponse.getReturnToken())) {
						
						PerformPosIdandBpMatchResponse validPosIdResponse = validationBO
								.validatePosId(request,oESignupDTO );
						response = Response.status(200).entity(validPosIdResponse)
								.build();
						logger.info("inside performPosidAndBpMatch:: affiliate Id : "
								+ request.getAffiliateId()
								+ "::rendering response pojo :: " + response);
												
						if(StringUtils.equals(request.getAffiliateId(),"372529") 
								&& StringUtils.isNotBlank(validPosIdResponse.getBpMatchFlag())							
								&& !StringUtils.equals(validPosIdResponse.getStatusCode(), "00")){	
							logger.info("inside sendPowerGeniusConfirmationEmail");
							try{
								oeBo.sendPowerGeniusConfirmationEmail(request.getEmail());
							}catch(Exception e){
								logger.error("inside performPosidAndBpMatch :: email sent failed for Power genius Online affiliates.", e);							
							}
						}
						// End : Validate for Power Genius Online Affiliates by KB
					}
	
					else { // returning exception and error as token server is down
						logger.info("inside performPosidAndBpMatch:: Token Server Down ");
						tokenResponse.setStatusCode(Constants.STATUS_CODE_STOP);
						tokenResponse.setMessageCode(Constants.TOKEN_SERVER_DOWN);
						tokenResponse.setMessageText(msgSource
								.getMessage(TOKEN_SERVER_DOWN_MSG_TXT));
						tokenResponse
								.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE);
						response = Response.status(200).entity(tokenResponse)
								.build();
						return response;
					}
				} else if (tokenResponse.getResultCode().equals(
				Constants.RESULT_CODE_EXCEPTION_FAILURE)) { // if validation fail for this scenario
	
					response = Response.status(200).entity(tokenResponse).build();
					return response;
				} else {
					tokenResponse.setStatusCode(Constants.STATUS_CODE_STOP);
					tokenResponse.setMessageCode(Constants.TOKEN_SERVER_DOWN);
					tokenResponse.setMessageText(msgSource
							.getMessage(TOKEN_SERVER_DOWN_MSG_TXT));
					tokenResponse
							.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE);
					response = Response.status(200).entity(tokenResponse).build();
					return response;
				}
			} else {
				tokenResponse.setStatusCode(Constants.STATUS_CODE_STOP);
				tokenResponse.setMessageCode(Constants.TOKEN_SERVER_DOWN);
				tokenResponse.setMessageText(msgSource
						.getMessage(TOKEN_SERVER_DOWN_MSG_TXT));
				tokenResponse
						.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE);
				response = Response.status(200).entity(tokenResponse).build();
				return response;
			}
		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_POSID, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
       return response;
	}
	
	@POST
	@Path(API_AVAILABLE_DATES)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getESIDAndCalendarDates(
			@Valid EsidCalendarRequest request) {
		long startTime = CommonUtil.getStartTime();
		Response response = null;
		try{
			if (StringUtils.isBlank(request.getLanguageCode())) request.setLanguageCode(Constants.LOCALE_LANGUAGE_CODE_E);
				EsidInfoTdspCalendarResponse esidInfoTdspResponse = oeBO
					.getESIDAndCalendarDates(request.getCompanyCode(),
							request.getAffiliateId(),
							request.getBrandId(),
							request.getServStreetNum(),
							request.getServStreetName(),
							request.getServStreetAptNum(),
							request.getServZipCode(),
							request.getTdspCodeCCS(),
							request.getTransactionType(),
							request.getTrackingId(),
							request.getBpMatchFlag(),
							request.getLanguageCode(),
							request.getEsid(),
							httpRequest.getSession(true).getId());
				response = Response.status(Response.Status.OK).entity(esidInfoTdspResponse).build();
		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_AVAILABLE_DATES, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
       return response;
	}
	
	@POST
	@Path(API_CHECK_CREDIT)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response performCreditCheck(
			@Valid CreditCheckRequest request) throws OEException {
		long startTime = CommonUtil.getStartTime();
		Response response = null;
		try{
			HashMap<String, Object> mandatoryParamList = new HashMap<String, Object>();
	
			if (StringUtils.isBlank(request.getBillStreetNum())
					&& StringUtils.isBlank(request.getBillStreetName())) {
				// Either Billing PO box or Billing Street num/name should be
				// supplied
				mandatoryParamList.put("billPOBox",
						request.getBillPOBox());
			} else {
				mandatoryParamList.put("billStreetNum",
						request.getBillStreetNum());
				mandatoryParamList.put("billStreetName",
						request.getBillStreetName());
			}
	
			if (StringUtils.isBlank(request.getLanguageCode()))
				request
						.setLanguageCode(Constants.LOCALE_LANGUAGE_CODE_E);
	
			HashMap<String, Object> mandatoryParamCheckResponse = CommonUtil
					.checkMandatoryParam(mandatoryParamList);
	
			String resultCode = (String) mandatoryParamCheckResponse
					.get("resultCode");
	
			logger.debug("inside performCreditCheck:: resultcode is :: "+resultCode);
			
			if (StringUtils.isNotBlank(request.getBpMatchFlag())
					&& request.getBpMatchFlag().equalsIgnoreCase(BPSD))
				request.setMatchedBP(EMPTY);
	
			if (StringUtils.isNotBlank(resultCode)
					&& resultCode.equalsIgnoreCase(Constants.SUCCESS_CODE)) {
				NewCreditScoreResponse newCreditScoreResponse = oeBO
						.performCreditCheck(oeRequestHandler
								.createNewCreditScoreRequest(request),
								request);
				response = Response.status(Response.Status.OK)
						.entity(newCreditScoreResponse).build();
			} else {
				String errorDesc = (String) mandatoryParamCheckResponse
						.get("errorDesc");
				if (StringUtils.isNotBlank(errorDesc)) {
					response = CommonUtil.buildNotValidResponse(resultCode,
							errorDesc);
				} else {
					response = CommonUtil.buildNotValidResponse(errorDesc,
							Constants.STATUS_CODE_ASK);
				}
				logger.debug("Inside performCreditCheck:: errorDesc is " + errorDesc);
			}
			logger.debug("END ******* performCreditCheck API**********");
		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_CHECK_CREDIT, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
		return response;
	}
	
	@POST
	@Path(API_CREDIT_DATA)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response submitUCCData(@Valid UCCDataRequest request) {
		long startTime = CommonUtil.getStartTime();
		Response response = null;
		String errorDesc = null;
		HashMap<String, Object> mandatoryParamList = null;
		HashMap<String, Object> mandatoryParamCheckResponse = null;
		
		mandatoryParamList = new HashMap<String, Object>();
		try{
			// Either Billing PO box or Billing Street num/name should be supplied
			mandatoryParamList.put("firstName",
					request.getFirstName());

			mandatoryParamList.put("lastName",
					request.getLastName());
			mandatoryParamList.put("depositAmount",
					request.getDepositAmount());
		

		mandatoryParamCheckResponse = CommonUtil
		.checkMandatoryParam(mandatoryParamList);
		String resultCode = (String) mandatoryParamCheckResponse
		.get("resultCode");
		
		if (StringUtils.isNotBlank(resultCode)
				&& !resultCode.equalsIgnoreCase(Constants.SUCCESS_CODE)) {

					errorDesc = (String) mandatoryParamCheckResponse
					.get("errorDesc");
					
					if (StringUtils.isNotBlank(errorDesc)) {
						response = CommonUtil.buildNotValidResponse(resultCode,
						errorDesc);
					} else {
						response = CommonUtil.buildNotValidResponse(errorDesc,
						Constants.STATUS_CODE_ASK);
					}
					logger.info("Inside submitUCCData:: errorDesc is " + errorDesc);
				
					return response;
				}
		
		
		
		HashMap<String, Object> nagativeParamList = null;
		HashMap<String, Object> negativeParamCheckResponse = null;
		
		nagativeParamList = new HashMap<String, Object>();


		nagativeParamList.put("depositAmount",
				request.getDepositAmount());

		if(StringUtils.isNotEmpty(request.getCreditScore())) {
			nagativeParamList.put("creditScore",
					request.getCreditScore());
		}

		

		negativeParamCheckResponse = CommonUtil
		.checkNegaviteValueInParam(nagativeParamList);
		 resultCode = (String) negativeParamCheckResponse
		.get("resultCode");
		
		if (StringUtils.isNotBlank(resultCode)
				&& !resultCode.equalsIgnoreCase(Constants.SUCCESS_CODE)) {

					errorDesc = (String) negativeParamCheckResponse
					.get("errorDesc");
					
					if (StringUtils.isNotBlank(errorDesc)) {
						response = CommonUtil.buildNotValidResponse(resultCode,
						errorDesc);
					} else {
						response = CommonUtil.buildNotValidResponse(errorDesc,
						Constants.STATUS_CODE_ASK);
					}
					logger.info("Inside submitUCCData:: errorDesc is " + errorDesc);
				
					return response;
				}
		
		
		UCCDataResponse uccResp = oeBO.submitUCCData(request,
				httpRequest.getSession(true).getId());
		response = Response.status(Response.Status.OK).entity(uccResp).build();
		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_CREDIT_DATA, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
       return response;
	}
	
	@POST
	@Path(API_SUBMIT_ENROLLMENT)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response submitEnrollment(@Valid EnrollmentRequest request)
			throws OEException {
		long startTime = CommonUtil.getStartTime();
		Response response = null;
	    try{
	    	EnrollmentResponse enrollmentResponse = oeBO.submitEnrollment(request);
	    	response = Response.status(Response.Status.OK).entity(enrollmentResponse).build();
	    } catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_SUBMIT_ENROLLMENT, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
       return response;
	}
	
	@POST
    @Path(API_GET_KBA_QUESTIONS)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getKBAQuestions(@Valid GetKBAQuestionsRequest request) {
		long startTime = CommonUtil.getStartTime();
		Response response=null;
       try{
        	GetKBAQuestionsResponse getKBAQuestionsResponse = oeBO.getKBAQuestions(request);
            response = Response.status(Response.Status.OK).entity(getKBAQuestionsResponse).build();
   		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_GET_KBA_QUESTIONS, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
       return response;
    }
	
	@POST
	@Path(API_KBA_RESULT)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response submitKBAAnswers(@Valid KbaAnswerRequest request) throws Exception {
		long startTime = CommonUtil.getStartTime();
		Response response=null;
		
		try{
			KbaAnswerResponse kbaAnsweresponse = oeBO.submitKBAAnswers(request);
			response = Response.status(Response.Status.OK).entity(kbaAnsweresponse).build();
   		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_KBA_RESULT, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
       return response;
	}
	
	/**
	 * START :OE ADO SPrint4 : Tokenization API
	 * @author Asingh
	 * @param actionCode
	 * @param numToBeTokenized
	 * @return
	 */
	@GET
	@Path(API_TOKEN)	
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getTokenResponse(@QueryParam(value = "actionCode") String actionCode,@QueryParam(value ="numToBeTokenized") String numToBeTokenized) throws Exception {
		Response response=null;
		TokenRequestVO request = new TokenRequestVO();
		long startTime = CommonUtil.getStartTime();
		try{
			request.setActionCode(actionCode);
			request.setNumToBeTokenized(numToBeTokenized);
			TokenizedResponse tokenizedResponse = oeBO.getTokenResponse(request);
			Response.Status status = tokenizedResponse.getHttpStatus() != null ? tokenizedResponse.getHttpStatus() :Response.Status.OK;
			response = Response.status(status).entity(tokenizedResponse).build();
		}catch(Exception e){ 
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
			logger.error(e.fillInStackTrace());
		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_TOKEN, false, request, response, CommonUtil.getElapsedTime(startTime), EMPTY, EMPTY);
   		}
		return response;
	}
	
	/**
	 * START :OE ADO SPrint4 : To get Prospect Data
	 * @author Kdeshmu1
	 * @param prospectID
	 * @param lastFourSSN
	 * @param companyCode
	 * @param languageCode
	 * @return
	 */
	@GET
	@Path(API_PROSPECT)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProspectData(@QueryParam(value = "prospectID")   String prospectID,
			@QueryParam(value = "last4SSN")   String lastFourSSN,
			@QueryParam(value = "companyCode")   String companyCode,
			@QueryParam(value = "languageCode")   String languageCode) {
		Response response = null;
		long startTime = CommonUtil.getStartTime();
		ProspectDataRequest request = null;
		try{
			request = new ProspectDataRequest();
			request.setCompanyCode(companyCode);
			request.setProspectID(prospectID);
			request.setLastfourdigitSSN(lastFourSSN);
		ProspectDataResponse prospectDataResponse = oeBO.getProspectData(prospectID,lastFourSSN,companyCode);
		Response.Status status = prospectDataResponse.getHttpStatus() != null ? prospectDataResponse.getHttpStatus() :Response.Status.OK;
		response = Response.status(status).entity(prospectDataResponse).build();
		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(languageCode))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_PROSPECT, false, request, response, CommonUtil.getElapsedTime(startTime), EMPTY, EMPTY);
   		}
		return response;
	}

	@POST
	@Path(API_ESID)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
	public Response getESIDDetails(@Valid EsidRequest request){
		Response response = null;
		long startTime = CommonUtil.getStartTime();
		try{
			EsidResponse getEsiidResponse = oeBO.getESIDDetails(request);
			response = Response.status(Response.Status.OK).entity(getEsiidResponse).build();
		}catch (Exception e) {
	   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
	   			logger.error(e.fillInStackTrace());
	   	}finally{
	   			utilityloggerHelper.logSalesAPITransaction(API_PROSPECT, false, request, response, CommonUtil.getElapsedTime(startTime), EMPTY, EMPTY);
	   	}
		return response;	
	}
}
