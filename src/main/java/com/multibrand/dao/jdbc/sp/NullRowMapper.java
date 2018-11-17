// $codepro.audit.disable unnecessaryExceptions, com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.alwaysOverridetoString.alwaysOverrideToString, com.instantiations.assist.eclipse.analysis.deserializeabilitySecurity
package com.multibrand.dao.jdbc.sp;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * Represents a null row mapper for <code>ProcedureOutParameter</code>
 * annotation as a default value for procedure out parameter of cursor type.
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.0
 */
public class NullRowMapper implements RowMapper<Object> {

	/**
	 * Default method that throws <code>UnsupportedOperationException</code>.
	 * 
	 * @param rs
	 *            <code>ResultSet</code> instance.
	 * @param rowNo
	 *            Current row number.
	 * @return Do nothing and throws <code>UnsupportedOperationException</code>.
	 * @throws <code>SQLException</code>.
	 */
	@Override
	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		throw new UnsupportedOperationException(
				"NullRowMapper mapRow is not supported !");
	}

}
