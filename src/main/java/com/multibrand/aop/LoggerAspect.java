package com.multibrand.aop;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.GenericResponse;

/**
 * 
 * An aspect class that logs request, response details of every NRGREST API calls.
 * 
 * @author Jenith (jyogapa1)
 * @version Since JDK 1.6, Spring 3.2 and Jersey 1.17 (1.x)
 */
@Aspect
@Component
public class LoggerAspect {

	public static enum METHOD_TYPE {
		RESOURCE, SERVICE, HELPER
	};

	Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Autowired(required = true)
	private HttpServletRequest request;

	@Pointcut("execution(public * com.multibrand.resources.*.*(..))")
	public void resourceMethods() {
	}

	@Around("resourceMethods()")
	public Object logRequestDetailsForResourceMethods(
			ProceedingJoinPoint methodPoint) throws Throwable {

		return logRequestDetails(methodPoint, METHOD_TYPE.RESOURCE);
	}

	/**
	 * Logs the request details and returns to the caller.
	 * 
	 * <p>
	 * The <code>logs</code> will be written in the following format.
	 * <ul>
	 * <li><strong>API URL</strong>: &quot;URL&quot;</li>
	 * <li><strong>Execution Time</strong>: &quot;x milliseconds&quot;</li>
	 * <li><strong>Request</strong>: &quot;parameters list&quot;</li>
	 * <li><strong>Response</strong>: &quot;response json&quot;</li>
	 * <li><strong>System Exception</strong>: &quot;exception log trace&quot;</li>
	 * </ul>
	 * </p>
	 * 
	 * @param methodPoint
	 *            Instance of <code>ProceedingJoinPoint</code>.
	 * @param methodType
	 *            Instance of <code>METHOD_TYPE</code>.
	 */
	public Object logRequestDetails(ProceedingJoinPoint methodPoint,
			METHOD_TYPE methodType) throws Throwable {

		Response output = null;

		// Build log message prefix
		String logPrefix = getLogPrefix(methodType);
		String className = methodPoint.getTarget().getClass().getName();
		String methodName = methodPoint.getSignature().getName();
		String methodSignature = className + "::" + methodName + "(..)";
		String logMessagePrefix = logPrefix + methodSignature;

		// Jackson mapper for JSON
		ObjectMapper mapper = new ObjectMapper();

		logger.info("###########START-" + logMessagePrefix + "-###########");

		long startTime = System.currentTimeMillis();
		try {
			output = (Response) methodPoint.proceed();
		} catch (Exception ex) {
			logger.info("System Exception: " + ex.getMessage());
			logger.error("ERROR LOG:", ex);
			/**
			 * Handling General/unknown/Runtime Exception which is thrown from
			 * Resource
			 */
			GenericResponse genericResponse = new GenericResponse();
			genericResponse.setErrorCode(Constants.RESULT_CODE_EXCEPTION_FAILURE);
			output = Response.status(Response.Status.OK)
					.entity(genericResponse).build();
		}
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;

		logger.info("Execution Time: " + elapsedTime + " ms "+"-API URL: " + CommonUtil.getFullURL(request));
		//logger.info("Execution Time: " + elapsedTime + " milliseconds.");
		logger.info("Request: " + buildParameterMap(methodPoint));
		if (output != null) {
			// logger.info(output);
			logger.info("Response status code: " + output.getStatus());
			if (!CommonUtil.shouldExcludeResponseLog(methodName)) {
				logger.info("Response: "
						+ mapper.writeValueAsString(output.getEntity()));
			}
		}
		logger.info("###########END-" + logMessagePrefix + "-###########");

		return output;
	}

	/**
	 * 
	 * <p>
	 * Builds request parameters written in the following format.
	 * <ul>
	 * <li><strong>Parameter</strong>: &quot;value&quot;</li>
	 * </ul>
	 * </p>
	 * 
	 * @param methodPoint
	 * @return
	 */
	private String buildParameterMap(ProceedingJoinPoint methodPoint) {

		StringBuffer parameterData = new StringBuffer();

		// Build Request parameters
		MethodSignature sig = (MethodSignature) methodPoint.getSignature();
		String[] parameterNames = sig.getParameterNames();
		Object[] parameters = methodPoint.getArgs();

		if (parameterNames != null && parameterNames.length > 0
				&& parameters != null && parameters.length > 0) {

			int i = 0;

			parameterData.append("[");
			for (String paramKey : parameterNames) {

				if (parameters[i] != null && !(parameters[i] instanceof String)) {
					parameterData.append((parameters[i]).toString());
				} else {
					if (CommonUtil.hasPrivacyData(paramKey)){
						parameterData.append(paramKey + "=" + Constants.MASK_CHAR);
					} else {
						parameterData.append(paramKey + "=" + parameters[i]);
					}
				}
				parameterData.append(";");
				i++;
			}
			parameterData.deleteCharAt(parameterData.length() - 1);
			parameterData.append("]");

		}
		return parameterData.toString();
	}

	/**
	 * 
	 * Gets log method prefix based on the class method type.
	 * 
	 * @param methodType
	 * @return
	 */
	private String getLogPrefix(METHOD_TYPE methodType) {

		String logPrefix = "";

		switch (methodType) {
		case RESOURCE:
			logPrefix = "RESOURCE METHOD :";
			break;
		case SERVICE:
			logPrefix = "SERVICE METHOD :";
			break;
		case HELPER:
			logPrefix = "HELPER METHOD :";
			break;
		}

		return logPrefix;
	}

}