@GetYearlyUsageFeature
Feature: USAGE - Get Yearly Usage

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx',  'Cache-Control': 'no-cache', 'Postman-Token': '9d5bdbc6-3691-4f31-bd05-8db8c240366f' }
    * url BASE_SERVER_URL
    * json yearlyusagerequest = read('classpath:com/it/multibrand/karate/usage/GetYearlyUsage.json')
    * def AccountNumber = yearlyusagerequest.accountNumber
    * def ESID = yearlyusagerequest.esid
    * def ContractId = yearlyusagerequest.contractId
    * def CompanyCode = yearlyusagerequest.companyCode
    * def BrandName = yearlyusagerequest.brandName
    * def CurrentDate = yearlyusagerequest.currentDate

  @validYearlyUsage
  Scenario: Successfully Display yearly usage with valid Account number, ESID, contract ID
    Given path '/NRGREST/rest/history/getMonthlyUsage'
    And form field accountNumber = AccountNumber
    And form field esid = ESID
    And form field contractId = ContractId
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field currentDate = CurrentDate
    When method POST
    Then status 200
    #Verify successful response
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Get Yearly Usage Successful'
    And print 'Valid Get Yearly Usage Response Time:' + responseTime
    * print response
    
  @validYearlyUsage2
  Scenario: Successfully Display yearly usage with Null contract Account number
    Given path '/NRGREST/rest/history/getMonthlyUsage'
    And form field accountNumber = ''
    And form field esid = ESID
    And form field contractId = ContractId
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field currentDate = CurrentDate
    When method POST
    Then status 200
    #Verify successful response
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Get Yearly Usage Successful'
    And print 'Valid Get Yearly Usage Response Time:' + responseTime
    * print response

  @InvalidYearlyUsage1
  Scenario: Unsuccessfully Display Yearly Usage with Invalid ESID entered
    Given path '/NRGREST/rest/history/getMonthlyUsage'
    And form field accountNumber = AccountNumber
    And form field esid = '10443720008135'
    And form field contractId = ContractId
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field currentDate = CurrentDate
    When method POST
    Then status 200
    #Verify response:
    Then match response.monthlyUsageResponse == null
    Then match response.resultCode == '3'
    Then match response.resultDescription == 'No Data'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText contains 'Uh-oh.'
    And print 'Get Yearly Usage Unsuccessful'
    And print 'Invalid Get Yearly Usage Response Time:' + responseTime
    * print response

	@InvalidYearlyUsage2
  Scenario:  Unsuccessfully Display Yearly Usage with Invalid Contract ID entered
    Given path '/NRGREST/rest/history/getMonthlyUsage'
    And form field accountNumber = AccountNumber
    And form field esid = ESID
    And form field contractId = '0046834'
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field currentDate = CurrentDate
    When method POST
    Then status 200
    #Verify response:
    Then match response.monthlyUsageResponse == null
    Then match response.resultCode == '3'
    Then match response.resultDescription == 'No Data'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText contains 'Uh-oh.'
    And print 'Get Yearly Usage Unsuccessful'
    And print 'Invalid Get Yearly Usage Response Time:' + responseTime
    * print response