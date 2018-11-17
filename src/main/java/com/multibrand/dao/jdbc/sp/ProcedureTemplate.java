// $codepro.audit.disable codeInComments, commentLocalVariables, documentClosingBraces, caughtExceptions, methodJavadoc, com.instantiations.assist.eclipse.analysis.disallowReturnMutable, com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.alwaysOverridetoString.alwaysOverrideToString, booleanMethodNamingConvention, com.instantiations.assist.eclipse.analysis.classGetNameUsage, com.instantiations.assist.eclipse.analysis.avoidComparingClassesByStrings, fieldMayHaveNullValue, handleNumericParsingErrors, possibleNullPointer, com.instantiations.eclipse.analysis.audit.security.variableShouldNotHaveNullValue, nullPointerDereference, com.instantiations.assist.eclipse.analysis.deserializeabilitySecurity, reflectionInjection
package com.multibrand.dao.jdbc.sp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.util.Assert;

/**
 * Procedure template used to execute the configured database procedure through
 * <code>@Procedure</code> annotation.
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.0 and Jersey 1.7
 */
public class ProcedureTemplate {

	private final static Logger LOGGER = LogManager
			.getLogger(ProcedureTemplate.class);

	private final DataSource dataSource;
	protected final ProcedureTemplateHelper helper;

	/**
	 * Default constructor.
	 * 
	 * @param dataSource
	 *            <code>DataSource</code> instance.
	 * @param helper
	 *            <code>ProcedureTemplateHelper</code> instance.
	 */
	public ProcedureTemplate(DataSource dataSource,
			ProcedureTemplateHelper helper) {
		Assert.notNull(dataSource, "Datasource can't be null.");
		Assert.notNull(helper, "helper must not be null.");
		this.dataSource = dataSource;
		this.helper = helper;
	}

	/**
	 * Executes the annotated procedure and sets the procedure OUT parameters to
	 * the object fields annotated with <code>ProcedureOutParameter</code>.
	 * 
	 * @param procedureMapper
	 *            An procedure object annotated with <code>Procedure</code>
	 *            annotation.
	 */
	public void execute(Object procedureMapper) {
		executeAndSetOutParameters(procedureMapper);
	}

	/**
	 * Executes the annotated procedure and sets the procedure OUT parameters to
	 * the object fields annotated with <code>ProcedureOutParameter</code>.
	 * 
	 * @param procedureMapper
	 *            An procedure object annotated with <code>Procedure</code>
	 *            annotation.
	 */
	private void executeAndSetOutParameters(Object procedureMapper) {
		Map<String, Object> output = executeForMap(procedureMapper);
		setProcedureOutParameterValues(output, procedureMapper);
	}

	/**
	 * Executes the <code>Procedure</code> annotated procedure.
	 * 
	 * @param procedureMapper
	 *            An procedure object annotated with <code>Procedure</code>
	 *            annotation.
	 * @return <code>Map</code> containing procedure output result.
	 */
	private Map<String, Object> executeForMap(Object procedureMapper) {
		Assert.notNull(procedureMapper,
				"Procedure filter object can't be null.");
		helper.initProcedureConfigCache(procedureMapper.getClass());
		String procedureName = helper.getProcedureName(procedureMapper
				.getClass());
		SqlParameter[] params = getProcedureParameters(procedureMapper);
		Map<String, Object> paramValues = getProcedureInParameterValues(procedureMapper);
		BaseStoredProcedure baseProc = new BaseStoredProcedure(dataSource,
				procedureName, params, paramValues);
		Map<String, Object> output = baseProc.execute();
		// setProcedureOutParameterValues(output);
		return output;
	}

	@SuppressWarnings({ "rawtypes" })
	private SqlParameter[] getProcedureParameters(Object procedureMapper) {
		SqlParameter[] params = null;
		int paramsMaxIndex = getMaxParameterIndex(procedureMapper);
		if (procedureMapper != null && paramsMaxIndex > 0) {
			params = new SqlParameter[paramsMaxIndex];
			Class clazz = procedureMapper.getClass();
			String className = clazz.getName();
			String inParamNamePrefix = className
					+ ProcedureTemplateHelper.KEY_SUFFIX_PROCEDURE_IN_PARAMETER_NAME;
			String outParamNamePrefix = className
					+ ProcedureTemplateHelper.KEY_SUFFIX_PROCEDURE_OUT_PARAMETER_NAME;
			ProcedureConfigCache cache = ProcedureConfigCache.getInstance();
			Set<String> keySet = cache.keySet();
			for (String key : keySet) {
				if (StringUtils.isNotEmpty(key)
						&& key.startsWith(inParamNamePrefix)) {
					String fieldName = key
							.substring(inParamNamePrefix.length());
					String paramName = cache.get(key);
					String paramType = cache.get(helper
							.getProcedureInParameterTypeConfigKey(className,
									fieldName));
					String paramIndex = cache.get(helper
							.getProcedureParameterIndexConfigKey(className,
									fieldName));
					if (StringUtils.isNotEmpty(paramName)
							&& NumberUtils.isNumber(paramType)
							&& NumberUtils.isNumber(paramIndex)) {
						SqlParameter param = new SqlParameter(paramName,
								Integer.parseInt(paramType));
						int paramIdx = Integer.parseInt(paramIndex);
						if (paramIdx > 0) {
							params[paramIdx - 1] = param;
						}
					}
				} else if (StringUtils.isNotEmpty(key)
						&& key.startsWith(outParamNamePrefix)) {
					String fieldName = key.substring(outParamNamePrefix
							.length());
					String paramName = cache.get(key);
					String paramType = cache.get(helper
							.getProcedureOutParameterTypeConfigKey(className,
									fieldName));
					String paramIndex = cache.get(helper
							.getProcedureParameterIndexConfigKey(className,
									fieldName));
					if (StringUtils.isNotEmpty(paramName)
							&& NumberUtils.isNumber(paramType)
							&& NumberUtils.isNumber(paramIndex)) {
						SqlOutParameter param = null;
						if (Integer.parseInt(paramType) == OracleTypes.CURSOR) {
							String rowMapperClass = cache
									.get(helper
											.getProcedureOutParameterRowMapperConfigKey(
													className, fieldName));
							Assert.isTrue(!(NullRowMapper.class.getName()
									.equalsIgnoreCase(rowMapperClass)),
									"Provide the row mapper implementation class name.");
							if (StringUtils.isNotEmpty(rowMapperClass)) {
								RowMapper rowMapper = null;
								try {
									rowMapper = (RowMapper) (Class
											.forName(rowMapperClass)
											.newInstance());
								} catch (Exception e) {
									rowMapper = null;
									LOGGER.error("Invalid RowMapper class: '"
											+ rowMapperClass + "'", e);
								}
								if (rowMapper != null) {
									param = new SqlOutParameter(paramName,
											Integer.parseInt(paramType),
											rowMapper);
								}
							}
						} else {
							param = new SqlOutParameter(paramName,
									Integer.parseInt(paramType));
						}

						int paramIdx = Integer.parseInt(paramIndex);
						if (param != null && paramIdx > 0) {
							params[paramIdx - 1] = param;
						}
					}
				}
			}
		}
		return params;
	}

	@SuppressWarnings({ "rawtypes" })
	private int getMaxParameterIndex(Object procedureMapper) {
		int index = 0;
		if (procedureMapper != null) {
			Class clazz = procedureMapper.getClass();
			String className = clazz.getName();
			String parameterIndexPrefix = className
					+ ProcedureTemplateHelper.KEY_SUFFIX_PROCEDURE_PARAMETER_INDEX;
			ProcedureConfigCache cache = ProcedureConfigCache.getInstance();
			Set<String> keySet = cache.keySet();

			for (String key : keySet) {
				if (StringUtils.isNotEmpty(key)
						&& key.startsWith(parameterIndexPrefix)) {
					String paramIndxTxt = cache.get(key);
					if (NumberUtils.isNumber(paramIndxTxt)) {
						int paramIndx = Integer.parseInt(paramIndxTxt);
						if ((index < paramIndx) && (paramIndx > 0)) {
							index = paramIndx;
						}
					}
				}
			}
		}
		LOGGER.debug("PARAMETER MAX INDEX = " + index);
		return index;
	}

	@SuppressWarnings({ "rawtypes" })
	private Map<String, Object> getProcedureInParameterValues(
			Object procedureMapper) {
		Map<String, Object> inputs = new HashMap<String, Object>();
		if (procedureMapper != null) {
			Class clazz = procedureMapper.getClass();
			String className = clazz.getName();
			String inParamNamePrefix = className
					+ ProcedureTemplateHelper.KEY_SUFFIX_PROCEDURE_IN_PARAMETER_NAME;
			ProcedureConfigCache cache = ProcedureConfigCache.getInstance();
			Set<String> keySet = cache.keySet();
			for (String key : keySet) {
				if (StringUtils.isNotEmpty(key)
						&& key.startsWith(inParamNamePrefix)) {
					String fieldName = key
							.substring(inParamNamePrefix.length());
					String paramName = cache.get(key);
					String paramType = cache.get(helper
							.getProcedureInParameterTypeConfigKey(className,
									fieldName));
					if (StringUtils.isNotEmpty(paramName)
							&& NumberUtils.isNumber(paramType)) {
						Object fieldVal = null;
						try {
							fieldVal = PropertyUtils.getProperty(
									procedureMapper, fieldName);
						} catch (Exception e) {
							LOGGER.error(
									"Error occured while fetching the field value of field: '"
											+ fieldName + "' of class: '"
											+ className + "'", e);
						}
						inputs.put(paramName, fieldVal);
					}
				}
			}
		}
		return inputs;
	}

	@SuppressWarnings({ "rawtypes" })
	private void setProcedureOutParameterValues(Map<String, Object> output,
			Object procedureMapper) {
		if (procedureMapper != null && output != null && !output.isEmpty()) {
			Class clazz = procedureMapper.getClass();
			String className = clazz.getName();
			String outParamNamePrefix = className
					+ ProcedureTemplateHelper.KEY_SUFFIX_PROCEDURE_OUT_PARAMETER_NAME;
			ProcedureConfigCache cache = ProcedureConfigCache.getInstance();
			Set<String> keySet = cache.keySet();
			for (String key : keySet) {
				if (StringUtils.isNotEmpty(key)
						&& key.startsWith(outParamNamePrefix)) {
					String fieldName = key.substring(outParamNamePrefix
							.length());
					String paramName = cache.get(key);
					String paramType = cache.get(helper
							.getProcedureOutParameterTypeConfigKey(className,
									fieldName));
					if (StringUtils.isNotEmpty(paramName)
							&& NumberUtils.isNumber(paramType)) {
						Object fieldVal = output.get(paramName);
						// if (fieldVal != null) {
						try {
							PropertyUtils.setProperty(procedureMapper,
									fieldName, fieldVal);
						} catch (Exception e) {
							LOGGER.error(
									"Error occured while setting the field value of field: '"
											+ fieldName + "' of class: '"
											+ className + "'", e);
						}
						// }
					}
				}
			}
		}
	}

}
