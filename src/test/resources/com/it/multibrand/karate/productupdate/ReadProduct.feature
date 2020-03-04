@ReadProductFeature
Feature: Product Update - READ
  Display products in account number.

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx',  'Cache-Control': 'no-cache', 'Postman-Token': '22cf0b9a-3c0b-4800-92a0-cb0f5a936a50' }
    * url BASE_SERVER_URL
    * json readproduct = read('classpath:com/it/multibrand/karate/productupdate/ReadProduct.json')
    * def AccountNumber = readproduct.accountNumber
    * def CompanyCode = readproduct.companyCode
    * def Action = readproduct.action

  @validReadProduct
  Scenario: Read valid account number
    Given path '/NRGREST/rest/profile/productUpdate'
    And form field accountNumber = AccountNumber
    And form field action = Action
    And form field companyCode = CompanyCode
    When method POST
    Then status 200
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Read Product Successful'
    And print 'Valid Read Product Response Time:' + responseTime
    * print response

  @invalidReadProduct1
  Scenario: Read invalid account number
    Given path '/NRGREST/rest/profile/productUpdate'
    And form field accountNumber = '00001379575'
    And form field action = Action
    And form field companyCode = CompanyCode
    When method POST
    Then status 200
    Then match response.productDO == null
    Then match response.resultCode == '2'
    Then match response.resultDescription == 'CA not found.'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == 'Sorry! Something went wrong. Please try again'
    And print 'Read Product Unsuccessful'
    And print 'Invalid Read Product Response Time:' + responseTime
    * print response

  @invalidReadProduct2
  Scenario: Read de-enrolled account number
    Given path '/NRGREST/rest/profile/productUpdate'
    And form field accountNumber = AccountNumber
    And form field action = Action
    And form field companyCode = CompanyCode
    When method POST
    Then status 200
    Then match response.productDO == []
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'No Data'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Read Product Unsuccessful'
    And print 'Invalid Read Product Response Time:' + responseTime
    * print response
