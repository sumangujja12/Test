package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.Logger;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.multibrand.bo.AutoPayBO;
import com.multibrand.helper.ErrorContentHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.vo.request.autopay.AutoPayRequest;
import com.multibrand.vo.response.AutoPayBankResponse;
import com.multibrand.vo.response.AutoPayCCResponse;
import com.multibrand.vo.response.DeEnrollResponse;
import com.multibrand.vo.response.ValidateBankResponse;
import com.multibrand.vo.response.ValidateCCResponse;


@Component
@Path("autoPay")
public class AutoPayResource {
	
	private Logger logger =LogManager.getLogger("NRGREST_LOGGER");
	
	@Context 
	private HttpServletRequest httpRequest;

	@Autowired
	ErrorContentHelper errorContentHelper;
	
	@Autowired
	private AutoPayBO autoPayBO;
	
	/**
	 * This service is to validate Bank Account Details  from Redbull Service Payment Domain
	 * 
	 * @author ahanda1
	 * @param accountNumber
	 * @param bankAccountNumber
	 * @param bankRountingNumber
	 * @param companyCode
	 * @return
	 */
	@POST
	@Path("validateBankDetails")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	public Response validateBankDetails(@FormParam("accountNumber") String accountNumber, @FormParam("bankAccountNumber") String bankAccountNumber, @FormParam("bankRoutingNumber") String bankRountingNumber, @FormParam("companyCode") String companyCode,@FormParam("brandName") String brandName){
		logger.debug("Start AutoPayResource.validateBankDetails :: START");
		Response response = null;
		ValidateBankResponse validateBankResp = autoPayBO.validateBankDetails(accountNumber, bankAccountNumber, bankRountingNumber, companyCode, httpRequest.getSession(true).getId(),brandName);
		response = Response.status(200).entity(validateBankResp).build();
		logger.info(" START ******* Input for the validateBankDetails API**********");
		logger.info(" accountNumber - {}",accountNumber);
		logger.info("  companyCode  - {}",companyCode);
		logger.info("  brandName  - {}",brandName);
		logger.info("  bankRountingNumber  - {}",bankRountingNumber);
		logger.info(" OUTPUT of the validateBankDetails API*************");
		String json = CommonUtil.wirteObjectToJson(response);
		logger.info(" Response  - {}",json);
		logger.info("END of the validateBankDetails API*************");
		logger.debug("End AutoPayResource.validateBankDetails :: END");
		return response;
	}
	
	/**
	 * @author ahanda1
	 * @param accountNumber
	 * @param bankAccountNumber
	 * @param bankRountingNumber
	 * @param companyCode
	 * @param accountName
	 * @param accountChkDigit
	 * @param locale
	 * @param email
	 * @return
	 */
	@POST
	@Path("submitBankAutoPay")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	public Response submitBankAutoPay(
			@FormParam("accountNumber") String accountNumber, 
			@FormParam("bankAccountNumber") String bankAccountNumber, 
			@FormParam("bankRoutingNumber") String bankRountingNumber, 
			@FormParam("companyCode") String companyCode, 
			@FormParam("accountName") String accountName, 
			@FormParam("accountChkDigit") String accountChkDigit, 
			@FormParam("languageCode")String locale, 
			@FormParam("email") String email,
			@FormParam("emailTypeId")String emailTypeId ,
			@FormParam("brandName") String brandName, 
			@FormParam("bpNumber") String bpNumber, 
			@FormParam("source") String source,@Context HttpHeaders hh) {
		
		logger.info("Start AutoPayResource.submitBankAutoPay :: START");
		
		Response response = null;
		AutoPayBankResponse autoPayBankRep = null;
		String host = "";

		AutoPayRequest autoPayRequest = new AutoPayRequest();
		

		autoPayRequest.setAccountNumber(accountNumber);
		autoPayRequest.setBankAccountNumber(bankAccountNumber);
		autoPayRequest.setBankRoutingNumber(bankRountingNumber);
		autoPayRequest.setCompanyCode(companyCode);
		autoPayRequest.setAccountName(accountName);
		autoPayRequest.setAccountChkDigit(accountChkDigit);
		autoPayRequest.setLanguageCode(locale);
		autoPayRequest.setEmail(email);
		autoPayRequest.setEmailTypeId(emailTypeId);
		autoPayRequest.setBrandName(brandName);
		autoPayRequest.setBpid(bpNumber);
		autoPayRequest.setSource(source);
		
		MultivaluedMap<String, String> requestHeadersMap = hh.getRequestHeaders();
		
		if (requestHeadersMap!=null&&requestHeadersMap.containsKey("Host")){
			host = requestHeadersMap.getFirst("Host");
		}
		
		if( StringUtils.isNotEmpty(host) && host.indexOf("service.nrg.com") > -1)	 {
			autoPayBankRep = autoPayBO.submitMobileAppBankAutoPay(autoPayRequest, httpRequest.getSession(true).getId(), hh);
		} else {
			autoPayBankRep = autoPayBO.submitBankAutoPay(autoPayRequest, httpRequest.getSession(true).getId());
		}
		
		
		
		response = Response.status(200).entity(autoPayBankRep).build();
		logger.debug("End AutoPayResource.submitBankAutoPay :: END");
		
		return response;
	}
	
	
	
	/**
	 * @author ahanda1
	 * @param accountNumber
	 * @param bpid
	 * @param ccNumber
	 * @param cvvNumber
	 * @param expirationDate in MM/yy format
	 * @param billingZip
	 * @return
	 */
	
	@POST
	@Path("validateCCDetails")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	public Response validateCCDetails(
			@FormParam("authType") String authType, 
			@FormParam("accountNumber") String accountNumber, 
			@FormParam("bpid") String bpid, 
			@FormParam("ccNumber") String ccNumber, 
			@FormParam("cvvNumber") String cvvNumber,
			@FormParam("expirationDate") String expirationDate, 
			@FormParam("billingZip") String billingZip,
			@FormParam("companyCode") String companyCode, 
			@FormParam("brandName,") String brandName){
		logger.debug("Start AutoPayResource.validateCCDetails :: START");
		Response response = null;
		
		AutoPayRequest autoPayRequest = new AutoPayRequest();
		
		autoPayRequest.setAuthType(authType);
		autoPayRequest.setAccountNumber(accountNumber);
		autoPayRequest.setBpid(bpid);
		autoPayRequest.setCcNumber(ccNumber);
		autoPayRequest.setCvvNumber(cvvNumber);
		autoPayRequest.setExpirationDate(expirationDate);
		autoPayRequest.setBillingZip(billingZip);
		autoPayRequest.setCompanyCode(companyCode);
		autoPayRequest.setBrandName(brandName);

		
		ValidateCCResponse validateCCResp = autoPayBO.validateCCDetails(autoPayRequest, httpRequest.getSession(true).getId());
		
		response = Response.status(200).entity(validateCCResp).build();
		logger.debug("End AutoPayResource.validateCCDetails :: END");
		return response;
	}

	
	
	/**
	 * @author ahanda1
	 * @param authType
	 * @param accountName
	 * @param accountNumber
	 * @param bpid
	 * @param ccNumber
	 * @param expirationDate
	 * @param billingZip
	 * @param companyCode
	 * @param email
	 * @return
	 */
	
	@POST
	@Path("submitCCAutoPay")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	public Response submitCCAutoPay(
			@FormParam("authType") String authType, 
			@FormParam("accountName")String accountName, 
			@FormParam("accountNumber") String accountNumber, 
			@FormParam("bpid") String bpid, 
			@FormParam("ccNumber") String ccNumber, 
			@FormParam("expirationDate") String expirationDate, 
			@FormParam("billingZip") String billingZip, 
			@FormParam("companyCode") String companyCode, 
			@FormParam("email") String email, 
			@FormParam("languageCode")String languageCode,
			@FormParam("emailTypeId")String emailTypeId, 
			@FormParam("brandName")String brandName, 
			@FormParam("source")String source) {
		logger.debug("Start AutoPayResource.submitCCAutoPay :: START");
		Response response = null;
		
		AutoPayRequest autoPayRequest = new AutoPayRequest();
		
		autoPayRequest.setAuthType(authType);
		autoPayRequest.setAccountName(accountName);
		autoPayRequest.setAccountNumber(accountNumber);
		autoPayRequest.setBpid(bpid);
		autoPayRequest.setCcNumber(ccNumber);
		autoPayRequest.setExpirationDate(expirationDate);
		autoPayRequest.setBillingZip(billingZip);
		autoPayRequest.setCompanyCode(companyCode);
		autoPayRequest.setEmail(email);
		autoPayRequest.setLanguageCode(languageCode);
		autoPayRequest.setEmailTypeId(emailTypeId);
		autoPayRequest.setBrandName(brandName);
		autoPayRequest.setSource(source);
		
		AutoPayCCResponse autoPayCCResp = autoPayBO.submitCCAutoPay(autoPayRequest, httpRequest.getSession(true).getId());
		
		
		response = Response.status(200).entity(autoPayCCResp).build();
		logger.debug("End AutoPayResource.submitCCAutoPay :: END");
		return response;
	}

	/**
	 * @author kdeshmu1
	 * @param accountNumber
	 * @param companyCode
	 * @param email
	 * @return
	 */
	@POST
	@Path("deEnroll")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	public Response deEnroll(
			@FormParam("accountNumber") String accountNumber,
			@FormParam("companyCode") String companyCode, 
			@FormParam("email") String email, 
			@FormParam("languageCode")String languageCode,
			@FormParam("brandName") String brandName,
			@FormParam("bpNumber") String bpNumber,
			@FormParam("source") String source) {
		
		logger.debug("Start AutoPayResource.deEnroll :: START");
		Response response = null;
		
		AutoPayRequest autoPayRequest = new AutoPayRequest();
		
		autoPayRequest.setAccountNumber(accountNumber);
		autoPayRequest.setCompanyCode(companyCode);
		autoPayRequest.setEmail(email);
		autoPayRequest.setLanguageCode(languageCode);
		autoPayRequest.setBrandName(brandName);
		autoPayRequest.setBpNumber(bpNumber);
		autoPayRequest.setSource(source);
		
		
		DeEnrollResponse deEnrollResponse = autoPayBO.deEnroll(autoPayRequest, httpRequest.getSession(true).getId());
		
		
		response = Response.status(200).entity(deEnrollResponse).build();
		logger.debug("End AutoPayResource.deEnroll :: END");
		return response;
	}
	
	

}
