
package com.multibrand.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.multibrand.dao.AbstractSpringDAO;
import com.multibrand.dao.TCSDAO;
import com.multibrand.dao.mapper.TCSPersonalizedFlagsRowMapper;
import com.multibrand.dto.response.TCSPersonalizedFlagsDTO;
import com.multibrand.util.Constants;

@Repository("tcsDAO")
public class TCSDAOImpl extends AbstractSpringDAO  implements TCSDAO, Constants {
	/**
	 * Holds a Logger instance
	 */
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Autowired(required = true)
	public TCSDAOImpl(@Qualifier("tcsReadJdbcTemplate") JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		init(TCSDAOImpl.class);
	}
	
public TCSPersonalizedFlagsDTO getPersonalizedFlags(String bp, String ca) {
		
		String methodName = "Load TCSDAOImpl: getPersonalizedFlags(..)";
		logger.debug("START:" + methodName);
	
		TCSPersonalizedFlagsDTO tcsPersonalizedFlagsDTO = null;
		try {

			String sqlQuery = getSqlMessage().getMessage(DB_TCS_PERSONALIZED_FLAGS_FROM_CUST_BASE ,  null, null );		
			
			tcsPersonalizedFlagsDTO = getJdbcTemplate().queryForObject(sqlQuery,new Object[] {bp,ca}, new TCSPersonalizedFlagsRowMapper());

		}  catch(DataAccessException de)
		{
			logger.error("DAO Exception in:" + methodName , de);
			
		}			
		logger.debug("END:" + methodName);
		return tcsPersonalizedFlagsDTO;
	}	
	
}
