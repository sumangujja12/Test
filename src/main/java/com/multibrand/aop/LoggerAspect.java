package com.multibrand.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.helper.ErrorContentHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.GenericResponse;

/**
 * 
 * An aspect class that logs request, response details of every NRGREST API
 * calls.
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

	@Autowired
	private ErrorContentHelper errorContentHelper;

	@Pointcut("execution(public * com.multibrand.resources.*.*(..))" + "&& !within(com.multibrand.resources.SalesAPIResource)" +"&& !within(com.multibrand.resources.NEISimplySmartResource)" )
	public void resourceMethods() {
	}

	@Around("resourceMethods()")
	public Object logRequestDetailsForResourceMethods(ProceedingJoinPoint methodPoint) throws Throwable {

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
	 * @param methodPoint Instance of <code>ProceedingJoinPoint</code>.
	 * @param methodType  Instance of <code>METHOD_TYPE</code>.
	 */
	public Object logRequestDetails(ProceedingJoinPoint methodPoint, METHOD_TYPE methodType) throws Throwable {

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
			getErrorDisplay(output,methodName);
		} catch (Exception ex) {
			logger.info("System Exception: " + ex.getMessage());
			logger.error("ERROR LOG:", ex);
			/**
			 * Handling General/unknown/Runtime Exception which is thrown from Resource
			 */
			GenericResponse genericResponse = new GenericResponse();
			genericResponse.setErrorCode(Constants.RESULT_CODE_EXCEPTION_FAILURE);
			genericResponse.setErrorDescription(Constants.RESULT_DESCRIPTION_EXCEPTION);
			output = Response.status(Response.Status.OK).entity(genericResponse).build();
		}
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;

		logger.info("Execution Time: " + elapsedTime + " ms " + "-API URL: " + CommonUtil.getFullURL(request));
		// logger.info("Execution Time: " + elapsedTime + " milliseconds.");
		logger.info("Request: " + buildParameterMap(methodPoint));
		if (output != null) {
			// logger.info(output);
			logger.info("Response status code: " + output.getStatus());
			if (!CommonUtil.shouldExcludeResponseLog(methodName)) {
				logger.info("Response: " + mapper.writeValueAsString(output.getEntity()));
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

		if (parameterNames != null && parameterNames.length > 0 && parameters != null && parameters.length > 0) {

			int i = 0;

			parameterData.append("[");
			for (String paramKey : parameterNames) {

				if (parameters[i] != null && !(parameters[i] instanceof String)) {
					parameterData.append((parameters[i]).toString());
				} else {
					if (CommonUtil.hasPrivacyData(paramKey)) {
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

	private void getErrorDisplay(Response output, String methodName) {
		logger.info("###########START- getErrorDisplay -###########");

		Object obj = output.getEntity();
		if (obj != null && obj.getClass().getSuperclass().isAssignableFrom(GenericResponse.class)) {
			String resultCode;
			String invalidLoginCount;
			try {				
				resultCode =  isReplace(isParentMethod(obj, "getResultCode", null));
				invalidLoginCount =  isReplace(isParentMethod(obj, "getInvalidLoginCount", null));
				String errorCodeActual = isParentMethod(obj, "getErrorCode", null);
				String errorCode = isReplace(errorCodeActual);
				StringBuffer key = new StringBuffer();

				if (((StringUtils.isBlank(errorCode) || errorCode.equalsIgnoreCase(Constants.ZERO))
					&& StringUtils.isNotBlank(resultCode)) && !resultCode.equalsIgnoreCase(Constants.ZERO)) {
					if (resultCode.length() == 1) {
						key.append(methodName);
						key.append(Constants.STR_SYMBOL_EIPHEN);
						key.append(Constants.ZERO);
						key.append(resultCode);
					} else {
						key.append(methodName);
						key.append(Constants.STR_SYMBOL_EIPHEN);
						key.append(resultCode);
					}

				} else if ((StringUtils.isNotBlank(errorCode) && StringUtils.isNotBlank(resultCode))
						&& (!resultCode.equalsIgnoreCase(Constants.ZERO)
								&& !errorCode.equalsIgnoreCase(Constants.ZERO))) {
					if (resultCode.length() == 1) {
						key.append(methodName);
						key.append(Constants.STR_SYMBOL_EIPHEN);
						key.append(Constants.ZERO);
						key.append(resultCode);
						key.append(Constants.STR_SYMBOL_EIPHEN);
						if (errorCode.length() == 1) {
							key.append(Constants.ZERO);
						}
						key.append(isReplace(errorCode));
					} else {
						key.append(methodName);
						key.append(Constants.STR_SYMBOL_EIPHEN);
						key.append(resultCode);
						key.append(Constants.STR_SYMBOL_EIPHEN);
						if (errorCode.length() == 1) {
							key.append(Constants.ZERO);
						}
						key.append(errorCode);
					}

				}
				
				if (StringUtils.isNotBlank(key.toString())) {
					String genericError = errorContentHelper.getErrorMessage(key.toString());
					String errorDescription = isParentMethod(obj, "getErrorDescription", null);

					if ( org.apache.commons.lang3.StringUtils.isNotEmpty(invalidLoginCount)) {
						errorDescription = errorDescription.replace("$invalidLoginCount", invalidLoginCount);
					}
					if ((StringUtils.isNotBlank(genericError)
							&& genericError.equalsIgnoreCase(Constants.ERROR_CONTENT_DEFAULT))
							&& StringUtils.isNotBlank(errorDescription)) {
						getMethodRun(getSuperClassMethod(obj, "setResultDisplayText", String.class), obj,
								errorDescription);
						getMethodRun(getSuperClassMethod(obj, "setResultDisplayCode", String.class), obj,
								errorCodeActual);
					} else {

						getMethodRun(getSuperClassMethod(obj, "setResultDisplayText", String.class), obj, genericError);
						getMethodRun(getSuperClassMethod(obj, "setResultDisplayCode", String.class), obj,
								key.toString());
					}
				}

			} catch (Exception ex) {
				logger.info("System Exception: " + ex.getMessage());
			}
		}
		logger.info("###########END- getErrorDisplay -###########");
	}

	private Method getSuperClassMethod(Object obj, String methodName, Class<?> param)
			throws NoSuchMethodException, SecurityException {
		if (param != null) {
			return obj.getClass().getSuperclass().getDeclaredMethod(methodName, param);
		}

		return obj.getClass().getSuperclass().getDeclaredMethod(methodName);
	}

	private Object getMethodRun(Method method, Object obj, String param)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		method.setAccessible(true);
		if (param == null) {
			return method.invoke(obj);
		} else {
			return method.invoke(obj, param);
		}

	}
	
	private String isParentMethod(Object obj, String methodName, Class<?> param) {
		String strReturn = "";
		try {
			if (param != null) {
				strReturn = (String) getMethodRun(obj.getClass().getDeclaredMethod(methodName, param), obj, null);
			}

			strReturn = (String) getMethodRun(obj.getClass().getDeclaredMethod(methodName), obj, null);
		} catch (Exception e) {
			logger.info("DOES NOT HAVE Method " + methodName + " in Object " + obj.toString()
					+ " so going to look in parent");
			try {
				return (String) getMethodRun(getSuperClassMethod(obj, methodName, param), obj, null);
			} catch (Exception e1) {
				logger.info("DOES NOT HAVE Method in Child " + methodName + " in Object " + obj.toString()
						+ " so return blank string");
			}
			return strReturn;
		}

		if (StringUtils.isBlank(strReturn)) {
			logger.info("Value is null in child Method " + methodName + " in Object " + obj.toString()
					+ " so going to look in parent");
			try {
				strReturn = (String) getMethodRun(getSuperClassMethod(obj, methodName, param), obj, null);
			} catch (Exception e1) {
				logger.info("DOES NOT HAVE Method in super class " + methodName + " in Object " + obj.toString()
						+ " so return blank string");
			}
		}

		return strReturn;

	}
	
	private String isReplace(String str) {
		if(StringUtils.isNotBlank(str)) {
			String strPattern = "[^a-zA-Z0-9_-]";
			str =  CommonUtil.runRegex(str,strPattern, "");
			strPattern = "^00{1}+";
			str =  CommonUtil.runRegex(str,strPattern, "");
		}	
		return str;
	}

}