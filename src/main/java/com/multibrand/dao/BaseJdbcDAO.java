package com.multibrand.dao;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.multibrand.util.Constants;


/**
 * This base class is typically used by all Spring JDBC DAO
 * 
 * @author kdeshmu1
 * 
 */

@Repository
public abstract class BaseJdbcDAO implements Constants {

	private JdbcTemplate jdbcTemplate;

	private ReloadableResourceBundleMessageSource sqlMessage;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	protected void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public ReloadableResourceBundleMessageSource getSqlMessage() {
		return sqlMessage;
	}

	protected void setSqlMessage(
			ReloadableResourceBundleMessageSource sqlMessage) {
		this.sqlMessage = sqlMessage;
	}

	
}