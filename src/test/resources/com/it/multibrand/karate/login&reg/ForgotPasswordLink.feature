@ForgotPasswordLinkFeature
Feature: Forgot Password Link

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization' : 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': '2844effb-f17d-4227-8f56-027a11caf41a' }
    * url BASE_SERVER_URL
    * json forgotpasswordlink = read('classpath:com/it/multibrand/karate/login&reg/ForgotPasswordLink.json')
    * def TransactionId = forgotpasswordlink.transactionId
    * def CompanyCode = forgotpasswordlink.companyCode
    * def BrandName = forgotpasswordlink.brandName

  @validForgotPasswordLink
  Scenario: Successful validation of forgot password link - Link expired
    Given path '/NRGREST/rest/profile/validatePasswordlink'
    And form field transactionId = TransactionId
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify successful response
    Then match response.valid == false
    Then match response.userName == ''
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == 'Link Expired'
    Then match response.resultDisplayText == ''
    And print 'Forgot Password Link Successful'
    And print 'Valid Forgot Password Link Response Time:' + responseTime
    * print response

  @invalidForgotPasswordLink
  Scenario: Unsuccessful validation of forgot password link - Null transaction ID
    Given path '/NRGREST/rest/profile/validatePasswordlink'
    And form field transactionId = ''
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify response
    Then match response.valid == false
    Then match response.userName == null
    Then match response.resultCode == '5'
    Then match response.resultDescription == 'Invalid Input Parameters'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == 'Invalid Paramenter'
    Then match response.resultDisplayText == 'Sorry! Something went wrong. Please try again'
    And print 'Forgot Password Link Unsuccessful'
    And print 'Invalid Forgot Password LinkResponse Time:' + responseTime
    * print response
