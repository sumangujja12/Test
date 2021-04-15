package com.multibrand.dao.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.multibrand.dao.AbstractSpringDAO;
import com.multibrand.dao.SmartCarDAO;
import com.multibrand.util.Constants;


@Repository("smartCarDAO")
public class SmartCarDaoImpl extends AbstractSpringDAO implements SmartCarDAO,Constants {


	@Resource(name = "smartCarJdbcTemplate")
    JdbcTemplate smartCarJdbcTemplate;
	
	@Autowired(required = true)
	public SmartCarDaoImpl(
			@Qualifier("smartCarJdbcTemplate") JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		init(SmartCarDaoImpl.class);
	}

	@Override
	public List<Map<String, Object>> getUserSmartCarTokens(String userUniqueId) {
	
		logger.info("SmartCarDaoImpl.getUserSmartCarTokens----------Start{}",userUniqueId);
		
		String query = getSqlMessage().getMessage(QUERY_GET_SMART_CAR_USER_PROFILE, null, null);
		logger.info("getAccessTokenById query:{}", query);
		Object[] insAccountParams = new Object[] { userUniqueId };
		List<Map<String, Object>> resultList = smartCarJdbcTemplate.queryForList(query, insAccountParams);
		logger.info("Value getUserSmartCarTokens of i : Rows Affected:{}", resultList);
		
		return resultList;
	}
	
}
