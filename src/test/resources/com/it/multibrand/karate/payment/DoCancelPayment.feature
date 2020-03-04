@DoCancelPaymentFeature
Feature: PAYMENT - Cancel Payment

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': '6827693f-08a0-4c1b-9c97-16ad2a350672' }
    * url BASE_SERVER_URL
    * json cancelpayment = read('classpath:com/it/multibrand/karate/payment/DoCancelPayment.json')
    * def AccountNumber = cancelpayment.accountNumber
    * def CompanyCode = cancelpayment.companyCode
    * def PaymentId = cancelpayment.paymentId
    * def BrandName = cancelpayment.brandName

  @invalidDoCancelPayment 
   	Scenario: Cancel payment unsuccessfully
    Given path '/NRGREST/rest/billResource/doCancelPayment'
    And form field accountNumber = AccountNumber
    And form field companyCode = CompanyCode
    And form field paymentId = PaymentId
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify unsuccessful response:
    Then match response.successCode == '' 
    Then match response.errorCode == 'MSG_CCSERR_3_CANCEL_PAY'
    Then match response.resultCode == '2'
    Then match response.resultDescription == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == "Unable to Cancel Payment We are unable to cancel your payment for one of the following reasons: - It is after 5 p.m. on the scheduled payment date, or - The payment has been used to restore service that had been disconnected, or to stop disconnection from occurring.\n"
    And print 'Do Cancel Payment Unsuccessful'
    And print 'Invalid Do Cancel Payment Response Time:' + responseTime
    * print response