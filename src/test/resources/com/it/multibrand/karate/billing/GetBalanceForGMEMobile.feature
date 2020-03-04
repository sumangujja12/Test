@GetBalanceforGMEMobile
Feature: Get Balance For GME Mobile

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': '46124531-1b7e-4315-a44d-aa9b31d97669' }
    * url BASE_SERVER_URL
    * json balance = read('classpath:com/it/multibrand/karate/billing/GetBalanceForGMEMobile.json')
    * def BrandName = balance.brandName
    * def CompanyCode = balance.companyCode
    * def AccountNumber = balance.accountNumber
    * def Bpid = balance.bpid
    
  @validBalanceForGME
  Scenario: Get Balance For GME Mobile successfully
    Given path '/NRGREST/rest/billResource/getBalanceForGMEMobile'
    And form field brandName = BrandName
    And form field companyCode = CompanyCode
    And form field accountNumber = AccountNumber
    And form field bpid = Bpid
    When method POST
    Then status 200
    #Verify successful response
    Then match response.recentPendingPayDate == '#notnull'
    Then match response.currentDueDate == '#notnull'
    Then match response.currentArBalance == '#notnull'
    Then match response.lastPayAmt == '#notnull'
    Then match response.lastPayDate == '#notnull'
    Then match response.pastDueAmt == '#notnull'
    Then match response.creditAmt == '#notnull'
    Then match response.resultCode == '0'
    Then match response.resultDescription == ''
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Get Balance for GME Mobile Successful'
    And print 'Valid Get Balance for GME Mobile Response Time:' + responseTime
    * print response
    
  @invalidBalanceForGME1
  Scenario: Get Balance For GME Mobile unsuccessfully with invalid account number
    Given path '/NRGREST/rest/billResource/getBalanceForGMEMobile'
    And form field brandName = BrandName
    And form field companyCode = CompanyCode
    And form field accountNumber = '000072'
    And form field bpid = Bpid
    When method POST
    Then status 200
    #Verify successful response
    Then match response.recentPendingPayDate == null
    Then match response.currentDueDate == ''
    Then match response.currentArBalance == ''
    Then match response.lastPayAmt == '0.00'
    Then match response.lastPayDate == ''	
    Then match response.pastDueAmt == ''
    Then match response.creditAmt == ''
    Then match response.resultCode == '0'
    Then match response.resultDescription == ''
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Get Balance for GME Mobile Unsuccessful'
    And print 'Invalid Get Balance for GME Mobile Response Time:' + responseTime
    * print response
    
	@invalidBalanceForGME2
  Scenario: Get Balance For GME Mobile unsuccessfully with null account number
    Given path '/NRGREST/rest/billResource/getBalanceForGMEMobile'
    And form field brandName = BrandName
    And form field companyCode = CompanyCode
    And form field accountNumber = ''
    And form field bpid = Bpid
    When method POST
    Then status 200
    #Verify successful response
    Then match response.recentPendingPayDate == null
    Then match response.currentDueDate == ''
    Then match response.currentArBalance == ''
    Then match response.lastPayAmt == '0.00'
    Then match response.lastPayDate == ''
    Then match response.pastDueAmt == ''
    Then match response.creditAmt == ''
    Then match response.resultCode == '1'
    Then match response.resultDescription == 'Exception Occurred'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == 'Sorry! Something went wrong. Please try again.'
    And print 'Get Balance for GME Mobile Unsuccessful'
    And print 'Invalid Get Balance for GME Mobile Response Time:' + responseTime
    * print response