@GetAccountDetailsFeature
Feature: Account Details - Get Account Details

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': '3daff321-8802-4475-bce4-daba25271e32' }
    * url BASE_SERVER_URL
    * json getaccountdetails = read('classpath:com/it/multibrand/karate/accountdetails/GetAccountDetails.json')
    * def BrandName = getaccountdetails.brandName
    * def AccountNumber = getaccountdetails.accountNumber
    * def CompanyCode = getaccountdetails.companyCode

  @validGetAccountDetails
  Scenario: Successful display of Account Details
    Given path '/NRGREST/rest/billResource/getAccountDetails'
    And form field brandName = BrandName
    And form field accountNumber = AccountNumber
    And form field companyCode = CompanyCode
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
    And print 'Get Account Details Successful'
    And print 'Valid Get Account DetailsResponse Time:' + responseTime
    * print response

  @invalidGetAccountDetails
  Scenario: Invalid Account Number entered
    Given path '/NRGREST/rest/billResource/getAccountDetails'
    And form field brandName = BrandName
    And form field accountNumber = '00001379575'
    And form field companyCode = CompanyCode
    When method POST
    Then status 200
    #Verify response
    Then match response.paymentReceiptPopupShowFlag == null
    Then match response.emailBounceFlag == ''
    Then match response.emailID == ''
    Then match response.mktPreference == ''
    Then match response.phoneDO == null
    Then match response.contractAccountDO == null
    Then match response.resultCode == '0'
    Then match response.resultDescription == ''
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Get Account Details Unsuccessful'
    And print 'Invalid Get Account DetailsResponse Time:' + responseTime
    * print response
