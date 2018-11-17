package com.multibrand.request.validation;

import javax.validation.groups.Default;

/**
 * A marker interface which suggests that the grouping class for the fields Date
 * and other format constraint validations like ValidDateTime Pattern etc...
 * 
 * @author Jenith. Y
 * @version Since JDK 1.6, Spring 3.2, Hibernate-validator 4.3.2 and Jersey 1.17 (1.x)
 */
public interface FormatConstraint extends Default {
}
