@EnrollAverageBillingFeature
Feature: Enroll Average Billing

  Background: 
		* configure charset = null
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': 'e2044f73-b9d3-4b89-91f3-7c8a5b1140ae' }
    * url BASE_SERVER_URL
    * json enrollaveragebilling = read('classpath:com/it/multibrand/karate/billing/EnrollAverageBilling.json')
    * def AmtAdjust = enrollaveragebilling.amtAdjust
    * def AmtFinal = enrollaveragebilling.companyCode
    * def BbpBasis = enrollaveragebilling.bbpBasis
    * def BillAllocDate = enrollaveragebilling.billAllocDate
    * def EstSign = enrollaveragebilling.estSign
    * def Invoice = enrollaveragebilling.invoice
    * def ResStatus = enrollaveragebilling.resStatus
    * def AmbAmount = enrollaveragebilling.ambAmount
    * def BpNumber = enrollaveragebilling.bpNumber
    * def RetroFlag = enrollaveragebilling.retroFlag
    * def AccountNumber = enrollaveragebilling.accountNumber
    * def ContractId = enrollaveragebilling.contractId
    * def ToEmail = enrollaveragebilling.toEmail
    * def LanguageCode = enrollaveragebilling.languageCode

  @validEnrollAveBilling1
  Scenario: Enroll Average Billing successfully
    Given path '/NRGREST/rest/billResource/saveAMBSignUp'
    And form field amtAdjust = AmtAdjust
    And form field amtFinal = AmtFinal
    And form field bbpBasis = BbpBasis
    And form field billAllocDate = BillAllocDate
    And form field estSign = EstSign
    And form field invoice = Invoice
    And form field resStatus = ResStatus
    And form field ambAmount = AmbAmount
    And form field bpNumber = BpNumber
    And form field retroFlag = RetroFlag
    And form field accountNumber = AccountNumber
    And form field contractId = ContractId
    And form field toEmail = ToEmail
    And form field languageCode = LanguageCode
    When method POST
    Then status 200
    #Verify successful response
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Enroll Average Billing Successful'
    And print 'Valid Enroll Average Billing Response Time:' + responseTime
    * print response
    
  @validEnrollAveBilling2
  Scenario: Enroll Average Billing successfully with null invoice
    Given path '/NRGREST/rest/billResource/saveAMBSignUp'
    And form field amtAdjust = AmtAdjust
    And form field amtFinal = AmtFinal
    And form field bbpBasis = BbpBasis
    And form field billAllocDate = BillAllocDate
    And form field estSign = EstSign
    And form field invoice = ''
    And form field resStatus = ResStatus
    And form field ambAmount = AmbAmount
    And form field bpNumber = BpNumber
    And form field retroFlag = RetroFlag
    And form field accountNumber = AccountNumber
    And form field contractId = ContractId
    And form field toEmail = ToEmail
    And form field languageCode = LanguageCode
    When method POST
    Then status 200
    #Verify successful response
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Enroll Average Billing Successful'
    And print 'Valid Enroll Average Billing Response Time:' + responseTime
    * print response
  
 @validEnrollAveBilling3
  Scenario: Enroll Average Billing successfully with null BP number
    Given path '/NRGREST/rest/billResource/saveAMBSignUp'
    And form field amtAdjust = AmtAdjust
    And form field amtFinal = AmtFinal
    And form field bbpBasis = BbpBasis
    And form field billAllocDate = BillAllocDate
    And form field estSign = EstSign
    And form field invoice = Invoice
    And form field resStatus = ResStatus
    And form field ambAmount = AmbAmount
    And form field bpNumber = ''
    And form field retroFlag = RetroFlag
    And form field accountNumber = AccountNumber
    And form field contractId = ContractId
    And form field toEmail = ToEmail
    And form field languageCode = LanguageCode
    When method POST
    Then status 200
    #Verify successful response
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Enroll Average Billing Successful'
    And print 'Valid Enroll Average Billing Response Time:' + responseTime
    * print response
      
	@invalidEnrollAveBilling1
  Scenario: Enroll Average Billing unsuccessfully with null account number
    Given path '/NRGREST/rest/billResource/saveAMBSignUp'
    And form field amtAdjust = AmtAdjust
    And form field amtFinal = AmtFinal
    And form field bbpBasis = BbpBasis
    And form field billAllocDate = BillAllocDate
    And form field estSign = EstSign
    And form field invoice = Invoice
    And form field resStatus = ResStatus
    And form field ambAmount = AmbAmount
    And form field bpNumber = BpNumber
    And form field retroFlag = RetroFlag
    And form field accountNumber = ''
    And form field contractId = ContractId
    And form field toEmail = ToEmail
    And form field languageCode = LanguageCode
    When method POST
    Then status 200
    #Verify response
    Then match response.resultCode == '0'
    Then match response.resultDescription == ''
    Then match response.errorCode == '1'
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Enroll Average Billing Unsuccessful'
    And print 'Invalid Enroll Average Billing Response Time:' + responseTime
    * print response
    
  @invalidEnrollAveBilling2
  Scenario: Enroll Average Billing unsuccessfully with invalid account number
    Given path '/NRGREST/rest/billResource/saveAMBSignUp'
    And form field amtAdjust = AmtAdjust
    And form field amtFinal = AmtFinal
    And form field bbpBasis = BbpBasis
    And form field billAllocDate = BillAllocDate
    And form field estSign = EstSign
    And form field invoice = Invoice
    And form field resStatus = ResStatus
    And form field ambAmount = AmbAmount
    And form field bpNumber = BpNumber
    And form field retroFlag = RetroFlag
    And form field accountNumber = '000072'
    And form field contractId = ContractId
    And form field toEmail = ToEmail
    And form field languageCode = LanguageCode
    When method POST
    Then status 200
    #Verify response
    Then match response.resultCode == '0'
    Then match response.resultDescription == ''
    Then match response.errorCode == '1'
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Enroll Average Billing Unsuccessful'
    And print 'Invalid Enroll Average Billing Response Time:' + responseTime
    * print response