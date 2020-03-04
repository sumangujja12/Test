@EnrollProductFeature
Feature: Product Update - Enroll

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': 'a70b9685-f5c9-41ba-954f-ccacc754650d' }
    * url BASE_SERVER_URL
    * json enrollproduct = read('classpath:com/it/multibrand/karate/productupdate/EnrollProduct.json')
    * def AccountNumber = enrollproduct.accountNumber
    * def Action = enrollproduct.action
    * def ObjectId = enrollproduct.objectId
    * def ExtUi = enrollproduct.extUi
    * def EnrollType = enrollproduct.enrollType
    * def RequestDate = enrollproduct.requestDate
    * def ManuPartNo = enrollproduct.manuPartNo
    * def CompanyCode = enrollproduct.companyCode

  @validEnrollProduct
  Scenario: Enroll product account successfully
    Given path '/NRGREST/rest/profile/productUpdate'
    And form field accountNumber = AccountNumber
    And form field action = Action
    And form field extUi = ExtUi
    And form field enrollType = EnrollType
    And form field requestDate = RequestDate
    And form field manuPartNo = ManuPartNo
    And form field companyCode = CompanyCode
    When method POST
    Then status 200
    Then match response.productDO == null
    Then match response.resultCode == '0'
    Then match response.resultDescription == 'Success'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == ''
    And print 'Enroll Product Successful'
    And print 'Valid Enroll Product Response Time:' + responseTime
    * print response

  @invalidEnrollProduct1
  Scenario: Enroll account that is already enrolled
    Given path '/NRGREST/rest/profile/productUpdate'
    And form field accountNumber = AccountNumber
    And form field action = Action
    And form field extUi = ExtUi
    And form field enrollType = EnrollType
    And form field requestDate = RequestDate
    And form field manuPartNo = ManuPartNo
    And form field companyCode = CompanyCode
    When method POST
    Then status 200
    Then match response.productDO == null
    Then match response.resultCode == '4'
    Then match response.resultDescription == "Account already enrolled"
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == 'It looks like this account is already registered.'
    And print 'Enroll Product Unsuccessful'
    And print 'Invalid Enroll Product Response Time:' + responseTime
    * print response

  @invalidEnrollProduct2
  Scenario: Enroll invalid account
    Given path '/NRGREST/rest/profile/productUpdate'
    And form field accountNumber = '00001379575'
    And form field action = Action
    And form field extUi = ExtUi
    And form field enrollType = EnrollType
    And form field requestDate = RequestDate
    And form field manuPartNo = ManuPartNo
    And form field companyCode = CompanyCode
    When method POST
    Then status 200
    Then match response.productDO == null
    Then match response.resultCode == '4'
    Then match response.resultDescription == "Account already enrolled"
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == 'It looks like this account is already registered.'
    And print 'Enroll Product Unsuccessful'
    And print 'Invalid Enroll Product Response Time:' + responseTime
    * print response
