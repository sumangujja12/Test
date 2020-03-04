@SubmitCCPaymentFeature
Feature: PAYMENT - Submit CC Payment
 
  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': '2a4f9652-e05c-40bf-b0ad-139fb53f5abf' }
    * url BASE_SERVER_URL
    * json submitccpayment = read('classpath:com/it/multibrand/karate/payment/SubmitCCPayment.json')
    * def AuthType = submitccpayment.authType
    * def AccountNumber = submitccpayment.accountNumber
    * def Bpid = submitccpayment.bpid
    * def CcNumber = submitccpayment.ccNumber
    * def CvvNumber = submitccpayment.cvvNumber
    * def ExpirationDate = submitccpayment.expirationDate
    * def BillingZip = submitccpayment.billingZip
    * def PaymentAmount = submitccpayment.paymentAmount
    * def AccountName = submitccpayment.accountName
    * def AccountChkDigit = submitccpayment.accountChkDigit
    * def Locale = submitccpayment.locale
    * def Email = submitccpayment.email
    * def CompanyCode = submitccpayment.companyCode
    * def PaymentDate = submitccpayment.paymentDate
    * def BrandName = submitccpayment.brandName
    * def EmailTypeId = submitccpayment.emailTypeId

  @validSubmitCCPayment
  Scenario: Submit cc payment unsuccessfully
    Given path '/NRGREST/rest/billResource/submitCCPayment'
    And form field authType = AuthType
    And form field accountNumber = AccountNumber
    And form field bpid = Bpid
    And form field ccNumber = CcNumber
    And form field cvvNumber = CvvNumber
    And form field expirationDate = ExpirationDate
    And form field billingZip = BillingZip
    And form field paymentAmount = PaymentAmount
    And form field accountName = AccountName
    And form field accountChkDigit = AccountChkDigit
    And form field locale = Locale
    And form field email = Email
    And form field companyCode = CompanyCode
    And form field paymentDate = PaymentDate
    And form field brandName = BrandName
    And form field emailTypeId = EmailTypeId
    When method POST
    Then status 200
    #Verify successful response:
    Then match response.errorCode == ''
    Then match response.errorMessage == ''
    Then match response.successCode == '00'
    Then match response.confNumber == '#notnull'
    Then match response.resultCode == '0'
    Then match response.resultDescription == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Submit CC Payment Successful'
    And print 'Valid Submit CC Payment Response Time:' + responseTime
    * print response
	
	@invalidSubmitCCPayment
  Scenario: Submit cc payment unsuccessfully
    Given path '/NRGREST/rest/billResource/submitCCPayment'
    And form field authType = AuthType
    And form field accountNumber = AccountNumber
    And form field bpid = Bpid
    And form field ccNumber = CcNumber
    And form field cvvNumber = CvvNumber
    And form field expirationDate = ExpirationDate
    And form field billingZip = BillingZip
    And form field paymentAmount = PaymentAmount
    And form field accountName = AccountName
    And form field accountChkDigit = AccountChkDigit
    And form field locale = Locale
    And form field email = Email
    And form field companyCode = CompanyCode
    And form field paymentDate = PaymentDate
    And form field brandName = BrandName
    And form field emailTypeId = EmailTypeId
    When method POST
    Then status 200
    #Verify unsuccessful response:
    Then match response.errorCode == 'MSG_CCSERR_52_PAY_CC'
    Then match response.errorMessage == ''
    Then match response.successCode == ''
    Then match response.confNumber == ''
    Then match response.resultCode == '2'
    Then match response.resultDescription == null
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == "Duplicate payments are prohibited for your protection. Our records indicate that a payment using the same payment source and amount was submitted earlier today for this account. Refer to Payment History to view previous payments."
    And print 'Submit CC Payment Unsuccessful'
    And print 'Invalid Submit CC Payment Response Time:' + 
		* print response