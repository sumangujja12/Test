@GetMonthlyUsageFeature
Feature: USAGE - Get Monthly Usage

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': 'ca2c9933-4f53-4ff4-bce7-a0a0ffd7a70d' }
    * url BASE_SERVER_URL
    * json monthlyusagerequest = read('classpath:com/it/multibrand/karate/usage/GetMonthlyUsage.json')
    * def AccountNumber = monthlyusagerequest.accountNumber
    * def ESID = monthlyusagerequest.esid
    * def ContractId = monthlyusagerequest.contractId
    * def CompanyCode = monthlyusagerequest.companyCode
    * def BrandName = monthlyusagerequest.brandName
    * def DyHrInd = monthlyusagerequest.dyHrInd
    * def CurrentDate = monthlyusagerequest.currentDate
    * def CurDayInd = monthlyusagerequest.curDayInd

  @validMonthlyUsage1
  Scenario: Display Monthly Usage with valid Account number, ESID, contract ID successfully
    #* set monthlyusagerequest.accountNumber = '000073263788'
    Given path '/NRGREST/rest/history/getDailyHourlyUsage'
    And form field accountNumber = AccountNumber
    And form field esid = ESID
    And form field contractId = ContractId
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field dyHrInd = DyHrInd
    And form field currentDate = CurrentDate
    And form field curDayInd = CurDayInd
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
    And print 'Get Monthly Usage Successful'
    And print 'Valid Get Monthly Usage Response Time:' + responseTime
    * print response

  @validMonthlyUsage2
  Scenario: Successfully Display Monthly Usage with Null account number entered
    Given path '/NRGREST/rest/history/getDailyHourlyUsage'
    And form field accountNumber = ''
    And form field esid = ESID
    And form field contractId = ContractId
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field dyHrInd = DyHrInd
    And form field currentDate = CurrentDate
    And form field curDayInd = CurDayInd
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
    And print 'Get Monthly Usage Successful'
    And print 'Valid Get Monthly Usage Response Time:' + responseTime
    * print response

  @invalidMonthlyUsage1
  Scenario: Unsuccessfully Display Monthly Usage with Invalid ESID entered
    Given path '/NRGREST/rest/history/getDailyHourlyUsage'
    And form field accountNumber = AccountNumber
    And form field esid = '10443720008135'
    And form field contractId = ContractId
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field dyHrInd = DyHrInd
    And form field currentDate = CurrentDate
    And form field curDayInd = CurDayInd
    When method POST
    Then status 200
    #Verify response:
    Then match response.dailyUsageList == null
    Then match response.resultCode == '3'
    Then match response.resultDescription == 'No Data'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText contains 'Sorry! Something went wrong. Please try again'
    And print 'Get Monthly Usage Unsuccessful'
    And print 'Invalid Get Monthly Usage Response Time:' + responseTime
    * print response

  @invalidMonthlyUsage2
  Scenario: Unsuccessfully Display Monthly Usage with Invalid Contract ID entered
    Given path '/NRGREST/rest/history/getDailyHourlyUsage'
    And form field accountNumber = AccountNumber
    And form field esid = ESID
    And form field contractId = '0046834'
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field dyHrInd = DyHrInd
    And form field currentDate = CurrentDate
    And form field curDayInd = CurDayInd
    When method POST
    Then status 200
    #Verify response:
    Then match response.dailyUsageList == null
    Then match response.resultCode == '3'
    Then match response.resultDescription == 'No Data'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText contains 'Sorry! Something went wrong. Please try again'
    And print 'Get Monthly Usage Unsuccessful'
    And print 'Invalid Get Monthly Usage Response Time:' + responseTime
    * print response
