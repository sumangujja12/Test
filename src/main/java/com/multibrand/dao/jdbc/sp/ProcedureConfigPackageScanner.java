// $codepro.audit.disable codeInComments, commentLocalVariables, documentClosingBraces, caughtExceptions, methodJavadoc, com.instantiations.assist.eclipse.analysis.disallowReturnMutable, com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.alwaysOverridetoString.alwaysOverrideToString, booleanMethodNamingConvention, com.instantiations.assist.eclipse.analysis.classGetNameUsage, com.instantiations.assist.eclipse.analysis.avoidComparingClassesByStrings, fieldMayHaveNullValue, handleNumericParsingErrors, possibleNullPointer, com.instantiations.eclipse.analysis.audit.security.variableShouldNotHaveNullValue, nullPointerDereference, com.instantiations.assist.eclipse.analysis.deserializeabilitySecurity, reflectionInjection
package com.multibrand.dao.jdbc.sp;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.util.Assert;

import com.multibrand.util.PackageUtils;

/**
 * Scans entities for procedure configuration in a given package.
 * 
 * <p>
 * Procedure configured (using <code>@Procedure</code> annotation) package
 * scanner to which scans all the entities (which are annotated with
 * <code>@Procedure</code> annotation) in a given package and then prepares the
 * procedure configuration cache ({@link ProcedureConfigCache}).
 * </p>
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.0 and Jersey 1.7
 */
public class ProcedureConfigPackageScanner {

	private final static Logger LOGGER = LogManager
			.getLogger(ProcedureConfigPackageScanner.class);

	/**
	 * Package list for scanning the packages.
	 */
	protected final List<String> packageNames;

	/**
	 * An procedure helper instance that helps in initializing procedure
	 * configuration caching.
	 */
	protected final ProcedureTemplateHelper helper;

	/**
	 * Immutable default constructor.
	 * 
	 * @param packageNames
	 *            Contains list of package names for scanning.
	 * @param helper
	 *            Helper instance that helps in initializing procedure
	 *            configuration caching.
	 */
	public ProcedureConfigPackageScanner(List<String> packageNames,
			ProcedureTemplateHelper helper) {
		Assert.notNull(packageNames, "packageNames must not be null.");
		Assert.notNull(helper, "helper must not be null.");
		this.packageNames = packageNames;
		this.helper = helper;
		scan();
	}

	/**
	 * Scans the packages and prepares procedure configuration caching.
	 */
	@SuppressWarnings("rawtypes")
	protected void scan() {
		LOGGER.info("Scanning database procedure mappers for packages: "
				+ packageNames + " ...");
		for (String scanPackageName : packageNames) {
			Class[] procedureConfigClasses = PackageUtils.getAnnotatedClasses(
					scanPackageName, Procedure.class);
			if (procedureConfigClasses != null) {
				for (Class procedureConfigClass : procedureConfigClasses) {
					helper.initProcedureConfigCache(procedureConfigClass);
				}
			}
		}
		LOGGER.info("Finished scanning database procedure mappers for packages.");
	}

}
