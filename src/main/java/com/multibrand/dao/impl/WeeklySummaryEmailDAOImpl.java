package com.multibrand.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.multibrand.dao.AbstractSpringDAO;
import com.multibrand.dao.WeeklySummaryEmailDAO;
import com.multibrand.dto.response.PersonResponse;
import com.multibrand.util.Constants;
import com.multibrand.util.DateUtil;
import com.multibrand.vo.response.WeeklySummaryEmailResponse;
import com.multibrand.vo.response.WeeklySummaryEmailResponseList;

@Component("weeklySummaryEmailDAO")
public class WeeklySummaryEmailDAOImpl extends AbstractSpringDAO implements WeeklySummaryEmailDAO, Constants {

	@Autowired(required=true)
	public WeeklySummaryEmailDAOImpl(@Qualifier("cpdbJdbcTemplate") JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);					
	}

	@Override
	public WeeklySummaryEmailResponse getHistoricalWse(String contractAccountNumber, String companyCode) {
			logger.info("START WeeklySummaryEmailDAOImpl.historicalWse");
			WeeklySummaryEmailResponse weeklySummaryEmailResponse = new WeeklySummaryEmailResponse();
			weeklySummaryEmailResponse.setContractAccountNumber(contractAccountNumber);
			weeklySummaryEmailResponse.setCompanyCode(companyCode);
			if (StringUtils.isNotEmpty(contractAccountNumber) && StringUtils.isNotEmpty(companyCode)) {
				try {
					String sqlQuery = sqlMessage.getMessage(QUERY_GET_WEEKLY_SUMMARY_EMAIL, null, null);
					List<WeeklySummaryEmailResponseList> wseResponseList = getJdbcTemplate().query(
							sqlQuery, new Object[] { contractAccountNumber, companyCode },
							new RowMapper<WeeklySummaryEmailResponseList>() {
								@Override
								public WeeklySummaryEmailResponseList mapRow(ResultSet rs, int rowNo)
										throws SQLException {
									WeeklySummaryEmailResponseList dataRow = new WeeklySummaryEmailResponseList();
									dataRow.setEmailFileName(rs.getString("EMAIL_FILE_NAME"));
									dataRow.setEmailSentDate(DateUtil.getFormattedDate(MMddyyyy, DT_SQL_FMT_DB, rs.getString("EMAIL_SENT_DATE")));
									dataRow.setEmailAddress(rs.getString("EMAIL_ADDRESS"));
									dataRow.setContractAccountNumber(rs.getString("CONTRACT_ACCOUNT_NUMBER"));
									dataRow.setEmailUrl(rs.getString("VIEW_EMAIL_URL"));
									dataRow.setContractAccount(rs.getString("CONTRACT_NUMBER"));
									
									return dataRow;
								}
							});
					logger.info("END WeeklySummaryEmailDAOImpl.historicalWse" +wseResponseList.toString());
					if (wseResponseList != null && wseResponseList.size() > 0) {
						weeklySummaryEmailResponse.setWseList(wseResponseList);
					}
				} catch (Exception e) {
					logger.error("Problem occurred while getting a "
							+ "WSE details for contractAccountNumber: " + contractAccountNumber, e);
					weeklySummaryEmailResponse = null;
				}
			}
			logger.info("END WeeklySummaryEmailDAOImpl.historicalWse");
			return weeklySummaryEmailResponse;
		}
		
}
