package com.multibrand.resources.provider.entity;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.metadata.ConstraintDescriptor;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.dto.request.FormEntityRequest;
import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.DuplicateRecordGroupConstraint;
import com.multibrand.request.validation.ErrorMessageDescriptor;
import com.multibrand.request.validation.FormatConstraint;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.web.i18n.WebI18nMessageSource;

/**
 * Reads HTTP request message body and then translates and maps request
 * parameters to entities of type <code>FormEntityRequest</code>.
 * 
 * <p>
 * <strong>IMPORTANT: </strong> the message body reader will only work for REST
 * requests of type <code>FormEntityRequest</code> and consumes
 * &quot;application/x-www-form-urlencoded&quot; or &quot;application/json&quot;.
 * </p>
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.0 and Jersey 1.17
 */
@Component
@Provider
@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
public class FormEntityRequestMessageBodyReader implements
		MessageBodyReader<FormEntityRequest> {

	@Autowired(required = true)
	private HttpServletRequest request;

	@Autowired
	private Validator validator;

	@Resource(name = "webI18nMessageSource")
	protected WebI18nMessageSource msgSource;

	private static final Logger LOGGER = LogManager
			.getLogger(FormEntityRequestMessageBodyReader.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		LOGGER.debug("Content-Type = " + mediaType);
		
		return (FormEntityRequest.class.isAssignableFrom(type) && (MediaType.APPLICATION_FORM_URLENCODED
				.equalsIgnoreCase(mediaType.toString()) || MediaType.APPLICATION_JSON
				.equalsIgnoreCase(mediaType.toString())));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FormEntityRequest readFrom(Class<FormEntityRequest> type,
			Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		
		Map<String, String> parameterMap = null;
		FormEntityRequest request = null;

		// Get contents/body of the input
		String queryString = IOUtils.toString(entityStream);
		LOGGER.debug("QueryString = " + queryString);

		try {
			// FormEntityRequest 
			request = type.newInstance();

			// Build the parameters from the content based on the content-type
			parameterMap = this.getParametersByContentType(
					mediaType.toString(), queryString);

			// Bind the request parameters in the FormEntityRequest object
			BeanUtils.copyProperties(request, parameterMap);

			LOGGER.debug("RequestObject = " + request);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// Bean validation framework for input validations. Added by Jenith on 05/07/2015
		this.addValidationSupport(parameterMap, request, annotations);

		return request;
	}
	
	/**
	 * Gets URI parameters by content type
	 * 
	 * @param contentType
	 * @param content
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * 
	 * @author jyogapa1 (Jenith)
	 */
	@SuppressWarnings("unchecked")
	private Map<String, String> getParametersByContentType(
			String contentType, String content) throws JsonParseException,
			JsonMappingException, IOException {
		
		Map<String, String> parameterMap = null;
		
		if (StringUtils.equalsIgnoreCase(MediaType.APPLICATION_JSON,
				contentType)) {
			ObjectMapper mapper = new ObjectMapper();
			
			parameterMap = mapper.readValue(content, Map.class);

		} else if (StringUtils.equalsIgnoreCase(
				MediaType.APPLICATION_FORM_URLENCODED, contentType)) {
			
			parameterMap = CommonUtil.getUrlParameters(content);
		
		}

		LOGGER.debug("Parameters Map built is = " + parameterMap);

		return parameterMap;
	}
	
	/**
	 * Includes Bean validation 1.0.0 support using Hibernate validator 4.3.2
	 * 
	 * @param parameterMap
	 * @param request
	 * @param annotations
	 * 
	 * @author jyogapa1 (Jenith)
	 */
	private void addValidationSupport(Map<String, String> parameterMap,
			FormEntityRequest request, Annotation[] annotations) {
		
		Response errors = this.validateRequest(request, annotations);

		if (errors != null) {
			// log request details
			this.logApiRequest(parameterMap, errors);

			throw new WebApplicationException(errors);
		}
	}

	/**
	 * Validates an input form entity using hibernate validator plug-in.
	 * 
	 * @param form
	 * @param annotations
	 * 
	 * @throws WebApplicationException
	 * 
	 * @author jyogapa1 (Jenith)
	 * 
	 */
	private Response validateRequest(FormEntityRequest form,
			Annotation[] annotations) {
		Response errorsResponse = null;

		// Plug the validation framework. Added by Jenith on 05/07/2015
		if (hasValidAnnotation(annotations)) {

			// Validate for the basic constraint phase level.
			// (@NotNull, @NotBlank, @NotEmpty etc...)
			errorsResponse = this.validateByConstraintGroup(form,
					BasicConstraint.class);

			if (errorsResponse != null) {
				return errorsResponse;
			}

			// Validate for the size constraint phase level.
			// (@Min, @Max, @Length etc...)
			errorsResponse = this.validateByConstraintGroup(form,
					SizeConstraint.class, Constants.BOOLEAN_TRUE);

			if (errorsResponse != null) {
				return errorsResponse;
			}

			// Validate for the date format and other format constraint phase
			// level. (@ValidDateTime @Pattern etc...)
			errorsResponse = this.validateByConstraintGroup(form,
					FormatConstraint.class, Constants.BOOLEAN_TRUE);

			if (errorsResponse != null) {
				return errorsResponse;
			}

			errorsResponse = this.validateByConstraintGroup(form,
					DuplicateRecordGroupConstraint.class,
					Constants.BOOLEAN_TRUE);

		}

		return errorsResponse;
	}

	/**
	 * Validates input fields by grouping.
	 * 
	 * Note: Skips validation for empty value.
	 * 
	 * @param form
	 * @param groupingClass
	 * @param shouldSkipForEmpty
	 * @return
	 * 
	 * @author jyogapa1 (Jenith)
	 */
	private Response validateByConstraintGroup(FormEntityRequest form,
			Class<?> groupingClass, Boolean shouldSkipForEmpty) {
		Response errorsResponse = null;

		// Validate for the size constraint phase (Min, Max, Length)
		List<ErrorMessageDescriptor> errors = validate(form, groupingClass,
				shouldSkipForEmpty);

		if (!errors.isEmpty()) {
			LOGGER.info("Number of validation errors found: " + errors.size());
			StringBuilder msg = new StringBuilder(
					"Request entity has following errors:");
			StringBuilder msgCode = new StringBuilder();
			StringBuilder msgCodeTxt = new StringBuilder();
			for (ErrorMessageDescriptor errorMsgDesp : errors) {
				String errMsgCode = errorMsgDesp.getMessageCode();
				String errMsgCodeTxt = errorMsgDesp.getMessageCodeText();
				String errMsg = errorMsgDesp.getMessage();
				if (StringUtils.isNotEmpty(errMsgCode)) {
					if (StringUtils.isEmpty(msgCode.toString())) {
						msgCode.append(errMsgCode);
					} else {
						msgCode.append(", ").append(errMsgCode);
					}
				}
				if (StringUtils.isNotEmpty(errMsgCodeTxt)) {
					if (StringUtils.isEmpty(msgCodeTxt.toString())) {
						msgCodeTxt.append(errMsgCodeTxt);
					} else {
						msgCodeTxt.append(", ").append(errMsgCodeTxt);
					}
				}
				msg.append(' ').append(errMsg).append('*');
			}

			errorsResponse = createErrorsResponse(msg.toString(),
					msgCode.toString(), msgCodeTxt.toString());
		}

		return errorsResponse;
	}

	/**
	 * Validates input fields by grouping.
	 * 
	 * 
	 * @param form
	 * @param groupingClass
	 * @return
	 */
	private Response validateByConstraintGroup(FormEntityRequest form,
			Class<?> groupingClass) {
		Response errorsResponse = this.validateByConstraintGroup(form,
				groupingClass, Constants.BOOLEAN_FALSE);
		return errorsResponse;
	}

	/**
	 * Checks fields are annotated.
	 * 
	 * @param annotations
	 * @return
	 * 
	 * @author jyogapa1 (Jenith)
	 */
	private boolean hasValidAnnotation(Annotation[] annotations) {
		for (Annotation annotation : annotations) {
			if (Valid.class.equals(annotation.annotationType())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * VALIDATES REQUEST ENTITY.
	 * 
	 * @param formEntity
	 * @return List of errors.
	 * 
	 * @author jyogapa1 (Jenith)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<ErrorMessageDescriptor> validate(Object requestEntity,
			Class<?> group, Boolean shouldSkipForEmpty) {

		List<ErrorMessageDescriptor> errors = new LinkedList<ErrorMessageDescriptor>();
		// LOGGER.debug("Validator loaded: " + validator);
		Set<ConstraintViolation<Object>> violations = validator.validate(
				requestEntity, group);
		for (ConstraintViolation<Object> v : violations) {
			// Skip validation error message if the field value is blank.
			if (shouldSkipForEmpty
					&& StringUtils.isBlank(v.getInvalidValue().toString())) {
				continue;
			}
			// errors.add(String.format("%s %s (was %s)", v.getPropertyPath(),
			// v.getMessage(), v.getInvalidValue()));

			String messageCode = StringUtils.EMPTY;
			String messageCodeText = StringUtils.EMPTY;
			String message = String.format("%s %s ", v.getPropertyPath(),
					v.getMessage());

			ConstraintDescriptor cd = v.getConstraintDescriptor();
			Map cdMap = cd.getAttributes();
			if (cdMap != null && !cdMap.isEmpty()) {
				// Form the messageCode:
				messageCode = CommonUtil.getMessageCode(cdMap);

				// Form the messageCodeText:
				messageCodeText = CommonUtil.getMessageCodeText(cdMap);
				try {
					String msgSrcMsgCodeTxt = msgSource
							.getMessage(messageCodeText);
					if (StringUtils.isNotEmpty(msgSrcMsgCodeTxt)) {
						messageCodeText = msgSrcMsgCodeTxt;
					}
				} catch (Exception e) {
					messageCodeText = StringUtils.EMPTY;
				}
			}

			ErrorMessageDescriptor error = new ErrorMessageDescriptor(
					messageCode, messageCodeText, message);
			errors.add(error);
		}

		return errors;
	}

	/**
	 * 
	 * BUILDS VALIDATION ERROR RESPONSES WITH THE GIVEN MESSAGE.
	 * 
	 * @param errorMsg
	 * @param errorMsgCode
	 * @param errorMsgCodeTxt
	 * @return
	 * 
	 * @author jyogapa1 (Jenith)
	 */
	private Response createErrorsResponse(String errorMsg, String errorMsgCode,
			String errorMsgCodeTxt) {
		LOGGER.info("Building bean/entity level validation error with the message = \n'"
				+ errorMsg
				+ "'\n and messageCode = \n'"
				+ errorMsgCode
				+ "'\n and messageCodeText = \n'" + errorMsgCodeTxt + "'\n");

		Response errors = null;

		errors = CommonUtil.buildErrorsResponse(errorMsg, errorMsgCode,
				errorMsgCodeTxt, Constants.STATUS_CODE_STOP);

		return errors;
	}

	/**
	 * Logs api request and reponse details.
	 * 
	 * @param requestParams
	 * @param response
	 */
	private void logApiRequest(Map<String, String> requestParams,
			Response response) {
		try {
			String requestData = this
					.constructSecuredRequestParams(requestParams);

			this.logRequestDetails(requestData, response);
		} catch (Throwable e) {
			LOGGER.info("System Exception: " + e.getMessage());
			LOGGER.error("ERROR LOG:", e);
		}
	}

	/**
	 * Logs request details.
	 * 
	 * @param requestParams
	 * @param response
	 * @throws Throwable
	 */
	private void logRequestDetails(String requestParams, Response response)
			throws Throwable {

		// Jackson mapper for JSON
		ObjectMapper mapper = new ObjectMapper();

		LOGGER.info("API URL: " + CommonUtil.getFullURL(request));
		// LOGGER.info("Execution Time: " + elapsedTime + " milliseconds.");
		LOGGER.info("Request: " + requestParams);
		if (LOGGER != null) {
			// LOGGER.info(response);
			LOGGER.info("Response status code: " + response.getStatus());
			LOGGER.info("Response: "
					+ mapper.writeValueAsString(response.getEntity()));
		}
	}

	/**
	 * Build request parameters sanitized for log. (With privacy data masking)
	 * 
	 * @param paramMap
	 * @return
	 */
	private String constructSecuredRequestParams(Map<String, String> paramMap) {
		StringBuffer parameterData = new StringBuffer();
		String paramName = null;
		String paramValue = null;
		parameterData.append("[");

		for (Map.Entry<String, String> entry : paramMap.entrySet()) {
			paramName = entry.getKey();
			paramValue = entry.getValue();

			if (CommonUtil.hasPrivacyData(paramName)) {
				parameterData.append(paramName + "=" + Constants.MASK_CHAR);
			} else {
				parameterData.append(paramName + "=" + paramValue);
			}
			parameterData.append(";");
		}

		parameterData.deleteCharAt(parameterData.length() - 1);
		parameterData.append("]");

		return parameterData.toString();
	}

}
