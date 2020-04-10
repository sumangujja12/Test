Feature: Verify AMB Status and Eligibility for the customers
    Verifies if a customer is active on AMB and if not verifies if a customer is eligible for AMB

  Background: 
    * configure headers = {Accept: 'application/json', Authorization: 'Basic a2hvdXNlcjA2OlN0YWdpbmcx'}
    * configure charset = null
    * url BASE_SERVER_URL_1
    * json endpoints = read('classpath:com/it/multibrand/karate/endpoints.json')
    * json ambPayLoad = read('classpath:com/it/multibrand/karate/resources/billResource/requestBody/ambStatusEligibility.json')
    * json ambData = read('classpath:com/it/multibrand/karate/resources/billResource/testData/ambStatusTestdata.json')
    * def ambCheckResource = get[0] endpoints $..AMB_CHECK_ENDPOINT 


  @amb-status-eligibility
  Scenario Outline: AMB Status and Eligibility
    Given path ambCheckResource
    And set ambPayLoad.bpNumber = bpNum
    And set ambPayLoad.accountNumber = accountNum
    And set ambPayLoad.contractId = coId
    #And set ambPayLoad.companyCode = coCode
    #And set ambPayLoad.brandName = brName
    #And remove ambPayLoad.companyCode
    And eval if(scenario == "Two") karate.remove('ambPayLoad','$.companyCode')
    And request ambPayLoad
    #And form field bpNumber = bpNum
    #And form field accountNumber = accountNum
    #And form field contractId = coId
    #And request {bpNumber:17774, accountNumber:'000000011155', contractId:'0046379097'}
    #And request ambData
    When method POST
    Then status 200
    Then match response.prgStatus.abPlanActive == abActive
    Then match response.prgStatus.abPlanEligible == abEligible
    * print , response

    Examples: 
      | ambData |
