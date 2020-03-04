@ForgotPasswordFeature
Feature: Forgot Password

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Cache-Control': 'no-cache', 'Postman-Token': '65ba487f-ae2b-439e-8f4f-55b31044548c' }
    * url BASE_SERVER_URL
    * json forgotpassword = read('classpath:com/it/multibrand/karate/login&reg/ForgotPassword.json')
    * def AccountNumber = forgotpassword.accountNumber
    * def CompanyCode = forgotpassword.companyCode
    * def Zip = forgotpassword.zip
    * def BrandName = forgotpassword.brandName

  @validForgotPassword
  Scenario: Successful request for password reset
    Given path '/NRGREST/rest/profile/forgotPassword/'
    And form field accountNumber = AccountNumber
    And form field companyCode = CompanyCode
    And form field zip = Zip
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify successful response
    Then match response.isCallSuccess == true
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == 'Password reset call sucessfull'
    Then match response.resultDisplayText == ''
    And print 'Forgot Password Successful'
    And print 'Valid Forgot Password Response Time:' + responseTime
    * print response

  @invalidForgotPassword1
  Scenario: Unsuccessful request for password reset - invalid account number
    Given path '/NRGREST/rest/profile/forgotPassword/'
    And form field accountNumber = '000013331905'
    And form field companyCode = CompanyCode
    And form field zip = Zip
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify successful response
    Then match response.isCallSuccess == null
    Then match response.resultCode == '2'
    Then match response.resultDescription == 'Invalid Account Number'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == 'Invalid Contract Account details- Account validation failed'
    Then match response.resultDisplayText == "Whoops! This account number isn't valid."
    And print 'Forgot Password Unsuccessful'
    And print 'Invalid Forgot Password Response Time:' + responseTime
    * print response

  @invalidForgotPassword2
  Scenario: Unsuccessful request for password reset - invalid zip
    Given path '/NRGREST/rest/profile/forgotPassword/'
    And form field accountNumber = AccountNumber
    And form field companyCode = CompanyCode
    And form field zip = '77220'
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify successful response
    Then match response.isCallSuccess == false
    Then match response.resultCode == '7'
    Then match response.resultDescription == 'Invalid Zip Code'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == 'Invalid Zip Code'
    Then match response.resultDisplayText == "Whoops! This zip code isn’t valid."
    And print 'Forgot Password Unsuccessful'
    And print 'Invalid Forgot Password Response Time:' + responseTime
    * print response

  @invalidForgotPassword3
  Scenario: Unsuccessful request for password - null account number
    Given path '/NRGREST/rest/profile/forgotPassword/'
    And form field accountNumber = ''
    And form field companyCode = CompanyCode
    And form field zip = Zip
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify successful response
    Then match response.isCallSuccess == false
    Then match response.resultCode == '5'
    Then match response.resultDescription == 'Invalid Input Parameters'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == 'Invalid Input Parameters'
    Then match response.resultDisplayText == "Whoops! We couldn't validate your details."
    And print 'Forgot Password Unsuccessful'
    And print 'Invalid Forgot Password Response Time:' + responseTime
    * print response
