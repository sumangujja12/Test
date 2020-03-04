@CreateUserFeature
Feature: Create User

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json'}
    * url BASE_SERVER_URL
    * json createuser = read('classpath:com/it/multibrand/karate/login&reg/CreateUser.json')
    * def AccountNumber = createuser.accountNumber
    * def LastName = createuser.lastName
    * def CompanyCode = createuser.companyCode
    * def BrandName = createuser.brandName
    * def UserName = createuser.userName
    * def Email = createuser.email
    * def FirstName = createuser.firstName
    * def Password = createuser.password
    * def LanguageCode = createuser.languageCode
    * def ApplicationArea = createuser.applicationArea
    * def CheckDigit = createuser.checkDigit

  @invalidCreateUser
  Scenario: Create user unsuccessfully - account already registered
    Given path '/NRGREST/rest/registration/createUser'
    And form field accountNumber = AccountNumber
    And form field lastName = LastName
    And form field companyCode = CompanyCode
    And form field brandName = BrandName
    And form field userName = UserName
    And form field email = Email
    And form field firstName = FirstName
    And form field password = Password
    And form field languageCode = LanguageCode
    And form field applicationArea = ApplicationArea
    And form field checkDigit = CheckDigit
    When method POST
    Then status 200
    #Verify successful response
    Then match response.resultCode == '3'
    Then match response.resultDescription == 'Account Already Registered'
    Then match response.errorCode == ''
    Then match response.errorDescription == ''
    Then match response.statusCode == '00'
    Then match response.messageCode == ''
    Then match response.messageText == ''
    Then match response.resultDisplayText == 'It looks like this account number is already registered.'
    And print 'Create User Unsuccessful'
    And print 'Invalid Create User Response Time:' + responseTime
    * print response
