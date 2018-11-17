package com.multibrand.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.dto.request.EmailRequest;
import com.multibrand.dto.response.EmailResponse;
import com.multibrand.exception.UtilityServiceException;
import com.multibrand.util.JAXBUtil;
import com.multibrand.util.NRGRestUtil;
import com.nrgenergy.utility.webservices.AdapterTypes;
import com.nrgenergy.utility.webservices.EmailAddressTypes;
import com.nrgenergy.utility.webservices.EmailDetails;
import com.nrgenergy.utility.webservices.EmailTransaction;
import com.nrgenergy.utility.webservices.EmailTransactionRequest;
import com.nrgenergy.utility.webservices.MessageDetails;
import com.nrgenergy.utility.webservices.MessageProperties;
import com.nrgenergy.utility.webservices.MessageProperty;

/**
 * @author bbachin1
 * 
 */
@Component
public class EmailServiceImpl implements EmailService {
	
	private static Logger logger = LogManager.getLogger(EmailServiceImpl.class);
	
	@Autowired
	private UtilityService utilityService;

	@Override
	public EmailResponse sendEmail(EmailRequest emailRequest) {
		
		EmailResponse emailResult = new EmailResponse();
		try{
			emailRequest.validateRequest();
			MessageProperties msgProp = getMessageProperties(emailRequest);
			List<EmailDetails> emailDtlsList = populateEmailListFromRequest(emailRequest);
		    MessageDetails msgDtls = generateMessageDtls(emailDtlsList,msgProp, emailRequest.getSubject());
		    String companyCode = getCompanyCodeFromRequest(emailRequest);
		    AdapterTypes templateType = getTemplateTypeFromRequest(emailRequest);
		    String externalId = getExternalIdFromRequest(emailRequest);
		    boolean sendStatus = sendEmail(msgDtls,companyCode,externalId,templateType);
		    emailResult.setResultcode(sendStatus?"0":"1");
		    emailResult.setExternalid(externalId);
		    emailResult.setResultdescription(sendStatus?"Success":"Failure");
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE SENDING THE EMAIL::::::::",ex);
			emailResult.setResultdescription((ex instanceof UtilityServiceException)?"System Down":NRGRestUtil.getMessageFromException(ex));
			emailResult.setResultcode("3");
		}
	    return emailResult;
	}
	
	
	
	private String getExternalIdFromRequest(EmailRequest emailRequest){
		
		String externalId = emailRequest.getExternalId();
		if(!StringUtils.contains(externalId, "_US") && StringUtils.isNotBlank(emailRequest.getLanguageCode())){
			externalId = StringUtils.substringBefore(externalId, ".")+"."+emailRequest.getLanguageCode()+"_US";
		}
		logger.info("EXTERNAL ID FORMED:::::"+externalId);
		return externalId;
	}
	
	
	/*private String getExternalIdFromInput(EmailRequest emailRequest){
		
		String externalIdWithoutlangCode = StringUtils.substringAfterLast(emailRequest.getExternalId(), ".");
	}*/
	
	private AdapterTypes getTemplateTypeFromRequest(EmailRequest emailRequest){
		
		if(StringUtils.isNotBlank(emailRequest.getTemplateType())){
			if(StringUtils.equalsIgnoreCase(emailRequest.getTemplateType(), "XSLT")){return AdapterTypes.XSLT;}
			if(StringUtils.equalsIgnoreCase(emailRequest.getTemplateType(), "HTML")){return AdapterTypes.HTML;}
			if(StringUtils.equalsIgnoreCase(emailRequest.getTemplateType(), "BASE64HTML")){return AdapterTypes.BASE_64_HTML;}
		}
		return AdapterTypes.HTML;
	}
	
	
	private String getCompanyCodeFromRequest(EmailRequest emailRequest){
		
		if(StringUtils.isNotBlank(emailRequest.getCompanyCode())){
			return emailRequest.getCompanyCode();
		}
		return "0121";
	}
	
	
	private List<EmailDetails> populateEmailListFromRequest(EmailRequest emailRequest){
		
		List<EmailDetails> emailDtlsList = new ArrayList<EmailDetails>();
		EmailDetails emailDtls1 = null;
		if(null != emailRequest.getToEmailList()){
			for(String email : emailRequest.getToEmailList()){
				emailDtls1 =generateEmailDtls(EmailAddressTypes.TO,email);
				emailDtlsList.add(emailDtls1);
			}	
		}
		if(null != emailRequest.getBccEmailList()){
			for(String email : emailRequest.getBccEmailList()){
				emailDtls1 =generateEmailDtls(EmailAddressTypes.BCC,email);
				emailDtlsList.add(emailDtls1);
			}	
		}
		if(null != emailRequest.getCcEmailList()){
			for(String email : emailRequest.getCcEmailList()){
				emailDtls1 =generateEmailDtls(EmailAddressTypes.CC,email);
				emailDtlsList.add(emailDtls1);
			}	
		}
		if(null != emailRequest.getFromEmailList()){
			for(String email : emailRequest.getFromEmailList()){
				emailDtls1 =generateEmailDtls(EmailAddressTypes.FROM,email);
				emailDtlsList.add(emailDtls1);
			}	
		}
		if(null != emailRequest.getReplyToEmailList()){
			for(String email : emailRequest.getReplyToEmailList()){
				emailDtls1 =generateEmailDtls(EmailAddressTypes.REPLYTO,email);
				emailDtlsList.add(emailDtls1);
			}	
		}
		if(null != emailRequest.getBounceAddEmailList()){
			for(String email : emailRequest.getBounceAddEmailList()){
				emailDtls1 =generateEmailDtls(EmailAddressTypes.BOUNCEADD,email);
				emailDtlsList.add(emailDtls1);
			}	
		}
		return emailDtlsList;
	}
	
	
	private MessageProperties getMessageProperties(EmailRequest emailRequest){
		
		List<MessageProperty> msgPropList = new ArrayList<MessageProperty>();
		if(null != emailRequest.getPropertyList()){
			for(String str : emailRequest.getPropertyList()){
				 msgPropList.add(setTemplateVar(StringUtils.substringBefore(str, ":"),StringUtils.substringAfter(str, ":")));
			}
		}
		MessageProperties msgProp = generateTemplateVariables(msgPropList);
		return msgProp;
	}
	
	
	
	/**
	 *
	   This method set the variables in the template
	 *
	 */
	private MessageProperty setTemplateVar(String key, String value){
		MessageProperty property = new MessageProperty();
		property.setPropertyName(key);
		property.setPropertyValue(appendCDATA(value));
		return property;
	}

	
	private MessageProperties generateTemplateVariables(List<MessageProperty> messageProperty){
		MessageProperties msgProp = new MessageProperties();
		for(MessageProperty prop : messageProperty){
			msgProp.getMessageProperty().add(prop);
		}
		return msgProp;
	}
	

	private EmailDetails generateEmailDtls(EmailAddressTypes emailAddressType, String emailAddress){
		EmailDetails emailDtls = new EmailDetails();
		emailDtls.setEmailAddressType(emailAddressType);
		emailDtls.setEmailAddress(emailAddress);
		return emailDtls;
	}

	/**
	 *
	 * This method set the request and convert the request to xmlrequest
	 * send the email and return the response as true or false
	 */
	private MessageDetails generateMessageDtls(EmailDetails emailDtls, MessageProperties msgProp, String subject){

		MessageDetails msgDtls = new MessageDetails();
		msgDtls.getEmailDetails().add(emailDtls);
		msgDtls.setMessageProperties(msgProp);
		msgDtls.setSubject(subject);
		return msgDtls;
	}

	
	private MessageDetails generateMessageDtls(List<EmailDetails> emailDtls, MessageProperties msgProp, String subject){

		MessageDetails msgDtls = new MessageDetails();
		for(EmailDetails email : emailDtls){
			msgDtls.getEmailDetails().add(email);
		}
		msgDtls.setMessageProperties(msgProp);
		msgDtls.setSubject(subject);
		return msgDtls;
	}

	private String appendCDATA(String templateVar){
		return "<![CDATA["+templateVar+"]]>";
	}

	private Boolean sendEmail(MessageDetails msgDtls,String companyCode, String externalId, AdapterTypes adaptorType) throws UtilityServiceException{
		
		try{
			EmailTransaction transaction = populateEmailTransaction(msgDtls,companyCode,externalId,adaptorType);
			String strRequest = JAXBUtil.marshal(transaction);
			String strResponse = utilityService.sendReliantEmail(strRequest);
			if(StringUtils.isNotBlank(strResponse)){
				transaction =  (EmailTransaction) JAXBUtil.unmarshal(strResponse, "com.nrgenergy.utility.webservices.EmailTransaction");
				if(("Success").equalsIgnoreCase(transaction.getEmailTransactionResponse().getResponseDetails().getStatusCode())){
					logger.info("THE EMAIL HAS BEEN SENT FOR THE EXTERNAL ID::::"+externalId);
					return true;
				}
			} 
			logger.info("THE EMAIL HAS NOT BEEN SENT FOR THE EXTERNAL ID::::"+externalId);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE SENDING THE EMAIL::::::::",ex);
			throw new UtilityServiceException(ex);
		}
		return false;
	}

	
	private EmailTransaction populateEmailTransaction(MessageDetails msgDtls,String companyCode, String externalId, AdapterTypes adaptorType){
		EmailTransactionRequest request = new EmailTransactionRequest();
		EmailTransaction transaction = new EmailTransaction();
		request.setMessageDetails(msgDtls);
		request.setAdapterType(adaptorType);
		request.setCompanyCode(companyCode);
		request.setExternalID(externalId);
		transaction.setEmailTransactionRequest(request);
		return transaction;
	}
	
	
	

}
