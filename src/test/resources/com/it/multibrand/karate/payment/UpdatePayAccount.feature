@UpdatePayAccountFeature
Feature: PAYMENT - Update pay account

  Background: 
  	* configure charset = null
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': '2ce46608-5c5e-4efe-887d-f73084de562e' }
    * url BASE_SERVER_URL
    * json updatepayaccount = read('classpath:com/it/multibrand/karate/payment/UpdatePayAccount.json')
    * def ContractAccountNumber = updatepayaccount.contractAccountNumber
    * def OnlinePayAccountType = updatepayaccount.onlinePayAccountType
    * def LastFourDigit = updatepayaccount.lastFourDigit
    * def NameOnAccount = updatepayaccount.nameOnAccount
    * def PayAccountNickName = updatepayaccount.payAccountNickName
    * def PayAccountToken = updatepayaccount.payAccountToken
    * def ZipCode = updatepayaccount.zipCode
    * def ActiveFlag = updatepayaccount.activeFlag
    * def ActivationDate = updatepayaccount.activationDate
    * def VerifyCard = updatepayaccount.verifyCard
    * def RoutingNumber = updatepayaccount.routingNumber
    * def CcExpMonth = updatepayaccount.ccExpMonth
    * def CcExpYear = updatepayaccount.ccExpYear
    * def OnlinePayAccountId = updatepayaccount.onlinePayAccountId
    * def CcType = updatepayaccount.ccType
    * def AutoPay = updatepayaccount.autoPay
    * def PaymentInstitutionName = updatepayaccount.paymentInstitutionName
    * def CompanyCode = updatepayaccount.companyCode
    * def BrandName = updatepayaccount.brandName

  @validUpdatePayAccount
  Scenario: Update pay account successfully
    Given path '/NRGREST/rest/billResource/updatePayAccount'
    And form field contractAccountNumber = ContractAccountNumber
    And form field onlinePayAccountType = OnlinePayAccountType
    And form field lastFourDigit = LastFourDigit
    And form field nameOnAccount = NameOnAccount
    And form field payAccountNickName = PayAccountNickName
    And form field payAccountToken = PayAccountToken
    And form field zipCode = ZipCode
    And form field activeFlag = ActiveFlag
    And form field activationDate = ActivationDate
    And form field verifyCard = VerifyCard
    And form field routingNumber = RoutingNumber
    And form field ccExpMonth = CcExpMonth
    And form field ccExpYear = CcExpYear
    And form field onlinePayAccountId = OnlinePayAccountId
    And form field ccType = CcType
    And form field autoPay = AutoPay
    And form field paymentInstitutionName = PaymentInstitutionName
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify successful response:
    Then match response.successFlag == true
    Then match response.resultCode == '0'
    Then match response.resultDescription == ''
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Update Pay Account Successful'
    And print 'Valid Update Pay Account Response Time:' + responseTime
    * print response

  @invalidUpdatePayAccount1
  Scenario: Update pay account unsuccessfully with null account number
    Given path '/NRGREST/rest/billResource/updatePayAccount'
    And form field contractAccountNumber = ''
    And form field onlinePayAccountType = OnlinePayAccountType
    And form field lastFourDigit = LastFourDigit
    And form field nameOnAccount = NameOnAccount
    And form field payAccountNickName = PayAccountNickName
    And form field payAccountToken = PayAccountToken
    And form field zipCode = ZipCode
    And form field activeFlag = ActiveFlag
    And form field activationDate = ActivationDate
    And form field verifyCard = VerifyCard
    And form field routingNumber = RoutingNumber
    And form field ccExpMonth = CcExpMonth
    And form field ccExpYear = CcExpYear
    And form field onlinePayAccountId = OnlinePayAccountId
    And form field ccType = CcType
    And form field autoPay = AutoPay
    And form field paymentInstitutionName = PaymentInstitutionName
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify unsuccessful response:
    Then match response.successFlag == false
    Then match response.resultCode == '0'
    Then match response.resultDescription == ''
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Update Pay Account Unsuccessful'
    And print 'Invalid Update Pay Account Response Time:' + responseTime
    * print response
    
  @invalidUpdatePayAccount2
  Scenario: Update pay account unsuccessfully with invalid account number
    Given path '/NRGREST/rest/billResource/updatePayAccount'
    And form field contractAccountNumber = '000072'
    And form field onlinePayAccountType = OnlinePayAccountType
    And form field lastFourDigit = LastFourDigit
    And form field nameOnAccount = NameOnAccount
    And form field payAccountNickName = PayAccountNickName
    And form field payAccountToken = PayAccountToken
    And form field zipCode = ZipCode
    And form field activeFlag = ActiveFlag
    And form field activationDate = ActivationDate
    And form field verifyCard = VerifyCard
    And form field routingNumber = RoutingNumber
    And form field ccExpMonth = CcExpMonth
    And form field ccExpYear = CcExpYear
    And form field onlinePayAccountId = OnlinePayAccountId
    And form field ccType = CcType
    And form field autoPay = AutoPay
    And form field paymentInstitutionName = PaymentInstitutionName
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    When method POST
    Then status 200
    #Verify unsuccessful response:
    Then match response.successFlag == false
    Then match response.resultCode == '0'
    Then match response.resultDescription == ''
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Update Pay Account Unsuccessful'
    And print 'Invalid Update Pay Account Response Time:' + responseTime
    * print response
    