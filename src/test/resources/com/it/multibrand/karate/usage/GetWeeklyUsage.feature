@GetWeeklyUsageFeature
Feature: USAGE - Get Weekly Usage

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': '70b3a61d-ce18-4487-a180-1a116c2fb60f' }
    * url BASE_SERVER_URL
    * json monthlyusagerequest = read('classpath:com/it/multibrand/karate/usage/GetWeeklyUsage.json')
    * def AccountNumber = monthlyusagerequest.accountNumber
    * def ESID = monthlyusagerequest.esid
    * def ZoneId = monthlyusagerequest.zoneId
    * def ContractId = monthlyusagerequest.contractId
    * def CompanyCode = monthlyusagerequest.companyCode
    * def WeekNumber = monthlyusagerequest.weekNumber
    * def BrandName = monthlyusagerequest.brandName
    * def Year = monthlyusagerequest.year

  @validWeeklyUsage1
  Scenario: Successfully Display Weekly usage with valid contract account number, ESIID, contract ID
    Given path '/NRGREST/rest/history/getWeeklyUsage'
    And form field accountNumber = AccountNumber
    And form field esid = ESID
    And form field zoneId = ZoneId
    And form field contractId = ContractId
    And form field companyCode = CompanyCode
    And form field weekNumber = WeekNumber
    And form field brandName = BrandName
    And form field year = Year
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
    And print 'Get Weekly Usage Successful'
    And print 'Valid Get Weekly Usage Response Time:' + responseTime
    * print response
    
  @validWeeklyUsage2
  Scenario: Successfully Display Weekly usage with Null Account Number entered
    Given path '/NRGREST/rest/history/getWeeklyUsage'
    And form field accountNumber = ''
    And form field esid = ESID
    And form field zoneId = ZoneId
    And form field contractId = ContractId
    And form field companyCode = CompanyCode
    And form field weekNumber = WeekNumber
    And form field brandName = BrandName
    And form field year = Year
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
    And print 'Get Weekly Usage Successful'
    And print 'Valid Get Weekly Usage Response Time:' + responseTime
    * print response

  @invalidWeeklyUsage1
  Scenario: Unsuccessfully Display Weekly usage with Null ESID entered
    Given path '/NRGREST/rest/history/getWeeklyUsage'
    And form field accountNumber = AccountNumber
    And form field esid = ''
    And form field zoneId = ZoneId
    And form field contractId = ContractId
    And form field companyCode = CompanyCode
    And form field weekNumber = WeekNumber
    And form field brandName = BrandName
    And form field year = Year
    When method POST
    Then status 200
    #Verify response:
    Then match response.weeklyUsageData == null
    Then match response.resultCode == '0'
    Then match response.resultDescription == ''
    Then match response.errorCode == '1'
    Then match response.errorDescription == 'Exception Occurred'
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText contains ''
    And print 'Get Weekly Usage Unsuccessful'
    And print 'Invalid Get Weekly Usage Response Time:' + responseTime
    * print response

  @invalidWeeklyUsage2
  Scenario: Unsuccessfully Display Weekly usage with Null Contract ID entered
    Given path '/NRGREST/rest/history/getWeeklyUsage'
    And form field accountNumber = AccountNumber
    And form field esid = ESID
    And form field zoneId = ZoneId
    And form field contractId = ''
    And form field companyCode = CompanyCode
    And form field weekNumber = WeekNumber
    And form field brandName = BrandName
    And form field year = Year
    When method POST
    Then status 200
    #Verify response:
    Then match response.weeklyUsageData == null
    Then match response.resultCode == '0'
    Then match response.resultDescription == ''
    Then match response.errorCode == '1'
    Then match response.errorDescription == 'Exception Occurred'
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText contains ''
    And print 'Get Weekly Usage Unsuccessful'
    And print 'Invalid Get Weekly Usage Response Time:' + responseTime
    * print response
