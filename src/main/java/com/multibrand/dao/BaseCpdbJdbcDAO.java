package com.multibrand.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * This base class is typically used by all CPDB related JDBC DAO
 * 
 * @author kdeshmu1
 * 
 */

@Repository
public class BaseCpdbJdbcDAO extends BaseJdbcDAO {
	/**
	 * Holds a Logger instance
	 */
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	public final int MIN_VALUE = 0;
	public final int MAX_VALUE = 999999;

	@Autowired
	public BaseCpdbJdbcDAO(@Qualifier("cpdbJdbcTemplate") JdbcTemplate jdbcTemplate) {

		setJdbcTemplate(jdbcTemplate);
	}
}
