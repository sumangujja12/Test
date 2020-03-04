@AverageBillingFeature
Feature: Average Billing

  Background: 
    * configure charset = null
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': '96ee8787-d8fa-43c5-ad5f-dd00584c6591' }
    * url BASE_SERVER_URL
    * json averagebillingdata = read('classpath:com/it/multibrand/karate/billing/AverageBilling.json')
    * def AccountNumber = averagebillingdata.accountNumber
    * def CompanyCode = averagebillingdata.companyCode
    * def BPNumber = averagebillingdata.bpNumber
    * def ContractId = averagebillingdata.contractId

  @validAverageBilling
  Scenario: Display Average Billing successfully
    Given path '/NRGREST/rest/billResource/ambEligibilityCheck'
    And form field accountNumber = AccountNumber
    And form field companyCode = CompanyCode
    And form field bpNumber = BPNumber
    And form field contractId = ContractId
    When method POST
    Then status 200
    #Verify successful response
    Then match response.ambWebTab == '#notnull'
    Then match response.deffAmt == '#notnull'
    Then match response.ambAmt == '#notnull'
    Then match response.respStatus == ''
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Get Average Billing Successful'
    And print 'Valid Average Billing Response Time:' + responseTime
    * print response

  @invalidAverageBilling
  Scenario: Display Average Billing unsuccessfully with null account number
    Given path '/NRGREST/rest/billResource/ambEligibilityCheck'
    And form field accountNumber = ''
    And form field companyCode = CompanyCode
    And form field bpNumber = BPNumber
    And form field contractId = ContractId
    When method POST
    Then status 200
    #Verify successful response
    Then match response.errMessage == null
    Then match response.errCode == null
    Then match response.prgStatus == null
    Then match response.ambWebTab == null
    Then match response.deffAmt == null
    Then match response.ambAmt == null
    Then match response.respStatus == null
    Then match response.resultCode == '5'
    Then match response.resultDescription == 'Bad Request'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == 'Sorry! Something went wrong. Please try again'
    And print 'Get Average Billing Unsuccessful'
    And print 'Invalid Average Billing Response Time:' + responseTime
    * print response
