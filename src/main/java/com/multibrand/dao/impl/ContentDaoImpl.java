package com.multibrand.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.multibrand.dao.ContentDao;
import com.multibrand.dto.MobileContentDto;
import com.multibrand.util.DBConstants;
import com.multibrand.vo.request.MaintenanceScheduleRequest;
import com.multibrand.vo.response.contentResponse.MaintenanceSchedule;

@Repository("contentDao")
public class ContentDaoImpl implements ContentDao, DBConstants {

	@Resource(name = "sqlQuerySource")
	protected AbstractMessageSource sqlMessage;

	@Autowired
	@Qualifier("gmeResJdbcTemplate")
	private JdbcTemplate gmeResJdbcTemplate;
	
	@Autowired
	@Qualifier("cpdbJdbcTemplate")
	private JdbcTemplate cpdbJdbcTemplate;
	

	@Override
	public List<MobileContentDto> getMobileContentData() {
		
		//Getting Data from the Content table.
		List<MobileContentDto> contents = null;
		String getAllContent = sqlMessage.getMessage(DBConstants.QUERY_GET_ALL_CONTENT, null, null);
			contents = gmeResJdbcTemplate.query(getAllContent, new RowMapper<MobileContentDto>() {
				public MobileContentDto mapRow(ResultSet rs, int row) throws SQLException {
					MobileContentDto cDto = new MobileContentDto();
					cDto.setContentId(Integer.valueOf(rs.getInt(DBConstants.COL_MOBILE_CONTENT_CNTNT_ID)));
					cDto.setArea(rs.getString(DBConstants.COL_MOBILE_CONTENT_AREA));
					cDto.setLanuage(rs.getString(DBConstants.COL_MOBILE_CONTENT_LANG));
					cDto.setName(rs.getString(DBConstants.COL_MOBILE_CONTENT_NAME));
					cDto.setValue(rs.getString(DBConstants.COL_MOBILE_CONTENT_VALUE));
					return cDto;
				}
			});
		return contents;
	}
	
	@Override
	public List<MaintenanceSchedule> getMaintenanceSchedule(MaintenanceScheduleRequest request) {
		List<MaintenanceSchedule> schedules;
		//this query has been put as String  rather put it in dbsql.proprties due to input parameters type mismatch.
		String maintenanceScheduleQuery = " SELECT OUTAGE_STATUS, OUTAGE_MESSAGE,SCHEDULED_OUTAGE_FLAG,  "
				+ " TO_CHAR(SCHEDULED_OUTAGE_START, 'yyyy-mm-dd hh24:mi:ss') AS SCHEDULED_OUTAGE_START, "
				+ " TO_CHAR(SCHEDULED_OUTAGE_END, 'yyyy-mm-dd hh24:mi:ss') AS SCHEDULED_OUTAGE_END, "
				+ " DYNAMIC_MESSAGE_TYPE,DYNAMIC_MESSAGE_STATUS,DYNAMIC_MESSAGE_CTR, "
				+ " NON_SCHEDULED_OUTAGE_FLAG, TO_CHAR(NON_SCHEDULED_OUTAGE_START, 'yyyy-mm-dd hh24:mi:ss') AS NON_SCHEDULED_OUTAGE_START, "
				+ " TO_CHAR(NON_SCHEDULED_OUTAGE_END, 'yyyy-mm-dd hh24:mi:ss') AS NON_SCHEDULED_OUTAGE_END,MSG_PRIORITY, "
				+ " MESSAGE_CODE " + " FROM CPDB1_MAIN.COMPONENT_OUTAGE " + " WHERE OUTAGE_STATUS='Y' " + " AND APPLICATION_COMPANY_CODE= '"
				+ request.getCompanyCode() + "'";

		schedules = cpdbJdbcTemplate.query(maintenanceScheduleQuery, new RowMapper<MaintenanceSchedule>() {
			public MaintenanceSchedule mapRow(ResultSet rs, int row) throws SQLException {
				MaintenanceSchedule schedule = new MaintenanceSchedule();
				schedule.setOutageStatus(rs.getString(DBConstants.COL_OUTAGE_STATUS));
				schedule.setOutageMessage(rs.getString(DBConstants.COL_OUTAGE_MESSAGE));
				schedule.setScheduledOutageFlag(rs.getString(DBConstants.COL_SCHEDULED_OUTAGE_FLAG));
				schedule.setScheduledOutageStart(rs.getString(DBConstants.COL_SCHEDULED_OUTAGE_START));
				schedule.setScheduledOutageEnd(rs.getString(DBConstants.COL_SCHEDULED_OUTAGE_END));
				schedule.setDynamicMessageType(rs.getString(DBConstants.COL_DYNAMIC_MESSAGE_TYPE));
				schedule.setDynamicMessageStatus(rs.getString(DBConstants.COL_DYNAMIC_MESSAGE_STATUS));
				schedule.setDynamicMessageCtr(rs.getString(DBConstants.COL_DYNAMIC_MESSAGE_CTR));
				schedule.setNonScheduledOutageFlag(rs.getString(DBConstants.COL_NON_SCHEDULED_OUTAGE_FLAG));
				schedule.setNonScheduledOutageStart(rs.getString(DBConstants.COL_NON_SCHEDULED_OUTAGE_START));
				schedule.setNonScheduledOutageEnd(rs.getString(DBConstants.COL_NON_SCHEDULED_OUTAGE_END));
				schedule.setMessagePriority(rs.getString(DBConstants.COL_MSG_PRIORITY));
				schedule.setMessageCode(rs.getString(DBConstants.COL_MESSAGE_CODE));
				return schedule;
			}
		});
		return schedules;
	}

}
