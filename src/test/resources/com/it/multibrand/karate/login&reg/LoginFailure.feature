@LoginFailureFeature
Feature: Login Failure

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': 'cec424a0-af2d-405d-aa76-334e81f91b11' }
    * url BASE_SERVER_URL
    
  @validLoginFailure
  Scenario: Successful login failure call
    Given path '/NRGREST/rest/authorization/loginFailureCall'
    And request ''
    When method POST
    Then status 200
    #Verify successful response
    Then match response.invalidLoginCount == '1'
    Then match response.resultCode == '1'
    Then match response.resultDescription == 'credentialsMismatch'
    Then match response.errorCode == 'MSG_BAD_LOGIN'
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == "The user name or password doesn't match. Please try again."
    And print 'Login Failure Successful'
    And print 'Valid Login Failure Response Time:' + responseTime
    * print response
