package com.multibrand.util;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Package based utils.
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.0 and Jersey 1.7
 */
public class PackageUtils {

	private final static Logger LOGGER = LogManager.getLogger(PackageUtils.class);

	public final static String PACKAGE_SEPARATOR = ".";

	/**
	 * Scans all classes accessible from the context class loader which belong
	 * to the given package and sub packages.
	 * 
	 * @param packageName
	 *            The base package.
	 * @return Classes in a named <code>packageName</code>.
	 */
	@SuppressWarnings("rawtypes")
	public static Class[] getClasses(String packageName) {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		assert classLoader != null;
		ArrayList<Class> classes = new ArrayList<Class>();
		String path = packageName.replace(PACKAGE_SEPARATOR, "/");
		Enumeration<URL> resources = null;
		try {
			resources = classLoader.getResources(path);
		} catch (IOException e) {
			LOGGER.debug("Error in finding classes.", e);
		}
		if (resources != null) {
			List<File> dirs = new ArrayList<File>();
			while (resources.hasMoreElements()) {
				URL resource = resources.nextElement();
				dirs.add(new File(resource.getFile()));
			}
			for (File directory : dirs) {
				classes.addAll(findClasses(directory, packageName));
			}
		}
		return classes.toArray(new Class[classes.size()]);
	}

	/**
	 * Scans all annotated classes accessible from the context class loader
	 * which belong to the given package and sub packages.
	 * 
	 * @param packageName
	 *            The base package.
	 * @param annotationClass
	 *            Annotation class.
	 * @return Annotated classes in a named <code>packageName</code>.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Class[] getAnnotatedClasses(String packageName,
			Class<? extends Annotation> annotationClass) {
		Class[] classList = getClasses(packageName);
		List<Class> annotationClassList = new ArrayList<Class>();
		if (classList != null) {
			for (Class clazz : classList) {
				if (clazz != null
						&& clazz.getAnnotation(annotationClass) != null) {
					annotationClassList.add(clazz);
					LOGGER.debug("Class: '" + clazz.getName() + "' has '@"
							+ annotationClass.getSimpleName() + "' annotation.");
				}
			}
		}
		return annotationClassList
				.toArray(new Class[annotationClassList.size()]);
	}

	/**
	 * Recursive method used to find all classes in a given directory and sub
	 * directories.
	 * 
	 * @param directory
	 *            The base directory
	 * @param packageName
	 *            The package name for classes found inside the base directory
	 * @return The classes
	 */
	@SuppressWarnings({ "rawtypes" })
	private static List<Class> findClasses(File directory, String packageName) {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(PACKAGE_SEPARATOR);
				classes.addAll(findClasses(file, packageName
						+ PACKAGE_SEPARATOR + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				try {
					classes.add(Class.forName(packageName
							+ PACKAGE_SEPARATOR
							+ file.getName().substring(0,
									file.getName().length() - 6)));
				} catch (ClassNotFoundException e) {
					LOGGER.debug("Error in finding classes.", e);
				}
			}
		}
		return classes;
	}

}
