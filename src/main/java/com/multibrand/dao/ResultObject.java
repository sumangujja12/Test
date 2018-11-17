package com.multibrand.dao;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 
 * This class is used to set the database stored procedure output parameter sql
 * data type and result set mapper/handler object.
 * 
 * 
 * @author jyogapa1
 * 
 * @version $Revision: 1.0 $
 */
public class ResultObject {
	/**
	 * Field sqlType.
	 */
	private Integer sqlType;
	/**
	 * Field resultHandler.
	 */
	private Object resultHandler;

	/**
	 * Constructor for ResultObject.
	 */
	public ResultObject() {
		super();
	}

	/**
	 * Constructor for ResultObject.
	 * 
	 * @param sqlType
	 */
	public ResultObject(Integer sqlType) {
		super();
		this.sqlType = sqlType;
	}

	/**
	 * Constructor for ResultObject.
	 * 
	 * @param sqlType
	 * @param resultHandler
	 */
	public ResultObject(Integer sqlType, Object resultHandler) {
		super();
		this.sqlType = sqlType;
		this.resultHandler = resultHandler;
	}

	/**
	 * 
	 * 
	 * 
	 * @return sqlType
	 */
	public Integer getSqlType() {
		return sqlType;
	}

	/**
	 * 
	 * @param sqlType
	 */
	public void setSqlType(Integer sqlType) {
		this.sqlType = sqlType;
	}

	/**
	 * 
	 * 
	 * 
	 * @return resultHandler
	 */
	public Object getResultHandler() {
		return resultHandler;
	}

	/**
	 * 
	 * @param resultHandler
	 */
	public void setResultHandler(Object resultHandler) {
		this.resultHandler = resultHandler;
	}

	/**
	 * Method toString.
	 * 
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return "ResultObject [sqlType=" + sqlType + ", resultHandler="
				+ resultHandler + "]";
	}

	/**
	 * Method readObject.
	 * 
	 * @param in
	 *            ObjectInputStream
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private final void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		throw new IOException("Non-deserializable class");
	}

}
