// $codepro.audit.disable documentClosingBraces, com.instantiations.assist.eclipse.analysis.disallowReturnMutable, booleanMethodNamingConvention, instanceFieldNamingConvention, emptyMethod, fieldMayHaveNullValue, com.instantiations.eclipse.analysis.audit.security.variableShouldNotHaveNullValue, com.instantiations.assist.eclipse.analysis.deserializeabilitySecurity
package com.multibrand.dao.jdbc.sp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Maintains procedure configuration cache.
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.0
 */
public final class ProcedureConfigCache {

	private final Map<String, String> CONFIG_CACHE = new HashMap<String, String>();
	private static ProcedureConfigCache INSTANCE = null;

	/**
	 * Default constructor.
	 */
	private ProcedureConfigCache() {

	}

	/**
	 * @return Singleton instance of this class.
	 */
	public static ProcedureConfigCache getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ProcedureConfigCache();
		}
		return INSTANCE;
	}

	/**
	 * @return <code>Set</code> containing all the procedure configured keys.
	 */
	public Set<String> keySet() {
		return CONFIG_CACHE.keySet();
	}

	/**
	 * Puts the cache config.
	 * 
	 * @param key
	 *            Cache key.
	 * @param value
	 *            Cache value.
	 */
	public void put(String key, String value) {
		CONFIG_CACHE.put(key, value);
	}

	/**
	 * @param key
	 *            Cache key.
	 * @return Cache value.
	 */
	public String get(String key) {
		return CONFIG_CACHE.get(key);
	}

	/**
	 * @param key
	 *            Cache key.
	 * @return Checks containment of cache key.
	 */
	public boolean containsKey(String key) {
		return CONFIG_CACHE.containsKey(key);
	}

	/**
	 * @return String representation of this object.
	 */
	@Override
	public String toString() {
		return "CONFIG_CACHE: [" + CONFIG_CACHE + "]";
	}

}
