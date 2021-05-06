package com.multibrand.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

import com.multibrand.service.UtilityService;
import com.multibrand.util.Constants;
import com.multibrand.util.EmailConstants;
import com.multibrand.util.EmailTemplateEnum;
import com.multibrand.util.GuidGenerator;
import com.multibrand.util.JAXBUtil;
import com.nrg.utility.webservices.UtilityWebServices;
import com.nrg.utility.webservices.UtilityWebServicesResponse;
import com.nrgenergy.utility.webservices.AdapterTypes;
import com.nrgenergy.utility.webservices.EmailAddressTypes;
import com.nrgenergy.utility.webservices.EmailDetails;
import com.nrgenergy.utility.webservices.EmailTransaction;
import com.nrgenergy.utility.webservices.EmailTransactionRequest;
import com.nrgenergy.utility.webservices.EmailTransactionResponse;
import com.nrgenergy.utility.webservices.MessageDetails;
import com.nrgenergy.utility.webservices.MessageProperties;
import com.nrgenergy.utility.webservices.MessageProperty;
import com.nrgenergy.utility.webservices.ResponseDetails;

@Component
public class EmailHelper implements Constants {
	
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	public static boolean sendMail(String emailAddress, String subject,
			String templateName, HashMap<String, String> templateProperties, String companyCode)
			throws Exception {

		MessageProperties msgProp = new MessageProperties();
		MessageDetails msgDtls = new MessageDetails();
		EmailDetails emailDtls1 = new EmailDetails();
		List<MessageProperty> msgPropList = new ArrayList<MessageProperty>();

		Set<String> tempKeySet = templateProperties.keySet();
		for (String tempKey : tempKeySet) {
			msgPropList.add(setTemplateVar(tempKey,
					templateProperties.get(tempKey)));

		}
		
		msgProp = generateTemplateVariables(msgPropList);
		List<EmailDetails> emailDtlsList = new ArrayList<EmailDetails>();
		//emailDtls1 = generateEmailDtls(EmailAddressTypes.TO, emailAddress);
		//emailDtlsList.add(emailDtls1);
		for(String mail: emailAddress.split(",")){
			emailDtls1 = generateEmailDtls(EmailAddressTypes.TO, mail.trim());
			emailDtlsList.add(emailDtls1);
	   }

		msgDtls = genarateMessageDtls(emailDtlsList, msgProp, subject);


		boolean status = false;
		
		if(EmailTemplateEnum.getEnumMap().containsKey(templateName)){
			status = processSendEmail(msgDtls, companyCode , templateName, AdapterTypes.HTML);
		 }else{
		        status = processSendEmail(msgDtls, companyCode , templateName, AdapterTypes.XSLT);
		 }
		return status;
	}
	
	/**
	 * For sending mails to given TO email address and BCC email address
	 * @param toEmailAddress
	 * @param bccEmailAddress
	 * @param subject
	 * @param templateName
	 * @param templateProperties
	 * @param companyCode
	 * @return
	 * @throws Exception
	 */
	public static boolean sendMailWithBCC(String toEmailAddress, String bccEmailAddress, String subject,
			String templateName, HashMap<String, String> templateProperties, String companyCode)
			throws Exception {

		MessageProperties msgProp = new MessageProperties();
		MessageDetails msgDtls = new MessageDetails();
		EmailDetails emailDtls1 = new EmailDetails();
		EmailDetails emailDtlsBCC = new EmailDetails();
		List<MessageProperty> msgPropList = new ArrayList<MessageProperty>();

		Set<String> tempKeySet = templateProperties.keySet();
		for (String tempKey : tempKeySet) {
			msgPropList.add(setTemplateVar(tempKey,
					templateProperties.get(tempKey)));

		}
		
		msgProp = generateTemplateVariables(msgPropList);
		List<EmailDetails> emailDtlsList = new ArrayList<EmailDetails>();
		for(String mail: toEmailAddress.split(",")){
		emailDtls1 = generateEmailDtls(EmailAddressTypes.TO, mail.trim());
		emailDtlsList.add(emailDtls1);
		}
		for(String mail : bccEmailAddress.split(",")){
		emailDtlsBCC = generateEmailDtls(EmailAddressTypes.BCC, mail.trim());
		emailDtlsList.add(emailDtlsBCC);
		}

		msgDtls = genarateMessageDtls(emailDtlsList, msgProp, subject);

		boolean status = false; 
		
		//if(templateName.equalsIgnoreCase(SUBMIT_TOS_CONF_ES)||templateName.equalsIgnoreCase(SUBMIT_TOS_CONF_EN)||templateName.equalsIgnoreCase(SUBMIT_SWAP_CONF_EN)|| templateName.equalsIgnoreCase(SUBMIT_SWAP_CONF_ES)){
		/*if(templateName.equalsIgnoreCase(CIRRO_SUBMIT_SWAP_CONF_EN) || templateName.equalsIgnoreCase(CIRRO_SUBMIT_SWAP_CONF_ES) || templateName.equalsIgnoreCase(SUBMIT_SWAP_CONF_EN)|| templateName.equalsIgnoreCase(SUBMIT_SWAP_CONF_ES) || templateName.equalsIgnoreCase(SUBMIT_TOS_CONF_EN) || templateName.equalsIgnoreCase(SUBMIT_TOS_CONF_ES)
				|| templateName.equalsIgnoreCase(GME_SUBMIT_AMB_EN_US) || templateName.equalsIgnoreCase(GME_SUBMIT_AMB_ES_US))*/
			if(templateName.equalsIgnoreCase(CIRRO_SUBMIT_SWAP_CONF_EN) || templateName.equalsIgnoreCase(CIRRO_SUBMIT_SWAP_CONF_ES) || templateName.equalsIgnoreCase(SUBMIT_SWAP_CONF_EN)|| templateName.equalsIgnoreCase(SUBMIT_SWAP_CONF_ES) ||
				 templateName.equalsIgnoreCase(GME_SUBMIT_AMB_EN_US) || templateName.equalsIgnoreCase(GME_SUBMIT_RETRO_AMB_ES_US) || templateName.equalsIgnoreCase(GME_SUBMIT_RETRO_AMB_EN_US) || templateName.equalsIgnoreCase(GME_SUBMIT_AMB_ES_US) ||
				 templateName.equalsIgnoreCase(CIRRO_EMAIL_CHANGE_EXTERNAL_ID_EN) || templateName.equalsIgnoreCase(POWER_GENIUS_ENROLL_CONF_EN) || templateName.equalsIgnoreCase(CREATE_USER_EN) || templateName.equalsIgnoreCase(CREATE_USER_ES) || EmailTemplateEnum.getEnumMap().containsKey(templateName))
		{				
			status = processSendEmail(msgDtls, companyCode , templateName, AdapterTypes.HTML);
			
		}else{
			status = processSendEmail(msgDtls, companyCode , templateName, AdapterTypes.XSLT);
			
		}
		logger.info("Email Sending status is : "+status);

		return status;

	}


	@SuppressWarnings("unused")
	private static Boolean processSendEmail(MessageDetails msgDtls,
			String companyCode, String externalId, AdapterTypes adaptorType)
			throws Exception {
		String strRequest = "";

		EmailTransactionRequest request = new EmailTransactionRequest();
		EmailTransaction transaction = new EmailTransaction();
		request.setMessageDetails(msgDtls);
		request.setAdapterType(adaptorType);
		request.setCompanyCode(companyCode);
		request.setExternalID(externalId);
		transaction.setEmailTransactionRequest(request);
		strRequest = JAXBUtil.marshal(transaction);

		logger.info("strRequest = JAXBUtil.marshal :: " + strRequest);

		UtilityWebServices port = new UtilityService().getUtilityServiceProxy();
		/*UtilityWebServicesService service = new UtilityWebServicesServiceLocator();
		
		UtilityWebServices port = service.getUtilityWebServicesPort(new URL("http://dev1-ws.nrgenergy.com/UtilityWebServices/utilityWebServices"));
		*/
		
		String txnGUID = GuidGenerator.getGuid(true);
		String serviceName = "EmailService";
		// String serviceRequest= createServiceRequest("ahanda1@reliant.com",
		// "Testing");

		UtilityWebServicesResponse response = port.submitTransactions(txnGUID,
				serviceName, strRequest);

		transaction = (EmailTransaction) JAXBUtil.unmarshal(
				response.getServiceResponse(),
				"com.nrgenergy.utility.webservices.EmailTransaction");
		if (("Success").equalsIgnoreCase(transaction
				.getEmailTransactionResponse().getResponseDetails()
				.getStatusCode())) {
			logger.info("Success");
			logger.info("emailTransactionResp :: "
					+ transaction.getEmailTransactionResponse());

			EmailTransactionRequest req = transaction
					.getEmailTransactionRequest();
			logger.info("req.getEmailTransactionRequest ::"
					+ transaction.getEmailTransactionRequest());

			EmailTransactionResponse resp = transaction
					.getEmailTransactionResponse();
			logger.info("ExternalID :: " + resp.getExternalID());
			ResponseDetails respDetails = resp.getResponseDetails();
			logger.info("Status description ::: "
					+ respDetails.getStatusDescription());
			Object object[] = respDetails.getStatusDescription().toArray();
			for (Object obj : object)
				logger.info(obj);
			return true;
		} else {

			return false;
		}

	}

	private static MessageProperty setTemplateVar(String key, String value) {
		MessageProperty property = new MessageProperty();
		property.setPropertyName(key);
		property.setPropertyValue(value);
		return property;
	}

	private static MessageProperties generateTemplateVariables(
			List<MessageProperty> messageProperty) {
		MessageProperties msgProp = new MessageProperties();
		for (MessageProperty prop : messageProperty) {
			msgProp.getMessageProperty().add(prop);
		}
		return msgProp;
	}

	private static MessageDetails genarateMessageDtls(
			List<EmailDetails> emailDtls, MessageProperties msgProp,
			String subject) {

		MessageDetails msgDtls = new MessageDetails();
		for (EmailDetails email : emailDtls) {
			msgDtls.getEmailDetails().add(email);
		}
		msgDtls.setMessageProperties(msgProp);
		msgDtls.setSubject(subject);
		return msgDtls;
	}

	private static EmailDetails generateEmailDtls(
			EmailAddressTypes emailAddressType, String emailAddress) {

		EmailDetails emailDtls = new EmailDetails();
		emailDtls.setEmailAddressType(emailAddressType);
		emailDtls.setEmailAddress(emailAddress);

		return emailDtls;
	}

	
	// START OE-REDESIGN-JASVEEN
	public boolean sendEnrollmentConfirmationMail(String firstName,
			String lastName, String emailAddress, String strLanguage)

	{
		logger.info("EmailService:sendEnrollmentConfirmationMail(..) Start");

		logger.debug("LanguageCode==" + strLanguage);

		Boolean status = false;
		try {
			String strExternalIdKey = EmailConstants.OE_ENROLLMENT_CONFIRMATION_EXTERNAL_ID;
			String strExternalId = null;

			String txtName = firstName + " " + lastName;

			MessageProperties msgProp = new MessageProperties();

			MessageDetails msgDtls = new MessageDetails();
			EmailDetails emailDtls1 = new EmailDetails();
			List<MessageProperty> msgPropList = new ArrayList<MessageProperty>();

			msgPropList.add(setTemplateVar("NAME", txtName));

			strExternalId = strExternalIdKey + "." + strLanguage.toUpperCase();
			// TODO
			// strExternalId =
			// this.appConstMessageSource.getMessage(strExternalId, null, null);
			logger.debug("strExternalIdKey==" + strExternalId);

			msgProp = generateTemplateVariables(msgPropList);
			List<EmailDetails> emailDtlsList = new ArrayList<EmailDetails>();
			emailDtls1 = generateEmailDtls(EmailAddressTypes.TO, emailAddress);
			emailDtlsList.add(emailDtls1);

			msgDtls = generateMessageDtls(emailDtlsList, msgProp, null);

			status = sendEmail(msgDtls, EmailConstants.RELIANT_COMPANY_CODE,
					strExternalId, AdapterTypes.HTML);

			logger.info("The Email status is::::::::" + status);

		} catch (Exception e) {
			logger.error("Exception in sendEnrollmentConfirmationMail :", e);

		}
		logger.info("EmailDomainHelper:sendEnrollmentConfirmationMail(..) End");
		return status;

	}

	private MessageDetails generateMessageDtls(List<EmailDetails> emailDtls,
			MessageProperties msgProp, String subject) {

		MessageDetails msgDtls = new MessageDetails();
		for (EmailDetails email : emailDtls) {
			msgDtls.getEmailDetails().add(email);
		}
		msgDtls.setMessageProperties(msgProp);
		msgDtls.setSubject(subject);
		return msgDtls;
	}
	
	private Boolean sendEmail(MessageDetails msgDtls,String companyCode, String externalId, AdapterTypes adaptorType) throws Exception{
		String strRequest = "";


		EmailTransactionRequest request = new EmailTransactionRequest();
		EmailTransaction transaction = new EmailTransaction();
		request.setMessageDetails(msgDtls);
		request.setAdapterType(adaptorType);
		request.setCompanyCode(companyCode);
		request.setExternalID(externalId);
		transaction.setEmailTransactionRequest(request);
		strRequest = JAXBUtil.marshal(transaction);
		UtilityService utilityservice = new UtilityService();
        String strResponse = utilityservice.sendReliantEmail(strRequest);
		if(StringUtils.isNotBlank(strResponse)){
			
			transaction =  (EmailTransaction) JAXBUtil.unmarshal(strResponse, "com.nrgenergy.utility.webservices.EmailTransaction");
			if(("Success").equalsIgnoreCase(transaction.getEmailTransactionResponse().getResponseDetails().getStatusCode())){
				logger.info("THE EMAIL HAS BEEN SENT FOR THE EXTERNAL ID::::"+externalId);
				return true;
			} else {
				logger.info("THE EMAIL HAS FAILED FOR THE EXTERNAL ID::::"+externalId);
				return false;
			}
		} else {
			logger.info("THE EMAIL HAS FAILED FOR THE EXTERNAL ID::::"+externalId);
			return false;
		}
	}
}
