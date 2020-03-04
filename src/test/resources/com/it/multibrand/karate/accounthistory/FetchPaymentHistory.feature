@FetchPaymentHistoryFeature
Feature: Account History - Fetch Payment History

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': 'a089bd6d-42ee-4468-92d8-4592ded5a0f3' }
    * url BASE_SERVER_URL
    * json fetchpayment = read('classpath:com/it/multibrand/karate/accounthistory/FetchPaymentHistory.json')
    * def AccountNumber = fetchpayment.accountNumber
    * def StartDate = fetchpayment.startDate
    * def EndDate = fetchpayment.endDate
    * def CompanyCode = fetchpayment.companyCode
    * def BrandName = fetchpayment.brandName

  @validFetchPaymentHistory
  Scenario: Display payment history successfully
    Given path '/NRGREST/rest/history/fetchPaymentHistory'
    And form field accountNumber = AccountNumber
    And form field startDate = StartDate
    And form field endDate = EndDate
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
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
    And print 'Fetch Payment History Successful'
    And print 'Valid Fetch Payment History Response Time:' + responseTime
    * print response

  @invalidFetchPaymentHistory1
  Scenario: Display payment history unsuccessfully with invalid account number
    Given path '/NRGREST/rest/history/fetchPaymentHistory'
    And form field accountNumber = '2934'
    And form field startDate = StartDate
    And form field endDate = EndDate
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify response
    Then match response.resultCode == '3'
    Then match response.resultDescription == 'No Data'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == "Uh-oh. There's nothing here. Want to go back?"
    And print 'Fetch Payment History Unsuccessful'
    And print 'Invalid Fetch Payment History Response Time:' + responseTime
    * print response

  @invalidFetchPaymentHistory2
  Scenario: Display payment history unsuccessfully with null account number
    Given path '/NRGREST/rest/history/fetchPaymentHistory'
    And form field accountNumber = ''
    And form field startDate = StartDate
    And form field endDate = EndDate
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify successful response
    Then match response.resultCode == '0'
    Then match response.resultDescription == ''
    Then match response.errorCode == '1'
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Fetch Payment History Unsuccessful'
    And print 'Invalid Fetch Payment History Response Time:' + responseTime
    * print response
