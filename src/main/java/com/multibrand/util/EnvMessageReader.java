package com.multibrand.util;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * 	  The Property Service Class will be used to get value of the key set with ENV prefix in enviromment.properties file
 * </pre>
 * @author nulthi1
 * @version 1.0
 */
@Component
public class EnvMessageReader implements Constants {
	
	@Autowired
	@Qualifier("environmentMessageSource")
	protected ReloadableResourceBundleMessageSource environmentMessageSource;
	
	Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
		
	private Boolean isEnvJNDIEnable = null;
	
	public EnvMessageReader(){
		
	}
	
	public EnvMessageReader(String basename) {
		
		environmentMessageSource = new ReloadableResourceBundleMessageSource();
		environmentMessageSource.setBasename(basename);
	}
	
	/**
	 * 
	 * @param key
	 * @return String Object, the value set for the key in the properties file 
	 */
	public String getMessage(String key){
		String strMessage = getMessage(key,null, false, true);
		return strMessage;
	}

	/**
	 * This method will return requried JNDI value.
	 * <br>Note: This similar to getting a message using key
	 * 
	 * @param key - The JNDI key for lookup
	 * @param forceToUseJNDI - Forces to use JNDI variable configured in Websphere 
	 * and ignores reading of properties files even though the JNDI variable JNDI_ENV_ENABLE set to 'false'.<br/> 
	 * If <code>forceToUseJNDI</code> is false, then system will verifies the JNDI variable JNDI_ENV_ENABLE 
	 * value and acts accordingly i.e. in case JNDI_ENV_ENABLE = true, reads 
	 * from JNDI and JNDI_ENV_ENABLE = false reads from properties file
	 * @return String - JNDI value 
	 */
	public String getMessage(String key, boolean forceToUseJNDI){
		return this.getMessage(key, null, forceToUseJNDI, false);
	}

	/**
	 * @param key
	 * @param forceToUseJNDI - Forces to use JNDI variable configured in Websphere 
	 * and ignores reading of properties files even though the JNDI variable JNDI_ENV_ENABLE set to 'false'.<br/> 
	 * If <code>forceToUseJNDI</code> is false, then system will verifies the JNDI variable JNDI_ENV_ENABLE 
	 * value and acts accordingly i.e. in case JNDI_ENV_ENABLE = true, reads 
	 * from JNDI and JNDI_ENV_ENABLE = false reads from properties file
	 * @param doLogJndiValues - Instruct to log JNDI values retrieved from server/properties file.<br/>
	 * <b>Important</b>: Use this with caution as it may print sensitive data such as passwords. 
	 * @return String - JNDI value 
	 */
	public String getMessage(String key, boolean forceToUseJNDI, boolean doLogJndiValues){
		return this.getMessage(key, null, forceToUseJNDI, doLogJndiValues);
	}	
	
	/**
	 * 
	 * @param key
	 * @param args
	 * @return String Object, the value with dynamic parameters set for
	 *  the key  in the properties file 
	 */
	public String getMessage(String key, Object[] args, boolean forceToUseJNDI, boolean doLogEnvValue){
		
		logger.debug("START:********************************* getMessage isEnvJNDIEnable :" +isEnvJNDIEnable );
		String envVarValue = null;
		if(doLogEnvValue) {
			logger.debug("getMessage() :: JNDI look-up for :" + key 
					+ ((null != args && args.length > 0) ? " with pace holder values :" + Arrays.toString(args) :"" )
					+ " Force use JNDI " + forceToUseJNDI);
		}
		if(forceToUseJNDI || isEnvJNDIEnable()) {
			logger.debug(" JNDI Enabled flag is true. So Environment properties are Pulling from JNDI :");
			envVarValue = CommonUtil.jndiLookup(key); 
		}
		else{
			logger.debug("********************************* environmentMessageSource :" +environmentMessageSource);
			envVarValue = environmentMessageSource.getMessage(key, args, null);
		}
		if(doLogEnvValue) {
			logger.debug("getMessage() :: JNDI Value Returned :" + envVarValue);
		}
		
		return envVarValue;
	}
    
	
	/**
	 * 
	 * @return String
	 */
    public boolean isEnvJNDIEnable(){
    	logger.debug("START********************************* getEnvJNDIFlag:isEnvJNDIEnable :" +isEnvJNDIEnable);
		if(isEnvJNDIEnable == null ) {
			loadEnvJNDIFlag();
		}
		logger.debug("END********************************* getEnvJNDIFlag:environment :" +isEnvJNDIEnable);
		
		return isEnvJNDIEnable;
	}
    
    
    /**
	 * 
	 * @return String
	 */
    public  synchronized void loadEnvJNDIFlag(){
		
    	if(isEnvJNDIEnable == null) {
    		//START CIRRO FE by Jsingh1
    		//hardcoding envJNDIEnableFlag as empty as NEV_JNDI_ENABLE not available on server JNDI
			//String envJNDIEnableFlag = CommonUtil.jndiLookup(ENV_JNDI_ENABLE);
    		logger.info("inside loadEnvJNDIFlag:: setting envJNDIEnableFlag as EMPTY as JNDI not Available on server");
    		String envJNDIEnableFlag =EMPTY;
			//END CIRRO FE by Jsingh1
			logger.info("*********** ENV_JNDI_ENABLE : from jndi" + envJNDIEnableFlag);
			if(StringUtils.isBlank(envJNDIEnableFlag) ||  (!StringUtils.equalsIgnoreCase(envJNDIEnableFlag, "true")) ) {
				isEnvJNDIEnable = false;
				logger.info("*********** defaulting  ENV_JNDI_ENABLE  to 'false'" );
			}
			else {
				isEnvJNDIEnable = true;
			}
		}
		
	}
    
   
}
