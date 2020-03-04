@ScheduleOneTimeCCPaymentFeature
Feature: PAYMENT - Schedule One Time CC Payment

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': '96194394-7cba-4b65-8e71-1951bfea973a' }
    * url BASE_SERVER_URL
    * json scheduleonetimecc = read('classpath:com/it/multibrand/karate/payment/ScheduleOneTimeCCPayment.json')
    * def BusinessPartnerId = scheduleonetimecc.businessPartnerId
    * def ContractAccountNumber = scheduleonetimecc.contractAccountNumber
    * def CcNumber = scheduleonetimecc.ccNumber
    * def ExpMonth = scheduleonetimecc.expMonth
    * def ExpYear = scheduleonetimecc.expYear
    * def PaymentAmount = scheduleonetimecc.paymentAmount
    * def ScheduledDate = scheduleonetimecc.scheduledDate
    * def ZipCode = scheduleonetimecc.zipCode
    * def CompanyCode = scheduleonetimecc.companyCode
    * def BrandName = scheduleonetimecc.brandName

  @validScheduleOneTimeCCPayment
  Scenario: Schedule one time payment cc successfully
    Given path '/NRGREST/rest/billResource/scheduleOneTimeCCPayment'
    And form field businessPartnerId = BusinessPartnerId
    And form field contractAccountNumber = ContractAccountNumber
    And form field ccNumber = CcNumber
    And form field expMonth = ExpMonth
    And form field expYear = ExpYear
    And form field paymentAmount = PaymentAmount
    And form field scheduledDate = ScheduledDate
    And form field zipCode = ZipCode
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify successful response:
    Then match response.eTrackingId == '#notnull'
    Then match response.errorCode == null
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Schedule One Time CC Payment Successful'
    And print 'Valid Schedule One Time CC Payment Response Time:' + responseTime
    * print response

  @invalidScheduleOneTimeCCPayment1
  Scenario: Schedule one time payment cc unsuccessfully with past date
    Given path '/NRGREST/rest/billResource/scheduleOneTimeCCPayment'
    And form field businessPartnerId = BusinessPartnerId
    And form field contractAccountNumber = ContractAccountNumber
    And form field ccNumber = CcNumber
    And form field expMonth = ExpMonth
    And form field expYear = ExpYear
    And form field paymentAmount = PaymentAmount
    And form field scheduledDate = '12/02/2019'
    And form field zipCode = ZipCode
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify unsuccessful response:
    Then match response.eTrackingId == null
    Then match response.errorCode == '08'
    Then match response.resultCode == '2'
    Then match response.resultDescription == 'Scheduled CC Payment date is in the past.'
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == "Sorry! Something went wrong. Please try again"
    And print 'Schedule One Time CC Payment Unsuccessful'
    And print 'Invalid Schedule One Time CC Payment Response Time:' + responseTime
    * print response

  @invalidScheduleOneTimeCCPayment2
  Scenario: Schedule one time payment cc unsuccessfully with expired cc
    Given path '/NRGREST/rest/billResource/scheduleOneTimeCCPayment'
    And form field businessPartnerId = BusinessPartnerId
    And form field contractAccountNumber = ContractAccountNumber
    And form field ccNumber = CcNumber
    And form field expMonth = ExpMonth
    And form field expYear = '2019'
    And form field paymentAmount = PaymentAmount
    And form field scheduledDate = ScheduledDate
    And form field zipCode = ZipCode
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify unsuccessful response:
    Then match response.eTrackingId == null
    Then match response.errorCode == '06'
    Then match response.resultCode == '2'
    Then match response.resultDescription == 'Expiry date is in the past.'
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == "Sorry! Something went wrong. Please try again"
    And print 'Schedule One Time CC Payment Unsuccessful'
    And print 'Invalid Schedule One Time CC Payment Response Time:' + responseTime
    * print response

  @invalidScheduleOneTimeCCPayment3
  Scenario: Schedule one time payment cc unsuccessfully with invalid account number
    Given path '/NRGREST/rest/billResource/scheduleOneTimeCCPayment'
    And form field businessPartnerId = BusinessPartnerId
    And form field contractAccountNumber = '000072'
    And form field ccNumber = CcNumber
    And form field expMonth = ExpMonth
    And form field expYear = ExpYear
    And form field paymentAmount = PaymentAmount
    And form field scheduledDate = ScheduledDate
    And form field zipCode = ZipCode
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify unsuccessful response:
    Then match response.eTrackingId == null
    Then match response.errorCode == '01'
    Then match response.resultCode == '2'
    Then match response.resultDescription == 'Invalid Contract Account/Business Partner.'
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == "Sorry! Something went wrong. Please try again"
    And print 'Schedule One Time CC Payment Unsuccessful'
    And print 'Invalid Schedule One Time CC Payment Response Time:' + responseTime
    * print response

  @invalidScheduleOneTimeCCPayment4
  Scenario: Schedule one time payment cc unsuccessfully with invalid account number
    Given path '/NRGREST/rest/billResource/scheduleOneTimeCCPayment'
    And form field businessPartnerId = '00062'
    And form field contractAccountNumber = ContractAccountNumber
    And form field ccNumber = CcNumber
    And form field expMonth = ExpMonth
    And form field expYear = ExpYear
    And form field paymentAmount = PaymentAmount
    And form field scheduledDate = ScheduledDate
    And form field zipCode = ZipCode
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify unsuccessful response:
    Then match response.eTrackingId == null
    Then match response.errorCode == '01'
    Then match response.resultCode == '2'
    Then match response.resultDescription == 'Invalid Contract Account/Business Partner.'
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == "Sorry! Something went wrong. Please try again"
    And print 'Schedule One Time CC Payment Unsuccessful'
    And print 'Invalid Schedule One Time CC Payment Response Time:' + responseTime
    * print response
