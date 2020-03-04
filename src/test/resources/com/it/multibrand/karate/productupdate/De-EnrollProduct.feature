@DeEnrollProductFeature
Feature: Product Update - De-enroll

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': 'eb64e111-709a-47bf-8af3-d6f87972d143'}
    * url BASE_SERVER_URL
    * json deenrollproduct = read('classpath:com/it/multibrand/karate/productupdate/De-EnrollProduct.json')
    * def AccountNumber = deenrollproduct.accountNumber
    * def ExtUi = deenrollproduct.extUi
    * def RequestDate = deenrollproduct.requestDate
    * def ManuPartNo = deenrollproduct.manuPartNo
    * def CompanyCode = deenrollproduct.companyCode
    * def Action = deenrollproduct.action
    * def ObjectId = deenrollproduct.objectId

  @validDeEnrollProduct
  Scenario: De-Enroll account successfully
    Given path '/NRGREST/rest/profile/productUpdate'
    And form field accountNumber = AccountNumber
    And form field action = Action
    And form field objectId = ObjectId
    And form field extUi = ExtUi
    And form field requestDate = RequestDate
    And form field manuPartNo = ManuPartNo
    And form field companyCode = CompanyCode
    When method POST
    Then status 200
    Then match response.productDO == null
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Sucessfully updated the contract End date.'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'De-Enroll Product Successful'
    And print 'Valid De-Enroll Product Response Time:' + responseTime
    * print response

  @invalidDeEnrollProduct
  Scenario: De-Enroll invalid account unsuccessfully
    Given path '/NRGREST/rest/profile/productUpdate'
    And form field accountNumber = '00001379575'
    And form field action = Action
    And form field objectId = ObjectId
    And form field extUi = ExtUi
    And form field requestDate = RequestDate
    And form field manuPartNo = ManuPartNo
    And form field companyCode = CompanyCode
    When method POST
    Then status 200
    Then match response.productDO == null
    Then match response.resultCode == '2'
    Then match response.resultDescription == 'CA not found.'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == 'Sorry! Something went wrong. Please try again'
    And print 'De-Enroll Product Unsuccessful'
    And print 'Invalid De-Enroll Product Response Time:' + responseTime
    * print response
