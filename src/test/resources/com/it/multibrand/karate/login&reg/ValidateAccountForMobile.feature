@ValidateAccountForMobileFeature
Feature: Validate Account For Mobile

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': 'f8d2288f-a29b-4e6e-8046-45e892f4d07a' }
    * url BASE_SERVER_URL
    * json validateaccount = read('classpath:com/it/multibrand/karate/login&reg/ValidateAccountForMobile.json')
    * def AccountNumber = validateaccount.accountNumber
    * def LastName = validateaccount.lastName
    * def CompanyCode = validateaccount.companyCode
    * def BrandName = validateaccount.brandName

  @validValidateAccountForMobile
  Scenario: Validate Account For Mobile successfully
    Given path '/NRGREST/rest/registration/validateAccountForMobile'
    And form field accountNumber = AccountNumber
    And form field lastName = LastName
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify successful response
    Then match response.firstName == '#notnull'
    Then match response.lastName == LastName
    Then match response.userName == ''
    Then match response.checkDigit == '#notnull'
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Account information valid'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == 'Account information valid - Success you can now create username with this A/C and Lastname'
    Then match response.resultDisplayText == ''
    And print 'Validate Account for Mobile Successful'
    And print 'Valid Validate Account for Mobile Response Time:' + responseTime
    * print response

  @invalidValidateAccountForMobile1
  Scenario: Validate Account For Mobile unsuccessfully
    Given path '/NRGREST/rest/registration/validateAccountForMobile'
    And form field accountNumber = '000013331905'
    And form field lastName = 'MEJIA'
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify unsuccessful response
    Then match response.firstName == ''
    Then match response.lastName == '#notnull'
    Then match response.userName == ''
    Then match response.checkDigit == ''
    Then match response.resultCode == '2'
    Then match response.resultDescription == 'No Match'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == 'Invalid Contract Account details- Account validation failed'
    Then match response.resultDisplayText contains "no match for the account number and last name."
    And print 'Validate Account for Mobile Unsuccessful'
    And print 'Invalid Validate Account for Mobile Response Time:' + responseTime
    * print response

 	@invalidValidateAccountForMobile1
  Scenario: Validate Account For Mobile unsuccessfully with null data
    Given path '/NRGREST/rest/registration/validateAccountForMobile'
    And form field accountNumber = ''
    And form field lastName = ''
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify unsuccessful response
    Then match response.firstName == ''
    Then match response.lastName == ''
    Then match response.userName == ''
    Then match response.checkDigit == ''
    Then match response.resultCode == '6'
    Then match response.resultDescription == 'Invalid Input Parameters'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == 'Invalid Input Parameters - Please check entered A/C number and Lastname'
    Then match response.resultDisplayText == 'Sorry! Something went wrong. Please try again'
    And print 'Validate Account for Mobile Unsuccessful'
    And print 'Invalid Validate Account for Mobile Response Time:' + responseTime
    * print response
