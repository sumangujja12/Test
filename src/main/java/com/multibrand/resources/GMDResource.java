package com.multibrand.resources;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import com.multibrand.bo.GMDBO;
import com.multibrand.bo.OEBO;
import com.multibrand.dto.request.EsidRequest;
import com.multibrand.dto.request.GMDEnrollmentRequest;
import com.multibrand.dto.request.GMDEsidCalendarRequest;
import com.multibrand.dto.request.GmdMdStmtRequest;
import com.multibrand.dto.request.MoveOutRequest;
import com.multibrand.dto.response.EsidResponse;
import com.multibrand.dto.response.GMDEnrollmentResponse;
import com.multibrand.exception.OAMException;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.ESIDForAddressResponse;
import com.multibrand.vo.response.EsidInfoTdspCalendarResponse;
import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.gmd.GMDOfferResponse;
import com.multibrand.vo.response.gmd.GMDPricingResponse;
import com.multibrand.vo.response.gmd.GMDStatementBreakDownResponse;
import com.multibrand.vo.response.gmd.GmdHourHeadsSpikeResponse;
import com.multibrand.vo.response.gmd.GmdMdStmtResponse;
import com.multibrand.vo.response.gmd.LmpPriceSpikeResponse;
import com.multibrand.vo.response.gmd.MoveOutResponse;
import com.multibrand.vo.response.gmd.PriceSpikeAlertResponse;


/** This Resource is to handle all the GMD APP Related API calls.
 * 
 * @author rpendur1
 */
/**
 * @author RPendur1
 *
 */
@Component
@Path("gmdResource")
public class GMDResource extends BaseResource {
	
	private static Logger logger = LogManager.getLogger(GMDResource.class);
	
	@Context 
	private HttpServletRequest httpRequest;
	
	/** Object of BillingBO class. */
	@Autowired
	private GMDBO gmdBO;
	
	/** Object of oeBO class. */
	@Autowired
	private OEBO oeBO;
	
	@Autowired
	@Qualifier("appConstMessageSource")
	protected ReloadableResourceBundleMessageSource appConstMessageSource;
	
	/**
	 * This service is to provide GMD Statement details from CCS Service
	 * @param accountNumber
	 * @param companyCode
	 * @param brandName
	 * @param esiId
	 * @param year
	 * @param month
	 * @return
	 */
	@POST
	@Path(API_GET_GMD_STATEMENT_DATA)
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	
	public Response getGMDStatementDetails(@FormParam("contractAccountNumber") String accountNumber,@FormParam("companyCode") String companyCode, @FormParam("brandName") String brandName,
			@FormParam("esiId") String esiId, @FormParam("year") String year, @FormParam("month") String month, @FormParam("isAllInPriceCall") boolean isAllInPriceCall) {
		
		logger.info(" START ******* getGMDStatementDetails API**********");
		Response response = null;
		GMDStatementBreakDownResponse gmdStatementBreakDownResp = gmdBO.getGMDStatementDetails(accountNumber, companyCode, esiId, year, month, isAllInPriceCall, httpRequest.getSession(true).getId());
		
		response = Response.status(200).entity(gmdStatementBreakDownResp).build();
		
		logger.info("END of the getGMDStatementDetails API*************");
		return response;
		
	}
	
	
	@POST
	@Path(API_GET_GMD_PRICE_DATA)
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	
	public Response getGMDPriceDetails(@FormParam("contractAccountNumber") String accountNumber,@FormParam("contractId") String contractId, 
			@FormParam("companyCode") String companyCode, @FormParam("brandName") String brandName,
			@FormParam("esiId") String esiId) {
		
		logger.info(" START ******* getGMDPriceDetails API**********");
		
		Response response = null;
		GMDPricingResponse gmdPricingResp = gmdBO.getGMDPriceDetails(accountNumber, contractId, companyCode, esiId, httpRequest.getSession(true).getId());
		
		response = Response.status(200).entity(gmdPricingResp).build();
		
		logger.info("END of the getGMDPriceDetails API*************");
		return response;
		
	}	

	 
	@POST
	@Path("/getESIDAndCalendarDates")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getESIDAndCalendarDates(GMDEsidCalendarRequest esidCalendarRequest) {
		Response response = null;
		EsidInfoTdspCalendarResponse esidInfoTdspResponse = null;
		if (StringUtils.isBlank(esidCalendarRequest.getLanguageCode()))
			esidCalendarRequest.setLanguageCode(Constants.LOCALE_LANGUAGE_CODE_E);
		esidInfoTdspResponse = gmdBO.getESIDAndCalendarDates(esidCalendarRequest.getCompanyCode(),
				esidCalendarRequest.getBrandName(), esidCalendarRequest.getTdspCode(),
				esidCalendarRequest.getTransactionType(),
				esidCalendarRequest.getEsiId());

		response = Response.status(Response.Status.OK).entity(esidInfoTdspResponse).build();

		return response;

	}
	

	@POST
	@Path("/submitEnrollment")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response submitEnrollment(GMDEnrollmentRequest enrollmentRequest) {
		Response response = null;
		// Start Submit enrollment call
		GMDEnrollmentResponse enrollmentResponse = gmdBO.submitEnrollment(enrollmentRequest, httpRequest.getSession(true).getId());
		response = Response.status(Response.Status.OK).entity(enrollmentResponse).build();
		return response;
	}
	
	@GET
	@Path("/gmdOfferDocs/{tdspCode}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getGMDOfferDocs(@PathParam("tdspCode") String tdspCode) {
		Response response = null;

		GMDOfferResponse gmdOfferResponse = gmdBO.getGMDOfferDocs(tdspCode);
		
		response = Response.status(Response.Status.OK).entity(gmdOfferResponse).build();
		return response;
	}
	
	@POST
	@Path("/ESIDForAddress")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response submitEnrollment(@FormParam("apartmentNumber")String apartmentNumber, 
			@FormParam("city")String city, 
			@FormParam("country")String country,
			@FormParam("state")String state,
			@FormParam("streetName")String streetName,
			@FormParam("streetNumber")String streetNumber,
			@FormParam("zip")String zip, 
			@FormParam("companyCode") String companyCode) {
		
		Response response = null;
		ESIDForAddressResponse esidForAddressResponse = new ESIDForAddressResponse();
		
		try{
			EsidRequest request = new EsidRequest();
			
			request.setServStreet(streetNumber + " " + streetName);
			request.setServStreetAptNum(apartmentNumber);
			request.setServCity(city);
			request.setServZipCode(zip);
			
			EsidResponse getEsiidResponse = oeBO.getESIDDetails(request);
			
			if ( getEsiidResponse.getEsidList() != null && !getEsiidResponse.getEsidList().isEmpty() &&  getEsiidResponse.getEsidList().size() == 1) {
				esidForAddressResponse.setPointofDeliveryID(getEsiidResponse.getEsidList().get(0).getEsidNumber());
				esidForAddressResponse.setServiceId(getEsiidResponse.getEsidList().get(0).getEsidTDSP());
				esidForAddressResponse.setCustomerClass(getEsiidResponse.getEsidList().get(0).getEsidClass());
				
			} else {
				esidForAddressResponse.setPointofDeliveryID("<ESIDNOTFOUND>");
				esidForAddressResponse.setResultCode("1");
				esidForAddressResponse.setResultDescription("MSG_ERR_ESI_LOOKUP");
				esidForAddressResponse.setResultDisplayText("Sorry! Something went wrong. Please try again");
			}
			esidForAddressResponse.setCompanyCode(companyCode);
			
			response = Response.status(Response.Status.OK).entity(esidForAddressResponse).build();
			
			
			
		} catch (Exception e) {
			logger.error(e);
			esidForAddressResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			esidForAddressResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), esidForAddressResponse);			
		} 	
		
		
		
		return response;
	}
		
	@POST
	@Path(API_CREATE_GMD_MOVE_OUT)
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createMoveOut(MoveOutRequest moveOutRequest) {
		MoveOutResponse moveOutResponse = new MoveOutResponse();
		moveOutResponse = gmdBO.createMoveOut(moveOutRequest);
		Response response = Response.status(Response.Status.OK).entity(moveOutResponse).build();
		return response;
	}
	
	@POST
	@Path(API_GET_GMD_PRICE_SPIKE_ALERT_DATA)
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getGmdPriceSpikeAlertData() {
		PriceSpikeAlertResponse priceSpikeAlertResponse = new PriceSpikeAlertResponse();
		priceSpikeAlertResponse = gmdBO.getGMDPriceSpikeAlert();
		Response response = Response.status(Response.Status.OK).entity(priceSpikeAlertResponse).build();
		return response;
	}	
	
	@POST
	@Path(API_GET_GMD_MD_STMT_DATA)
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getGmdMdStmtData(GmdMdStmtRequest request) {
		GmdMdStmtResponse gmdMdStmtResponse = new GmdMdStmtResponse();
		gmdMdStmtResponse = gmdBO.getGmdMdStmtData(request);
		Response response = Response.status(Response.Status.OK).entity(gmdMdStmtResponse).build();
		return response;
	}	
	
	
	@POST
	@Path(API_GET_GMD_LMP_PRICE_SPIKE)
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getGmdLmpPriceSpike(@FormParam("buckers") String buckers) {
		LmpPriceSpikeResponse lmpPriceSpikeResponse = new LmpPriceSpikeResponse();
		lmpPriceSpikeResponse = gmdBO.getGmdLmpPriceSpike(buckers);
		Response response = Response.status(Response.Status.OK).entity(lmpPriceSpikeResponse).build();
		return response;
	}	
	
	@POST
	@Path(API_GET_GMD_HOURHEAD_SPIKE)
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getGmdLmpPriceSpike(@FormParam("imThreshold") BigDecimal imThreshold) {
		GmdHourHeadsSpikeResponse gmdHourHeadsSpikeResponse = new GmdHourHeadsSpikeResponse();
		gmdHourHeadsSpikeResponse = gmdBO.getGmdHourHeadSpikeData(imThreshold);
		Response response = Response.status(Response.Status.OK).entity(gmdHourHeadsSpikeResponse).build();
		return response;
	}		
}	
