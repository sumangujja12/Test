package com.multibrand.dao;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * This base class is typically used by all Cirro related JDBC DAO
 * 
 * @author agarneh
 * 
 */

@Repository
public class BaseCirroJdbcDAO extends BaseJdbcDAO {
	/**
	 * Holds a Logger instance
	 */
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	public final int MIN_VALUE = 0;
	public final int MAX_VALUE = 999999;

	@Autowired
	public BaseCirroJdbcDAO(
			@Qualifier("cirroJdbcTemplate") JdbcTemplate jdbcTemplate)
			{

		setJdbcTemplate(jdbcTemplate);
	}

	// The following method is added for testing multiple jdbc template
	public String getSchema() {
		String schemaName = null;
		try {
			schemaName = getJdbcTemplate().getDataSource().getConnection()
					.getMetaData().getUserName();
			logger.info("Schema Name in ProfileDAO :" + schemaName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in getSchema :", e);

		}
		return schemaName;
	}

}