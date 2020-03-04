@COStatusFEature
Feature: Account Details - CO Status

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': 'efd3b6f7-4490-4aa8-be3c-ae858776bec9' }
    * url BASE_SERVER_URL
    * json costatus = read('classpath:com/it/multibrand/karate/accountdetails/CO_Status.json')
    * def ESID = costatus.esid
    * def CompanyCode = costatus.companyCode

  @validCOStatus
  Scenario: Successful display of CO Status
    Given path '/NRGREST/rest/tos/getESIDProfile'
    And form field esid = ESID
    And form field companyCode = CompanyCode
    When method POST
    Then status 200
    #Verify successful response
    Then match response.esid == ESID
    Then match response.esidStatus == 'Active'
    Then match response.meterType == '#notnull'
    Then match response.premiseType == '#notnull'
    Then match response.resultCode == '0'
    Then match response.resultDescription == ''
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Get CO Status Successful'
    And print 'Valid CO Status Response Time:' + responseTime
    * print response

  @invalidCOStatus
  Scenario: Invalid ESID entered
    Given path '/NRGREST/rest/tos/getESIDProfile'
    And form field esid = '1008901001900070110'
    And form field companyCode = CompanyCode
    When method POST
    Then status 200
    #Verify response
    Then match response.esidStatus == 'Inactive'
    Then match response.meterType == '#notnull'
    Then match response.premiseType == '#notnull'
    Then match response.resultCode == '0'
    Then match response.resultDescription == ''
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Get CO Status Unsuccessful'
    And print 'Invalid CO Status Response Time:' + responseTime
    * print response
