package com.multibrand.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.multibrand.dao.AbstractSpringDAO;
import com.multibrand.dao.ExternalContentDao;

@Repository("externalContentDao")
public class ExternalContentDaoImpl extends AbstractSpringDAO implements
		ExternalContentDao {

	@Autowired(required = true)
	public ExternalContentDaoImpl(
			@Qualifier("choiceJdbcTemplate") JdbcTemplate externalContentJdbcTemplate) {
		super(externalContentJdbcTemplate);
		init(ExternalContentDaoImpl.class);
	}

	public String getNextValForSequence(String sequence) {
		String retVal = "";
		String query = sqlMessage.getMessage(QUERY_SEQUENCE_NEXTVAL,
				new Object[] { sequence }, null);

		retVal = getDataWithOutParam(query);
		if (logger.isDebugEnabled()) {
			logger.debug("getNextValForSequence : next value for sequence : "
					+ sequence + " is " + retVal);
		}
		return retVal;
	}

}
