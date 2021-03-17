package com.multibrand.helper;


import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.multibrand.domain.CreateContactLogRequest;
import com.multibrand.domain.UpdateAlertPrefRequest;
import com.multibrand.domain.UpdateContactRequest;
import com.multibrand.proxy.ProfileProxy;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.LoggingVO;

/**
 * The Class AsyncHelper.
 * @author ahanda1
 */
@Component
public class AsyncHelper implements Constants  {
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
		 
	
	/**
	 * Run in separate thread which is managed by ExecutorService.
	 *
	 * @param request the request
	 * @return true, if successful
	 */
	@SuppressWarnings("unchecked")
	public boolean excuteASyncService(final Class serviceClass, final String methodName, final Object requestObject){
		logger.debug("START: AsyncHelper::excuteASyncService()");
		
		Callable call = new Callable (){
			@Override
			public Object call() {
				
				executeServiceCall(serviceClass, methodName, requestObject);															
				return null;
			}
			
		};
		
		ExecutorService service = Executors.newFixedThreadPool(1);
		service.submit(call);		
		service.shutdown();					
		logger.debug("END: AsyncHelper::excuteASyncService()");
		
		return true;		
	}
	

	/**
	 * Returns the response object by executing the service call using reflection package.
	 *
	 * @param serviceClass the service class
	 * @param methodName the method name
	 * @param requestObject the request object
	 * @return the object
	 */
	@SuppressWarnings("unchecked")
	private void executeServiceCall(Class serviceClass, String methodName, Object requestObject){
		
		try {
			
			Object serviceObj =  serviceClass.newInstance();
			Method method = serviceClass.getDeclaredMethod(methodName, new Class[] { requestObject.getClass() });
			method.invoke(serviceObj, requestObject);
			
		} catch (Exception e) {
			logger.error("executeService() Failed :{} ", e.getMessage());			
		}
		
	}
		
	
	/** Methods to be running Asynchronously*/
	
	public void asychLogging(LoggingVO logvo) {

		logger.debug("START: synchronizing LOGGING in asynchronous manner");
		excuteASyncService(UtilityLoggerHelper.class, METHOD_SYNCHRONIZE_LOGGING, logvo);
		logger.debug("END: synchronizing LOGGING in asynchronous manner");
	}

	public void updateContactInCRM(UpdateContactRequest updateContactRequest) {

		logger.debug("START: Updating CRM in asynchronous manner");
		excuteASyncService(ProfileProxy.class, METHOD_UPDATE_CONTACT_ASYNC, updateContactRequest);
		logger.debug("END: Updating CRM in asynchronous manner");
	}

	public void asychUpdateContactLog(CreateContactLogRequest request) {

		logger.debug("START: Updating CCSContactLog in asynchronous manner");
		excuteASyncService(ContactLogHelper.class, METHOD_SYNCHRONIZE_CSS_CONTACT_LOG, request);
		logger.debug("END: Updating CCSContactLog in asynchronous manner");

	}
  	/**
     * update alert preferences asynchronous
     * 
     * @param oeSignUpDTO, updateAlertPrefRequest
     */
   public void asychUpdatePrepayAlertPreferences(UpdateAlertPrefRequest updateAlertPrefRequest) {
  		
  	logger.debug("START: Updateing prepay alert preference in asynchronous manner");
  	excuteASyncService(ProfileProxy.class, METHOD_PREPAY_ALERT_TO_CRM,updateAlertPrefRequest);
 		logger.debug("END: Updateing prepay alert preference in asynchronous manner");
  	 }	
}
