package com.multibrand.dao;

import java.util.List;
import java.util.Map;

import com.multibrand.dto.request.AddPersonRequest;
import com.multibrand.dto.request.UpdatePersonRequest;
import com.multibrand.dto.response.PersonResponse;

/**
 * DAO which deals with Person database operations.
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.0 and Jersey 1.17
 */
public interface PersonDao {

	/**
	 * Gets Person ID associated with a given tracking number.
	 * 
	 * @param trackingNo
	 *            Tracking number.
	 * @return Person ID associated with a given tracking number.
	 */
	public String getPersonIdByTrackingNo(String trackingNo);

	/**
	 * Gets map of Person ID and Retry Count associated with a given tracking
	 * number.
	 * 
	 * @param trackingNo
	 *            Tracking number.
	 * @return Map of Person ID and Retry Count associated with a given tracking
	 *         number.
	 */
	public List<Map<String, String>> getPersonIdAndRetryCountByTrackingNo(
			String trackingNo);

	/**
	 * Adds person details into the database.
	 * 
	 * @param request
	 *            Instance of <code>AddPersonRequest</code>.
	 * @return If a person is added successfully into the database then, it
	 *         returns the Person ID otherwise, for the failed scenario, it
	 *         return <code>null</code>.
	 */
	public String addPerson(AddPersonRequest request);

	/**
	 * Updates person details into the database.
	 * 
	 * @param request
	 *            Instance of <code>UpdatePersonRequest</code>.
	 * @return If a person is update successfully into the database then, it
	 *         returns empty error code otherwise, for the failed scenario, it
	 *         return &quot;FAILED&quot; error code.
	 */
	public String updatePerson(UpdatePersonRequest request);

	/**
	 * Get person details.
	 * 
	 * @param personId
	 *            Person ID.
	 * @return Person details (in the form of <code>PersonResponse</code>
	 *         instance).
	 */
	public PersonResponse getPerson(String personId);

}
