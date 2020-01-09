package com.multibrand.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.multibrand.dao.AbstractSpringDAO;
import com.multibrand.dao.WeeklySummaryEmailDAO;
import com.multibrand.util.Constants;
import com.multibrand.util.DateUtil;
import com.multibrand.vo.request.WeeklySummaryEmailRequest;
import com.multibrand.vo.response.WeeklySummaryEmailResponse;
import com.multibrand.vo.response.WseDO;
import com.multibrand.vo.response.WseResponse;

@Component("weeklySummaryEmailDAO")
public class WeeklySummaryEmailDAOImpl extends AbstractSpringDAO implements WeeklySummaryEmailDAO, Constants {

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	@Autowired(required=true)
	public WeeklySummaryEmailDAOImpl(@Qualifier("cpdbJdbcTemplate") JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);					
	}

	@Override
	public WeeklySummaryEmailResponse getHistoricalWse(WeeklySummaryEmailRequest request) {
			logger.info("START WeeklySummaryEmailDAOImpl.historicalWse");
			WeeklySummaryEmailResponse weeklySummaryEmailResponse = new WeeklySummaryEmailResponse();

			if (StringUtils.isNotEmpty(request.getContractAccountNumber()) && StringUtils.isNotEmpty(request.getCompanyCode())) {
				try {
					
					weeklySummaryEmailResponse.setContractAccountNumber(request.getContractAccountNumber());
					weeklySummaryEmailResponse.setCompanyCode(request.getCompanyCode());
					
					String sqlQuery = sqlMessage.getMessage(QUERY_GET_WEEKLY_SUMMARY_EMAIL, null, null);
					List<WseResponse> wseResponseList = getJdbcTemplate().query(
							sqlQuery, new Object[] { request.getContractAccountNumber(), request.getCompanyCode(), request.getWseReportTotaldays()},
							new RowMapper<WseResponse>() {
								@Override
								public WseResponse mapRow(ResultSet rs, int rowNo)
										throws SQLException {
									WseDO dataRow = new WseDO();
									WseResponse wseResponse = new WseResponse();
									dataRow.setEmailFileName(rs.getString("EMAIL_FILE_NAME"));
									dataRow.setEmailSentDate(DateUtil.getFormattedDate(MMddyyyy, DT_SQL_FMT_DB, rs.getString("EMAIL_SENT_DATE")));
									dataRow.setEmailAddress(rs.getString("EMAIL_ADDRESS"));
									dataRow.setContractAccountNumber(rs.getString("CONTRACT_ACCOUNT_NUMBER"));
									dataRow.setEmailUrl(rs.getString("VIEW_EMAIL_URL"));
									dataRow.setContractAccount(rs.getString("CONTRACT_NUMBER"));
									wseResponse.setWse(dataRow);
									return wseResponse;
								}
							});
					if (wseResponseList != null && wseResponseList.size() > 0) {
						weeklySummaryEmailResponse.setWseList(wseResponseList);
					} else {
						weeklySummaryEmailResponse.setErrorCode("ERR_NO_DATA");
						weeklySummaryEmailResponse.setErrorDescription(Constants.ERR_NO_DATA);
					}
				} catch(DataAccessException ex){
					logger.error("Error occurred while getting WSE details : " + ex);
					weeklySummaryEmailResponse.setErrorCode("ERR_DB");
					weeklySummaryEmailResponse.setErrorDescription(Constants.ERR_DB);
				} catch(Exception e){
					logger.error("Error occurred while getting WSE details : " + e);
					weeklySummaryEmailResponse.setErrorCode("ERR_UNKNOWN");
					weeklySummaryEmailResponse.setErrorDescription(Constants.ERR_UNKNOWN);
				}
			}
			logger.info("END WeeklySummaryEmailDAOImpl.historicalWse");
			return weeklySummaryEmailResponse;
		}
		
}
