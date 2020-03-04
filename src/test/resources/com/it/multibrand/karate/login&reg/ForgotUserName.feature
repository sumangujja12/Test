@ForgotUsernameFeature
Feature: Forgot Username

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Cache-Control': 'no-cache', 'Postman-Token': '138a28b2-5404-47a2-a0c9-0e8591648841' }
    * url BASE_SERVER_URL
    * json forgotuser = read('classpath:com/it/multibrand/karate/login&reg/ForgotUserName.json')
    * def AccountNumber = forgotuser.accountNumber
    * def CompanyCode = forgotuser.companyCode
    * def Zip = forgotuser.zip
    * def BrandName = forgotuser.brandName

  @validForgotUsername
  Scenario: Successful request for username
    Given path '/NRGREST/rest/profile/forgotUserName'
    And form field accountNumber = AccountNumber
    And form field companyCode = CompanyCode
    And form field zip = Zip
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify successful response
    Then match response.userName == '#notnull'
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == 'Email with Username sent to user'
    Then match response.resultDisplayText == ''
    And print 'Forgot Username Successful'
    And print 'Valid Forgot Username Response Time:' + responseTime
    * print response

  @invalidForgotUsername1
  Scenario: Unsuccessful request for username - invalid account number
    Given path '/NRGREST/rest/profile/forgotUserName'
    And form field accountNumber = '000013331905'
    And form field companyCode = CompanyCode
    And form field zip = Zip
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify successful response
    Then match response.userName == null
    Then match response.resultCode == '2'
    Then match response.resultDescription == 'Invalid Account Number'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == 'Invalid Contract Account details- Account validation failed'
    Then match response.resultDisplayText == "Whoops! This account number isn't valid."
    And print 'Forgot Username Unsuccessful'
    And print 'Invalid Forgot Username Response Time:' + responseTime
    * print response

  @invalidForgotUsername2
  Scenario: Unsuccessful request for password reset - invalid zip
    Given path '/NRGREST/rest/profile/forgotUserName'
    And form field accountNumber = AccountNumber
    And form field companyCode = CompanyCode
    And form field zip = '77220'
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify successful response
    Then match response.userName == '#notnull'
    Then match response.resultCode == '7'
    Then match response.resultDescription == 'Invalid Zip Code'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == 'Invalid Zip Code'
    Then match response.resultDisplayText == "Whoops! This zip code isn't valid."
    And print 'Forgot Username Unsuccessful'
    And print 'Invalid Forgot Username Response Time:' + responseTime
    * print response

  @invalidForgotUsername3
  Scenario: Unsuccessful request for username - null account number
    Given path '/NRGREST/rest/profile/forgotUserName'
    And form field accountNumber = ''
    And form field companyCode = CompanyCode
    And form field zip = Zip
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify successful response
    Then match response.userName == null
    Then match response.resultCode == '5'
    Then match response.resultDescription == 'Invalid Input Parameters'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == 'Invalid Input Parameters'
    Then match response.resultDisplayText == "Whoops! We couldn't validate your details."
    And print 'Forgot Username Unsuccessful'
    And print 'Invalid Forgot Username Response Time:' + responseTime
    * print response
