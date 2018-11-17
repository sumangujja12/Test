package com.multibrand.request.validation;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * An immutable class that stores error message details.
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.x and Jersey 1.17
 */
public class ErrorMessageDescriptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String messageCode;
	private final String messageCodeText;
	private final String message;

	/**
	 * Default constructor.
	 * 
	 * @param messageCode
	 *            Error message code.
	 * @param messageCode
	 *            Error message code text.
	 * @param message
	 *            Error message text.
	 */
	public ErrorMessageDescriptor(String messageCode, String messageCodeText,
			String message) {
		this.messageCode = messageCode;
		this.messageCodeText = messageCodeText;
		this.message = message;
	}

	/**
	 * @return the messageCode
	 */
	public String getMessageCode() {
		return messageCode;
	}

	/**
	 * @return the messageCodeText
	 */
	public String getMessageCodeText() {
		return messageCodeText;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
