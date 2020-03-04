@SubmitSwapFeature
Feature: Submit Swap

  Background: 
    #* configure charset = null
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': '508f1db0-bbbd-48d2-9ce1-1f18e289698f' }
    * url BASE_SERVER_URL
    * json submitswap = read('classpath:com/it/multibrand/karate/renewal&swap/SubmitSwap.json')
    * def CampaignCode = submitswap.campaignCode
    * def OfferCode = submitswap.offerCode
    * def ContractId = submitswap.contractId
    * def Esid = submitswap.esid
    * def CurrentContractEndDate = submitswap.currentContractEndDate
    * def LanguageCode = submitswap.languageCode
    * def CompanyCode = submitswap.companyCode
    * def AccountNumber = submitswap.accountNumber
    * def CheckDigit = submitswap.checkDigit

  @validSubmitSwap
  Scenario: Get submit swap successfully
    Given path '/NRGREST/rest/swapResource/submitSwap'
    And form field campaignCode = CampaignCode
    And form field offerCode = OfferCode
    And form field esid = Esid
    And form field currentContractEndDate = CurrentContractEndDate
    And form field languageCode = LanguageCode
    And form field companyCode = CompanyCode
    And form field accountNumber = AccountNumber
    When method POST
    Then status 200
    #Verify successful response
    Then match response.idocNumber == "#notnull"
    Then match response.errorMessage == ''
    Then match response.errorCode == ''
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Check Submit Swap Successful'
    And print 'Valid Submit Swap Response Time:' + responseTime
    * print response
