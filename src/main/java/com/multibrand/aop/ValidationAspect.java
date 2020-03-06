package com.multibrand.aop;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import com.multibrand.vo.response.GenericResponse;

@Aspect
@Component
@Order(value=0)
public class ValidationAspect {

	private Validator validator;
	ConstraintViolationException constraintViolationException;
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	public ValidationAspect() {
		this.validator = Validation.buildDefaultValidatorFactory().getValidator();
	}
	@Pointcut("@annotation(com.multibrand.request.validation.ValidateGetMapppingRequestParam) && execution(public * com.multibrand.resources.SalesAPIResource.*(..))")
	public void validationPoint(){}

	@Around("validationPoint()")
	public Object validate(ProceedingJoinPoint joinPoint) throws Exception{
		List<GenericResponse> errorMessages = new ArrayList<GenericResponse>();
		StringBuilder responseBuilder = new StringBuilder();
		Set<ConstraintViolation<Object>> violations = null;
		Response response=null; 
		GenericResponse genericResponse =  new GenericResponse();
		responseBuilder.append("Request entity has following errors:");
		for (Object parameter : joinPoint.getArgs()) {
			violations = validator.validate(parameter);
			for (ConstraintViolation<Object> violation : violations) {
				responseBuilder.append(" * ");
				responseBuilder.append(violation.getPropertyPath()+" "+violation.getMessage());
			}	
		}
		if (null!=violations && !violations.isEmpty()) {
			genericResponse.setErrorCode("400");
			genericResponse.setErrorDescription(Status.BAD_REQUEST.toString());
			genericResponse.setResultDescription(responseBuilder.toString());
			genericResponse.setResultCode("400");
			errorMessages.add(genericResponse);
			response = Response.status(Status.BAD_REQUEST)
					.entity(errorMessages)
					.type(MediaType.APPLICATION_JSON)
					.build();
		}else {
			try {
				response = (Response) joinPoint.proceed();
			} catch (Throwable e) {
				logger.error("Exception in ValidationAspect.validate() -> "+e.getStackTrace());
			}
		}
		return response;
	}
}
