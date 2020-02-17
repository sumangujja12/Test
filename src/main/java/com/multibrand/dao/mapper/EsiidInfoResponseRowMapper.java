package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.multibrand.util.DBConstants;
import com.multibrand.vo.request.ESIDData;


public class EsiidInfoResponseRowMapper implements RowMapper<ESIDData>, DBConstants {

	@Override
	public ESIDData mapRow(ResultSet resultSet, int arg1) throws SQLException {
		ESIDData getEsiidResponse = new ESIDData();
		getEsiidResponse.setEsidNumber(resultSet.getString(V_ESID));
		getEsiidResponse.setEsidClass(resultSet.getString(V_CLASS));
		getEsiidResponse.setEsidDeposit(resultSet.getString(V_DEPOSIT));
		getEsiidResponse.setEsidTDSP(resultSet.getString(V_TDSP));
		getEsiidResponse.setEsidStatus(resultSet.getString(V_STATUS));
		getEsiidResponse.setPremiseType(resultSet.getString(V_PERMISETYPE));
		return getEsiidResponse;
	}

}


