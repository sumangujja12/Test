// $codepro.audit.disable codeInComments, commentLocalVariables, documentClosingBraces, caughtExceptions, methodJavadoc, com.instantiations.assist.eclipse.analysis.disallowReturnMutable, com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.alwaysOverridetoString.alwaysOverrideToString, booleanMethodNamingConvention, com.instantiations.assist.eclipse.analysis.classGetNameUsage, com.instantiations.assist.eclipse.analysis.avoidComparingClassesByStrings, fieldMayHaveNullValue, handleNumericParsingErrors, possibleNullPointer, com.instantiations.eclipse.analysis.audit.security.variableShouldNotHaveNullValue, nullPointerDereference, com.instantiations.assist.eclipse.analysis.deserializeabilitySecurity, reflectionInjection
package com.multibrand.dao.jdbc.sp;

import java.lang.reflect.Field;

import oracle.jdbc.OracleTypes;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Procedure template used to execute the configured database procedure through
 * <code>Procedure</code> annotation.
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.0
 */
@Component("procedureTemplateHelper")
class ProcedureTemplateHelper {

	final static String KEY_SUFFIX_PROCEDURE_NAME = ".proc.";
	final static String KEY_SUFFIX_PROCEDURE_MESSAGE_SOURCE_ID = KEY_SUFFIX_PROCEDURE_NAME
			+ "procedureMessageSourceId";
	final static String KEY_SUFFIX_PROCEDURE_IN_PARAMETER_NAME = KEY_SUFFIX_PROCEDURE_NAME
			+ "in.param.name.";
	final static String KEY_SUFFIX_PROCEDURE_IN_PARAMETER_TYPE = KEY_SUFFIX_PROCEDURE_NAME
			+ "in.param.type.";
	final static String KEY_SUFFIX_PROCEDURE_OUT_PARAMETER_NAME = KEY_SUFFIX_PROCEDURE_NAME
			+ "out.param.name.";
	final static String KEY_SUFFIX_PROCEDURE_OUT_PARAMETER_TYPE = KEY_SUFFIX_PROCEDURE_NAME
			+ "out.param.type.";
	final static String KEY_SUFFIX_PROCEDURE_OUT_PARAMETER_ROWMAPPER = KEY_SUFFIX_PROCEDURE_NAME
			+ "out.param.rowmapper.";
	final static String KEY_SUFFIX_PROCEDURE_PARAMETER_INDEX = KEY_SUFFIX_PROCEDURE_NAME
			+ "param.index.";

	private final static Logger LOGGER = LogManager
			.getLogger(ProcedureTemplateHelper.class);

	@Autowired
	ApplicationContext context;

	/**
	 * Initializes procedure configuration cache.
	 * 
	 * @param clazz
	 *            An Procedure Mapper class (annotated with {@link Procedure}
	 *            annotation}).
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	void initProcedureConfigCache(Class clazz) {
		boolean initialisedProcedureConfig = initialisedProcedureConfigCache(clazz);
		if (initialisedProcedureConfig) {
			/*
			 * LOGGER.debug("Procedure: '" +
			 * ProcedureConfigCache.getInstance().get(
			 * getProcedureNameConfigKey(clazz.getName())) +
			 * "' is already configured in cache: '" +
			 * ProcedureConfigCache.getInstance() + "'");
			 */
		} else if (!initialisedProcedureConfig) {
			LOGGER.debug("Procedure with class name : {} is not configured in cache. Initialising now ..." , clazz);
			if (clazz != null && clazz.isAnnotationPresent(Procedure.class)) {
				String clazzName = clazz.getName();
				
				ProcedureConfigCache cache = ProcedureConfigCache.getInstance();

				// Load procedure message source:
				String procedureMessageSourceId = ((Procedure) clazz
						.getAnnotation(Procedure.class))
						.procedureMessageSourceId();

				if (StringUtils.isEmpty(procedureMessageSourceId)) {
					LOGGER.debug("Procedure message source ID is not defined for Procedure Mapper: '"
							+ clazz.getName()
							+ "' hence, neglecting to read procedure mapping from properties file"
							+ " and directly reading procedure mapping from Procedure Mapper.");
				}

				ReloadableResourceBundleMessageSource procedureSource = null;
				if (StringUtils.isNotEmpty(procedureMessageSourceId)) {
					cache.put(getProcedureMessageSourceIdKey(clazzName),
							procedureMessageSourceId);
					try {
						procedureSource = context.getBean(
								procedureMessageSourceId,
								ReloadableResourceBundleMessageSource.class);
					} catch (Exception e) {
						LOGGER.debug("Procedure message source with id: '"
								+ procedureMessageSourceId
								+ "' not found in spring context.", e);
					}
				}

				String procedureName = getProcedureMessage(procedureSource,
						((Procedure) clazz.getAnnotation(Procedure.class))
								.value());

				// Put procedure name:
				cache.put(getProcedureNameConfigKey(clazzName), procedureName);

				// Put procedure parameter configurations:
				for (Field field : clazz.getDeclaredFields()) {

					String fieldName = field.getName();

					// 1. Process IN parameters:
					if (field.isAnnotationPresent(ProcedureInParameter.class)
							&& StringUtils.isNotEmpty(field.getAnnotation(
									ProcedureInParameter.class).name())) {
						String parameterName = getProcedureMessage(
								procedureSource,
								(field.getAnnotation(ProcedureInParameter.class))
										.name());
						int parameterType = (field
								.getAnnotation(ProcedureInParameter.class))
								.type();
						int parameterIndex = (field
								.getAnnotation(ProcedureInParameter.class))
								.parameterIndex();
						cache.put(
								getProcedureInParameterNameConfigKey(clazzName,
										fieldName), parameterName);
						cache.put(
								getProcedureInParameterTypeConfigKey(clazzName,
										fieldName), Integer
										.toString(parameterType));
						cache.put(
								getProcedureParameterIndexConfigKey(clazzName,
										fieldName), Integer
										.toString(parameterIndex));

						// 2. Process OUT parameters:
					} else if (field
							.isAnnotationPresent(ProcedureOutParameter.class)
							&& StringUtils.isNotEmpty(field.getAnnotation(
									ProcedureOutParameter.class).name())) {

						String parameterName = getProcedureMessage(
								procedureSource,
								(field.getAnnotation(ProcedureOutParameter.class))
										.name());
						int parameterType = (field
								.getAnnotation(ProcedureOutParameter.class))
								.type();
						int parameterIndex = (field
								.getAnnotation(ProcedureOutParameter.class))
								.parameterIndex();
						cache.put(
								getProcedureOutParameterNameConfigKey(
										clazzName, fieldName), parameterName);
						cache.put(
								getProcedureOutParameterTypeConfigKey(
										clazzName, fieldName), Integer
										.toString(parameterType));
						cache.put(
								getProcedureParameterIndexConfigKey(clazzName,
										fieldName), Integer
										.toString(parameterIndex));

						// Check the row mapping condition:
						if (parameterType == OracleTypes.CURSOR) {
							Class rowMapper = (field
									.getAnnotation(ProcedureOutParameter.class))
									.rowMapper();
							Assert.isTrue(!(NullRowMapper.class.getName()
									.equalsIgnoreCase(rowMapper.getName())),
									"Provide the row mapper implementation class name.");
							Assert.isAssignable(RowMapper.class, rowMapper,
									"Provide the row mapper implementation class name.");
							cache.put(
									getProcedureOutParameterRowMapperConfigKey(
											clazzName, fieldName), rowMapper
											.getName());
						}

					}
				}
			}
		}
	}

	@SuppressWarnings({ "rawtypes" })
	String getProcedureName(Class clazz) {
		String procName = null;
		if (clazz != null) {
			procName = ProcedureConfigCache.getInstance().get(
					getProcedureNameConfigKey(clazz.getName()));
		}
		return procName;
	}

	@SuppressWarnings({ "rawtypes" })
	private boolean initialisedProcedureConfigCache(Class clazz) {
		return ((clazz != null) && ProcedureConfigCache.getInstance()
				.containsKey(getProcedureNameConfigKey(clazz.getName())));
	}

	String getProcedureMessage(
			ReloadableResourceBundleMessageSource procedureSource,
			String procedureKey) {
		String procedureMessage = procedureKey;
		if (procedureSource != null && StringUtils.isNotEmpty(procedureKey)) {
			try {
				procedureMessage = procedureSource.getMessage(procedureKey,
						null, null);
			} catch (Exception e) {
				LOGGER.debug("Query message with key: '" + procedureKey
						+ "' not found in query message source.", e);
				procedureMessage = procedureKey;
			}
			LOGGER.debug("queryKey: '" + procedureKey + "' has query value: '"
					+ procedureMessage + "'");
		}
		return procedureMessage;
	}

	String getProcedureMessageSourceIdKey(String parameterClassName) {
		String key = null;
		if (StringUtils.isNotEmpty(parameterClassName)) {
			key = parameterClassName + KEY_SUFFIX_PROCEDURE_MESSAGE_SOURCE_ID;
		}
		return key;
	}

	String getProcedureNameConfigKey(String parameterClassName) {
		String key = null;
		if (StringUtils.isNotEmpty(parameterClassName)) {
			key = parameterClassName + KEY_SUFFIX_PROCEDURE_NAME + "name";
		}
		return key;
	}

	String getProcedureInParameterNameConfigKey(String parameterClassName,
			String fieldName) {
		String key = null;
		if (StringUtils.isNotEmpty(parameterClassName)
				&& StringUtils.isNotEmpty(fieldName)) {
			key = parameterClassName + KEY_SUFFIX_PROCEDURE_IN_PARAMETER_NAME
					+ fieldName;
		}
		return key;
	}

	String getProcedureInParameterTypeConfigKey(String parameterClassName,
			String fieldName) {
		String key = null;
		if (StringUtils.isNotEmpty(parameterClassName)) {
			key = parameterClassName + KEY_SUFFIX_PROCEDURE_IN_PARAMETER_TYPE
					+ fieldName;
		}
		return key;
	}

	String getProcedureOutParameterNameConfigKey(String parameterClassName,
			String fieldName) {
		String key = null;
		if (StringUtils.isNotEmpty(parameterClassName)) {
			key = parameterClassName + KEY_SUFFIX_PROCEDURE_OUT_PARAMETER_NAME
					+ fieldName;
		}
		return key;
	}

	String getProcedureOutParameterTypeConfigKey(String parameterClassName,
			String fieldName) {
		String key = null;
		if (StringUtils.isNotEmpty(parameterClassName)) {
			key = parameterClassName + KEY_SUFFIX_PROCEDURE_OUT_PARAMETER_TYPE
					+ fieldName;
		}
		return key;
	}

	String getProcedureOutParameterRowMapperConfigKey(
			String parameterClassName, String fieldName) {
		String key = null;
		if (StringUtils.isNotEmpty(parameterClassName)) {
			key = parameterClassName
					+ KEY_SUFFIX_PROCEDURE_OUT_PARAMETER_ROWMAPPER + fieldName;
		}
		return key;
	}

	String getProcedureParameterIndexConfigKey(String parameterClassName,
			String fieldName) {
		String key = null;
		if (StringUtils.isNotEmpty(parameterClassName)) {
			key = parameterClassName + KEY_SUFFIX_PROCEDURE_PARAMETER_INDEX
					+ fieldName;
		}
		return key;
	}

}
