@GetPaymentMethodsFeature
Feature: PAYMENT - Get payment methods
 
  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': 'b3ccfb8b-870c-4278-ac91-0d32d12dc26b' }
    * url BASE_SERVER_URL
   	* json paymentmethods = read('classpath:com/it/multibrand/karate/payment/GetPaymentMethods.json')
    * def ContractAccountNumber = paymentmethods.contractAccountNumber
    * def CompanyCode = paymentmethods.companyCode
    * def BrandName = paymentmethods.brandName

  @validGetPaymentMethods
  Scenario: Get payment methods successfully
    Given path '/NRGREST/rest/billResource/getPaymentMethods'
    And form field contractAccountNumber = ContractAccountNumber
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify successful response:
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == 'Successfully retrieved all Menthods of Payments'
    Then match response.messageText == ''
    Then match response.resultDisplayText contains ''
    And print 'Get Payment Methods Successful'
    And print 'Valid Payment Methods Response Time:' + responseTime
    * print response
    
  @invalidGetPaymentMethods1
  Scenario: Get payment methods unsuccessfully with null account number
    Given path '/NRGREST/rest/billResource/getPaymentMethods'
    And form field contractAccountNumber = ''
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify unsuccessful response:
    Then match response.paymentMethods == null
    Then match response.resultCode == '5'
    Then match response.resultDescription == 'Invalid Input Parameters'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == 'Invalid Input Parameters - Please check entered A/C number and Company Code'
    Then match response.messageText == ''
    Then match response.resultDisplayText == 'Sorry! Something went wrong. Please try again.'
    And print 'Get Payment Methods Unsuccessful'
    And print 'Invalid Payment Methods Response Time:' + responseTime
    * print response

  @invalidGetPaymentMethods2
  Scenario: Get payment methods unsuccessfully with invalid account number
    Given path '/NRGREST/rest/billResource/getPaymentMethods'
    And form field contractAccountNumber = '000072'
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify unsuccessful response:
    Then match response.paymentMethods == null
    Then match response.resultCode == '1'
    Then match response.resultDescription == 'Exception Occurred'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == 'Sorry! Something went wrong. Please try again.'
    And print 'Get Payment Methods Unsuccessful'
    And print 'Invalid Payment Methods Response Time:' + responseTime
    * print response
    