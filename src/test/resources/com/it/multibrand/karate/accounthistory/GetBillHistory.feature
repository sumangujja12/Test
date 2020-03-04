@GetBillHistoryFeature
Feature: Account History - Get Bill History

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': 'fed0bbab-b5cc-4d29-902f-4cb9c6ae113d' }
    * url BASE_SERVER_URL
    * json billhistory = read('classpath:com/it/multibrand/karate/accounthistory/GetBillHistory.json')
    * def AccountNumber = billhistory.accountNumber
    * def StartDate = billhistory.startDate
    * def EndDate = billhistory.endDate
    * def CompanyCode = billhistory.companyCode
    * def BrandName = billhistory.brandName

  @validBillHistory
  Scenario: Display bill history successfully
    Given path '/NRGREST/rest/history/getBillHistory'
    And form field accountNumber = AccountNumber
    And form field startDate = StartDate
    And form field endDate = EndDate
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify successful response
    Then match response.ccsDataFlag == 'X'
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Get Bill History Successful'
    And print 'Valid Get Bill History Response Time:' + responseTime
    * print response

  @invalidBillHistory
  Scenario: Display bill history unsuccessfully with invalid account number
    Given path '/NRGREST/rest/history/getBillHistory'
    And form field accountNumber = '2934'
    And form field startDate = StartDate
    And form field endDate = EndDate
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify response
    Then match response.ccsDataFlag == 'O'
    Then match response.resultCode == '3'
    Then match response.resultDescription == 'No Data'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == "Sorry! Something went wrong. Please try again"
    And print 'Get Bill History Unsuccessful'
    And print 'Invalid Get Bill History Response Time:' + responseTime
    * print response

  @invalidBillHistory
  Scenario: Display bill history unsuccessfully with null account number
    Given path '/NRGREST/rest/history/getBillHistory'
    And form field accountNumber = ''
    And form field startDate = StartDate
    And form field endDate = EndDate
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify response
    Then match response.ccsDataFlag == 'O'
    Then match response.resultCode == '3'
    Then match response.resultDescription == 'No Data'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == "Sorry! Something went wrong. Please try again"
    And print 'Get Bill History Unsuccessful'
    And print 'Invalid Get Bill History Response Time:' + responseTime
    * print response
