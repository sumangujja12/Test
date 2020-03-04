@CheckSwapEligibilityFeature
Feature: Check Swap Eligibility

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': '632a691f-61d4-4466-bde0-f99098482d51' }
    * url BASE_SERVER_URL
    * json checkswap = read('classpath:com/it/multibrand/karate/renewal&swap/CheckSwapEligibility.json')
    * def AccountNumber = checkswap.accountNumber
    * def LanguageCode = checkswap.languageCode
    * def CompanyCode = checkswap.companyCode
    * def BrandName = checkswap.brandName

  @validCheckSwapEligibility
  Scenario: Check swap eligibility successfully
    Given path 'NRGREST/rest/billResource/checkSwapEligibility'
    And form field accountNumber = AccountNumber
    And form field languageCode = LanguageCode
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify successful response
    Then match response.yearlyTreesAbsorbed == '#notnull'
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Check Swap Eligibility Successful'
    And print 'Valid Check Swap Eligibility Response Time:' + responseTime
    * print response

  @invalidCheckSwapEligibility1
  Scenario: Check swap eligibility unsuccessfully with invalid account number
    Given path 'NRGREST/rest/billResource/checkSwapEligibility'
    And form field accountNumber = '0001'
    And form field languageCode = LanguageCode
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify response
    Then match response.yearlyTreesAbsorbed == null
    Then match response.resultCode == '1'
    Then match response.resultDescription == 'Exception Occurred'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == 'Sorry! Something went wrong. Please try again'
    And print 'Check Swap Eligibility Unsuccessful'
    And print 'Invalid Check Swap Eligibility Response Time:' + responseTime
    * print response

  @invalidCheckSwapEligibility2
  Scenario: Check swap eligibility unsuccessfully with null account number
    Given path 'NRGREST/rest/billResource/checkSwapEligibility'
    And form field accountNumber = ''
    And form field languageCode = LanguageCode
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify response
    Then match response.yearlyTreesAbsorbed == null
    Then match response.resultCode == '1'
    Then match response.resultDescription == 'Exception Occurred'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == 'Sorry! Something went wrong. Please try again'
    And print 'Check Swap Eligibility Unsuccessful'
    And print 'Invalid Check Swap Eligibility Response Time:' + responseTime
    * print response
