package com.multibrand.aop;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.apache.axis.transport.http.HTTPConstants;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.multibrand.domain.OEDomain;
import com.multibrand.domain.ValidationDomain;
import com.multibrand.util.Constants;
import com.multibrand.util.EnvMessageReader;

@Aspect
@Component
public class MockHeaderDataAspect implements Constants{
	
	@Autowired
	private ValidationDomain validationDomainPortProxy;
	@Autowired
	protected EnvMessageReader envMessageReader;
	
	@Autowired
	private OEDomain oeDomainPortProxy;
	
		@Pointcut("execution(public * com.multibrand.service.ValidationService.*(..))")
	    public void addSoapHeaderPoint() {}
	
		@Around("addSoapHeaderPoint()")
		public Object afterReturningAdvice(ProceedingJoinPoint jp) throws Throwable{
			Object responseObject = null;
			HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			org.apache.axis.client.Stub proxyObjectStub = ( org.apache.axis.client.Stub)validationDomainPortProxy;
			if(isNonProdUrl())
			{
				
		        proxyObjectStub.clearHeaders();
		        Hashtable<String, String> headers = new Hashtable<String, String>();
		        if(StringUtils.isNotBlank(httpServletRequest.getHeader(CONST_USE_MOCK_DATA))) {
		        	headers.put(CONST_USE_MOCK_DATA, httpServletRequest.getHeader(CONST_USE_MOCK_DATA));
		        }
		        proxyObjectStub._setProperty(HTTPConstants.REQUEST_HEADERS, headers);
			}
	        responseObject = jp.proceed();
	        
      		return responseObject;
	    }
		@Pointcut("execution(public * com.multibrand.proxy.OEProxy.*(..))")
	    public void addSoapHeaderPointForOEPRoxy() {}
		
		@Around("addSoapHeaderPointForOEPRoxy()")
		public Object addSoapHeaderElementForOEProxy(ProceedingJoinPoint jp) throws Throwable{
		    Object responseObject = null;
			HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	        org.apache.axis.client.Stub proxyObjectStub = (org.apache.axis.client.Stub) oeDomainPortProxy;
	        if(isNonProdUrl())
				{	        proxyObjectStub.clearHeaders();
		        Hashtable<String, String> headers = new Hashtable<String, String>();
		        if(StringUtils.isNotBlank(httpServletRequest.getHeader(CONST_USE_MOCK_DATA))) {
		        	headers.put(CONST_USE_MOCK_DATA, httpServletRequest.getHeader(CONST_USE_MOCK_DATA));
		        }
		        proxyObjectStub._setProperty(HTTPConstants.REQUEST_HEADERS, headers);
			}
      		responseObject = jp.proceed();
	        return responseObject;
		}
	
		@Pointcut("execution(public * com.multibrand.service.OEService.*(..))")
	    public void addSoapHeaderPointForOEService() {}
		
		@Around("addSoapHeaderPointForOEService()")
		public Object addSoapHeaderPointForOEService(ProceedingJoinPoint jp) throws Throwable{
		    Object responseObject = null;
			HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	        org.apache.axis.client.Stub proxyObjectStub = (org.apache.axis.client.Stub) oeDomainPortProxy;
	        if(isNonProdUrl())
			{
		        proxyObjectStub.clearHeaders();
		        Hashtable<String, String> headers = new Hashtable<String, String>();
		        if(StringUtils.isNotBlank(httpServletRequest.getHeader(CONST_USE_MOCK_DATA))) {
		        	headers.put(CONST_USE_MOCK_DATA, httpServletRequest.getHeader(CONST_USE_MOCK_DATA));
		        }
		        proxyObjectStub._setProperty(HTTPConstants.REQUEST_HEADERS, headers);
			}
      		responseObject = jp.proceed();
	        return responseObject;
		}
		
		@Pointcut("execution(public* com.multibrand.resources.OEResource.*(..))")
	    public void addMockFlagSoapHeaderPointForOEPRoxy() {}
		
		@Around("addMockFlagSoapHeaderPointForOEPRoxy()")
		public Object addMockFlagIntoResponseHeader(ProceedingJoinPoint jp) throws Throwable{
			Response responseObject = null;
			HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
      		responseObject = (Response) jp.proceed();
      		if(isNonProdUrl())
			{
	      		if(StringUtils.isNotBlank(httpServletRequest.getHeader(CONST_USE_MOCK_DATA))) {
	      			responseObject.getMetadata().add(CONST_IS_MOCK_RESPONSE,FLAG_TRUE);
		        }
			}
	        return responseObject;
		}
		
		/**
		 * 
		 * @param requestUrl
		 * @return
		 */
		private boolean isNonProdUrl(){
			
			String defaultEnv  = this.envMessageReader.getMessage(PROP_DEFAULT_ENV);
			if(!StringUtils.equalsIgnoreCase(defaultEnv, ENV_PROD))
			{
				return true;
			}else{
				return false;
			}
		}
	
	
	
	 	
	 
	
}
