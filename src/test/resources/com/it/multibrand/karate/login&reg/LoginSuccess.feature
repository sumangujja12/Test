@LoginSuccessFeature
Feature: Login Success

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': '34acb728-404e-42df-9a61-642515a9cc35' }
    * url BASE_SERVER_URL
    
  @validLoginSuccess
  Scenario: Successful login
    Given path '/NRGREST/rest/authorization/loginSuccessCall'
    And request ''
    When method POST
    Then status 200
    #Verify successful response
    Then match response.accountNumber == '#notnull'
    Then match response.userID == '#notnull'
    Then match response.userUniqueID == '#notnull'
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Login Success Successful'
    And print 'Valid Login Success Response Time:' + responseTime
    * print response
