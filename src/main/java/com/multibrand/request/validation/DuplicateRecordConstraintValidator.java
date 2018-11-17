package com.multibrand.request.validation;

import java.io.Serializable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Constraint validator for validating the duplicate records.
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.x, Hibernate-validator 4.x and Jersey 1.17
 */
public class DuplicateRecordConstraintValidator implements
		ConstraintValidator<AssertDuplicateRecord, Serializable> {

	private static final Logger LOGGER = LogManager
			.getLogger(DuplicateRecordConstraintValidator.class);
	private Class<? extends DuplicateRecordValidator> duplicateRecordValidatorClass;

	/**
	 * Initializes duplicate record validator class.
	 * 
	 * @param constraintAnnotation
	 *            Indicates annotation of type
	 *            <code>AssertDuplicateRecord</code>.
	 */
	@Override
	public void initialize(AssertDuplicateRecord constraintAnnotation) {
		duplicateRecordValidatorClass = constraintAnnotation
				.duplicateRecordValidatorClass();
	}

	/**
	 * Check duplicate record by input <code>value</code>.
	 * 
	 * @param value
	 *            An <code>value</code> against which, the duplicate record will
	 *            be checked.
	 * @param constraintContext
	 *            Instance of <code>ConstraintValidatorContext</code>.
	 * @return <code>false</code> if duplicate record is found otherwise, it
	 *         returns <code>true</code>.
	 */
	@Override
	public boolean isValid(Serializable value,
			ConstraintValidatorContext constraintContext) {
		if (value == null) {
			return false;
		}
		boolean isDuplicateRecord = false;
		DuplicateRecordValidator validatorClassInstance;
		try {
			validatorClassInstance = duplicateRecordValidatorClass
					.newInstance();
			isDuplicateRecord = validatorClassInstance.isDuplicateRecord(value);
		} catch (Exception e) {
			LOGGER.error(
					"Exception occurred while processing the "
							+ "duplicate record using value = " + value
							+ " and validatorClass = "
							+ duplicateRecordValidatorClass.getName(), e);
			isDuplicateRecord = false;
		}
		boolean isValid = isDuplicateRecord ? false : true;
		LOGGER.debug("isDuplicateRecord = " + isDuplicateRecord);
		LOGGER.debug("isValid = " + isValid);
		return isValid;
	}

}
