package com.multibrand.request.validation;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.context.ContextLoader;

import com.multibrand.dao.ServiceLocationDao;
import com.multibrand.dto.response.ServiceLocationResponse;

/**
 * Validator for checking duplicate enrollment.
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.x, Hibernate-validator 4.x and Jersey 1.17
 */
public class EnrollmentDuplicateRecordValidator implements
		DuplicateRecordValidator {

	private static final Logger LOGGER = LogManager
			.getLogger(EnrollmentDuplicateRecordValidator.class);

	/**
	 * Default constructor.
	 */
	public EnrollmentDuplicateRecordValidator() {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDuplicateRecord(Serializable value) {
		boolean isDuplicateRecord = false;
		LOGGER.debug("Entity value = " + value);
		if (value != null && value instanceof String) {
			ServiceLocationDao dao = ContextLoader
					.getCurrentWebApplicationContext().getBean(
							"serviceLocationDAO", ServiceLocationDao.class);
			String trackingId = (String) value;
			ServiceLocationResponse entity = dao.getServiceLocation(trackingId);
			if (entity != null) {
				String contractAccountNo = entity.getContractAccountNum();
				LOGGER.debug("contractAccountNo = " + contractAccountNo);
				// For this trackingId if, CA is not empty means it is a
				// duplicate enrollment record.
				isDuplicateRecord = StringUtils.isNotEmpty(contractAccountNo);
			}
		}
		LOGGER.debug("isDuplicateRecord = " + isDuplicateRecord);
		return isDuplicateRecord;
	}

}
