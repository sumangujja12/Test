@RefreshTokenFeature
Feature: Refresh Token

  Background: 
    * configure headers = {'Accept': '*/*', 'Content-Type': 'application/x-www-form-urlencoded', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': '83c97beb-0dde-40cc-a402-15df5a775ee0'  }
    * url BASE_SERVER_URL

  @validRefreshToken
  Scenario: Successful refresh token
    Given path 'NRGREST/rest/authorization/refreshtoken'
    And request ''
    When method POST
    Then status 200
    #Verify successful response
    Then match response.refreshVal == '#notnull'
    Then match response.resultCode == '0'
    Then match response.resultDescription == ''
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Refresh Token Successful'
    And print 'Valid Refresh Token Response Time:' + responseTime
    * print response
