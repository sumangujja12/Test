package com.multibrand.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.multibrand.util.DBConstants;

@Repository("abstractSpringDAO")
public abstract class AbstractSpringDAO
  implements DBConstants
{
  protected JdbcTemplate jdbcTemplate;
  protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  protected Logger logger = null;

  @Resource(name="sqlQuerySource")
  protected AbstractMessageSource sqlMessage;

  protected AbstractSpringDAO(JdbcTemplate jdbcTemplate) { setJdbcTemplate(jdbcTemplate); }

  protected AbstractSpringDAO(JdbcTemplate jdbcTemplate, ReloadableResourceBundleMessageSource sqlMessage)
  {
    setJdbcTemplate(jdbcTemplate);
    setSqlMessage(sqlMessage);
  }

  protected void init(Class<? extends AbstractSpringDAO> className) {
    this.logger = LogManager.getLogger(className);
  }

  public JdbcTemplate getJdbcTemplate() {
    return this.jdbcTemplate;
  }

  private void setJdbcTemplate(JdbcTemplate jdbcTemplate)
  {
    this.jdbcTemplate = jdbcTemplate;
  }

  protected <T> List<T> getDataWithParam(Map param, String sqlQuery, RowMapper<T> rowMapper) {
    List<T> resultList = null;
    resultList = this.namedParameterJdbcTemplate.query(sqlQuery, param, rowMapper);
    return resultList;
  }

  protected <T> List<T> getDataWithOutParam(String sqlQuery, RowMapper<T> rowMapper) {
    List<T> resultList = null;
    resultList = getJdbcTemplate().query(sqlQuery, rowMapper);
    return resultList;
  }

  protected String getDataWithOutParam(String sqlQuery)
  {
    Integer nextVal = Integer.valueOf(0);
    String retVal = null;
    try {
      nextVal = getJdbcTemplate().queryForObject(sqlQuery, Integer.class);
    } catch (DataAccessException dae) {
      this.logger.error("getDataWithOutParam returing String: Exception while performing DB operation : " + dae.getMessage());
    }
    catch (Throwable t)
    {
      this.logger.error("getDataWithOutParam returing String: Throwable Exception while performing DB operation : " + t.getMessage());
    }

    if ((nextVal != null) && (nextVal.intValue() > 0)) {
      retVal = nextVal.toString();
    }
    return retVal;
  }

  protected List<Map<String, Object>> getMapDataWithoutParam(String sqlQuery) {
    List<Map<String, Object>> resultList = null;
    resultList = getJdbcTemplate().queryForList(sqlQuery);

    return resultList;
  }

  protected int getIntWithoutParam(String sqlQuery)
  {
    int status = 0;

    status = getJdbcTemplate().update(sqlQuery);
    this.logger.info("The status is : " + status);
    return status;
  }

  public AbstractMessageSource getSqlMessage()
  {
    return this.sqlMessage;
  }

  public void setSqlMessage(AbstractMessageSource sqlMessage) {
    this.sqlMessage = sqlMessage;
  }
}