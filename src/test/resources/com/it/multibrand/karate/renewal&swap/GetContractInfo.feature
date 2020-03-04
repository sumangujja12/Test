@GetContractInfoFeature
Feature: Get Contract Info

  Background: 
    #* configure charset = null
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': '508f1db0-bbbd-48d2-9ce1-1f18e289698f' }
    * url BASE_SERVER_URL
    * json contractinfo = read('classpath:com/it/multibrand/karate/renewal&swap/GetContractInfo.json')
    * def AccountNumber = contractinfo.accountNumber
    * def BpNumber = contractinfo.bpNumber
    * def ContractId = contractinfo.contractId
    * def Esid = contractinfo.esid
    * def LanguageCode = contractinfo.languageCode
    * def CompanyCode = contractinfo.companyCode
    * def BrandName = contractinfo.brandName

  @validGetContractInfo
  Scenario: Get contract info successfully
    Given path '/NRGREST/rest/profile/getContractInfo'
    And form field accountNumber = AccountNumber
    And form field bpNumber = BpNumber
    And form field contractId = ContractId
    And form field esid = Esid
    And form field languageCode = LanguageCode
    And form field companyCode = CompanyCode
    And form field brandName:GME = BrandName
    When method POST
    Then status 200
    #Verify successful response
    Then match response.pendingSwapDO.strBPNumber == BpNumber
    Then match response.pendingSwapDO.strCANumber == AccountNumber
    Then match response.pendingSwapDO.strContractID == ContractId
    Then match response.pendingSwapDO.strESIID == Esid
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Get Contract Info Successful'
    And print 'Valid Get Contract Info Response Time:' + responseTime
    * print response
    
  @invalidGetContractInfo1
  Scenario: Get contract info unsuccessfully with null account number
    Given path '/NRGREST/rest/profile/getContractInfo'
    And form field accountNumber = ''
    And form field bpNumber = BpNumber
    And form field contractId = ContractId
    And form field esid = Esid
    And form field languageCode = LanguageCode
    And form field companyCode = CompanyCode
    And form field brandName:GME = BrandName
    When method POST
    Then status 200
    #Verify response
    Then match response.resultCode == '2'
    Then match response.resultDescription contains 'Keydata Combination Does not exist'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == 'Sorry! Something went wrong. Please try again'
    And print 'Get Contract Info Unsuccessful'
    And print 'Invalid Get Contract Info Response Time:' + responseTime
    * print response

  @invalidGetContractInfo2
  Scenario: Get contract info unsuccessfully with null BP number
    Given path '/NRGREST/rest/profile/getContractInfo'
    And form field accountNumber = AccountNumber
    And form field bpNumber = ''
    And form field contractId = ContractId
    And form field esid = Esid
    And form field languageCode = LanguageCode
    And form field companyCode = CompanyCode
    And form field brandName:GME = BrandName
    When method POST
    Then status 200
    #Verify response
    Then match response.resultCode == '2'
    Then match response.resultDescription == 'Keydata is not available.Please fill the same !'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == 'Sorry! Something went wrong. Please try again'
    And print 'Get Contract Info Unsuccessful'
    And print 'Invalid Get Contract Info Response Time:' + responseTime
    * print response
