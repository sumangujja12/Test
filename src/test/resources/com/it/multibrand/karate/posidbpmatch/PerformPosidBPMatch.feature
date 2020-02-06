 Feature: Perform Posid BP Match funtionality
 
 Background:
  * url  BASE_SERVER_URL
  * json posidbpmatchrequest = read('classpath:com/it/multibrand/karate/posidbpmatch/PerformPosidBPMatch.json')

@validPosid
   Scenario: Validate Posid with valid Posid test data
   * set posidbpmatchrequest.firstName = 'CORLEY'
   * set posidbpmatchrequest.lastName = 'ZANNS'
   * set posidbpmatchrequest.dob = '03011968'
   * set posidbpmatchrequest.tdl = '7002012118'   
    Given path  '/NRGREST/rest/oeResource/performPosidAndBpMatch'
    And  request posidbpmatchrequest
    When method POST
    Then status 200    
    And print 'Validate Posid Response Successful'
    And print 'Valid Posid Response Time:' + responseTime
		And print 'Valid Posid Response Below:' 
    And print response
    And match response.posidDLDate != null

@invalidPosid    
     Scenario: Validate Posid with invalid Posid test data
   * set posidbpmatchrequest.firstName = 'CORLEY'
   * set posidbpmatchrequest.lastName = 'ZANNS'
   * set posidbpmatchrequest.dob = '03011968'
   * set posidbpmatchrequest.tdl = '12345'   
    Given path  '/NRGREST/rest/oeResource/performPosidAndBpMatch'
    And  request posidbpmatchrequest
    When method POST
    Then status 200    
    And print 'Validate Posid Response Successful'
    And print 'Posid Response Time:' + responseTime
		And print 'Posid Response Below:' 
    And print response
    And match response.posidDLDate == null