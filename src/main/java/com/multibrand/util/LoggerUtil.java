package com.multibrand.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 *
 * This a helper class for Centralized logging functionality
 * <p>
 * @author 
 *
 * @version $Revision: 1.0 $
 */
public class LoggerUtil {

	private static String START_TIME = "START_TIME";
	private static String END_TIME = "END_TIME";
	private static String END_POINT_URL = "END_POINT_URL";
	private static String TRANSACTION_TYPE = "TRANSACTION_TYPE";
	private static String REFERENCE_ID = "REFERENCE_ID";
	private static final String NEWLINE = "\n";
	private static final String REQUEST = "Request Parameters: ";
	private static final String RESPONSE = "Response Parameters: ";
	private static final String GETTER = "get";
	private static final String SETTER = "set";
	private static final String PASSWORD = "password";
	private static final String PRIVACY_DATA = "strverificationdata";
	
	Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	
    /**
     * Field className.
     */
    private String className;

    /**
     *
     * @param className
     */
    public LoggerUtil(String className) {
        this.className = className;
    }

    /**
     *
     * @param vClass Class
     * @return LoggerHelper
     */
    public static LoggerUtil getInstance(Class vClass) {
        return getInstance(vClass.getName());
    }

    /**
     *
     * @param strClassName
     * @return LoggerHelper
     */
    public static LoggerUtil getInstance(String strClassName) {
        return new LoggerUtil(strClassName);
    }

    /**
     * Log a message object with the INFO Level.
     * <p>
     * This method first checks if this category is INFO enabled by comparing the level of this category with INFO Level.
     * If the category is INFO enabled, then it converts the message object passed as parameter to a string by invoking the appropriate ObjectRenderer. It proceeds to call all the registered appenders in this category and also higher in the hierarchy depending on the value of the additivity flag.
     * <p>
     * WARNING Note that passing a Throwable to this method will print the name of the Throwable but no stack trace. To print a stack trace use the info(Object, Throwable) form instead.
     * @param message - the message object to log.
     */
    public void info(Object message) {
    	infoWithClassMessage(className, message);
    }

    /**
     * Log a message object with the INFO level including the stack trace of the Throwable t passed as parameter.
     * <p>
     * See info(Object) for more detailed information
     *
     * @param message - the message object to log.
     * @param t - the exception to log, including its stack trace.
     */
    public void info(Object message, Throwable t) {}

    /**
     * Log a message object with the DEBUG level.
     * <p>
     * This method first checks if this category is DEBUG enabled by comparing the level of this category with the DEBUG level.
     * If this category is DEBUG enabled, then it converts the message object (passed as parameter) to a string by invoking the appropriate ObjectRenderer.
     * It then proceeds to call all the registered appenders in this category and also higher in the hierarchy depending on the value of the additivity flag.
     * <p>
     * WARNING Note that passing a Throwable to this method will print the name of the Throwable but no stack trace. To print a stack trace use the debug(Object, Throwable) form instead.
     *
     * @param message -the message object to log
     */
    public void debug(Object message) {
    	this.debugWithClassMessage(className, message);
    }

    /**
     * Log a message object with the DEBUG level including the stack trace of the Throwable t passed as parameter.
     * <p>
     * See debug(Object) form for more detailed information.
     * <p>
     * @param message - the message object to log.
     * @param t - the exception to log, including its stack trace.
     */
    public void debug(Object message, Throwable t) {
        debug(className, message, t);
    }

    /**
     * Log a message object with the WARN Level.
     *     This method first checks if this category is WARN enabled by comparing the level of this category with WARN Level.
     *     If the category is WARN enabled, then it converts the message object passed as parameter to a string by invoking the appropriate ObjectRenderer.
     *     It proceeds to call all the registered appenders in this category and also higher in the hierarchy depending on the value of the additivity flag.
     * <p>
     * WARNING Note that passing a Throwable to this method will print the name of the Throwable but no stack trace. To print a stack trace use the warn(Object, Throwable) form instead.
     *
     * @param message - the message object to log.
     */
    public void warn(Object message) {
    	warnWithClassMessage(className, message);
    }

    /**
     * Log a message object with the WARN level including the stack trace of the Throwable t passed as parameter.
     * <p>
     * See warn(Object) form for more detailed information.
     * <p>
     * @param message - the message object to log.
     * @param t - the exception to log, including its stack trace.
     */
    public void warn(Object message, Throwable t) {
        warn(className, message, t);
    }

    /**
     * Log a message object with the ERROR Level.
     * <p>
     * This method first checks if this category is ERROR enabled by comparing the level of this category with ERROR Level.
     * If this category is ERROR enabled, then it converts the message object passed as parameter to a string by invoking the appropriate ObjectRenderer.
     * It proceeds to call all the registered appenders in this category and also higher in the hierarchy depending on the value of the additivity flag.
     * <p>
     * WARNING Note that passing a Throwable to this method will print the name of the Throwable but no stack trace. To print a stack trace use the error(Object, Throwable) form instead.
     *
     * @param message - the message object to log.
     */
    public void error(Object message) {
    	this.errorWithClassMessage(className, message);
    }

    /**
     * Log a message object with the ERROR level including the stack trace of the Throwable t passed as parameter.
     * <p>
     * See error(Object) form for more detailed information.
     * <p>
     * @param message - the message object to log.
     * @param t - the exception to log, including its stack trace.
     */
    public void error(Object message, Throwable t) {
        error(className, message, t);
    }

    /**
     * Log a message object with the FATAL Level.
     * <p>
     * This method first checks if this category is FATAL enabled by comparing the level of this category with FATAL Level.
     * If this category is FATAL enabled, then it converts the message object passed as parameter to a string by invoking the appropriate ObjectRenderer.
     * It proceeds to call all the registered appenders in this category and also higher in the hierarchy depending on the value of the additivity flag.
     * <p>
     * WARNING Note that passing a Throwable to this method will print the name of the Throwable but no stack trace. To print a stack trace use the fatal(Object, Throwable) form instead.
     *
     * @param message - the message object to log.
     */
    public void fatal(Object message) {
    	this.fatalWithClassMessage(className, message);
    }

    /**
     * Log a message object with the FATAL level including the stack trace of the Throwable t passed as parameter.
     * <p>
     * See fatal(Object) form for more detailed information.
     * <p>
     * @param message - the message object to log.
     * @param t - the exception to log, including its stack trace.
     */
    public void fatal(Object message, Throwable t) {
        fatal(className, message, t);
    }

    /**
     * Log a message object with the INFO level with two arguments - className & message
     * <p>
     * See info(Object) form for more detailed information.
     * <p>
     * @param  className - a String value of the class name
     * @param  message - logging message
     */
    private void infoWithClassMessage(String className, Object message) {
        info(className, message, null);
    }

    /**
     * Log a message object with the INFO level with three arguments - className, message and t
     * <p>
     * See info(Object) form for more detailed information.
     * <p>
     *
     * @param  className - a String value of the class name
     * @param  message - logging message
     * @param  t - exception
     */
    private void info(String className, Object message, Throwable t) {
        if (StringUtils.isBlank(className)) {
            className = this.getClass().getName();
        }

        Logger logger = LogManager.getLogger(className);

        this.logGenericPrinting(logger, Level.INFO, message, t);
    }

    /**
     * Log a message object with the WARN level with two arguments - className & message.
     * <p>
     * See warn(Object) form for more detailed information.
     * <p>
     * @param  className -a String value of the class name
     * @param  message - logging message
     */
    private void warnWithClassMessage(String className, Object message) {
        warn(className, message, null);
    }

    /**
     * Log a message object with the WARN level with three arguments - className ,message and t.
     * <p>
     * See warn(Object) form for more detailed information.
     * <p>
     *
     * @param  className -a String value of the class name
     * @param  message -logging message
     * @param  t -exception
     */
    private void warn(String className, Object message, Throwable t) {
        if (StringUtils.isBlank(className)) {
            className = this.getClass().getName();
        }

        Logger logger = LogManager.getLogger(className);

        this.logGenericPrinting(logger, Level.WARN, message, t);
    }

    /**
     * Log a message object with the DEBUG level with two arguments - className & message
     * <p>
     * See debug(Object) form for more detailed information.
     * <p>
     * @param  className - a String value of the class name
     * @param  message - logging message
     */
    private void debugWithClassMessage(String className, Object message) {
        debug(className, message, null);
    }

    /**
     * Log a message object with the DEBUG level with three arguments - className ,message and t.
     * <p>
     * See debug(Object) form for more detailed information.
     * <p>
     *
     * @param  className -a String value of the class name
     * @param  message -logging message
     * @param  t -exception
     */
    private void debug(String className, Object message, Throwable t) {
        if (StringUtils.isBlank(className)) {
            className = this.getClass().getName();
        }

        Logger logger = LogManager.getLogger(className);

        if (logger.isDebugEnabled()) {
        	this.logGenericPrinting(logger, Level.DEBUG, message, t);
        }
    }

    /**
     * Log a message object with the ERROR level with two arguments - className & message
     * <p>
     * See error(Object) form for more detailed information.
     * <p>
     * @param  className - a String value of the class name
     * @param  message - logging message
     */
    private void errorWithClassMessage(String className, Object message) {
        error(className, message, null);
    }

    /**
     * Log a message object with the ERROR level with three arguments - className ,message and t.
     * <p>
     * See error(Object) form for more detailed information.
     * <p>
     *
     * @param  className -a String value of the class name
     * @param  message -logging message
     * @param  t -exception
     */
    private void error(String className, Object message, Throwable t) {
        if (StringUtils.isBlank(className)) {
            className = this.getClass().getName();
        }

        Logger logger = LogManager.getLogger(className);

        this.logGenericPrinting(logger, Level.ERROR, message, t);
    }

    /**
     * Log a message object with the FATAL level with two arguments - className & message
     * <p>
     * See fatal(Object) form for more detailed information.
     * <p>
     * @param  className - a String value of the class name
     * @param  message - logging message
     */
    private void fatalWithClassMessage(String className, Object message) {
        fatal(className, message, null);
    }

    /**
     * Log a message object with the FATAL level with three arguments - className ,message and t.
     * <p>
     * See fatal(Object) form for more detailed information.
     * <p>
     *
     * @param  className -a String value of the class name
     * @param  message -logging message
     * @param  t -exception
     */
    private void fatal(String className, Object message, Throwable t) {
        if (StringUtils.isBlank(className)) {
            className = this.getClass().getName();
        }

        Logger logger = LogManager.getLogger(className);

        this.logGenericPrinting(logger, Level.FATAL, message, t);
    }

    /**
     * Check whether this category is enabled for the DEBUG Level.
     *
     * @return boolean - true if this category is debug enabled, false otherwise.
     */
    public boolean isDebugEnabled() {
        return isDebugEnabled(className);
    }

    /**
     * Check whether this category is enabled for the DEBUG Level.
     *
     * @param  className a String value of the class name
     *
     * @return boolean - true if this category is debug enabled, false otherwise.
     */
    private boolean isDebugEnabled(String className) {
        Logger logger = LogManager.getLogger(className);

        return logger.isDebugEnabled();
    }

    /**
     * This is the most generic printing method. It is intended to be invoked by wrapper classes.
     * <p>
     * @param className - a String value of the class name
     * @param level - The level of the logging request.
     * @param logMessage - The message of the logging request.
     * @param t - The throwable of the logging request, may be null.
     */
    public void log(String className, Level level, Object logMessage, Throwable t) {
        this.logGenericPrinting(LogManager.getLogger(className), level, logMessage, t);
    }

    /**
     * This is the most generic printing method. It is intended to be invoked by wrapper classes.
     * <p>
     *
     * @param className - a String value of the class name
     * @param level - The level of the logging request.
     * @param logMessage - The message of the logging request.
     * @param t - The throwable of the logging request, may be null
     */
    private void logGenericPrinting(Logger logger, Level level, Object logMessage, Throwable t) {
        if (t != null) {
            logger.log(level, logMessage, t);
        } else {
            logger.log(level, logMessage);
        }
    }
    
    /**
	 * The method is purposed for extracting parameter values 
	 * from request & response objects and construct a string of those values
	 * @param handler
	 * @return
	 */
	public String getData(Object handler) {
		Class objClass = handler.getClass();
		Method[] method = objClass.getDeclaredMethods();
		StringBuffer requestString = new StringBuffer();
		String strRequestString = "";
		for (Method method2 : method) {
			//info(method2.getName());
				try {
				// ignore setter methods
				if (method2.getName().startsWith(SETTER) 
						|| (method2.getName().toLowerCase().indexOf(PASSWORD) != -1 ) 
						||(method2.getName().toLowerCase().indexOf(PRIVACY_DATA) != -1) ) { 
					continue;
				} else if (method2.getName().startsWith(GETTER)) {
					String methodName = method2.getName()
							.replace(GETTER, "").toLowerCase()
							+ "=";
					requestString.append(methodName);
					requestString.append(method2.invoke(handler,(Object)null));
					requestString.append(";");
				} 
												
			} catch (IllegalArgumentException e) {
				logger.error("Exception Occured in getData ::: " +e);
			} catch (IllegalAccessException e) {
				logger.error("Exception Occured in getData ::: " +e);
			} catch (InvocationTargetException e) {
				logger.error("Exception Occured in getData ::: " +e);
				}
			}
		strRequestString = requestString.toString();
		strRequestString=strRequestString.replaceAll("(cvv[a-z,A-Z,0-9,\\s]*=\\s*)(\\d*?)(\\s*;\\s*.*?)", "$1"+"***"+"$3");
		strRequestString=strRequestString.replaceAll("(routing[a-z,A-Z,0-9,\\s]*=\\s*)(\\d*?)(\\s*;\\s*.*?)", "$1"+"*******"+"$3");
		strRequestString=strRequestString.replaceAll("(ccnumber[a-z,A-Z,0-9,\\s]*=\\s*)(\\d*?)(\\s*;\\s*.*?)", "$1"+"******"+"$3");
		strRequestString=strRequestString.replaceAll("(bankacc[a-z,A-Z,0-9,\\s]*=\\s*)(\\d*?)(\\s*;\\s*.*?)", "$1"+"*******"+"$3");
	return strRequestString;
	}
	
	/**
	 * 
	 * @param className
	 * @param message
	 */
	public void logInfo(String className, String message){
		//System.out.println("TEST:"+ message);
		info(className, message, null);
	}
	
	
	
    public void logTransaction(String className, Map<String, String> userMap, Object requestObj, Object responseObj){
		Long timeElapsed = null;
		Long startTime = null;
		Long endTime = null;
		String strCallType = "";
		if(className.endsWith("Dao")) {
			strCallType = "DB ";
		} else if (className.endsWith("Proxy")) {
			strCallType = "CCS ";
		} else if (className.endsWith("Facade")) {
			strCallType = "CCS and DB ";
		}
		try{
			//if(userMap.containsKey(START_TIME)){
				//startTime =  Long.valueOf(userMap.get(START_TIME));
			//}
			
			//if(userMap.containsKey(END_TIME)){
				//endTime =  Long.valueOf(userMap.get(END_TIME));
			//}		
			
			//if(startTime != null && endTime != null){
				//timeElapsed = endTime - startTime;
			//}
			StringBuffer message = new StringBuffer(NEWLINE);
			message.append(strCallType);
			//message.append("Call Invoked for: "+((userMap.containsKey(TRANSACTION_TYPE))? userMap.get(TRANSACTION_TYPE):""));
			//message.append(NEWLINE);
			//message.append(END_POINT_URL+": "+((userMap.containsKey(END_POINT_URL))? userMap.get(END_POINT_URL):""));
			//message.append(NEWLINE);
			//message.append("Response Time(ms): "+((timeElapsed != null)? timeElapsed:""));		
			//message.append(NEWLINE);
			//message.append(REFERENCE_ID+": "+((userMap.containsKey(REFERENCE_ID))? userMap.get(REFERENCE_ID):""));
			//message.append(NEWLINE);
			message.append(REQUEST);
			if(null != requestObj){
				message.append(getData(requestObj));
			}
			message.append(NEWLINE);
			message.append(RESPONSE);
			if(null != responseObj){
				//message.append(getData(responseObj));
			}
			//System.out.println("LoggerUtil = "+message+":"+className);
			logInfo(className, message.toString());	
		}catch(Exception e){
			logger.error("Exception Occured in logTransaction ::: " +e);
		}

	}
}