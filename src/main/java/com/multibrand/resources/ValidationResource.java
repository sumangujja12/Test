package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.bo.ValidationBO;
import com.multibrand.domain.ValidateCustReferralIdResponse;
import com.multibrand.dto.request.ValidateAddressRequest;
import com.multibrand.dto.response.ValidateAddressResponse;
import com.multibrand.exception.OEException;
import com.multibrand.vo.response.AddressValidateResponse;




/** This Resource is to handle all the Validation Related API calls.
 * 
 * @author rbansal30
 */
@RestController
public class ValidationResource extends ValidationAddressResource {
	
	/** Object of ValidationBO class. */
	@Autowired
	private ValidationBO validationBO;
	
	@Autowired
	private HttpServletRequest httpRequest;
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	/** This service is to do the trillium check on Client request.
	 * 
	 * @author rbansal30
	 * @param aptNumber		Apartment Number
	 * @param city			City
	 * @param country			Country
	 * @param state			State
	 * @param streetName		Street Name
	 * @param streetNum		Street Number
	 * @param zipCode			Zip Code
	 * @param companyCode		Company Code
	 * @return response			Provide JSON/XML balance data response
	 */
	@PostMapping(value = "/validateResource/validateBillingAddress", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Response validateBillingAddress(@FormParam("aptNumber") String aptNumber, @FormParam("city") String city,
			@FormParam("country") String country, @FormParam("state") String state, @FormParam("streetName") String streetName, @FormParam("streetNum") String streetNum, 
			@FormParam("zipCode") String zipCode, @FormParam("companyCode") String companyCode,@FormParam("poBox") String poBox, @FormParam("brandName")String brandName){
		logger.info(" START ******* validateBillingAddress API**********");
		Response response = null;
		AddressValidateResponse addressValidationResponse = validationBO.validateBillingAddress(aptNumber,city,country,state,streetName,streetNum,zipCode,companyCode,poBox,httpRequest.getSession(true).getId(),brandName);
		response = Response.status(200).entity(addressValidationResponse).build();
		logger.info(" END ******* validateBillingAddress API**********");
		return response;		
	}
	
	/**
	 * 
	 * This call validates and normalizes service or billing addresses so that
	 * they match USPS standards.
	 * 
	 * <p>
	 * The following properties are included in the request.
	 * <ul>
	 * <li><strong>affiliateId<strong>
	 * <li><strong>companyCode<strong>
	 * <li><strong>brandId<strong>
	 * <li><strong>languageCode<strong>
	 * <li><strong>streetNum<strong>
	 * <li><strong>streetName<strong>
	 * <li><strong>aptNum<strong>
	 * <li><strong>city<strong>
	 * <li><strong>state<strong>
	 * <li><strong>zipCode<strong>
	 * <li><strong>poBox<strong>
	 * <li><strong>country<strong>
	 * </ul>
	 * </p>
	 * 
	 * @param validateAddressRequest
	 *            instance of <code>ValidateAddressRequest</code>.
	 * 
	 * @return Response the address validation output (JSON format).
	 * 
	 * @throws OEException
	 *             if the address validation call contains any error or failed.
	 * 
	 * 
	 * @author Jenith (jyogapa1)
	 */
	@PostMapping(value = "/validateResource/cleanupAddress", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Response cleanupAddress(
			@Valid ValidateAddressRequest validateAddressRequest)
			throws OEException {

		Response response = null;

		// Do cleanup address input parameters extra validation errors check
		Response errors = this.validateBillingAddressParameters(
				validateAddressRequest.getStreetAddress(),
				validateAddressRequest.getPoBox());

		if (errors != null)	{return errors;}

		// Do cleanup address using Trillium
		ValidateAddressResponse validateAddressResponse = validationBO
				.validateAddress(validateAddressRequest);

		// Build cleanup address response
		response = Response.status(Response.Status.OK)
				.entity(validateAddressResponse).build();

		return response;
	}
	
	@PostMapping(value = "/validateResource/validateReferralId", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Response validateReferralId(@FormParam("referralId") String referralId, @FormParam("companyCode") String companyCode, @FormParam("brandId") String brandId){
		logger.info(" START ******* validateReferralId API**********");
		Response response = null;
		ValidateCustReferralIdResponse validateCustReferralIdResponse = validationBO.validateReferralId(referralId,companyCode,brandId);
		response = Response.status(200).entity(validateCustReferralIdResponse).build();
		logger.info(" END ******* validateReferralId API**********");
		return response;		
	}

}
