@UpdatePaperFreeBillingFeature
Feature: Update Paper Free Billing

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': '033d684f-5c69-41cf-9e8d-cb5655bac9f2' }
    * url BASE_SERVER_URL
    * json updatebilling = read('classpath:com/it/multibrand/karate/billing/UpdatePaperFreeBilling.json')
    * def AccountNumber = updatebilling.accountNumber
    * def EBillFlag = updatebilling.ebillflag
    * def PaperFlag = updatebilling.paperflag
    * def CompanyCode = updatebilling.companyCode
    * def Source = updatebilling.source
    * def BPNumber = updatebilling.bpNumber

  @validUpdatePaperFreeBilling
  Scenario: Update Paper Free Billing successfully
    Given path '/NRGREST/rest/billResource/updatePaperFreeBilling'
    And form field accountNumber = AccountNumber
    And form field flag = EBillFlag
    And form field companyCode = CompanyCode
    And form field source = Source
    And form field bpNumber = BPNumber
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
    And print 'Update Paper Free Billing Successful'
    And print 'Valid Update Paper Free Billing Response Time:' + responseTime
    * print response

  @validUpdatePaperBilling
  Scenario: Update Paper Billing successfully
    Given path '/NRGREST/rest/billResource/updatePaperFreeBilling'
    And form field accountNumber = AccountNumber
    And form field flag = PaperFlag
    And form field companyCode = CompanyCode
    And form field source = Source
    And form field bpNumber = BPNumber
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
    And print 'Update Paper Billing Successful'
    And print 'Valid Update Paper Billing Response Time:' + responseTime
    * print response

  @invalidUpdatePaperBilling
  Scenario: Update Paper Billing unsuccessfully
    Given path '/NRGREST/rest/billResource/updatePaperFreeBilling'
    And form field accountNumber = ''
    And form field flag = PaperFlag
    And form field companyCode = CompanyCode
    And form field source = Source
    And form field bpNumber = BPNumber
    When method POST
    Then status 200
    #Verify response
    Then match response.resultCode == '3'
    Then match response.resultDescription == 'No Data'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == "Uh-oh. There's nothing here. Want to go back?"
    And print 'Update Paper Billing Unsuccessful'
    And print 'Invalid Update Paper Billing Response Time:' + responseTime
    * print response
