@Feature
Feature: Account Details - Environmental Impact

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx',  'User-Agent': 'PostmanRuntime/7.20.0', 'Cache-Control': 'no-cache', 'Postman-Token': '0e37eada-39b2-4e61-b488-161deacc10ff' }
    * url BASE_SERVER_URL
    * json envimpact = read('classpath:com/it/multibrand/karate/accountdetails/EnvironmentalImpact.json')
    * def AccountNumber = envimpact.accountNumber
    * def CompanyCode = envimpact.companyCode

  @validEnvironmentalImpact
  Scenario: Successful display of Environmental Impact
    Given path '/NRGREST/rest/profile/environmentalImpacts'
    And form field accountNumber = AccountNumber
    And form field companyCode = CompanyCode
    When method POST
    Then status 200
    #Verify successful response
    Then match response.customerSince == '#notnull'
    Then match response.environmentImpacts == '#notnull'
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Get Environmental Impact Successful'
    And print 'Valid Environmental Impact Response Time:' + responseTime
    * print response

  @invalidEnvironmentalImpact
  Scenario: Invalid input of account number
    Given path '/NRGREST/rest/profile/environmentalImpacts'
    And form field accountNumber = '00001379575'
    And form field companyCode = CompanyCode
    When method POST
    Then status 200
    #Verify error response
    Then match response.customerSince == null
    Then match response.environmentImpacts == null
    Then match response.resultCode == '3'
    Then match response.resultDescription == 'No Data'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == 'Sorry! Something went wrong. Please try again'
    And print 'Get Environmental Impact Unsuccessful'
    And print 'Invalid Environmental Impact Response Time:' + responseTime
    * print response
