Feature: Get the account balance

  Background: 
    * configure headers = {Authorization: 'Basic a2hvdXNlcjA2OlN0YWdpbmcx'}
    * configure charset = null
    * url BASE_SERVER_URL_1
    * json endpoints = read('classpath:com/it/multibrand/karate/endpoints.json')
    * json getBalance = read('classpath:com/it/multibrand/karate/resources/billResource/requestBody/getBalance.json')
    * def balanceEndPoint = get[0] endpoints $..GET_AR_BALANCE_ENDPOINT

  @get-balance
  Scenario: Get account balance
    Given path balanceEndPoint
    And set getBalance.bpid = busPart
    And set getBalance.accountNumber = acctNumber
    And form fields getBalance
    When method POST
    Then status 200
    * print , response
