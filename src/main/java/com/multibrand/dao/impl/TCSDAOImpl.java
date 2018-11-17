
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
import com.multibrand.dao.mapper.TCSBPDetailsRowMapper;
import com.multibrand.dto.response.TCSBPDetailsDTO;
import com.multibrand.util.Constants;

@Repository("tcsDAO")
public class TCSDAOImpl extends AbstractSpringDAO  implements TCSDAO, Constants {
	/**
	 * Holds a Logger instance
	 */
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	
	@Autowired(required = true)
	public TCSDAOImpl(
			@Qualifier("tcsReadJdbcTemplate") JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		init(TCSDAOImpl.class);
	}
	
	
	public List<TCSBPDetailsDTO> getBPDetails( String agreementId ) {
		
		String METHOD_NAME = "Load TCSDAOImpl: getBPDetails(..)";
		logger.debug("START:" + METHOD_NAME);
	
		
		List<TCSBPDetailsDTO> tcsBPDetailsDTOList = null;
		try {

			String sqlQuery = getSqlMessage().getMessage(DB_TCS_CA_BP_FROM_LEASE_ID ,  null, null );		
			
			tcsBPDetailsDTOList = getJdbcTemplate().query(sqlQuery,new Object[] {agreementId}, new TCSBPDetailsRowMapper());
			

		}  catch(DataAccessException de)
		{
			logger.error("DAO Exception in:" + METHOD_NAME , de);
			
		}			
		logger.debug("END:" + METHOD_NAME);
		return tcsBPDetailsDTOList;

	}	
}
