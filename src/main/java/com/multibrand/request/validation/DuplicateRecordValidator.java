package com.multibrand.request.validation;

import java.io.Serializable;

/**
 * Validator for duplicate record.
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.x and Jersey 1.17
 */
public interface DuplicateRecordValidator {

	/**
	 * Checks duplicate record.
	 * 
	 * @param value
	 *            An <code>value</code> against which, the duplicate record will
	 *            be checked.
	 * @return <code>true</code> if, it contains duplicate record otherwise,
	 *         returns <code>false</code>.
	 */
	public boolean isDuplicateRecord(Serializable value);

}
