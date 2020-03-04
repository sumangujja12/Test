@UpdateBillingAddressFeature
Feature: Update Billing Address

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': '7bc00897-f2e6-4955-bbe2-b98f5956d255' }
    * url BASE_SERVER_URL
    * json updatebillingaddress = read('classpath:com/it/multibrand/karate/accountdetails/UpdateBillingAddress.json')
    * def AccountNumber = updatebillingaddress.accountNumber
    * def BPNumber = updatebillingaddress.bpNumber
    * def CompanyCode = updatebillingaddress.companyCode
    * def BrandName = updatebillingaddress.brandName
    * def StreetNum = updatebillingaddress.streetNum
    * def StreetName = updatebillingaddress.streetName
    * def AptNum = updatebillingaddress.aptNum
    * def City = updatebillingaddress.city
    * def State = updatebillingaddress.state
    * def PoBox = updatebillingaddress.poBox
    * def Zip = updatebillingaddress.zip

  @validUpdateBillingAddress
  Scenario: Successful update of billing address
    Given path '/NRGREST/rest/profile/updateBillingAddress'
    And form field accountNumber = AccountNumber
    And form field bpNumber = BPNumber
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field streetNum = StreetNum
    And form field streetName = StreetName
    And form field aptNum = AptNum
    And form field city = City
    And form field state = State
    And form field poBox = PoBox
    And form field zip = Zip
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
    And print 'Update Billing Addresss Successful'
    And print 'Valid Update Billing Address Response Time:' + responseTime
    * print response

  @invalidUpdateBillingAddress1
  Scenario: Unsuccessful update of billing address - Null account number entered
    Given path '/NRGREST/rest/profile/updateBillingAddress'
    And form field accountNumber = ''
    And form field bpNumber = BPNumber
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field streetNum = StreetNum
    And form field streetName = StreetName
    And form field aptNum = AptNum
    And form field city = City
    And form field state = State
    And form field poBox = PoBox
    And form field zip = Zip
    When method POST
    Then status 200
    #Verify error response
    Then match response.resultCode == '3'
    Then match response.resultDescription == 'MSG_ERR_UPD_BILL_ADDR'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == 'Sorry! Something went wrong. Please try again.'
    And print 'Update Billing Addresss Unsuccessful'
    And print 'Invalid Update Billing Address Response Time:' + responseTime
    * print response

  @invalidUpdateBillingAddress2
  Scenario: Unsuccessful update of billing address - Invalid zip code entered
    Given path '/NRGREST/rest/profile/updateBillingAddress'
    And form field accountNumber = AccountNumber
    And form field bpNumber = BPNumber
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field streetNum = StreetNum
    And form field streetName = StreetName
    And form field aptNum = AptNum
    And form field city = City
    And form field state = State
    And form field poBox = PoBox
    And form field zip = '7521'
    When method POST
    Then status 200
    #Verify error response
    Then match response.resultCode == '3'
    Then match response.resultDescription == 'MSG_ERR_UPD_BILL_ADDR'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == 'Sorry! Something went wrong. Please try again.'
    And print 'Update Billing Addresss Unsuccessful'
    And print 'Invalid Update Billing Address Response Time:' + responseTime
    * print response

  @invalidUpdateBillingAddress3
  Scenario: Unsuccessful update of billing address - Null Street Num entered
    Given path '/NRGREST/rest/profile/updateBillingAddress'
    And form field accountNumber = AccountNumber
    And form field bpNumber = BPNumber
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field streetNum = ''
    And form field streetName = StreetName
    And form field aptNum = AptNum
    And form field city = City
    And form field state = State
    And form field poBox = PoBox
    And form field zip = Zip
    When method POST
    Then status 200
    #Verify error response
    Then match response.resultCode == '3'
    Then match response.resultDescription == 'MSG_ERR_UPD_BILL_ADDR'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == 'Sorry! Something went wrong. Please try again.'
    And print 'Update Billing Addresss Unsuccessful'
    And print 'Invalid Update Billing Address Response Time:' + responseTime
    * print response

  @invalidUpdateBillingAddress4
  Scenario: Unsuccessful update of billing address - Null Street Name entered
    Given path '/NRGREST/rest/profile/updateBillingAddress'
    And form field accountNumber = AccountNumber
    And form field bpNumber = BPNumber
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field streetNum = StreetNum
    And form field streetName = ''
    And form field aptNum = AptNum
    And form field city = City
    And form field state = State
    And form field poBox = PoBox
    And form field zip = Zip
    When method POST
    Then status 200
    #Verify error response
    Then match response.resultCode == '3'
    Then match response.resultDescription == 'MSG_ERR_UPD_BILL_ADDR'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == 'Sorry! Something went wrong. Please try again.'
    And print 'Update Billing Addresss Unsuccessful'
    And print 'Invalid Update Billing Address Response Time:' + responseTime
    * print response

  @invalidUpdateBillingAddress5
  Scenario: Unsuccessful update of billing address - Null City entered
    Given path '/NRGREST/rest/profile/updateBillingAddress'
    And form field accountNumber = AccountNumber
    And form field bpNumber = BPNumber
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field streetNum = StreetNum
    And form field streetName = StreetName
    And form field aptNum = AptNum
    And form field city = ''
    And form field state = State
    And form field poBox = PoBox
    And form field zip = Zip
    When method POST
    Then status 200
    #Verify error response
    Then match response.resultCode == '3'
    Then match response.resultDescription == 'MSG_ERR_UPD_BILL_ADDR'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == 'Sorry! Something went wrong. Please try again.'
    And print 'Update Billing Addresss Unsuccessful'
    And print 'Invalid Update Billing Address Response Time:' + responseTime
    * print response

  @invalidUpdateBillingAddress6
  Scenario: Unsuccessful update of billing address - Null address entered
    Given path '/NRGREST/rest/profile/updateBillingAddress'
    And form field accountNumber = AccountNumber
    And form field bpNumber = BPNumber
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field streetNum = ''
    And form field streetName = ''
    And form field aptNum = ''
    And form field city = ''
    And form field state = ''
    And form field poBox = ''
    And form field zip = ''
    When method POST
    Then status 200
    #Verify error response
    Then match response.resultCode == '3'
    Then match response.resultDescription == 'MSG_ERR_UPD_BILL_ADDR'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == 'Sorry! Something went wrong. Please try again.'
    And print 'Update Billing Addresss Unsuccessful'
    And print 'Invalid Update Billing Address Response Time:' + responseTime
    * print response
