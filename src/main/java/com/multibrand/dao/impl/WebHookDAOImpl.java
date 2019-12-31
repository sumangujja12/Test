package com.multibrand.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.multibrand.dao.AbstractSpringDAO;
import com.multibrand.dao.WebHookDAOIF;
import com.multibrand.dto.request.WebHookRequest;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.WebHookResponse;

@Component("webhookdao")
public class WebHookDAOImpl extends AbstractSpringDAO implements WebHookDAOIF, Constants {
	
	@Autowired(required = true)
	public WebHookDAOImpl(
			@Qualifier("cpdbJdbcTemplate") JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		init(WebHookDAOImpl.class);
	}
	
	public WebHookResponse addWebHookData(WebHookRequest request) {
		
		WebHookResponse response = new WebHookResponse();
		
		int update = 0;
		logger.info("WebHookDAOImpl.addWebHookData()");
		try {
			Object[] params = new Object[] {request.getPaymentId(), request.getAccountNumber(), request.getAccountId(), request.getWebHookMetadata().getExternalAccountId()};  

			String sqlQuery = sqlMessage.getMessage(SQL_ADD_WEB_HOOK, null, null);
			update = getJdbcTemplate().update(sqlQuery, params);

			if (update == 1) {
				response.setResultcode(Constants.ZERO);
				response.setResultdescription(Constants.MSG_SUCCESS);	
				
				if (logger.isDebugEnabled()) {
					logger.debug("Inserted record successfully");
				}
			} else	{
				response.setResultcode(Constants.RESULT_CODE_EXCEPTION_FAILURE);
				response.setResultdescription(Constants.RESULT_DESCRIPTION_EXCEPTION);
				
				if (logger.isDebugEnabled()) {
					logger.debug("Failed to insert record");
				}
			}
			
		} catch (DataAccessException ex) {
			logger.info(ex);
			logger.error(ex);
			response.setResultcode(Constants.RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultdescription(Constants.RESULT_DESCRIPTION_EXCEPTION);
		}
		return response;
	}
}
	

