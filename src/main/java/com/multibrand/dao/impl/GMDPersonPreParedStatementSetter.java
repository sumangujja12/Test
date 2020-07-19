package com.multibrand.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.multibrand.dto.GMDPersonDetailsDTO;
import com.multibrand.resources.GMDResource;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;

public class GMDPersonPreParedStatementSetter implements PreparedStatementSetter {
	private static Logger logger = LogManager.getLogger(GMDResource.class);
	private GMDPersonDetailsDTO personDTO;

	public GMDPersonPreParedStatementSetter(GMDPersonDetailsDTO personDTO) {
		this.personDTO = personDTO;
	}

	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
		ps.setInt(1,personDTO.getPersonId());
		System.out.println(personDTO.getPersonId());
		ps.setString(2, personDTO.getNameFirst());
		ps.setString(3, personDTO.getNameLast());
		if (StringUtils.isNotBlank(personDTO.getDateOfBirth())) {
			ps.setDate(4, CommonUtil.getSqlDate(personDTO.getDateOfBirth(), Constants.MMddyyyy));
		} else {
			ps.setString(4, null);
		}
		ps.setString(5, personDTO.getPhoneNumber());
		ps.setString(6, personDTO.getEmailAddress());
		ps.setString(7, "");
		ps.setString(8, "");
		ps.setString(9, personDTO.getKeepMeInformedFlag());
		ps.setString(10, personDTO.getIdocNumber());
		ps.setString(11, personDTO.getPersonStatus());
		ps.setString(12,"");
		ps.setString(13, personDTO.getLanguagePref());
		ps.setString(14, personDTO.getBusinessPartnerNumber());
		ps.setString(15, Constants.GME_COMPANY_CODE);
		ps.setString(16, "");
	}

}
