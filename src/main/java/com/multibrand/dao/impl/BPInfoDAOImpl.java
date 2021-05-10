package com.multibrand.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.multibrand.dao.AbstractSpringDAO;
import com.multibrand.dao.BpInfoDAOIF;
import com.multibrand.util.Constants;
import com.multibrand.util.DBConstants;

@Repository("bpInfoDAO")
public class BPInfoDAOImpl extends AbstractSpringDAO implements
BpInfoDAOIF, Constants {

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	@Autowired
	@Qualifier("appConstMessageSource")
	protected ReloadableResourceBundleMessageSource appConstMessageSource;
	
	
	@Autowired(required = true)
	public BPInfoDAOImpl(
			@Qualifier("cpdbJdbcTemplate") JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		init(BPInfoDAOImpl.class);
	}
	/**
	 * Method to get BPs for a given userName
	 * @author kdeshmu1
	 * @param uid
	 * @return
	 */
	public  List<String> getBPforUserId(String uid) {
		List<String> bp= new ArrayList<>();
		logger.debug("--Inside getBPforUserId----- ");
		try {
			Object[] inParams = { "'" + uid + "'" };
			String query =getSqlMessage().getMessage(DBConstants.SMS_SELECT_PROFILE_GET_BP_NUMBER, inParams,null);			
			bp= getJdbcTemplate().queryForList(query,String.class);	
				
		}
		catch (Exception ex) {
		
			logger.error(ex);
		}
		logger.debug("--getBPforUserId End-- ");
		return bp;
	}
}
