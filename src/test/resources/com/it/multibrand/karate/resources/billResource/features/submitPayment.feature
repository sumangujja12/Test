@parallel=false
Feature: Submit a credit card/bank payment and verify if the payment is reflected in the balance

  Background: 
    * configure headers = {Authorization: 'Basic a2hvdXNlcjA2OlN0YWdpbmcx'}
    * configure charset = null
    * url BASE_SERVER_URL_1
    * json endpoints = read('classpath:com/it/multibrand/karate/endpoints.json')
    * json submitCCPayment = read('classpath:com/it/multibrand/karate/resources/billResource/requestBody/paymentWithCreditCard.json')
    * json submitBankPayment = read('classpath:com/it/multibrand/karate/resources/billResource/requestBody/paymentWithBank.json')
    * json getBalance = read('classpath:com/it/multibrand/karate/resources/billResource/requestBody/getBalance.json')
    * json ccpaymentData = read('classpath:com/it/multibrand/karate/resources/billResource/testData/creditCardPaymentTestData.json')
    * json bankpaymentData = read('classpath:com/it/multibrand/karate/resources/billResource/testData/bankPaymentTestData.json')
    * def ccPaymentEndpoint = get[0] endpoints $..SUBMIT_CC_PAYMENT_ENDPOINT
    * def bankPaymentEndpoint = get[0] endpoints $..SUBMIT_BANK_PAYMENT_ENDPOINT
    * def utility = Java.type('com.it.multibrand.karate.utils.BaseFunctions')

  @cc-payment
  Scenario Outline: Submit a credit card payment and verify if the payment is reflected in the balance
    * def pmtAmount = utility.generateRandomNumber()
    * def expDate = utility.getCardExpDate()
    Given path ccPaymentEndpoint
    And set submitCCPayment.bpid = bpid
    And set submitCCPayment.accountNumber = accountNumber
    And set submitCCPayment.ccNumber = ccNumber
    And set submitCCPayment.expirationDate = expDate
    And set submitCCPayment.billingZip = billingZip
    And set submitCCPayment.paymentAmount = pmtAmount
    And form fields submitCCPayment
    And retry until response.PayByCCResponse.resultCode  == "0" && response.PayByCCResponse.successCode == "00"
    When method POST
    Then status 200
    And match response.PayByCCResponse.resultCode == "0"
    And match response.PayByCCResponse.successCode == "00"
    And match response.PayByCCResponse.statusCode == "00"
    * print $..statusCode
    * print , response
    * def balanceCheck = call read('classpath:com/it/multibrand/karate/resources/billResource/features/getBalance.feature@get-balance') {busPart: '#(bpid)', acctNumber: '#(accountNumber)'}
    * match balanceCheck.response.GetArResponse.lastPaymentAmount == pmtAmount
    * print , "Credit Card Payment is posted and the payment reflects in the balance for a ",customerType," customer"

    Examples: 
      | ccpaymentData |

  @bank-payment
  Scenario Outline: Submit a bank payment and verify if the payment is reflected in the balance
    * def pmtAmount = utility.generateRandomNumber()
    * def pmtDate = utility.getCurrentDate()
    Given path bankPaymentEndpoint
    And set submitBankPayment.bpid = bpid
    And set submitBankPayment.accountNumber = accountNumber
    And set submitBankPayment.bankRoutingNumber = bankRoutingNumber
    And set submitBankPayment.bankAccountNumber = bankAccountNumber
    And set submitBankPayment.paymentDate = pmtDate
    And set submitBankPayment.paymentAmount = pmtAmount
    And form fields submitBankPayment
    And retry until response.PayByBankResponse.resultCode  == "0" && response.PayByBankResponse.successCode == "00"
    When method POST
    Then status 200
    And match response.PayByBankResponse.resultCode == "0"
    And match response.PayByBankResponse.successCode == "00"
    And match response.PayByBankResponse.statusCode == "00"
    * print , response
    * def balanceCheck = call read('classpath:com/it/multibrand/karate/resources/billResource/features/getBalance.feature@get-balance') {busPart: '#(bpid)', acctNumber: '#(accountNumber)'}
    * match balanceCheck.response.GetArResponse.lastPaymentAmount == pmtAmount
    * print , "Bank Payment is posted and the payment reflects in the balance for a ",customerType," customer"

    Examples: 
      | bankpaymentData |
