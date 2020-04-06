package com.multibrand.bo;

import java.util.Arrays;
import java.util.LinkedHashSet;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.bo.helper.OeBoHelper;
import com.multibrand.dto.request.AffiliateOfferRequest;
import com.multibrand.dto.request.EnrollmentRequest;
import com.multibrand.dto.request.IdentityRequest;
import com.multibrand.dto.request.PerformPosIdAndBpMatchRequest;
import com.multibrand.dto.request.ProspectDataRequest;
import com.multibrand.dto.request.SalesEnrollmentRequest;
import com.multibrand.dto.request.SalesEsidCalendarRequest;
import com.multibrand.dto.request.SalesOfferRequest;
import com.multibrand.dto.response.AffiliateOfferResponse;
import com.multibrand.dto.response.EnrollmentResponse;
import com.multibrand.dto.response.IdentityResponse;
import com.multibrand.dto.response.SalesBaseResponse;
import com.multibrand.dto.response.SalesEnrollmentResponse;
import com.multibrand.dto.response.SalesOfferResponse;
import com.multibrand.dto.response.ServiceLocationResponse;
import com.multibrand.util.Constants;
import com.multibrand.util.LoggerUtil;
import com.multibrand.util.Token;
import com.multibrand.vo.request.SalesTokenRequest;
import com.multibrand.vo.response.EsidInfoTdspCalendarResponse;
import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.ProspectDataInternalResponse;
import com.multibrand.vo.response.ProspectDataResponse;
import com.multibrand.vo.response.SalesEsidInfoTdspCalendarResponse;
import com.multibrand.vo.response.SalesTokenResponse;

@Component
public class SalesBO extends OeBoHelper implements Constants {

	LoggerUtil logger = LoggerUtil.getInstance("NRGREST_LOGGER");

	@Autowired
	private OEBO oeBO;

	@Context
	private HttpServletRequest httpRequest;

	public Response performPosidAndBpMatch(IdentityRequest request) throws Exception {
		PerformPosIdAndBpMatchRequest performPosidAndBPMatchRequest = new PerformPosIdAndBpMatchRequest();
		Response response = null;
		try {
			BeanUtils.copyProperties(request, performPosidAndBPMatchRequest);
			response = oeBO.performPosidAndBpMatch(performPosidAndBPMatchRequest);
			if (response.getEntity() instanceof GenericResponse) {
				GenericResponse affiliateResponse = (GenericResponse) response.getEntity();
				IdentityResponse identityResponse = new IdentityResponse();
				BeanUtils.copyProperties(affiliateResponse, identityResponse);
				response = Response.status(response.getStatus()).entity(identityResponse).build();
			}
		} catch (Exception e) {
			logger.error("Exception in SalesBO.performPosidAndBpMatch", e);
			throw e;
		}
		return response;
	}

	/**
	 * @author Kdeshmu1
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public SalesBaseResponse getSalesESIDAndCalendarDates(SalesEsidCalendarRequest salesEsidCalendarRequest,
			HttpServletRequest httpRequest) throws Exception {
		ServiceLocationResponse serviceLoationResponse = null;
		SalesEsidInfoTdspCalendarResponse salesEsidInfoTdspCalendarResponse = new SalesEsidInfoTdspCalendarResponse();
		String bpMatchFlag = null;
		String error = "";
		LinkedHashSet<String> serviceLocationResponseErrorList = new LinkedHashSet<>();
		SalesBaseResponse response = null;
		try {
			serviceLoationResponse = oeBO.getEnrollmentData(salesEsidCalendarRequest.getTrackingId(),
					salesEsidCalendarRequest.getGuid());
			if (null != serviceLoationResponse) {
				if ((StringUtils.equalsIgnoreCase(serviceLoationResponse.getErrorCode(), BPSD))
						&& (StringUtils.equalsIgnoreCase(salesEsidCalendarRequest.getPastServiceMatchedFlag(), "Y"))) {
					if (StringUtils.isNotBlank(serviceLoationResponse.getErrorCdlist())) {
						String[] errorCdArray = serviceLoationResponse.getErrorCdlist().split("\\|");

						serviceLocationResponseErrorList = new LinkedHashSet<>(Arrays.asList(errorCdArray));
						serviceLocationResponseErrorList.remove(BPSD);
					}

					boolean errorCode = oeBO.updateErrorCodeinSLA(salesEsidCalendarRequest.getTrackingId(),
							salesEsidCalendarRequest.getGuid(), error,
							StringUtils.join(serviceLocationResponseErrorList, SYMBOL_PIPE));

				} else if ((StringUtils.equalsIgnoreCase(serviceLoationResponse.getErrorCode(), BPSD))
						&& (!StringUtils.equalsIgnoreCase(salesEsidCalendarRequest.getPastServiceMatchedFlag(), "Y"))) {
					bpMatchFlag = BPSD;
				}
				EsidInfoTdspCalendarResponse esidInfoTdspResponse = oeBO.getESIDAndCalendarDates(
						salesEsidCalendarRequest.getCompanyCode(), salesEsidCalendarRequest.getAffiliateId(),
						salesEsidCalendarRequest.getBrandId(), serviceLoationResponse.getServStreetNum(),
						serviceLoationResponse.getServStreetName(), serviceLoationResponse.getServStreetAptNum(),
						serviceLoationResponse.getServZipCode(), serviceLoationResponse.getTdspCode(),
						serviceLoationResponse.getServiceRequestTypeCode(), salesEsidCalendarRequest.getTrackingId(),
						bpMatchFlag, salesEsidCalendarRequest.getLanguageCode(), serviceLoationResponse.getEsid(),
						httpRequest.getSession(true).getId(), serviceLoationResponse.getErrorCode());

				BeanUtils.copyProperties(esidInfoTdspResponse, salesEsidInfoTdspCalendarResponse);
				response = salesEsidInfoTdspCalendarResponse;
			} else {
				response = salesEsidInfoTdspCalendarResponse.populateInvalidTrackingAndGuidResponse();
			}
		} catch (Exception e) {
			logger.error("Exception in SalesBO.performPosidAndBpMatch" + e.getMessage());
			throw e;
		}

		return response;
	}

	public SalesTokenResponse getTokenResponse(SalesTokenRequest request) {

		logger.debug("getTokenResponse :::::::: Start");

		SalesTokenResponse tokenizedResponse = new SalesTokenResponse();
		String returnToken = null;

		if (StringUtils.isBlank(request.getActionCode())
				|| (!request.getActionCode().equalsIgnoreCase(Token.getCreditCardAction())
						&& !request.getActionCode().equalsIgnoreCase(Token.getBankAccountAction())
						&& !request.getActionCode().equalsIgnoreCase(Token.getDriverLicenceAction())
						&& !request.getActionCode().equalsIgnoreCase(Token.getSsnAction()))) {
			tokenizedResponse.setReturnToken(returnToken);
			tokenizedResponse.setStatusCode(STATUS_CODE_STOP);
			tokenizedResponse.setErrorCode(HTTP_BAD_REQUEST);
			tokenizedResponse.setErrorDescription(ACTION_CODE_INVALID);
			tokenizedResponse.setHttpStatus(Response.Status.BAD_REQUEST);
			logger.debug("getTokenResponse :::::::: Ends with invalid action code");
			return tokenizedResponse;
		}

		if (request.getActionCode().equalsIgnoreCase(Token.getCreditCardAction())) {
			returnToken = Token.getToken(request.getNumToBeTokenized());
			tokenizedResponse.setReturnToken(returnToken);
		} else if (request.getActionCode().equalsIgnoreCase(Token.getBankAccountAction())) {
			returnToken = Token.getBankAccountToken(request.getNumToBeTokenized());
			tokenizedResponse.setReturnToken(returnToken);
		} else if (request.getActionCode().equalsIgnoreCase(Token.getDriverLicenceAction())) {
			returnToken = Token.getDRLToken(request.getNumToBeTokenized());
			tokenizedResponse.setReturnToken(returnToken);
		} else if (request.getActionCode().equalsIgnoreCase(Token.getSsnAction())) {
			returnToken = Token.getSSNToken(request.getNumToBeTokenized());
			tokenizedResponse.setReturnToken(returnToken);
		}

		logger.debug("getTokenResponse :::::::: End");
		return tokenizedResponse;
	}

	public SalesOfferResponse getSalesOffers(SalesOfferRequest request, String sessionId) {
		AffiliateOfferRequest affiliateOfferRequest = new AffiliateOfferRequest();
		SalesOfferResponse salesOfferResponse = new SalesOfferResponse();
		try {
			BeanUtils.copyProperties(request, affiliateOfferRequest);
			AffiliateOfferResponse affiliateOfferResponse = oeBO.getAffiliateOffers(affiliateOfferRequest, sessionId);
			BeanUtils.copyProperties(affiliateOfferResponse, salesOfferResponse);
		} catch (Exception e) {
			logger.error("Exception in SalesBO.getSalesOffers()", e);
			throw e;
		}
		return salesOfferResponse;
	}

	public SalesBaseResponse getProspectData(ProspectDataRequest request) {
		ProspectDataResponse prospectDataExternalResponse = null;
		try {
			ProspectDataInternalResponse prospectDataInternalResponse = oeBO.getProspectData(request);
			if (StringUtils.equals(CHANNEL_AA, request.getChannelType())
					|| StringUtils.equals(CHANNEL_AFF, request.getChannelType())) {
				prospectDataExternalResponse = new ProspectDataResponse();
				BeanUtils.copyProperties(prospectDataInternalResponse, prospectDataExternalResponse);
				return prospectDataExternalResponse;
			} else {
				return prospectDataInternalResponse;
			}
		} catch (Exception e) {
			logger.error("Exception in SalesBO.getSalesOffers()", e);
			throw e;
		}
	}

	/**
	 * @author Asingh
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public SalesEnrollmentResponse getSalesSubmitEnrollment(SalesEnrollmentRequest enrollmentRequest) throws Exception {
		ServiceLocationResponse serviceLoationResponse = null;
		SalesEnrollmentResponse salesEnrollmentresponse = new SalesEnrollmentResponse();
		try {
			serviceLoationResponse = oeBO.getEnrollmentData(enrollmentRequest.getTrackingId(),
					enrollmentRequest.getGuid());
			if (null != serviceLoationResponse) {
				EnrollmentRequest request = new EnrollmentRequest();
				request.setChannelType(enrollmentRequest.getChannelType());
				request.setAffiliateId(enrollmentRequest.getAffiliateId());
				request.setCompanyCode(enrollmentRequest.getCompanyCode());
				request.setBrandId(enrollmentRequest.getBrandId());
				request.setEbillFlag(enrollmentRequest.getEbillFlag());
				request.setLanguageCode(enrollmentRequest.getLanguageCode());
				request.setTrackingId(enrollmentRequest.getTrackingId());

				if (StringUtils.isNotBlank(serviceLoationResponse.getErrorCdlist())) {
					String[] errorCdArray = serviceLoationResponse.getErrorCdlist().split("\\|");
					for (String holdtype : errorCdArray) {
						if (holdtype.equalsIgnoreCase(BPSD) || holdtype.equalsIgnoreCase(BP_RESTRICT)) {
							request.setBpMatchFlag(holdtype);
						}

						if (holdtype.equalsIgnoreCase(SWITCHHOLD)) {
							request.setSwitchHoldFlag(ON);
						}
					}
				}
				request.setMviDate(serviceLoationResponse.getServiceStartDate());
				request.setTransactionType(serviceLoationResponse.getServiceRequestTypeCode());
				request.setTdspCodeCCS(serviceLoationResponse.getTdspCode());
				// request.setSwitchHoldFlag(serviceLoationResponse.getSwitchHoldStatus());//
				request.setOfferCode(enrollmentRequest.getOfferCode());
				request.setPromoCode(enrollmentRequest.getPromoCode());
				request.setCampaignCode(enrollmentRequest.getCampaignCode());
				request.setProductPriceCode(enrollmentRequest.getProductPriceCode());
				request.setIncentiveCode(enrollmentRequest.getIncentiveCode());
				request.setMarketSegment(enrollmentRequest.getMarketSegment());
				request.setSapOfferTagline(serviceLoationResponse.getOfferCodeTitle());
				// Billing address
				request.setBillStreetName(serviceLoationResponse.getBillStreetName());
				request.setBillStreetNum(serviceLoationResponse.getBillStreetNum());
				request.setBillStreetAptNum(serviceLoationResponse.getBillStreetAptNum());
				request.setBillCity(serviceLoationResponse.getBillCity());
				request.setBillState(serviceLoationResponse.getBillState());
				request.setBillZipCode(serviceLoationResponse.getBillZipCode());
				request.setBillPOBox(serviceLoationResponse.getBillPoBox());

				// Service address
				request.setServStreetName(serviceLoationResponse.getServStreetName());
				request.setServStreetNum(serviceLoationResponse.getServStreetNum());
				request.setServStreetAptNum(serviceLoationResponse.getServStreetAptNum());
				request.setServCity(serviceLoationResponse.getServCity());
				request.setServState(serviceLoationResponse.getServState());
				request.setServZipCode(serviceLoationResponse.getServZipCode());

				request.setReferralId(serviceLoationResponse.getReferralId());
				request.setEsid(serviceLoationResponse.getEsid());

				EnrollmentResponse enrollmentResponse = oeBO.submitEnrollment(request,serviceLoationResponse);
				BeanUtils.copyProperties(enrollmentResponse, salesEnrollmentresponse);
			} else {
				salesEnrollmentresponse.populateInvalidTrackingAndGuidResponse();
			}

		} catch (Exception e) {
			logger.error("Exception in SalesBO.performPosidAndBpMatch" + e.getMessage());
			throw e;
		}

		return salesEnrollmentresponse;
	}

}
