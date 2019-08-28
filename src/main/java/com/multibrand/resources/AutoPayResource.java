package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.bo.AutoPayBO;
import com.multibrand.helper.ErrorContentHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.vo.response.AutoPayBankResponse;
import com.multibrand.vo.response.AutoPayCCResponse;
import com.multibrand.vo.response.DeEnrollResponse;
import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.ValidateBankResponse;
import com.multibrand.vo.response.ValidateCCResponse;


@RestController
public class AutoPayResource {
	
	private Logger logger =LogManager.getLogger("NRGREST_LOGGER");
	
	@Autowired 
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
	@PostMapping(value="/autoPay/validateBankDetails", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> validateBankDetails(@RequestParam("accountNumber") String accountNumber, @RequestParam("bankAccountNumber") String bankAccountNumber, @RequestParam("bankRoutingNumber") String bankRountingNumber, @RequestParam("companyCode") String companyCode,@RequestParam("brandName") String brandName){
		logger.debug("Start AutoPayResource.validateBankDetails :: START");
		//Response response = null;
		ValidateBankResponse validateBankResp = autoPayBO.validateBankDetails(accountNumber, bankAccountNumber, bankRountingNumber, companyCode, httpRequest.getSession(true).getId(),brandName);
		//response = Response.status(200).entity(validateBankResp).build();
		logger.info(" START ******* Input for the validateBankDetails API**********");
		logger.info(" accountNumber - "+accountNumber);
		logger.info("  companyCode  - "+companyCode);
		logger.info("  brandName  - "+brandName);
		logger.info("  bankAccountNumber  - "+CommonUtil.maskBankAccountNo(bankAccountNumber));
		logger.info("  bankRountingNumber  - "+bankRountingNumber);
		logger.info(" OUTPUT of the validateBankDetails API*************");
		//String json = CommonUtil.wirteObjectToJson(response);
		//logger.info(" Response  - "+json);
		//System.out.println(" Response  json- "+json);
		logger.info("END of the validateBankDetails API*************");
		logger.debug("End AutoPayResource.validateBankDetails :: END");
		return new ResponseEntity<GenericResponse>(validateBankResp, HttpStatus.OK);
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
	@PostMapping(value="/autoPay/submitBankAutoPay", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> submitBankAutoPay(@RequestParam("accountNumber") String accountNumber, @RequestParam("bankAccountNumber") String bankAccountNumber, @RequestParam("bankRoutingNumber") String bankRountingNumber, @RequestParam("companyCode") String companyCode, @RequestParam("accountName") String accountName, @RequestParam("accountChkDigit") String accountChkDigit, @RequestParam("languageCode")String locale, @RequestParam("email") String email,@RequestParam("emailTypeId")String emailTypeId ,@RequestParam("brandName") String brandName, @RequestParam("bpNumber") String bpNumber, @RequestParam("source") String source){
		logger.debug("Start AutoPayResource.submitBankAutoPay :: START");
		//Response response = null;
		AutoPayBankResponse autoPayBankRep = autoPayBO.submitBankAutoPay(accountNumber, bankAccountNumber, bankRountingNumber, companyCode, accountName, accountChkDigit, locale, email, httpRequest.getSession(true).getId(),emailTypeId,brandName,bpNumber,source);
		
		
		//response = Response.status(200).entity(autoPayBankRep).build();
		logger.debug("End AutoPayResource.submitBankAutoPay :: END");
		
		return new ResponseEntity<GenericResponse>(autoPayBankRep, HttpStatus.OK);
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
	@PostMapping(value="/autoPay/validateCCDetails", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> validateCCDetails(@RequestParam("authType") String authType, @RequestParam("accountNumber") String accountNumber, @RequestParam("bpid") String bpid, @RequestParam("ccNumber") String ccNumber, @RequestParam("cvvNumber") String cvvNumber, @RequestParam("expirationDate") String expirationDate, @RequestParam("billingZip") String billingZip, @RequestParam("companyCode") String companyCode, @RequestParam("brandName,") String brandName){
		logger.debug("Start AutoPayResource.validateCCDetails :: START");
		//Response response = null;
		
		ValidateCCResponse validateCCResp = autoPayBO.validateCCDetails(authType, accountNumber, bpid, ccNumber, cvvNumber, expirationDate, billingZip, companyCode, httpRequest.getSession(true).getId(),brandName);
		
		//response = Response.status(200).entity(validateCCResp).build();
		logger.debug("End AutoPayResource.validateCCDetails :: END");
		return new ResponseEntity<GenericResponse>(validateCCResp, HttpStatus.OK);
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
	@PostMapping(value="/autoPay/submitCCAutoPay", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> submitCCAutoPay(@RequestParam("authType") String authType, @RequestParam("accountName")String accountName, @RequestParam("accountNumber") String accountNumber, @RequestParam("bpid") String bpid, @RequestParam("ccNumber") String ccNumber, @RequestParam("expirationDate") String expirationDate, @RequestParam("billingZip") String billingZip, @RequestParam("companyCode") String companyCode, @RequestParam("email") String email, @RequestParam("languageCode")String languageCode,@RequestParam("emailTypeId")String emailTypeId, @RequestParam("brandName")String brandName, @RequestParam("source")String source){
		logger.debug("Start AutoPayResource.submitCCAutoPay :: START");
		//Response response = null;
		
		AutoPayCCResponse autoPayCCResp = autoPayBO.submitCCAutoPay(authType,accountName, accountNumber, bpid, ccNumber, expirationDate, billingZip, companyCode, email, httpRequest.getSession(true).getId(), languageCode,emailTypeId,brandName,source);
		
		
		//response = Response.status(200).entity(autoPayCCResp).build();
		logger.debug("End AutoPayResource.submitCCAutoPay :: END");
		 return new ResponseEntity<GenericResponse>(autoPayCCResp, HttpStatus.OK);
	}

	/**
	 * @author kdeshmu1
	 * @param accountNumber
	 * @param companyCode
	 * @param email
	 * @return
	 */
	@PostMapping(value="/autoPay/deEnroll", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> deEnroll(@RequestParam("accountNumber") String accountNumber,@RequestParam("companyCode") String companyCode, @RequestParam("email") String email, @RequestParam("languageCode")String languageCode,@RequestParam("brandName") String brandName,@RequestParam("bpNumber") String bpNumber,@RequestParam("source") String source){
		
		logger.debug("Start AutoPayResource.deEnroll :: START");
		//Response response = null;
		
		DeEnrollResponse deEnrollResponse = autoPayBO.deEnroll(accountNumber, companyCode, httpRequest.getSession(true).getId(), email, languageCode,brandName,bpNumber,source);
		
		
		//response = Response.status(200).entity(deEnrollResponse).build();
		logger.debug("End AutoPayResource.deEnroll :: END");
		 return new ResponseEntity<GenericResponse>(deEnrollResponse, HttpStatus.OK); 
	}
	
	

}
