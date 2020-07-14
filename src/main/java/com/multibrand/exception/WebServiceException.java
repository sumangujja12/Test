package com.multibrand.exception;

/**
 * 
 * @author rpendur1
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class WebServiceException extends Exception{
	
	

	/**
     *
     */
    public WebServiceException() {

        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     */
    public WebServiceException(String arg0) {
        super(arg0);

        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     */
    public WebServiceException(Throwable arg0) {
        super(arg0);

        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     * @param arg1
     */
    public WebServiceException(String arg0, Throwable arg1) {
        super(arg0, arg1);

        // TODO Auto-generated constructor stub
    }
    
}
