// $codepro.audit.disable documentClosingBraces, com.instantiations.assist.eclipse.analysis.disallowReturnMutable, com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.alwaysOverridetoString.alwaysOverrideToString, com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.constructorsOnlyInvokeFinalMethods, com.instantiations.eclipse.analysis.audit.security.variableShouldNotHaveNullValue, com.instantiations.assist.eclipse.analysis.deserializeabilitySecurity
package com.multibrand.dao.jdbc.sp;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Base stored procedure class.
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.0
 */
public class BaseStoredProcedure extends StoredProcedure {

	private final Map<String, Object> paramValues;

	/**
	 * Default constructor.
	 * 
	 * @param dataSource
	 *            <code>DataSource</code> instance.
	 * @param procedureName
	 *            Procedure name.
	 * @param params
	 *            Procedure parameter names.
	 * @param paramValues
	 *            Procedure parameter values.
	 */
	public BaseStoredProcedure(DataSource dataSource, String procedureName,
			SqlParameter[] params, Map<String, Object> paramValues) {
		super(dataSource, procedureName);
		if (params != null && params.length > 0) {
			for (SqlParameter param : params) {
				declareParameter(param);
			}
		}
		compile();
		this.paramValues = paramValues;
	}

	/**
	 * Executes a given procedure.
	 * 
	 * @return <code>Map</code> containing executed procedure output.
	 */
	public Map<String, Object> execute() {
		return super.execute(paramValues);
	}

}
