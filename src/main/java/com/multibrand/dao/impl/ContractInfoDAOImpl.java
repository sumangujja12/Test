package com.multibrand.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.multibrand.dao.BaseCpdbJdbcDAO;
import com.multibrand.dao.ContractInfoDAO;
import com.multibrand.dao.mapper.CustomerInfoRowMapper;
import com.multibrand.dataObjects.Contract;
import com.multibrand.exception.OAMException;
import com.multibrand.util.Constants;

@Component("contractinfodao")
public class ContractInfoDAOImpl extends BaseCpdbJdbcDAO implements ContractInfoDAO, Constants {
	
	Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	@Autowired(required=true)
	public ContractInfoDAOImpl(@Qualifier("cpdbJdbcTemplate") JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
				
		
	}
	
	@Override
	public List<Contract> getContractInfo(String zipcode, String streetnumber, String streetname, String city, String state, String country, String unitnumber, String ponumber) throws OAMException {
		//StringBuffer sqlQuery = new StringBuffer("select c.esi_id esi, b.business_partner_id bp, c.contract_account_number ca, c.contract_id contract, c.contract_end_date contract_end_date, c.contract_start_date contract_start_date from bpca b, contract c where sysdate>=c.contract_start_date and sysdate<=c.contract_end_date  and c.contract_account_number=b.contract_account_number and b.address_id in (");
		StringBuffer sqlQuery = new StringBuffer("select c.esi_id , b.business_partner_id , c.contract_account_number , c.contract_id , c.contract_end_date , c.contract_start_date, c.tdsp_code  from bpca b, contract c where c.contract_account_number=b.contract_account_number and b.address_id in (");
		StringBuffer adrQuery = new StringBuffer("select address_id from address where ");
		boolean first=false;
		if (StringUtils.isNotBlank(zipcode)) {
			adrQuery.append("ZIP_CODE='"+StringEscapeUtils.escapeSql(zipcode)+"' ");first=true;
		} else adrQuery.append("ZIP_CODE is null ");
		if (StringUtils.isNotBlank(streetnumber)) {
			adrQuery.append("and STREET_NUMBER='"+StringEscapeUtils.escapeSql(streetnumber)+"' ");
		} else adrQuery.append("and STREET_NUMBER is null ");
		if (StringUtils.isNotBlank(streetname)) {
			adrQuery.append("and STREET_NAME='"+StringEscapeUtils.escapeSql(streetname)+"' ");
		} else adrQuery.append("and STREET_NAME is null ");
		if (StringUtils.isNotBlank(city)) {
			adrQuery.append("and CITY='"+StringEscapeUtils.escapeSql(city)+"' ");
		} else adrQuery.append("and CITY is null ");
		if (StringUtils.isNotBlank(state)) {
			adrQuery.append("and STATE='"+StringEscapeUtils.escapeSql(state)+"' ");
		} else adrQuery.append("and STATE is null ");		
		if (StringUtils.isNotBlank(country)) {
			adrQuery.append("and COUNTRY='"+StringEscapeUtils.escapeSql(country)+"' ");
		} else adrQuery.append("and COUNTRY is null ");
		if (StringUtils.isNotBlank(unitnumber)) {
			adrQuery.append("and UNIT_NUMBER='"+StringEscapeUtils.escapeSql(unitnumber)+"' ");
		} else adrQuery.append("and UNIT_NUMBER is null ");
		if (StringUtils.isNotBlank(ponumber)) {
			adrQuery.append("and PO_BOX='"+StringEscapeUtils.escapeSql(ponumber)+"' ");
		} else adrQuery.append("and PO_BOX is null ");	
		sqlQuery.append(adrQuery.toString()).append(") order by c.contract_end_date desc ");		
		final String sql=sqlQuery.toString();
		logger.info("--------- Inside getContractInfo Method ---- sql="+sqlQuery.toString());
		List<Contract> contracts=null;
		
		try{
			 
			contracts = getJdbcTemplate().query(sql,new CustomerInfoRowMapper());
					
		} catch(Exception exception){
			logger.error("getContractInfo Error: " + exception.toString(),exception);
		}
		 
		return contracts;	
	}
	
	@Resource(name = "cpdbJdbcTemplate")
    JdbcTemplate jdbcTemplate;
}
