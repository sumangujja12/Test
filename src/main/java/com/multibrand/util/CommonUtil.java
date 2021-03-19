package com.multibrand.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.SOAPException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.CharEncoding;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.multibrand.dto.ErrorDTO;
import com.multibrand.dto.OESignupDTO;
import com.multibrand.dto.request.WebHookRequest;
import com.multibrand.vo.request.UserIdRequest;
import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.UserIdResponse;
import com.multibrand.vo.response.WebHookResponse;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;
import com.sun.jersey.core.util.MultivaluedMapImpl;


@Component
public class CommonUtil implements Constants {
	static Logger logger = LogManager.getLogger("CommonUtil");
	protected static final String[] COMPANY_CODE_ARRAY = {COMPANY_CODE_RELIANT, COMPANY_CODE_GME, COMPANY_CODE_PENNYWISE, COMPANY_CODE_EE};
	protected static HashSet<String> privacyDataParams = null;
	protected static HashSet<String> logExcludeResponseMethodList = null;
	private static final Random rand = new Random();
	private static final char[] alphanumeric = alphanumeric();
	private static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	
	static {
		init();
	}

	private static void init() {
		privacyDataParams = getPrivacyDataParams();
		logExcludeResponseMethodList = getLogExludeMethods();
	}
	  
	/**
	 * Enum of all inputs which require specific format validation
	 * @author Jasveen
	 */
	public enum validationFormatEnum{PHONE,EMAIL,SSN,TDL};

	private static final char[] bases = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z' };

	public static String getWSCredentials(String input) {
		String credential = jndiLookup(input);

		return credential;
	}

	public static String jndiLookup(String inputVal) {
		try {
			Context ic = new InitialContext();
			Context ctx = (Context) ic.lookup("java:comp/env");
			return (String) ctx.lookup(inputVal);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return "";
	}

	public static int getOAMHour() {
		return 24;
	}

	public static String paddedCa(String ca) {
		String retStr = null;
		if (ca != null) {
			int sz = ca.length();
			if (sz < 12)
				retStr = StringUtils.leftPad(ca, 12, '0');
			else if (sz == 12)
				retStr = ca;
			else
				retStr = ca.substring(0, 12);
		} else {
			retStr = "000000000000";
		}
		return retStr;
	}

	public static String addLeadingZeros(String number, int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length - number.length(); i++) {
			sb.append("0");
		}
		sb.append(number);
		return sb.toString();
	}

	public static String changeDateFormat(String strDate) {
		if ((strDate != null) && (!strDate.trim().equalsIgnoreCase(""))) {
			if (strDate.equalsIgnoreCase("0000-00-00")) {
				strDate = "00000000";
			} else {
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
				try {
					strDate = format2.format(format1.parse(strDate));
				} catch (ParseException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return strDate;
	}

	public static String changeDateFormat(String strDate, String srcFormat,
			String destFormat) {
		// System.out.println("strDate"+strDate);
		if ((strDate != null) && (!strDate.trim().equalsIgnoreCase(""))) {
			SimpleDateFormat format1 = new SimpleDateFormat(srcFormat);
			SimpleDateFormat format2 = new SimpleDateFormat(destFormat);
			try {
				strDate = format2.format(format1.parse(strDate));
			} catch (ParseException e) {
				logger.error(e.getMessage(), e);
			}
		}

		return strDate;
	}

	public static String normalizeNegativeNumber(String numericSpec) {
		return numericSpec.replaceAll("^(\\.[0-9]+)-", "-$1").replaceAll(
				"([0-9]+(?:\\.[0-9]+)?)-", "-$1");
	}

	public static String getYrMonthDay(Date date) {
		logger.debug("Inside getMonthDayYr in UsageHelper");
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int monthNo = 0;
			int dayNo = 0;
			if (calendar.get(2) + 1 < 10)
				monthNo = 0 + (calendar.get(2) + 1);
			else {
				monthNo = calendar.get(2) + 1;
			}
			if (calendar.get(5) < 10)
				dayNo = 0 + calendar.get(5);
			else {
				dayNo = calendar.get(5);
			}
			return Integer.toString(calendar.get(1) + monthNo + dayNo);
		}

		return "";
	}

	public static String getBlankString(String inputString) {
		if (inputString == null) {
			return "";
		}
		return inputString;
	}
	
	public static String getDefaultTemperature(String temp) {
		if (StringUtils.isBlank(temp)) {
			return "0";
		} else {
			return temp;
		}
	}

	public static long getStartTime() {
		return System.currentTimeMillis();
	}

	public static long getElapsedTime(long startTime) {
		return System.currentTimeMillis() - startTime;
	}

	public static String generateConfirmationNumber() {
		String hostString = "";
		String confirmationNumber = "";
		long ts = System.currentTimeMillis();
		try {
			hostString = InetAddress.getLocalHost().getHostAddress();
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < 2; i++) {
				int lastIndex = hostString.lastIndexOf(".") + 1;
				int length = hostString.length();
				buf.append(hostString.substring(lastIndex, length));
				hostString = hostString.substring(0, lastIndex - 1);
			}
			String currentHostAddress = obfuscate(36,
					Long.parseLong(buf.toString()));
			confirmationNumber = currentHostAddress + "-" + obfuscate(36, ts);
		} catch (Exception e) {
			confirmationNumber = obfuscate(36, ts);
			e.printStackTrace();
		}
		return confirmationNumber;
	}

	public static String obfuscate(int base, long value) {
		String transformed = "";
		if (value < base) {
			transformed = transformed + String.valueOf(bases[((int) value)]);
		} else {
			int mod = (int) (value % base);
			long nextValue = value / base;
			transformed = transformed + obfuscate(base, nextValue)
					+ String.valueOf(bases[mod]);
		}
		return transformed;
	}

	public static final String getGuid() throws Exception {
		String guid = generateUUID();
		return guid;
	}

	public static String generateUUID() {
		return String.valueOf(UUID.randomUUID());
	}

	public static String arrayToString(String[] a, String separator) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < a.length; i++)
			if (a[i] != null) {
				if (i != 0) {
					result.append(separator);
				}
				result.append("'");
				result.append(a[i]);
				result.append("'");
			}
		return result.toString();
	}

	public static Map<String, Object> loadUnknownErrorToMap(
			Map<String, Object> resultMap) {
		return loadErrorCodeToMap(resultMap, "MSG_UNKNOWN_ERROR");
	}

	public static Map<String, Object> loadErrorCodeToMap(
			Map<String, Object> resultMap, String errorCode) {
		if (resultMap == null) {
			resultMap = new HashMap();
		}

		resultMap.put("error", errorCode);

		return resultMap;
	}

	public static String doRender(Object object) {
		StringBuffer retValue = new StringBuffer();
		retValue.append('{');
		
		if (object != null) {
			
			Field[] sFields = object.getClass().getDeclaredFields();

			for (Field field : sFields) {
				String propertyName = field.getName();

				retValue.append(propertyName);
				retValue.append('=');

				Method ipGetterMethod = null;
				try {
					ipGetterMethod = PropertyUtils
							.getReadMethod(new PropertyDescriptor(propertyName,
									object.getClass()));
				} catch (IntrospectionException e) {
					logger.debug(" Ignore this IntrospectionException:"
							+ e.getMessage());
				}

				if (ipGetterMethod != null) {
					try {
						Object value = ipGetterMethod.invoke(object,
								new Object[0]);
						
						if (value != null
								&& StringUtils.isNotBlank(value.toString())
								&& hasPrivacyData(propertyName)) {
							
							retValue.append(MASK_CHAR);
						}	
						else {
							retValue.append(value);
						}	
					} catch (Exception e) {
						logger.error(" Ignore this error:" + e.getMessage());
					}
				}

				retValue.append(';');
			}
		} else {
			retValue = retValue.append("null");
		}

		retValue.append('}');

		return retValue.toString();
	}

	public static String getCurrentDateYYYYMMDD() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String formatedDate = df.format(new Date());

		return formatedDate;
	}

	/**
	 * The method will checks for the input flag and reassigns "X" if it is
	 * "YES" or "Y" & "O" if it is "NO" or "N". If none of them then in that
	 * case return as it is.
	 * 
	 * @param inputFlag
	 * @return String
	 */
	public static String getFlagValue(String inputFlag) {
		String retFlagVal = "";
		if (null != inputFlag) {
			if (Constants.FLAG_YES_PROFILE.equalsIgnoreCase(inputFlag)
					|| Constants.FLAG_Y.equalsIgnoreCase(inputFlag)) {
				retFlagVal = Constants.FLAG_X;
			} else if (Constants.FLAG_NO.equalsIgnoreCase(inputFlag)
					|| Constants.FLAG_N.equalsIgnoreCase(inputFlag)) {
				retFlagVal = Constants.FLAG_O;
			} else {
				retFlagVal = inputFlag;
			}
		}
		return retFlagVal;
	}

	/**
	 * The method will checks for the input flag and reassigns "X" if it is
	 * "YES" or "Y" & "O" if it is null or not equals to YES or Y.
	 * 
	 * @param inputFlag
	 * @return String
	 */
	public static String getSVTFlagValue(String inputFlag) {
		String retFlagVal = "";
		if (null != inputFlag) {
			if (Constants.FLAG_YES_PROFILE.equalsIgnoreCase(inputFlag)
					|| Constants.FLAG_Y.equalsIgnoreCase(inputFlag)) {
				retFlagVal = Constants.FLAG_X;
			} else {
				retFlagVal = Constants.FLAG_O;
			}
		} else {
			retFlagVal = Constants.FLAG_O;
		}
		return retFlagVal;
	}

	/**
	 * replace all characters in bank account no. with '*' leaving last 3
	 * characters as it is
	 * 
	 * @param bankAccountNo
	 * @return
	 */
	public static String maskBankAccountNo(String bankAccountNo) {
		String replaceCharSequence = bankAccountNo.substring(0,
				bankAccountNo.length() - 3);
		replaceCharSequence = replaceCharSequence.replaceAll("(?s).", "*");
		return replaceCharSequence
				+ bankAccountNo.substring(bankAccountNo.length() - 3,
						bankAccountNo.length());
	}

	/**
	 * replace all characters in Credit card no. with '*' leaving last 4
	 * characters as it is
	 * 
	 * @param ccNo
	 * @return
	 */
	public static String maskCCNo(String ccNo) {
		String replaceCharSequence = ccNo.substring(0, ccNo.length() - 4);
		replaceCharSequence = replaceCharSequence.replaceAll("(?s).", "*");
		return replaceCharSequence
				+ ccNo.substring(ccNo.length() - 4, ccNo.length());
	}

	/**
	 * replace all characters in input string no. with '*' leaving last 4
	 * characters as it is
	 * 
	 * @param rawData
	 * @return
	 */
	public static String maskPIIData(String rawData) {
		if(null!=rawData && rawData.length()>4){
			String replaceCharSequence = rawData.substring(0, rawData.length() - 4);
			replaceCharSequence = replaceCharSequence.replaceAll("(?s).", "*");
			return replaceCharSequence	+ rawData.substring(rawData.length() - 4, rawData.length());
		}else{
			return rawData;
		}
	}
	
	/**
	 * replace all characters in Credit card no. with '*' leaving last 4
	 * characters as it is
	 * 
	 * @param ccNo
	 * @return
	 */
	public static String maskCVVCode(String str){
		StringBuilder encodeCVV = new StringBuilder();
		if(StringUtils.isNotBlank(str)){
			encodeCVV = new StringBuilder(new String(Base64.encodeBase64(str.getBytes())));
		   return encodeCVV.toString();
		}
		else
		return encodeCVV.toString();		
	}
	/**
	 * forcing paymentAmount to two decimal points
	 * 
	 * @param paymentAmount
	 * @return
	 */
	public static String splitPaymentAmountDecimal(String paymentAmount) {
		String[] strArray = paymentAmount.split("\\.");
		if (strArray.length > 1) {
			if (strArray[1].length() == 1) {
				paymentAmount = paymentAmount + "0";
			} else if (strArray[1].length() > 2) {
				String strNew = strArray[1].substring(0, 2);
				paymentAmount = strArray[0] + "." + strNew;
			}
		} else {
			paymentAmount = paymentAmount + ".00";
		}
		return paymentAmount;
	}

	/**
	 * To get the Sql Date for the Given Date String.
	 * 
	 * @param strDate
	 * @param DateFrmt
	 * @return
	 */
	public static java.sql.Date getSqlDate(String strDate, String DateFrmt) {
		java.util.Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(DateFrmt);
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			return null;
		}

		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}

	/**
	 * 
	 * @param value
	 * @param maxDigit
	 * @return
	 */
	public static String getRoundingDecimal(String value, int maxDigit) {
		if (value == "" || value == null) {
			return "";
		}
		System.out.println("String" + value);
		BigDecimal big = new BigDecimal(value, new MathContext(maxDigit,
				RoundingMode.HALF_UP));
		System.out.println(" big.toString()" + big.doubleValue());
		return getDecimalFormat(big.doubleValue(), "##.#");
	}

	/**
	 * 
	 * @param value
	 * @param maxDigit
	 * @return
	 */
	public static String getRoundingDecimalCost(String value) {
		if (value == "" || value == null) {
			return "";
		}
		System.out.println("String" + value);
		BigDecimal big = new BigDecimal(value, new MathContext(20,
				RoundingMode.HALF_UP));
		System.out.println(" big.toString()" + big.doubleValue());
		return getDecimalFormat(big.doubleValue(), "##.##");
	}

	public static String getDecimalFormat(Double number, String pattern) {
		DecimalFormat df = new DecimalFormat(pattern);
		System.out.println("number" + number);
		String frmString = df.format(number);
		System.out.println(String.valueOf(frmString));
		return df.format(number).toString();
	}

	public static String wirteObjectToJson(Response response) {

		ObjectWriter ow = new ObjectMapper().writer()
				.withDefaultPrettyPrinter();
		String json = "";
		try {
			json = ow.writeValueAsString(response.getEntity());
		} catch (JsonGenerationException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return json;

	}

	
	 
	

	/**
	 * format the date in yyyyMMdd
	 * @param cal
	 * @return
	 */
	public static String changeDateFormat(Calendar cal) {

		DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String str="";
        if(cal!=null)
		  str = df.format(cal.getTime());

		return str;
	}

	/**
	 * change string in format MM/dd/yyyy
	 * to calendar object
	 * @param strDate
	 * @return
	 * @throws ParseException 
	 */
	public static Calendar getDatefromString(String strDate) throws ParseException {

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

		Date date = null;
		Calendar cal = null;
		try {
			if(strDate!=null && !strDate.equals("")){
				date = df.parse(strDate);
				cal=Calendar.getInstance();
				cal.setTime(date);
				return cal;	
			}
			else
				return cal;
		} catch (ParseException e) {
			logger.error("Exaception in getDatefromString " + e.getMessage());
			throw e;
		}

	}
	
	/**
	 * @param companyCode
	 * @param brandName
	 * @param emailTypeId
	 * @return boolean
	 */
	public static boolean checkIfGMEPrepay(String companyCode, String brandName, String emailTypeId) {
		if((GME_RES_COMPANY_CODE.equalsIgnoreCase(companyCode) || (GME_BRAND_NAME.equalsIgnoreCase(brandName))) && null!= emailTypeId && GME_RES_EMAIL_TYPE_ID_PREPAY.equalsIgnoreCase(emailTypeId))
			return true;
		else
			return false;				
	}
	
	/********************************************
	 * Function: getValue("Str") Purpose: makes null check Inputs: string
	 * Returns: string
	 * 
	 * @param value
	 *            String
	 * @return String
	 ********************************************/
	public static String getValue(String value) {
		if (StringUtils.isBlank(value) || "null".equals(value)) {
			return EMPTY;
		} else {
			return value;
		}
	}
	
	/********************************************
	 * Function: getPrevTransactionDate(trandate,locale) Purpose: returns the
	 * prev Transaction Date Inputs: none Returns: Complete Date
	 * 
	 * @param tranDate
	 *            java.sql.Date
	 * @param locale
	 *            String
	 * @return String
	 ********************************************/
	public static String getFormattedTransactionDate(java.sql.Date tranDate,
			String locale) {
		java.text.SimpleDateFormat sdf = null;
		// java.text.SimpleDateFormat sdf = new
		// java.text.SimpleDateFormat("MMM d, yyyy 'at' HH:mm aaa, 'Central'");
		if (locale.equals("en")) {
			sdf = new java.text.SimpleDateFormat(
					"MMMMM d, yyyy 'at' HH:mm aaa.", new Locale("en", "EN"));
		} else {
			sdf = new java.text.SimpleDateFormat(
					"d 'de' MMMMM, yyyy 'a las' HH:mm aaa.", new Locale("es",
							"ES"));
		}
		return sdf.format(tranDate);
	}

	
	/**Method for checking mandatory parameters which are required for making RestFul Call,
	 * Returns ResultCode and Error Desc with all missing parameters if any 
	 * @method checkMandatoryParam
	 * @param companyCode
	 * @param affiliatesId
	 * @return HashMap<String,Object>
	 * @author Jasveen Singh
	 */
	public static HashMap<String, Object> checkMandatoryParam(Map<String, Object> mandatoryParamMap)
	{
		logger.info("inside checkMandatoryParam:: entering method");
		HashMap<String, Object> mandatoryParamChkResponse= new HashMap<String, Object>(); 
		ArrayList<Object> mandatoryParamMissing = new ArrayList<Object>();
		String errorDesc="";
		if(mandatoryParamMap!=null && ((mandatoryParamMap.size())>0)){
			for(String key:mandatoryParamMap.keySet() )
			{
				logger.debug("inside checkmandatory param values and total values are::"+mandatoryParamMap.size());
				if(StringUtils.isBlank((String) mandatoryParamMap.get(key))){
					mandatoryParamMissing.add(key);
					if(StringUtils.isBlank(errorDesc)){
						errorDesc=key;
					}else{
						errorDesc+=", "+key;
					}
				}
				logger.debug("inside checkMandatoryParam:: "+mandatoryParamMissing.size());
			}
			if(mandatoryParamMissing!=null &&(mandatoryParamMissing.size()>0))
			{
				errorDesc="Missing Parameters are :: "+errorDesc;
				mandatoryParamChkResponse.put("resultCode", RESULT_CODE_EXCEPTION_FAILURE);
				mandatoryParamChkResponse.put("errorDesc", errorDesc);
			}
			else
			{
				mandatoryParamChkResponse.put("resultCode", SUCCESS_CODE);
				mandatoryParamChkResponse.put("errorDesc", errorDesc);
			}
		}
		else
		{
			mandatoryParamChkResponse.put("resultCode", SUCCESS_CODE);
			mandatoryParamChkResponse.put("errorDesc", errorDesc);
		}
		logger.info("inside checkMandatoryParam:: response is :: "+mandatoryParamChkResponse);
		return mandatoryParamChkResponse;
	}
	//Method to return a invalid Call or when mandatory details are not provided
	
	/**
	 * method to return response for invalid calls
	 * @param resultDesc
	 * @param errCode
	 * @return Response
	 * @author Jasveen Singh
	 */
	public static Response buildNotValidResponse(String resultCode, String resultDesc)
	{
		logger.info("inside generic exception builder");
		ResponseBuilder builder= new ResponseBuilderImpl();
		GenericResponse notAllowedResponse= new GenericResponse();
		//notAllowedResponse.setErrorCode(statusCode);
		//notAllowedResponse.setStatusCode(statusCode);
		notAllowedResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);//pass other constant
		notAllowedResponse.setResultDescription(resultDesc);
		notAllowedResponse.setStatusCode(Constants.STATUS_CODE_STOP);
		builder.entity(notAllowedResponse);
		builder.status(Response.Status.OK);// pass 200 always*
		return builder.build();
		
	}
	
	/**
	 * Returns errors response.
	 * 
	 * @param resultCode
	 * @param resultDescription
	 * @param messageCode
	 * @param messageText
	 * @param statusCode
	 * 
	 * @return Response
	 * @author Jenith (jyogapa1)
	 */
	public static Response buildErrorsResponse(String resultDescription, String messageCode, String messageText,
			String statusCode) {
		
		logger.info("INSIDE GENERIC ERRORS RESPONSE BUILDER");
		
		ResponseBuilder builder = new ResponseBuilderImpl();

		GenericResponse errors = new GenericResponse();

		// result
		errors.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		errors.setResultDescription(resultDescription);

		// message
		errors.setMessageCode(messageCode);
		errors.setMessageText(messageText);

		// status if any
		errors.setStatusCode(statusCode);

		builder.entity(errors);
		builder.status(Response.Status.OK);// pass 200 always*

		return builder.build();
	}
	
	/**
	 * Returns errors response.
	 * 
	 * @param resultDescription
	 * 
	 * 
	 * @return Response
	 * @author Jenith (jyogapa1)
	 */
	public static Response buildErrorsResponse(String resultDescription) {
		
		Response response = buildErrorsResponse(resultDescription,
				null, null, null);

		return response;
	}
	
	/**
	 * Returns errors response.
	 * 
	 * @param resultCode
	 * @param resultDescription
	 * @param statusCode
	 * 
	 * @return Response
	 * @author Jenith (jyogapa1)
	 */
	public static Response buildErrorsResponse(String resultDescription, String statusCode) {
		
		Response response = buildErrorsResponse(resultDescription,
				null, null, statusCode);

		return response;
	}
	
	public static String getFullURL(HttpServletRequest request) {
	    StringBuffer requestURL = request.getRequestURL();
	    String queryString = request.getQueryString();

	    if (queryString == null) {
	        return requestURL.toString();
	    } else {
	        return requestURL.append('?').append(queryString).toString();
	    }
	}

	
	/********************************************
	 * Function: addLeadingZeroes("Str",int) Purpose: convert input value to
	 * specified decimal point places Inputs: string Returns: string
	 * 
	 * @param value
	 *            String
	 * @param n
	 *            int
	 * @return String
	 ********************************************/
	public static String addLeadingZeroes(String value, int n) {
		String returnValue = StringUtils.leftPad(value, n, '0');
		return returnValue;
	}
	
	/**
	 * Method removeLeftPadZeros.
	 * 
	 * @param data
	 *            String
	 * @return String
	 */
	public static String removeLeftPadZeros(String data) {

		if (data == null) {
			return data;
		}
		return data.replaceAll("(\\s*0*)(.*)", "$2");
	}

	/**
	 * Method will take String phone number in (XXX)-XXX-XXXX format and return
	 * sting phone number in XXXXXXXXXX format
	 * 
	 * @param formatedPhone
	 *            -
	 * @return String
	 */
	public static String getRawPhoneNumberFromFormatedPhone(String formatedPhone) {
		String phoneNumber = EMPTY;
		if (null != formatedPhone && !EMPTY.equals(formatedPhone)) {
			phoneNumber = formatedPhone.replace("(", EMPTY);
			phoneNumber = phoneNumber.replace(")", EMPTY);
			phoneNumber = phoneNumber.replace(" ", EMPTY);
//			phoneNumber = phoneNumber.replaceAll("-", EMPTY);
			phoneNumber = StringUtils.replace(phoneNumber, "-", EMPTY);
		}

		return phoneNumber;
	}

	/**
	 * Method will take start and end dates and return a list with the dates in between (inclusive)
	 * 
	 * @author Mayank Mishra
	 * @param startDate
	 * @param endDate
	 * 
	 * @return List<String>
	 */
	public static List<String> getDaysInBetween(Date startDate, Date endDate) {
	    List<String> result = new ArrayList<String>();
	    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	    Calendar start = Calendar.getInstance();
	    start.setTime(startDate);
	    Calendar end = Calendar.getInstance();
	    end.setTime(endDate);
	    end.add(Calendar.DAY_OF_YEAR, 1); //Add 1 day to endDate to make sure endDate is included into the final list
	    while (start.before(end)) {
	        result.add(df.format(start.getTime()));
	        start.add(Calendar.DAY_OF_YEAR, 1);
	    }
	    return result;
	}
	
	/**
	 * Validates JSON text.
	 * 
	 * @param jsonText
	 *            JSON text.
	 * @return <code>true</code> if <code>jsonText</code> is valid otherwise,
	 *         returns <code>false</code>.
	 */
	public static boolean isValidJson(String jsonText) {
		boolean valid = false;
		JsonParser parser = null;
		try {
			parser = new ObjectMapper().getJsonFactory()
					.createJsonParser(jsonText);
			while (parser.nextToken() != null) {
			}
			valid = true;
		} catch (JsonParseException jpe) {
			logger.error(jpe.getMessage(), jpe);
		} catch (IOException ioe) {
			logger.error(ioe.getMessage(), ioe);
		} finally {
			if ( parser != null) {
				try {
					parser.close();
				} catch (IOException e) {
					logger.error("isValidJson jsonText failed.{}", e.getMessage());
				}
			}
		}
		return valid;
	}

	/**
	 * Convert <code>jsonText</code> to POJO.
	 * 
	 * @param jsonText
	 *            JSON text.
	 * @param clazz
	 *            POJO class to transform.
	 * @return POJO implementation of JSON text.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object convertPojoFromJsonText(String jsonText, Class clazz) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(
				DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Object result = null;
		try {
			result = mapper.readValue(jsonText, clazz);
		} catch (Exception e) {
			logger.error("Pojo conversion from jsonText failed.", e);
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * Convert POJO to <code>MultivaluedMap</code>.
	 * 
	 * @param pojo
	 *            POJO which will be converted to <code>MultivaluedMap</code>.
	 * @return POJO conversion to <code>MultivaluedMap</code>.
	 */
	@SuppressWarnings({ "unchecked" })
	public static MultivaluedMap<String, String> convertPojoToMultivaluedMap(
			Object pojo) {
		MultivaluedMap<String, String> map = null;
		if (pojo != null) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> pojoMap = mapper.convertValue(pojo,
						Map.class);
				logger.debug("pojoMap = " + pojoMap);
				if (pojoMap != null) {
					map = new MultivaluedMapImpl();
					extractPojoMap(StringUtils.EMPTY, pojoMap, map);
				}
			} catch (Exception e) {
				logger.error("Pojo conversion to MultivaluedMap failed.", e);
			}
		}
		logger.debug("MultivaluedMap.map = " + map);
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void extractPojoMap(String mapKeyPrifix,
			Map<String, Object> pojoMap, MultivaluedMap resultMap)
			throws ParseException {
		if (pojoMap != null && resultMap != null) {
			String attributeSeparater = ".";
			for (Map.Entry<String, Object> entry : pojoMap.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				String mapKey = StringUtils.isNotEmpty(mapKeyPrifix) ? (mapKeyPrifix
						+ attributeSeparater + key)
						: key;
				if (value instanceof String) {
					resultMap.add(mapKey, value != null ? value.toString()
							: null);
				} else if (value instanceof Map) {
					Map<String, Object> subMap = (Map<String, Object>) value;
					extractPojoMap(mapKey, subMap, resultMap);
				} else if (value instanceof List) {
					// TODO: need to fix the logic to populate list attributes.
					@SuppressWarnings("unused")
					List<Object> subList = (List<Object>) value;
					//extractPojoList(mapKey, subList, resultMap);
				}
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void extractPojoList(String mapKeyPrifix,
			List<Object> pojoList, MultivaluedMap resultMap)
			throws ParseException {
		if (pojoList != null && pojoList.size() > 0 && resultMap != null) {
			int count = 0;
			for (Object value : pojoList) {
				String mapKey = mapKeyPrifix + "[" + count + "]";
				if (value instanceof String) {
					resultMap.add(mapKey, value != null ? value.toString()
							: null);
				} else if (value instanceof Map) {
					Map<String, Object> subMap = (Map<String, Object>) value;
					extractPojoMap(mapKey, subMap, resultMap);
				} else if (value instanceof List) {
					List<Object> subList = (List<Object>) value;
					extractPojoList(mapKey, subList, resultMap);
				}
				count++;
			}
			count = 0;
		}
	}

	/**
	 * Get <code>Map</code> containing URL parameters & their values.
	 * 
	 * @param queryString
	 *            URL query string.
	 * @return Map of URL query parameters.
	 * @throws UnsupportedEncodingException
	 */
	public static Map<String, String> getUrlParameters(String queryString)
			throws UnsupportedEncodingException {
		Map<String, String> params = new HashMap<String, String>();
		for (String param : queryString.split("&")) {
			String pair[] = param.split("=");
			String key = URLDecoder.decode(pair[0], CharEncoding.UTF_8);
			String value = StringUtils.EMPTY;
			if (pair.length > 1) {
				value = URLDecoder.decode(pair[1], CharEncoding.UTF_8);
			}
			params.put(new String(key), new String(value));
		}
		return params;
	}

	/**
	 * Method will take zipCode+4 and return back a trimmed version with a valid
	 * zip code or empty String ""
	 * 
	 * @author Mayank Mishra
	 * @param zipCode
	 * 
	 * @return String
	 */
	public static String trimZipCode(String zipCode) {
	    if (StringUtils.isNotBlank(zipCode) && zipCode.length() > ZIPCODE_SIZE)
	    	return StringUtils.substring(CommonUtil.getValue(zipCode), 0, ZIPCODE_SIZE);
	    else
	    	return zipCode;
	}	
	
	/**
	 * Returns a Environment properties file handle if it is not found.
	 * 
	 * @param envMessageReader
	 * @return
	 */
	public static EnvMessageReader getEnvMessageReader(EnvMessageReader envMessageReader) {

		if (envMessageReader == null) {
			logger.debug("EnvMessageReader is null coz of Asynchronize call: Loading new instance of EnvMessageReader with "
					+ ENV_PROPERTIES_FILE);
			envMessageReader = new EnvMessageReader(ENV_PROPERTIES_FILE);
		}

		return envMessageReader;
	}
	
	/**
	 * @author jyogapa1
	 * @param keyName
	 * @param locale
	 * 
	 * @return
	 * @throws Exception
	 * @author jyogapa1
	 */
	public static String getAppProperty(
			ReloadableResourceBundleMessageSource appConstMessageSource,
			String keyName, Locale locale) {

		String propertyValue = null;

		propertyValue = appConstMessageSource.getMessage(keyName, null, locale);

		return propertyValue;

	}

	/**
	 * @author jyogapa1
	 * @param keyName
	 * @return
	 * @throws Exception
	 * @author jyogapa1
	 */
	public static String getAppProperty(
			ReloadableResourceBundleMessageSource appConstMessageSource,
			String keyName) {

		String propertyValue = null;

		propertyValue = getAppProperty(appConstMessageSource, keyName, null);

		return propertyValue;

	}
	
	/**
	 * 
	 * @author jyogapa1 (Jenith)
	 * 
	 * @param inputDateText
	 * @return
	 */
	public static String formatDateForNrgws(String inputDateText){
		
		if (StringUtils.isBlank(inputDateText)){
			return null;
		} else {
			return DateUtil.getFormattedDate(MM_dd_yyyy, MMddyyyy, inputDateText);
		}
	}
	
	/**
	 * method checks if the Token server returns a error message of successfully tokenized value
	 * @author Jasveen
	 * @param  returnToken
	 */
	public static boolean checkTokenDown(String returnToken)
	{
		
		if(StringUtils.isNumeric(returnToken.substring(returnToken.length()-4)))
		{
			logger.debug("Last 4 digits are numbers so the call was successful");
			return false;
		}
			
		return true;
	}
	
	/**
	 * Gets address line 1.
	 * 
	 * @param streetNum
	 *            Street number.
	 * @param streetName
	 *            Street name.
	 * 
	 * @return The addressLine1 as a concatenation of input arguments.
	 */
	public static String getAddressLine1(String streetNum, String streetName) {
		String addressLine1 = null;
		if (StringUtils.isNotEmpty(streetNum)) {
			addressLine1 = streetNum;
			if (StringUtils.isNotEmpty(streetName)) {
				addressLine1 = addressLine1 + " " + streetName;
			}
		} else if (StringUtils.isNotEmpty(streetName)) {
			addressLine1 = streetName;
		}
		return addressLine1;
	}
	
	
	/**
	 * concatenate complete address
	 */
	public static String getCompleteAddress(String aptNum,String streetNum, 
			String streetName,String city,String zipCode)
	{
		String completeAddress=null;
		String addressLine1=getAddressLine1(streetNum, streetName);
		if(StringUtils.isNotEmpty(aptNum))
		{
			completeAddress=addressLine1+ ", "+"#"+aptNum;}
		else 
		{
			completeAddress=addressLine1;}
		
		if(StringUtils.isNotEmpty(city))
		{
			completeAddress=completeAddress+", "+city;
		}
		completeAddress=completeAddress+", "+TX;
		if(StringUtils.isNotEmpty(zipCode))
			{completeAddress=completeAddress+", "+zipCode;}
		return completeAddress;
	}
	
	/**
	 * This method returns if the phone number or email id or ssn or tdl are in expected format
	 * @author Jasveen
	 * @param variableForValidation
	 * @param type
	 * @return boolean
	 */
	public static boolean isValidFormat(String variableForValidation,validationFormatEnum type )
	{
		if(type==validationFormatEnum.PHONE)
		{
			if(StringUtils.isNotBlank(variableForValidation)){
			if(variableForValidation.matches(PHONE_REGEX))
			return true;
			else 
				return false;
			}
		}
		else if (type==validationFormatEnum.EMAIL)
		{
			if(StringUtils.isNotBlank(variableForValidation)){
			if(variableForValidation.matches(EMAIL_REGEX)){
				logger.info("inside validationFormatEnum:: return true");
				return true;}
				else 
					return false;
			}
			
		}
		else if (type==validationFormatEnum.SSN)
		{
			if(variableForValidation.matches(SSN_REGEX)){
				logger.info("inside validationFormatEnum:: return true");
				return true;}
				else 
					return false;
		}
		else if (type==validationFormatEnum.TDL)
		{
			if(variableForValidation.matches(TDL_REGEX)){
				logger.info("inside validationFormatEnum:: return true");
				return true;}
				else 
					return false;
		}
		logger.info("inside validationFormatEnum:: returning false");
		return false;
		}

	
	
	/**
	 * This method returns if the date is in Valid Format
	 * @param date
	 * @return boolean
	 * @author Jasveen
	 */
	public static boolean isValidDate(String date)
	{
		boolean isValidDate= isValidDate(date, MMddyyyy);
		
		return isValidDate;
	}
	

	/**
	 * This method returns if the date is in Valid Format
	 * @param date
	 * @return boolean
	 * @author Jenith
	 */
	public static boolean isValidDate(String date, String format)
	{
		boolean isValidDate=false;
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		sdf.setLenient(false);
		try{
			logger.debug(sdf.parse(date));
			isValidDate=true;
		}
		catch(ParseException e){
			logger.error("inside isValidDate::parsing exception in date :: Exception is:: "+e.getMessage());
			isValidDate=false;
		}
		return isValidDate;
	}
	
	/**
	 * This method returns if the time is in Valid Format
	 * @param time
	 * @return boolean
	 * 
	 * @author Jenith
	 */
	public static boolean isValidTime(String time)
	{
		boolean isValidTime = isValidDate(time, TIME_FORMAT);
		
		return isValidTime;
	}
	
	/**
	 * This method returns languageCode based on the values found in request
	 * @author Jasveen Singh
	 * @param langCode
	 * @return Language Code
	 */
	public static String localeCode(String langCode)
	{
		if(StringUtils.isNotBlank(langCode) && langCode.equalsIgnoreCase(S))
			langCode=LOCALE_LANGUAGE_CODE_ES_US;
		else 
			langCode=LOCALE_LANGUAGE_CODE_EN_US;
		
		return langCode;
	}


	public static List<String> convertAsList(String delimiter,
			String delimitedText) {

		List<String> items = Arrays.asList(delimitedText.split(delimiter));

		return items;
	}

	public static String printAffiliateId(OESignupDTO oeSignUpDTO) {
		String affiliateIdDisplay = DOT + BLANK + "AffiliateId "
				+ oeSignUpDTO.getAffiliateId() + BLANK;
		
		return affiliateIdDisplay;
	}
	
	/**
	 * Checks size boundaries for the parameters list.
	 * 
	 * 
	 * @param sizeParamMap
	 * @return
	 * 
	 * @author jyogapa1
	 */
	public static HashMap<String, Object> checkSizeParam(Map<String, Object> sizeParamMap, Integer min, Integer max)
	{
		logger.info("inside Map<String, Object> checkSizeParam:: entering method");
		HashMap<String, Object> sizeParamChkResponse= new HashMap<String, Object>(); 
		ArrayList<Object> sizeParamMissing = new ArrayList<Object>();
		String errorDesc="";
		if(sizeParamMap!=null && ((sizeParamMap.size())>0)){
			String paramVal = null;
			for(String key:sizeParamMap.keySet() )
			{
				logger.debug("inside check size boundary param values and total values are::"+sizeParamMap.size());
				paramVal = (String) sizeParamMap.get(key);
				if(!(isValidSize(paramVal, min, max))){
					sizeParamMissing.add(key);
					if(StringUtils.isBlank(errorDesc)){
						errorDesc=key;
					}else{
						errorDesc+=", "+key;
					}
				}
				logger.debug("inside checkSizeParam:: "+sizeParamMissing.size());
			}
			if(sizeParamMissing!=null &&(sizeParamMissing.size()>0))
			{
				if (min == max){
					errorDesc="Length of following parameters must be " + min + " characters :: "+errorDesc;
				} else {
					errorDesc="Length of following parameters must be between " + min + " and " + max + " characters :: "+errorDesc;
				}
				sizeParamChkResponse.put("resultCode", RESULT_CODE_EXCEPTION_FAILURE);
				sizeParamChkResponse.put("errorDesc", errorDesc);
			}
			else
			{
				sizeParamChkResponse.put("resultCode", SUCCESS_CODE);
				sizeParamChkResponse.put("errorDesc", errorDesc);
			}
		}
		else
		{
			sizeParamChkResponse.put("resultCode", SUCCESS_CODE);
			sizeParamChkResponse.put("errorDesc", errorDesc);
		}
		logger.info("inside checkSizeParam:: response is :: "+ sizeParamChkResponse);
		return sizeParamChkResponse;
	}
	
	/**
	 * Validates size boundary and returns true of passed.
	 * 
	 * @param paramter
	 * @param min
	 * @param max
	 * @return
	 */
	private static Boolean isValidSize(String parameter, Integer min, Integer max) {

		Boolean isValid = BOOLEAN_TRUE;
		if (StringUtils.isNotBlank(parameter)) {
			Integer parameterLength = parameter.length();

			if (parameterLength >= min && parameterLength <= max) {
				isValid = BOOLEAN_TRUE;
			} else {
				// alert("Must be between 1 and 5")
				isValid = BOOLEAN_FALSE;
			}
		}
		return isValid;
	}
	
	/**
	 * Initializes privacy data parameters from constants.
	 * 
	 * @return
	 * 
	 * @author jyogapa1
	 * 
	 */
	private static HashSet<String> getPrivacyDataParams() {

		HashSet<String> values = new HashSet<String>();

		for (PRIVACY_DATA_PARAMETERS c : PRIVACY_DATA_PARAMETERS
				.values()) {
			values.add(c.name());
		}

		return values;
	}

	public static boolean hasPrivacyData(String parameterName) {

		return privacyDataParams.contains(parameterName);
	}
	
	/**
	 * Initializes log exclude method parameters
	 * 
	 * @return
	 * 
	 * @author jyogapa1
	 * 
	 */
	private static HashSet<String> getLogExludeMethods() {

		HashSet<String> values = new HashSet<String>();

		for (LOG_EXCLUDE_RESPONSE_METHODS c : LOG_EXCLUDE_RESPONSE_METHODS
				.values()) {
			values.add(c.name());
		}

		return values;
	}

	public static boolean shouldExcludeResponseLog(String parameterName) {

		return logExcludeResponseMethodList.contains(parameterName);
	}
	
	/**
	 * Method Returns Current Datee Formatted.
	 * @param pattern String
	 * @return String
	 */
	public static String getCurrentDateFormatted(String pattern){
		Calendar today = Calendar.getInstance();  

		return getFormatedDate(today.getTime(), pattern) ;
	}
	
	/**
	 * Method getFormatedDate.
	 * @param date Date
	 * @param pattern String
	 * @return String
	 */
	public static String getFormatedDate(Date date, String pattern) {

		String formatedDateStr = null;

		try{

			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			formatedDateStr = sdf.format(date);
		}
		catch(Exception e) {

			throw new RuntimeException("getFormatedDate() :: format date ('" + date +"')  with the pattern ('"+ pattern +"') Failed: cause: " +e.getCause(), e);
		}
		return formatedDateStr;
	}
	
	/**
	 * Gets company name based on company code and brand ID.
	 * 
	 * @param brandId
	 *            Brand ID.
	 * @param companyCode
	 *            Company code.
	 * @return Company name.
	 */
	public static String getCompanyName(String brandId, String companyCode) {
		String companyName = StringUtils.EMPTY;
		if (COMPANY_CODE_RELIANT.equalsIgnoreCase(companyCode)
				|| BRAND_ID_RELIANT.equalsIgnoreCase(brandId)) {
			companyName = COMPANY_NAME_RELIANT;
		} else if (COMPANY_CODE_GME.equalsIgnoreCase(companyCode)
				|| BRAND_ID_GME.equalsIgnoreCase(brandId)) {
			companyName = COMPANY_NAME_GME;
		} else if (COMPANY_CODE_CIRRO.equalsIgnoreCase(companyCode)
				&& BRAND_ID_CIRRO.equalsIgnoreCase(brandId)) {
			companyName = COMPANY_NAME_CIRRO;
		} else if (COMPANY_CODE_PENNYWISE.equalsIgnoreCase(companyCode)
				&& (StringUtils.isEmpty(brandId) || BRAND_ID_PENNYWISE
						.equalsIgnoreCase(brandId))) {
			companyName = COMPANY_NAME_PENNYWISE;
		} else if (BRAND_ID_EE.equalsIgnoreCase(brandId)
				|| COMPANY_CODE_EE.equalsIgnoreCase(companyCode)) {
			companyName = COMPANY_NAME_EE;
		}
		return companyName;
	}

	/**
	 * Gets brand name based on company code and brand ID.
	 * 
	 * @param brandId
	 *            Brand ID.
	 * @param companyCode
	 *            Company code.
	 * @return Brand name.
	 */
	public static String getBrandName(String brandId, String companyCode) {
		String brandName = StringUtils.EMPTY;
		if (BRAND_ID_RELIANT.equalsIgnoreCase(brandId)
				|| COMPANY_CODE_RELIANT.equalsIgnoreCase(companyCode)) {
			brandName = BRAND_NAME_RELIANT;
		} else if (BRAND_ID_GME.equalsIgnoreCase(brandId)
				|| COMPANY_CODE_GME.equalsIgnoreCase(companyCode)) {
			brandName = BRAND_NAME_GME;
		} else if (BRAND_ID_CIRRO.equalsIgnoreCase(brandId)
				&& COMPANY_CODE_CIRRO.equalsIgnoreCase(companyCode)) {
			brandName = BRAND_NAME_CIRRO;
		} else if ((StringUtils.isEmpty(brandId) || BRAND_ID_PENNYWISE
				.equalsIgnoreCase(brandId))
				&& COMPANY_CODE_PENNYWISE.equalsIgnoreCase(companyCode)) {
			brandName = BRAND_NAME_PENNYWISE;
		} else if (BRAND_ID_EE.equalsIgnoreCase(brandId)
				|| COMPANY_CODE_EE.equalsIgnoreCase(companyCode)) {
			brandName = BRAND_NAME_EE;
		}
		return brandName;
	}
	
	
	/**
	 * Gets brand name based on company code and brand ID.
	 * 
	 * @param brandId
	 *            Brand ID.
	 * @param companyCode
	 *            Company code.
	 * @return altBrandID
	 * 			  Alternate Brand ID
	 */
	public static String getAltBrandID(String brandId, String companyCode) {
		String altBrandID = StringUtils.EMPTY;
		if (BRAND_ID_RELIANT.equalsIgnoreCase(brandId)
				|| COMPANY_CODE_RELIANT.equalsIgnoreCase(companyCode)) {
			altBrandID = ALT_BRAND_ID_RELIANT;
		} else if (BRAND_ID_GME.equalsIgnoreCase(brandId)
				|| COMPANY_CODE_GME.equalsIgnoreCase(companyCode)) {
			altBrandID = ALT_BRAND_ID_GME;
		} else if (BRAND_ID_CIRRO.equalsIgnoreCase(brandId)
				&& COMPANY_CODE_CIRRO.equalsIgnoreCase(companyCode)) {
			altBrandID = ALT_BRAND_ID_PENNYWISE;
		} else if ((StringUtils.isEmpty(brandId) || BRAND_ID_PENNYWISE
				.equalsIgnoreCase(brandId))
				&& COMPANY_CODE_PENNYWISE.equalsIgnoreCase(companyCode)) {
			altBrandID = ALT_BRAND_ID_CIRRO;
		} else if (BRAND_ID_EE.equalsIgnoreCase(brandId)
				|| COMPANY_CODE_EE.equalsIgnoreCase(companyCode)) {
			altBrandID = ALT_BRAND_ID_EE;
		}
		return altBrandID;
	}
	/**
	 * Gets the message code.
	 * 
	 * @param constraintDescriptorMap
	 *            Constraint Descriptor Map containing all the configured
	 *            constraint attributes.
	 * @return Message code value from the underlying
	 *         <code>javax.validation.metadata.ConstraintDescriptor</code>.
	 */
	public static String getMessageCode(
			final Map<String, Object> constraintDescriptorMap) {
		String messageCode = StringUtils.EMPTY;
		if (constraintDescriptorMap != null
				&& !constraintDescriptorMap.isEmpty()
				&& constraintDescriptorMap
						.containsKey(Constants.CONSTRIANT_ATTR_MESSAGE_CODE)) {
			Object messageCodeValObj = constraintDescriptorMap
					.get(Constants.CONSTRIANT_ATTR_MESSAGE_CODE);
			if (messageCodeValObj != null
					&& messageCodeValObj instanceof String) {
				String messageCodeValue = (String) messageCodeValObj;
				if (StringUtils.isNotEmpty(messageCodeValue)) {
					messageCode = messageCodeValue;
				}
			}
		}
		return messageCode;
	}
	
	/**
	 * Gets the message code text.
	 * 
	 * @param constraintDescriptorMap
	 *            Constraint Descriptor Map containing all the configured
	 *            constraint attributes.
	 * @return Message code text value from the underlying
	 *         <code>javax.validation.metadata.ConstraintDescriptor</code>.
	 */
	public static String getMessageCodeText(
			final Map<String, Object> constraintDescriptorMap) {
		String messageCodeText = StringUtils.EMPTY;
		if (constraintDescriptorMap != null
				&& !constraintDescriptorMap.isEmpty()
				&& constraintDescriptorMap
						.containsKey(Constants.CONSTRIANT_ATTR_MESSAGE_CODE_TEXT)) {
			Object messageCodeTxtValObj = constraintDescriptorMap
					.get(Constants.CONSTRIANT_ATTR_MESSAGE_CODE_TEXT);
			if (messageCodeTxtValObj != null
					&& messageCodeTxtValObj instanceof String) {
				String messageCodeTxtValue = (String) messageCodeTxtValObj;
				if (StringUtils.isNotEmpty(messageCodeTxtValue)) {
					messageCodeText = messageCodeTxtValue;
				}
			}
		}
		return messageCodeText;
	}
	
	public static String stringToDecimalFormat(String value) {
		if (value == "" || value == null) {
			value ="0";
		}
		System.out.println("String" + value);
		BigDecimal big = new BigDecimal(value, new MathContext(20,
				RoundingMode.HALF_UP));
		return String.format("%.2f", big);
	}
	
	/**
	 * This method validates UserIdRequest
	 * companycode not blank
	 * brandname not blank
	 * contractaccountnumber not blank 
	 *   and size not greater than 12
	 * @param request
	 * @return
	 */
	
	public static UserIdResponse validateUserIdRequest(UserIdRequest request){
		
		UserIdResponse response = new UserIdResponse();
		boolean isValidRequest = true;
		String contractAccountNumber = request.getContractaccountnumber();
		StringBuffer strBuffer = new StringBuffer("Request Entity has following errors: ");
		List<String> errorMsgList = new ArrayList<String>();
		
		if(StringUtils.isBlank(request.getCompanycode())){
		    errorMsgList.add("companycode may not be empty,");
		}
		
		if(StringUtils.isBlank(request.getBrandname())){
			errorMsgList.add("brandname may not be empty,");
		}
		
		if(StringUtils.isBlank(contractAccountNumber)){
			errorMsgList.add("contractaccountnumber may not be empty,");
		}
		
		if(StringUtils.isNotBlank(contractAccountNumber) && contractAccountNumber.length()>12){
			errorMsgList.add("contractaccountnumber may not be more than 12 digits,");
		}
		
		if(StringUtils.isNotBlank(request.getCompanycode()) && request.getCompanycode().length()<4){
			errorMsgList.add("companycode may not be less than 4 digits,");
		}
		
		if(errorMsgList.size()>0)
			isValidRequest=false;
	    
		if(!isValidRequest){
			response.setResultcode(Constants.ONE);
			
			for(String errorMsg:errorMsgList){
				strBuffer.append(errorMsg);
			}
			
			strBuffer.deleteCharAt(strBuffer.length()-1);
			response.setResultdescription(strBuffer.toString());
		}
		
		return response;
	}
	
	/**
	 * This method validates WebHookRequest
	 * paymentId not blank
	 * accountNumber not blank
	 * accountId not blank
	 * CA not blank and size not greater than 12
	 * @param request
	 * @return
	 */
	
	public static WebHookResponse validateWebHookRequest(WebHookRequest request){
		
		WebHookResponse response = new WebHookResponse();
		boolean isValidRequest = true;
		String contractAccountNumber = request.getWebHookMetadata().getExternalAccountId();
		StringBuffer strBuffer = new StringBuffer("Request Entity has following errors: ");
		List<String> errorMsgList = new ArrayList<>();
		
		if(StringUtils.isBlank(request.getPaymentId())){
		    errorMsgList.add("payment id may not be empty,");
		}
		
		if(StringUtils.isBlank(request.getAccountNumber())){
			errorMsgList.add("bar code number may not be empty,");
		}
		
		if(StringUtils.isBlank(contractAccountNumber)){
			errorMsgList.add("contractaccountnumber may not be empty,");
		}
		
		if(StringUtils.isBlank(request.getAccountId())){
			errorMsgList.add("VD account id may not be empty,");
		}
		
		if(!errorMsgList.isEmpty()) {
			isValidRequest=false;
		}
	    
		if(!isValidRequest){
			response.setResultcode(Constants.ONE);
			
			for(String errorMsg:errorMsgList){
				strBuffer.append(errorMsg);
			}
			
			strBuffer.deleteCharAt(strBuffer.length()-1);
			response.setResultdescription(strBuffer.toString());
		}
		
		return response;
	}
	
	/**
	 * This method takes an input string and capitalize first character of every word and rest of each word in lowercase
	 * @param inputString
	 * @return
	 */
	public static String capitalizeAllWords(String inputString){
		
		String outputString = null;
		StringBuilder sb = new StringBuilder();
		StringTokenizer tk = new StringTokenizer(inputString);
		while(tk.hasMoreTokens()){
			
			String temp = tk.nextToken().toString();
			//System.out.println(StringUtils.capitalize(temp.toLowerCase()));
			sb.append(StringUtils.capitalize(temp.toLowerCase())+ " ");
		}
		//System.out.print(sb.toString().trim());
		outputString = sb.toString().trim();
		return outputString;
	}
	
	
	public static String getErrorJson(Exception ex, String errorCode){
		
		ErrorDTO error = new ErrorDTO();
		error.setErrorMessage(ex.getMessage());
		error.setErrorCode(errorCode);
		Gson gson = new Gson();
		return gson.toJson(error);
		
	}
	
	public static String get(int len) {
		StringBuffer out = new StringBuffer();

		while (out.length() < len) {
			int idx = Math.abs((rand.nextInt() % alphanumeric.length));
			out.append(alphanumeric[idx]);
		}
		return out.toString();
	}
	
	// create alphanumeric char array
		static char[] alphanumeric() {
			StringBuffer buf = new StringBuffer(250);
			for (int i = 48; i <= 57; i++)
				buf.append((char) i); // 0-9
			for (int i = 65; i <= 90; i++)
				buf.append((char) i); // A-Z
			for (int i = 97; i <= 122; i++)
				buf.append((char) i); // a-z
			return buf.toString().toCharArray();
		}
	
	/**
	 * Returns formatted account number with removed zeroes from front
	 * 
	 * @author Cuppala
	 * 
	 * @param number
	 * @param length
	 * @return
	 */
	public static String stripLeadingZeros(String arg) {
		String accountNumber = arg;
		int digitCnt = 0;
		int argLength = arg.length();

		for (int i = 0; i < argLength; i++) {
			char charTemp = (char) accountNumber.charAt(i);
			if (charTemp != '0') {
				break;
			} else {
				digitCnt++;
			}
		}
		if (digitCnt > 0 && accountNumber.length() > digitCnt) {
			accountNumber = accountNumber.substring(digitCnt,
					accountNumber.length());
		}
		return accountNumber;
	}
	

	public static HashMap<String, Object> checkNegaviteValueInParam(Map<String, Object> paramMap)
	{
		logger.info("inside checkMandatoryParam:: entering method");
		HashMap<String, Object> negativeParamChkResponse= new HashMap<String, Object>(); 
		ArrayList<Object> negativeParam = new ArrayList<Object>();
		String errorDesc="";
		if(paramMap!=null && ((paramMap.size())>0)){
			for(String key:paramMap.keySet() )
			{
				logger.debug("inside negative param values and total values are::"+paramMap.size());
				String value = (String) paramMap.get(key);
				double doublValue = 0.0;
				if(StringUtils.isNotEmpty(value)){					
					doublValue = Double.parseDouble(value);
				}
				if(doublValue<0){
					negativeParam.add(key);
					if(StringUtils.isBlank(errorDesc)){
						errorDesc=key;
					}else{
						errorDesc+=", "+key;
					}
				}
				logger.debug("inside checkMandatoryParam:: "+negativeParam.size());
			}
			if(negativeParam!=null &&(negativeParam.size()>0))
			{
				errorDesc="Negative Value for Parameters :: "+errorDesc;
				negativeParamChkResponse.put("resultCode", RESULT_CODE_EXCEPTION_FAILURE);
				negativeParamChkResponse.put("errorDesc", errorDesc);
			}
			else
			{
				negativeParamChkResponse.put("resultCode", SUCCESS_CODE);
				negativeParamChkResponse.put("errorDesc", errorDesc);
			}
		}
		else
		{
			negativeParamChkResponse.put("resultCode", SUCCESS_CODE);
			negativeParamChkResponse.put("errorDesc", errorDesc);
		}
		logger.info("inside checkNegativeParam:: response is :: "+negativeParamChkResponse);
		return negativeParamChkResponse;
	}
	
	public static  JsonObject getJsonObject(String restContentJson) {
		com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
		JsonObject json = (JsonObject) parser.parse(restContentJson);
		return json;
	}
	
		
	public static String getJsonValue(JsonObject JsonObject, String strKey) {
		String strRetVal = EMPTY;
		try {
			JsonElement  strJsonElement = JsonObject.get(strKey);
			if(strJsonElement != null 
					&& StringUtils.isNotBlank(strJsonElement.toString())) {
				return strJsonElement.toString();
			}

		} catch (Exception e) {
			logger.error("getJsonValueFailed", e);
		}
		
		return strRetVal;
	}
	
	public static String runRegex(String input, String pattern, String replace) {
		
		if(StringUtils.isNotBlank(input)) {
			return input.replaceAll(pattern,"");
		}
		
		return input;
	}
	
	public static boolean checkInactiveAccount(String companyCode, String moveOutDate) {
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		boolean isActive = false;
		try {
			Date currentDate = dateFormat.parse(dateFormat.format(Calendar.getInstance().getTime()));
			if (StringUtils.isNotBlank(moveOutDate)
					&& COMPANY_CODE_GME.equalsIgnoreCase(companyCode)) {
				if (dateFormat.parse(moveOutDate).before(currentDate)) {
					isActive= true;
				}
				
			}
		} catch (Exception e) {

			logger.warn("checkInactiveAccount date parsing failed:", e);
		}
		return isActive;
	}	
	public static String getCurrentDateandTime() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
		Date date = new Date();
		return dateFormat.format(date); //05/07/2019 06:53:11 PM
	}
	public static String getRequestParameter(HttpServletRequest request, String in_ParameterName)
    {
        if (request.getParameter(in_ParameterName) != null)
        {
            return request.getParameter(in_ParameterName);
        }
        
        return StringUtils.EMPTY;
 }
	public static String getBrandIdFromCompanycodeForCCS(String companyCode, String brandId){
		String brandName = EMPTY;
		
		switch(companyCode){
			case COMPANY_CODE_RELIANT:
					brandName = BRAND_ID_RELIANT;
					break;
			case COMPANY_CODE_GME:
					brandName = CCS_BRAND_ID_GME;
					break;
			case COMPANY_CODE_PENNYWISE:
					brandName = (StringUtils.equalsIgnoreCase(brandId, BRAND_ID_CIRRO) ? BRAND_ID_CIRRO: BRAND_ID_PENNYWISE);
					break;
			default:
				break;
		}
		
		return brandName;
	}	

	public static String getBrandIdFromCompanycodeForTogglz(String companyCode, String brandId){
		String brandName = EMPTY;
		
		switch(companyCode){
			case COMPANY_CODE_RELIANT:
					brandName = BRAND_ID_RELIANT;
					break;
			case COMPANY_CODE_GME:
					brandName = BRAND_ID_GME;
					break;
			case COMPANY_CODE_DISCOUNTPOWER:
					brandName = (StringUtils.equalsIgnoreCase(brandId, BRAND_ID_CIRRO) ? BRAND_ID_CIRRO: BRAND_ID_DISCOUNTPOWER);
					break;
			default:
				break;
		}
		
		return brandName;
	}
	
	public static String getChannelTypeForTogglz(String channelType){
		String channel = CHANNEL_AFF;
		if(channelType == null){
			channelType = StringUtils.EMPTY;
		}
		switch(channelType){
			case CHANNEL_AA:
				channel = CHANNEL_AA;
					break;
			case CHANNEL_WEB:
				channel = CHANNEL_WEB;
					break;			
			case CHANNEL_WEB_CLOUD:
				channel = CHANNEL_WEB_CLOUD;
					break;			
			default:
				break;
		}
		
		return channel;
	}


	public static String removeHTMLTags(String contentMsg)
	  {
	    String updatedContentMsg = "";

	    if (StringUtils.isNotBlank(contentMsg))
	    {
	      updatedContentMsg = contentMsg.replaceAll("<[^>]*>", "");
	    }
	    return updatedContentMsg;
	  }
	
	/**
	 * Returns Street Number from given full Street Address
	 * 
	 * @param fullStreetAdress
	 * @return String - Street Name from given full Street Address
	 * 
	 * */
	public static String stripStreetNum(String fullStreetAdress) {
		if (null != fullStreetAdress) {
			return RegExUtils.replacePattern(fullStreetAdress,"\\s*([0-9]*)\\s*.*", "$1");
		} else {
			return EMPTY;
		}
	}

	/**
	 * Returns Street Name from given full Street Address
	 * 
	 * @param fullStreetAdress
	 * @return String - Street Name from given full Street Address
	 */
	public static String stripStreetName(String fullStreetAdress) {
		if (null != fullStreetAdress) {
			return RegExUtils.replacePattern(fullStreetAdress,"\\s*[0-9]*\\s*(.*)", "$1");
		} else {
			return EMPTY;
		}
	}
	
	public static String getPipeSeperatedCallExecutedParamForDB(String currentApiCall, String callExecutedFromDB){
		String callExecutedStrForDB = StringUtils.EMPTY;
		String[] callExecutedArr = null;
		List<String> callExecutedList = new ArrayList<>();
		try{
			if(StringUtils.isNotBlank(callExecutedFromDB))
				callExecutedArr = callExecutedFromDB.split(ERROR_CD_LIST_SPLIT_PATTERN);
			callExecutedList = new ArrayList<String>(Arrays.asList(callExecutedArr));
			callExecutedList.add(currentApiCall);
			callExecutedStrForDB = StringUtils.join(callExecutedList,SYMBOL_PIPE);
			if(StringUtils.isNotBlank(callExecutedStrForDB) && callExecutedStrForDB.getBytes(Charsets.UTF_8).length>=255){
				callExecutedStrForDB = callExecutedStrForDB.substring(0, 254);
			}
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		return callExecutedStrForDB;
	}
	
	public static LinkedHashSet<String> getSetFromPipeSeparatedString(String pipeSeparatedStringValue){
		LinkedHashSet<String> valueSet = null;
		if(StringUtils.isNotBlank(pipeSeparatedStringValue)){
			String[] valueArray =pipeSeparatedStringValue.split(ERROR_CD_LIST_SPLIT_PATTERN);
			valueSet = new LinkedHashSet<>(Arrays.asList(valueArray));
		} else {
			valueSet = new LinkedHashSet<>();
		}
		return valueSet;		
	}
	
	public static boolean isValidCompanyCode(String value) {
		List<String> companyCodeList = Arrays.asList(COMPANY_CODE_ARRAY);
		if(value==null) {
			return false;
		}else {
			return companyCodeList.contains(value.trim());
		}
	}
	
	public static String getTrackingIdFromResponse(Response response){
		String trackingId = EMPTY;
		List trackingIdList = response.getMetadata().get(CONST_TRACKING_ID);
		if(trackingIdList != null && trackingIdList.size()>0){
			trackingId = (String) trackingIdList.get(0);
		}
		return trackingId;
	}
	
	  public static String removeHTMLComment(String contentMsg)
	  {
	    String updatedContentMsg = "";

	    if (StringUtils.isNotBlank(contentMsg))
	    {
	      updatedContentMsg = contentMsg.replaceAll("(?=<!--)([\\s\\S]*?)(-->)", "");
	    }

	    return updatedContentMsg;
	  }
		public static String extractHtmlCSSUsingRegexPattern(String holdStr){
			String htmlCommentPattern = "(?=<!--)([\\s\\S]*?)(-->)";
			String cssPatternRegex = "#"+".*"+"}";
			String multipleSpacePatternRegex = "\\t";
			//String htmlTagPatternRegex ="\\<.*?>";
			String htmlContentPatternRegex = "<[^>]*>";
			String output="";
			output =getRegexPattern(htmlCommentPattern).matcher(holdStr).replaceAll("");
			output =getRegexPattern(cssPatternRegex).matcher(output).replaceAll("");
			output =getRegexPattern(multipleSpacePatternRegex).matcher(output).replaceAll("");
			//output =getRegexPattern(htmlTagPatternRegex).matcher(output).replaceAll("");
			output =getRegexPattern(htmlContentPatternRegex).matcher(output).replaceAll("");
			return output;
		}
		
		public static Pattern getRegexPattern(String regex){
			Pattern p = Pattern.compile(regex);
			return p;
		}
		
		public static String getGuIdFromResponse(Response response){
			String guId = EMPTY;
			List guIdList = response.getMetadata().get(CONST_GUID);
			if(guIdList != null && guIdList.size()>0){
				guId = (String) guIdList.get(0);
			}
			return guId;
		}
		public static ByteArrayOutputStream getInvoiceTimeOutException(String customErrorMessage) throws DocumentException, IOException {
			logger.debug("Entering getInvoiceException..");
	
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Document document = new Document();
			PdfWriter.getInstance(document, baos);
			document.open();
			Paragraph paragraph = new Paragraph(customErrorMessage, new Font(FontFamily.HELVETICA, 15, Font.BOLD));
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);
			document.close();
			//toRet = baos.toByteArray();
			logger.debug("Exiting getInvoiceException..");
			return baos;
		} 

	public static <T> Object unmarshallSoapFault(String response, Class<T> responseClass)
			throws IOException, SOAPException, JAXBException {

		InputStream targetStream = new ByteArrayInputStream(response.getBytes());

		JAXBContext JAXBContext = javax.xml.bind.JAXBContext.newInstance(responseClass.getPackage().getName());
		Unmarshaller unmarshaller = JAXBContext.createUnmarshaller();

		JAXBElement<T> document = (JAXBElement<T>) unmarshaller.unmarshal(targetStream);

		return document.getValue();

	}
	public static String getTagValue(String xml, String tagName){
	    return xml.split("<"+tagName+">")[1].split("</"+tagName+">")[0];
	}	
	
	public static Map<String, String> getAdopeValueMap(String accountNumber, String messageId, String contractId,
			String bpNumber, String osType, String templateReportsuite, String errorMessage, String strSource, String messageIdMsg) {
		Map<String, String> linkedHashMap = new LinkedHashMap<String, String>();

		linkedHashMap.put(PARAMETER_VARIABLE_REPORTSUITE, templateReportsuite);
		linkedHashMap.put(PARAMETER_VARIABLE_BRAND, BRAND_NAME);
		linkedHashMap.put(PARAMETER_VARIABLE_CANUMBER, accountNumber);
		linkedHashMap.put(PARAMETER_VARIABLE_COMPANYCODE, COMPANY_CODE_GME);
		linkedHashMap.put(PARAMETER_VARIABLE_MSGID, messageId);
		linkedHashMap.put(PARAMETER_VARIABLE_ACTIONDATE, CommonUtil.getCurrentDateFormatted(CURRENT_DATE_FMT));
		if(StringUtils.contains(messageId,"$")) {
			String [] messageIdAr = messageId.split("\\$");
			if(messageIdAr.length > 0) {
				String date = CommonUtil.changeDateFormat(messageIdAr[1], "yyyyMMdd",
							   "MM/dd/yyyy");
				messageIdMsg = StringUtils.replace(messageIdMsg, "[date]", date);
				String tempString = messageIdAr[0] + "_" + messageIdMsg;
				messageId = tempString;
			}
			
		}
		
		if (StringUtils.equalsIgnoreCase(GET_PLAN_OFFER, strSource)) {
			linkedHashMap.put(PARAMETER_VARIABLE_MESSAGETYPE, PLAN_OFFER_MESSAGE_TYPE);
			linkedHashMap.put(PARAMETER_VARIABLE_MESSAGECAT, PLAN_OFFER_FUNCTION);
			
			if (!StringUtils.isNotBlank(errorMessage)) {
				linkedHashMap.put(PARAMETER_VARIABLE_MESSAGESTATUS, GET_PLAN_OFFER);
				linkedHashMap.put(PARAMETER_VARIABLE_ERRORMESSAGE, errorMessage);
				linkedHashMap.put(PARAMETER_VARIABLE_MESSAGE, "User Retrieved Plan Offer");
				linkedHashMap.put(PARAMETER_VARIABLE_MSGINSTANCE, messageId);

			} else {
				linkedHashMap.put(PARAMETER_VARIABLE_MESSAGESTATUS, GET_PLAN_OFFER_FAIL);
				linkedHashMap.put(PARAMETER_VARIABLE_ERRORMESSAGE, errorMessage);
				linkedHashMap.put(PARAMETER_VARIABLE_MESSAGE, errorMessage);
				linkedHashMap.put(PARAMETER_VARIABLE_MSGINSTANCE, messageId);
			}
		} else {
			linkedHashMap.put(PARAMETER_VARIABLE_MESSAGETYPE, ADOBE_MESSAGE_TYPE);
			linkedHashMap.put(PARAMETER_VARIABLE_MESSAGECAT, ADOBE_MESSAGE_FUNCTION);
			if (!StringUtils.isNotBlank(errorMessage)) {
				linkedHashMap.put(PARAMETER_VARIABLE_MESSAGESTATUS, SWAP_SUBMIT_SUCESS);
				linkedHashMap.put(PARAMETER_VARIABLE_ERRORMESSAGE, errorMessage);
				linkedHashMap.put(PARAMETER_VARIABLE_MESSAGE, SWAP_SUBMIT_SUCESS);
				linkedHashMap.put(PARAMETER_VARIABLE_MSGINSTANCE, messageId);
			} else {
				linkedHashMap.put(PARAMETER_VARIABLE_MESSAGESTATUS, SWAP_SUBMIT_FAIL);
				linkedHashMap.put(PARAMETER_VARIABLE_ERRORMESSAGE, errorMessage);
				linkedHashMap.put(PARAMETER_VARIABLE_MESSAGE, errorMessage);
				linkedHashMap.put(PARAMETER_VARIABLE_MSGINSTANCE, messageId);
			}
		}

		linkedHashMap.put(PARAMETER_VARIABLE_LANGUAGE, LANGUAGE_CODE_EN);
		linkedHashMap.put(PARAMETER_VARIABLE_OSTYPE, osType);
		linkedHashMap.put(PARAMETER_VARIABLE_CONTRACTID, contractId);
		linkedHashMap.put(PARAMETER_VARIABLE_BPNUMBER, bpNumber);

		return linkedHashMap;
	}
	
	public static String substituteVariables(String template, Map<String, String> variables) {
		Pattern pattern = Pattern.compile("\\$\\{|\\[(.+?)\\]|\\}");
		Matcher matcher = pattern.matcher(template);
		StringBuffer buffer = new StringBuffer();
		while (matcher.find()) {
			if (variables.containsKey(matcher.group(1))) {
				// System.out.println("Key"+matcher.group(1));

				String replacement = variables.get(matcher.group(1));
				// System.out.println("Key"+replacement);
				// quote to work properly with $ and {,} signs
				matcher.appendReplacement(buffer, replacement != null ? Matcher.quoteReplacement(replacement) : "null");
			}
		}
		matcher.appendTail(buffer);
		return buffer.toString();
	}

	public static String encodeValue(String value) {
		try {
			return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException ex) {
			logger.error("Error occured in converting encodeValue for the url {}- Exception Cause {}", value,
					ex.getStackTrace());
		}

		return null;
	}

	public static String getPostRequestDetails(Object serviceReqJSONBody, String serviceURL) {
		Gson gson = new Gson();
		Map<String, String> serviceHttpHeader = new HashMap<>();
		Map<String, String> serviceReqParameters = new HashMap<String, String>();
		Map<String, Object> ServiceDetailRequest = new LinkedHashMap<String, Object>();
		ServiceDetailRequest.put("serviceUrl", serviceURL);
		ServiceDetailRequest.put("httpMethod", Constants.HTTP_METHOD_GET);
		// System.out.println(gson.toJson(serviceHttpHeader).toString());
		ServiceDetailRequest.put("serviceHttpHeader", serviceHttpHeader);
		ServiceDetailRequest.put("serviceReqParameters", serviceReqParameters);
		ServiceDetailRequest.put("serviceReqJSONBody", serviceReqJSONBody);
		// System.out.println(gson.toJson(ServiceDetailRequest).toString());
		return gson.toJson(ServiceDetailRequest);
	}

	public static HttpEntity<String> getHttpEntity(Object inputJson, String serviceURL) {
		HttpEntity<String> entity = null;
		Map<String, String> header = new HashMap<String, String>();
		header.put(HEADER_CONTENT_TYPE_KEY, MediaType.APPLICATION_JSON_VALUE);
		header.put("Accept", MediaType.APPLICATION_JSON_VALUE);

		HttpHeaders httpHeaders = CommonUtil.generateHttpHeadersFromRequest(header);
		entity = new HttpEntity<String>(getPostRequestDetails(inputJson, serviceURL), httpHeaders);

		return entity;
	}

	public static HttpHeaders generateHttpHeadersFromRequest(Map<String, String> requestParams) {
		HttpHeaders headers = new HttpHeaders();

		if (requestParams.entrySet() != null) {
			for (Map.Entry<String, ? extends Object> entry : requestParams.entrySet()) {
				headers.add(entry.getKey(), requestParams.get(entry.getKey()));
			}
		}
		return headers;
	}

	public static String getDynamicEflUrl(String docId,String smartCode) {
		StringBuilder eflUrlBuilder = new StringBuilder();
		if(!StringUtils.isEmpty(docId)) {
			eflUrlBuilder.append(Constants.DOCID_URL_SUB_STR)
			.append(docId)
			.append(Constants.CONST_DOT_PDF);
			//.append("?project=DYN_EFL");
		}else if(!StringUtils.isEmpty(smartCode)){
			eflUrlBuilder.append(Constants.SMARTCODE_URL_SUB_STR)
			.append(smartCode)
			.append(Constants.CONST_DOT_PDF);
		}
		String eflUrl = eflUrlBuilder.toString();
		logger.debug("Efl URL returned from getDynamicEflUrl is  "+eflUrl);
		return eflUrl;
	}
	
	
	/**
	 * Return {@code true} if the supplied Collection is {@code null}
	 * or empty. Otherwise, return {@code false}.
	 * @param collection the Collection to check
	 * @return whether the given Collection is empty
	 */
	public static boolean isCollectionNullOrEmpty(final Collection<?> collection) {
		return (collection == null || collection.isEmpty());
	}

	/**
	 * Return {@code true} if the supplied Map is {@code null}
	 * or empty. Otherwise, return {@code false}.
	 * @param map the Map to check
	 * @return whether the given Map is empty
	 */
	public static boolean isMapNullOrEmpty(final Map<?,?> map) {
		return (map == null || map.isEmpty());
	}	
}