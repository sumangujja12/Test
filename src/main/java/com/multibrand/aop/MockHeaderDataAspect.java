package com.multibrand.aop;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis.transport.http.HTTPConstants;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.multibrand.domain.OEDomain;
import com.multibrand.domain.ValidationDomain;

@Aspect
@Component
public class MockHeaderDataAspect {
	
	@Autowired
	private ValidationDomain validationDomainPortProxy;
	
	@Autowired
	private OEDomain oeDomainPortProxy;
	
		@Pointcut("execution(* com.multibrand.service.ValidationService.*(..))")
	    public void addSoapHeaderPoint() {}
	
		@Around("addSoapHeaderPoint()")
		public Object afterReturningAdvice(ProceedingJoinPoint jp) throws Throwable{
			Object responseObject = null;
			HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			org.apache.axis.client.Stub proxyObjectStub = ( org.apache.axis.client.Stub)validationDomainPortProxy;
	        proxyObjectStub.clearHeaders();
	        Hashtable<String, String> headers = new Hashtable<String, String>();
	        if(StringUtils.isNotBlank(httpServletRequest.getHeader("mock_flag"))) {
	        	headers.put("usermockdata", httpServletRequest.getHeader("mock_flag"));
	        }
	        responseObject = jp.proceed();
	        proxyObjectStub._setProperty(HTTPConstants.REQUEST_HEADERS, headers);
      		return responseObject;
	    }
	
		@Pointcut("execution(* com.multibrand.proxy.OEProxy.*(..)) && execution(* com.multibrand.service.OEService.*(..))")
	    public void addSoapHeaderPointForOEPRoxy() {}
		
		@Around("addSoapHeaderPointForOEPRoxy()")
		public Object addSoapHeaderElementForOEProxy(ProceedingJoinPoint jp) throws Throwable{
		    Object responseObject = null;
			HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	        org.apache.axis.client.Stub proxyObjectStub = (org.apache.axis.client.Stub) oeDomainPortProxy;
	        proxyObjectStub.clearHeaders();
	        Hashtable<String, String> headers = new Hashtable<String, String>();
	        if(StringUtils.isNotBlank(httpServletRequest.getHeader("mock_flag"))) {
	        	headers.put("usermockdata", httpServletRequest.getHeader("mock_flag"));
	        }
	        proxyObjectStub._setProperty(HTTPConstants.REQUEST_HEADERS, headers);
      		responseObject = jp.proceed();
	        return responseObject;
		}
	
	
	
	 	
	 
	
}
