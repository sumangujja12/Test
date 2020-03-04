@ReadContactAlertPrefFeature
Feature: Read contact alert pref

  Background: 
    * configure charset = null
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': '73093175-d7f9-40be-829d-62c88ad27503' }
    * url BASE_SERVER_URL
    * json readcontactalert = read('classpath:com/it/multibrand/karate/accountdetails/ReadContactAlertPref.json')
    * def BusinessPartner = readcontactalert.businessPartner
    * def Contract = readcontactalert.contract
    * def ContractAccount = readcontactalert.contractAccount
    * def IsPrepay = readcontactalert.isPrepay
    * def CompanyCode = readcontactalert.companyCode
    * def BrandId = readcontactalert.brandId

  @validReadContactAlertPref1
  Scenario: Read contact alert pref successfully
    Given path '/NRGREST/rest/preferences/protected/readContactAlertPref'
    And form field businessPartner = BusinessPartner
    And form field contract = Contract
    And form field contractAccount = ContractAccount
    And form field isPrepay = IsPrepay
    And form field companyCode = CompanyCode
    And form field brandId = BrandId
    When method POST
    Then status 200
    #Verify response:
    Then match response.preferenceList == null
    Then match response.marketingPref == '#notnull'
    Then match response.billAlert == '#notnull'
    Then match response.usageAlert == '#notnull'
    Then match response.ppemailPref == '#notnull'
    Then match response.ppphonePref == '#notnull'
    Then match response.pptextPref == '#notnull'
    #Verify successful response
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorCode == null
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Get Read Contact Alert Pref Successful'
    And print 'Valid Read Contact Alert Pref Response Time:' + responseTime
    * print response

  @validReadContactAlertPref2
  Scenario: Read contact alert pref successfully with blank business partner
    Given path '/NRGREST/rest/preferences/protected/readContactAlertPref'
    And form field businessPartner = ''
    And form field contract = Contract
    And form field contractAccount = ContractAccount
    And form field isPrepay = IsPrepay
    And form field companyCode = CompanyCode
    And form field brandId = BrandId
    When method POST
    Then status 200
    #Verify response:
    Then match response.preferenceList == null
    Then match response.marketingPref == '#notnull'
    Then match response.billAlert == '#notnull'
    Then match response.usageAlert == '#notnull'
    Then match response.ppemailPref == '#notnull'
    Then match response.ppphonePref == '#notnull'
    Then match response.pptextPref == '#notnull'
    #Verify successful response
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorCode == null
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Get Read Contact Alert Pref Successful'
    And print 'Valid Read Contact Alert Pref Response Time:' + responseTime
    * print response

  @validReadContactAlertPref3
  Scenario: Read contact alert pref successfully with blank business partner
    Given path '/NRGREST/rest/preferences/protected/readContactAlertPref'
    And form field businessPartner = ''
    And form field contract = Contract
    And form field contractAccount = ContractAccount
    And form field isPrepay = IsPrepay
    And form field companyCode = CompanyCode
    And form field brandId = BrandId
    When method POST
    Then status 200
    #Verify response:
    Then match response.preferenceList == null
    Then match response.marketingPref == '#notnull'
    Then match response.billAlert == '#notnull'
    Then match response.usageAlert == '#notnull'
    Then match response.ppemailPref == '#notnull'
    Then match response.ppphonePref == '#notnull'
    Then match response.pptextPref == '#notnull'
    #Verify successful response
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorCode == null
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Get Read Contact Alert Pref Successful'
    And print 'Valid Read Contact Alert Pref Response Time:' + responseTime
    * print response
