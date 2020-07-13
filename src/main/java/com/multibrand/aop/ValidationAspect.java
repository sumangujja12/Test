package com.multibrand.aop;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.multibrand.dto.response.SalesBaseResponse;
import com.multibrand.util.Constants;

@Aspect
@Component
@Order(value=0)
public class ValidationAspect implements Constants{

	private Validator validator;
	ConstraintViolationException constraintViolationException;
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	public ValidationAspect() {
		this.validator = Validation.buildDefaultValidatorFactory().getValidator();
	}
	@Pointcut("execution(public * com.multibrand.resources.SalesAPIResource.*(..))")
	public void validationPoint(){}

	@Around("validationPoint()")
	public Object validate(ProceedingJoinPoint joinPoint) throws Exception{
		List<SalesBaseResponse> errorMessages = new ArrayList<SalesBaseResponse>();
		StringBuilder responseBuilder = new StringBuilder();
		Set<ConstraintViolation<Object>> violations = null;
		Response response=null; 
		SalesBaseResponse genericResponse =  new SalesBaseResponse();
		responseBuilder.append("Request entity has following errors:");
		for (Object parameter : joinPoint.getArgs()) {
			violations = validator.validate(parameter);
			for (ConstraintViolation<Object> violation : violations) {
				responseBuilder.append(" * ");
				responseBuilder.append(violation.getPropertyPath()+" "+violation.getMessage());
			}	
		}
		if (null!=violations && !violations.isEmpty()) {
			genericResponse.setErrorCode(Status.BAD_REQUEST.name());
			genericResponse.setErrorDescription(responseBuilder.toString());
			genericResponse.setStatusCode(STATUS_CODE_STOP);
			genericResponse.setHttpStatus(Status.BAD_REQUEST);
			errorMessages.add(genericResponse);
			response = Response.status(Status.BAD_REQUEST)
					.entity(genericResponse)
					//.type(MediaType.APPLICATION_JSON)
					.build();
		}else {
			try {
				response = (Response) joinPoint.proceed();
				HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
				if(StringUtils.isNotBlank(httpServletRequest.getHeader(CONST_USE_MOCK_DATA))) {
					response.getMetadata().add(CONST_USE_MOCK_DATA,FLAG_TRUE);
		        }
			} catch (Throwable e) {
				logger.error("Exception in ValidationAspect.validate() -> "+e.getStackTrace());
			}
		}
		return response;
	}
}
