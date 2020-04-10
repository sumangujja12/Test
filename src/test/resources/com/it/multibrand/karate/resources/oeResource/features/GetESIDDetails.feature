
@GetESIDDetailsFeature
Feature: Get ESID Details

Background:
  * url  BASE_SERVER_URL_1
	* json getesiddetailsrequest = read('classpath:com/it/multibrand/karate/resources/oeResource/requestBody/GetESIDDetails.json')
 
 	Scenario: Get ESID detail response using a valid address
    Given path  '/NRGREST/rest/oeResource/esiidDetails'
    And  request getesiddetailsrequest
    When method POST
    Then status 200    
    And print 'ESID Response Successful'
    And print 'Valid Address Response Time:' + responseTime
		And print 'Valid Address Response Below:' 
    And print response
    
  Scenario: Get ESID detail response using a invalid address
   * set getesiddetailsrequest.strStreet = '2020 Old Farm rd'
    Given path  '/NRGREST/rest/oeResource/esiidDetails'
    And  request getesiddetailsrequest
    When method POST
    Then status 200    
    And print 'MVI Promo Response Successful'
    And print 'InValid Address Response Time:' + responseTime
		And print 'InValid Address Response Below:' 
    And print response
    