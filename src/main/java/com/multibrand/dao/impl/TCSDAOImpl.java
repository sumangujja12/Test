
package com.multibrand.dao.impl;

import java.util.List;

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
	
public TCSPersonalizedFlagsDTO getPersonalizedFlags(String bp, String ca, String co) {
		
		String methodName = "Load TCSDAOImpl: getPersonalizedFlags(..)";
		logger.debug("START:" + methodName);
	
		List<TCSPersonalizedFlagsDTO> tcsPersonalizedFlagsDTO = null;
		try {

			String sqlQuery = getSqlMessage().getMessage(DB_TCS_PERSONALIZED_FLAGS_FROM_CUST_BASE ,  null, null );		
			
			tcsPersonalizedFlagsDTO = getJdbcTemplate().query(sqlQuery,new Object[] {bp,ca,co}, new TCSPersonalizedFlagsRowMapper());
			
			if ( tcsPersonalizedFlagsDTO.isEmpty() ){
			  return null;
			}else {
			  return tcsPersonalizedFlagsDTO.get(0);
			}

		}  catch(DataAccessException de)
		{
			logger.error("DAO Exception in:" + methodName , de);
			
		}			
		logger.debug("END:" + methodName);
		return null;
	}	
	
}
