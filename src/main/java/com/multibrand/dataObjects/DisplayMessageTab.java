package com.multibrand.dataObjects;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class DisplayMessageTab extends DisplayMessage implements SQLData{
	
	public String getSQLTypeName() throws SQLException {
        return "DISP_MSG_TYP";
    }

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		setDisplayMessage(stream.readString());
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(getDisplayMessage());
	}

}
