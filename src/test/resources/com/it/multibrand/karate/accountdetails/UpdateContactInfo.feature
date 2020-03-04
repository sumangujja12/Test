@UpdateContactInfoFeature
Feature: Update Contact Info

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic a2hvdXNlcjA2OlN0YWdpbmcx', 'Cache-Control': 'no-cache', 'Postman-Token': '609947c2-24e5-48a2-9c30-a685a3318432' }
    * url BASE_SERVER_URL
    * json updatecontactinfo = read('classpath:com/it/multibrand/karate/accountdetails/UpdateContactInfo.json')
    * def CompanyCode = updatecontactinfo.companyCode
    * def BrandName = updatecontactinfo.brandName
    * def AccountNumber = updatecontactinfo.accountNumber
    * def BPNumber = updatecontactinfo.bpNumber
    * def Email = updatecontactinfo.email
    * def HomePhone = updatecontactinfo.homePhone
    * def WorkPhone = updatecontactinfo.workPhone
    * def CellPhone = updatecontactinfo.cellPhone
    * def UniqueID = updatecontactinfo.uniqueID
    * def UserName = updatecontactinfo.userName

  @validUpdateContactInfo1
  Scenario: Successful update of contact info
    Given path '/NRGREST/rest/profile/updateContactInfo'
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field accountNumber = AccountNumber
    And form field bpNumber = BPNumber
    And form field email = Email
    And form field homePhone = HomePhone
    And form field workPhone = WorkPhone
    And form field cellPhone = CellPhone
    And form field uniqueID = UniqueID
    And form field userName = UserName
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
    And print 'Update Contact Info Successful'
    And print 'Valid Update Contact Info Response Time:' + responseTime
    * print response

  @validUpdateContactInfo2
  Scenario: Successful update of contact info with blank email
    Given path '/NRGREST/rest/profile/updateContactInfo'
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field accountNumber = AccountNumber
    And form field bpNumber = BPNumber
    And form field email = ''
    And form field homePhone = HomePhone
    And form field workPhone = WorkPhone
    And form field cellPhone = CellPhone
    And form field uniqueID = UniqueID
    And form field userName = UserName
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
    And print 'Update Contact Info Successful'
    And print 'Valid Update Contact Info Response Time:' + responseTime
    * print response

  @validUpdateContactInfo3
  Scenario: Successful update of contact info with blank work phone and cellphone
    Given path '/NRGREST/rest/profile/updateContactInfo'
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field accountNumber = AccountNumber
    And form field bpNumber = BPNumber
    And form field email = Email
    And form field homePhone = HomePhone
    And form field workPhone = ''
    And form field cellPhone = ''
    And form field uniqueID = UniqueID
    And form field userName = UserName
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
    And print 'Update Contact Info Successful'
    And print 'Valid Update Contact Info Response Time:' + responseTime
    * print response

  @validUpdateContactInfo4
  Scenario: Successful update of contact info with blank home phone and work phone
    Given path '/NRGREST/rest/profile/updateContactInfo'
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field accountNumber = AccountNumber
    And form field bpNumber = BPNumber
    And form field email = Email
    And form field homePhone = ''
    And form field workPhone = ''
    And form field cellPhone = CellPhone
    And form field uniqueID = UniqueID
    And form field userName = UserName
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
    And print 'Update Contact Info Successful'
    And print 'Valid Update Contact Info Response Time:' + responseTime
    * print response

  @validUpdateContactInfo5
  Scenario: Successful update of contact info with blank home phone and cellphone
    Given path '/NRGREST/rest/profile/updateContactInfo'
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field accountNumber = AccountNumber
    And form field bpNumber = BPNumber
    And form field email = Email
    And form field homePhone = ''
    And form field workPhone = WorkPhone
    And form field cellPhone = ''
    And form field uniqueID = UniqueID
    And form field userName = UserName
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
    And print 'Update Contact Info Successful'
    And print 'Valid Update Contact Info Response Time:' + responseTime
    * print response

  @validUpdateContactInfo6
  Scenario: Successful update of contact info with blank username
    Given path '/NRGREST/rest/profile/updateContactInfo'
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field accountNumber = AccountNumber
    And form field bpNumber = BPNumber
    And form field email = Email
    And form field homePhone = HomePhone
    And form field workPhone = WorkPhone
    And form field cellPhone = CellPhone
    And form field uniqueID = UniqueID
    And form field userName = ''
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
    And print 'Update Contact Info Successful'
    And print 'Valid Update Contact Info Response Time:' + responseTime
    * print response
