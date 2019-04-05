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

@Repository("contentDao")
public class ContentDaoImpl implements ContentDao, DBConstants {

	@Resource(name = "sqlQuerySource")
	protected AbstractMessageSource sqlMessage;

	@Autowired
	@Qualifier("gmeResJdbcTemplate")
	private JdbcTemplate gmeResJdbcTemplate;
	

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

}
