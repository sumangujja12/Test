package com.multibrand.dto.request;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.multibrand.exception.ValidateRequestException;
import com.multibrand.util.EnvMessageReader;

/**
 * @author bbachin1
 * 
 */
public class EmailRequest extends NRGServicesRequest implements BaseEmailRequest{
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	@Autowired
	protected EnvMessageReader envMessageReader;
	
	private String externalId;
	private String subject;
	private String companyCode;
	private String languageCode;
	private String brandName;
	private List<String> toEmailList = new ArrayList<String>();
	private List<String> bccEmailList;
	private List<String> ccEmailList;
	private List<String> fromEmailList;
	private List<String> replyToEmailList;
	private List<String> bounceAddEmailList;
	private List<String> propertyList = new ArrayList<String>();
	private String templateType;
	
	private static final String[] validCompCodeAry = {"0121","0271","0270"};
	
	
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public List<String> getToEmailList() {
		return toEmailList;
	}
	public void setToEmailList(List<String> toEmailList) {
		this.toEmailList = toEmailList;
	}
	public List<String> getBccEmailList() {
		return bccEmailList;
	}
	public void setBccEmailList(List<String> bccEmailList) {
		this.bccEmailList = bccEmailList;
	}
	public List<String> getCcEmailList() {
		return ccEmailList;
	}
	public void setCcEmailList(List<String> ccEmailList) {
		this.ccEmailList = ccEmailList;
	}
	public List<String> getFromEmailList() {
		return fromEmailList;
	}
	public void setFromEmailList(List<String> fromEmailList) {
		this.fromEmailList = fromEmailList;
	}
	public List<String> getReplyToEmailList() {
		return replyToEmailList;
	}
	public void setReplyToEmailList(List<String> replyToEmailList) {
		this.replyToEmailList = replyToEmailList;
	}
	public List<String> getBounceAddEmailList() {
		return bounceAddEmailList;
	}
	public void setBounceAddEmailList(List<String> bounceAddEmailList) {
		this.bounceAddEmailList = bounceAddEmailList;
	}
	public List<String> getPropertyList() {
		return propertyList;
	}
	public void setPropertyList(List<String> propertyList) {
		this.propertyList = propertyList;
	}
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	
	
	@Override
	public void validateRequest() throws ValidateRequestException {
		
		if(StringUtils.isBlank(this.companyCode)){throw new ValidateRequestException("COMPANY CODE SHOULD NOT BE EMPTY");}
		if(!ArrayUtils.contains(validCompCodeAry, this.companyCode)){throw new ValidateRequestException("COMPANY CODE IS NOT VALID");}
		if(StringUtils.isBlank(this.brandName)){throw new ValidateRequestException("BRAND NAME SHOULD NOT BE EMPTY");}
		if(StringUtils.isBlank(this.externalId)){throw new ValidateRequestException("EXTERNAL ID SHOULD NOT BE EMPTY");}
		if(StringUtils.isBlank(this.templateType)){throw new ValidateRequestException("TEMPLATE TYPE SHOULD NOT BE EMPTY");}
		if(StringUtils.isBlank(this.languageCode)){throw new ValidateRequestException("LANGUAGE CODE SHOULD NOT BE EMPTY");}
		if(null == this.toEmailList || this.toEmailList.size() == 0){throw new ValidateRequestException("ATLEAST ONE EMAIL IS REQUIRED IN TOEMAIL LIST");}
		
	}
	
	
	public void populateRequestForEBillBasedOnBrand(CCSEmailRequest request){
		
		this.subject = EBILL_SUBJECT;
		this.toEmailList.add(request.getEmailid());
		this.companyCode = request.getCompanycode();
		this.languageCode = request.getLanguagecode();
		this.brandName = request.getBrandname();
		if((StringUtils.isNotBlank(this.companyCode) && StringUtils.equalsIgnoreCase(this.companyCode, RELIANT_COMPANY_CODE)) &&
				(StringUtils.isNotBlank(this.brandName) && StringUtils.equalsIgnoreCase(this.brandName, RELIANT_BRAND_NAME))){
			this.externalId = StringUtils.equalsIgnoreCase(this.languageCode, EN)?EBILL_EXTERNAL_ID_EN:EBILL_EXTERNAL_ID_ES;
			this.templateType = EBILL_HTML_TEMPLATE_ID;
			this.propertyList.add(EBILL_CA_RAW+COLON+request.getContractaccountnumber());
			this.propertyList.add(EBILL_CHECK_DIGIT+COLON+request.getCheckdigit());
			this.propertyList.add(EBILL_NAME_ON_ACCOUNT+COLON+request.getCaname());
			this.propertyList.add(EBILL_DELIVERY_METHOD+COLON+getBillDeliveryMethod(request.getBilldeliverymethod()));
			this.propertyList.add(EBILL_CONFIRM_NUMBER+COLON+UUID.randomUUID());
		}else if((StringUtils.isNotBlank(this.companyCode) && StringUtils.equalsIgnoreCase(this.companyCode, GME_COMPANY_CODE)) 
				&& (StringUtils.isNotBlank(this.brandName) && StringUtils.equalsIgnoreCase(this.brandName, GME_BRAND_NAME))){
			this.externalId = StringUtils.equalsIgnoreCase(this.languageCode, EN)?GME_EBILL_EXTERNAL_ID_EN:GME_EBILL_EXTERNAL_ID_ES;
			this.templateType = GME_EBILL_TEMPLATE_ID;
			this.bccEmailList = new ArrayList<String>();
			String bccEmail = getBCCEmail();
			if(logger.isDebugEnabled()){logger.debug("BCC EMAIL::::::"+bccEmail);}
			this.bccEmailList.add(bccEmail);
		}else{
			logger.info("BRAND NAME/COMPANY CODE IS EMPTY::");
		}
		logger.info("Printing Object::::::"+ ReflectionToStringBuilder.toString(this));
	}
	
	
	private String getBillDeliveryMethod(String billDeliveryMethod){
		
		if(StringUtils.isNotBlank(this.languageCode) && StringUtils.isNotBlank(billDeliveryMethod)){
			if(StringUtils.equalsIgnoreCase(billDeliveryMethod, MAIL)){return (StringUtils.equalsIgnoreCase(this.languageCode, EN))?EBILL_DELIVERY_METHOD_VAL_MAIL_EN:EBILL_DELIVERY_METHOD_VAL_MAIL_ES;}
			if(StringUtils.equalsIgnoreCase(billDeliveryMethod, EMAIL)){return (StringUtils.equalsIgnoreCase(this.languageCode, EN))?EBILL_DELIVERY_METHOD_VAL_EMAIL_EN:EBILL_DELIVERY_METHOD_VAL_EMAIL_ES;}
			if(StringUtils.equalsIgnoreCase(billDeliveryMethod, BOTH)){return (StringUtils.equalsIgnoreCase(this.languageCode, EN))?EBILL_DELIVERY_METHOD_VAL_BOTH_EN:EBILL_DELIVERY_METHOD_VAL_BOTH_ES;}
		}else{
			logger.info("LANGUAGE CODE/BILL DELIVERY METHOD IS EMPTY::");
		}
		return null;
	}
	
	
	public void printObject(){
		logger.info("Printing Object::::" + ReflectionToStringBuilder.toString(this));
	}
	
	
	public String getBCCEmail() {
		
		String bccEmail = "";
		if(envMessageReader == null) {
			logger.info("EnvMessageReader is null coz of Asynchronize call: Loading new instance of EnvMessageReader with " +ENV_PROPERTIES_FILE);
			envMessageReader = new EnvMessageReader(ENV_PROPERTIES_FILE);
			bccEmail = envMessageReader.getMessage(EBILL_GME_BCC_EMAIL);
		}
		return bccEmail;
	}
	
	public static void main(String[] args){
		Gson gson = new Gson();
		EmailRequest re = new EmailRequest();
		System.out.println(gson.toJson(re));
	}
	
}
