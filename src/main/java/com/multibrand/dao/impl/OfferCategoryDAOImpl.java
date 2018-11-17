package com.multibrand.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.multibrand.dao.BaseCpdbJdbcDAO;
import com.multibrand.dao.OfferCategoryDAOIF;
import com.multibrand.util.Constants;

@Component("offerCategoryDAO")
public class OfferCategoryDAOImpl extends BaseCpdbJdbcDAO implements OfferCategoryDAOIF, Constants {
	
	@Resource(name = "cpdbJdbcTemplate")
    JdbcTemplate jdbcTemplate;
	
	Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	@Autowired(required=true)
	public OfferCategoryDAOImpl(@Qualifier("cpdbJdbcTemplate") JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
				
		
	}
	
	@Override
	public List<Map<String, Object>>  getOfferCategoryLookupDetails(String inputOfferCode){
		/*
		String sqlQuery="select oclu.offer_code , oclu.saleable , oclu.offer_category from offer_category_lookup oclu " +
  		" where oclu.offer_category='PREPAY' and offer_code in("+inputOfferCode+")";
		*/
		String sqlQuery="select OFFER_CODE , SALEABLE , OFFER_CATEGORY from offer_category_lookup " +
		  		" where offer_code in("+inputOfferCode+")";
		if(logger.isDebugEnabled()) {
			logger.debug("getOfferCategoryLookupDetails : query for offer lookup : " + sqlQuery);
		}
		List<Map<String, Object>> offerCategoryLookUp = getMapDataWithoutParam(jdbcTemplate,sqlQuery);
		return offerCategoryLookUp;		
	}
	
	  protected List<Map<String, Object>> getMapDataWithoutParam(JdbcTemplate jdbcTemplate,String sqlQuery) {
		    List<Map<String, Object>> resultList = null;
		    resultList = jdbcTemplate.queryForList(sqlQuery);

		    return resultList;
	  }
	

}
	

