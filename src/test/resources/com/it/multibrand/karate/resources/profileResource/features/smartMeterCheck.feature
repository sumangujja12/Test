@SmartMeterCheck
Feature: Verify whether meter is smart meter or not based on esid

  Background: 
    * configure headers = {Accept: 'application/json', Authorization: 'Basic a2hvdXNlcjA2OlN0YWdpbmcx'}
    * configure charset = null
    * url BASE_SERVER_URL_1
    * json endpoints = read('classpath:com/it/multibrand/karate/endpoints.json')
    * json smMeterCheckBody = read('classpath:com/it/multibrand/karate/resources/profileResource/requestBody/smartMeterCheck.json')
    * json smMeterCheckData = read('classpath:com/it/multibrand/karate/resources/profileResource/testData/smartMeterCheckTestdata.json')
    * json nsmMeterCheckData = read('classpath:com/it/multibrand/karate/resources/profileResource/testData/nonsmartMeterCheckTestdata.json')
    * def smartMeterCheckEndpoint = get[0] endpoints $..SMART_METER_CHECK_ENDPOINT

 Scenario Outline: Check if the meter is smart meter
 	Given path smartMeterCheckEndpoint
 	And set smMeterCheckBody.companyCode = companyCode
 	And set smMeterCheckBody.accountNumber = accountNumber
 	And set smMeterCheckBody.esid = esid
 	And form fields smMeterCheckBody
 	When method POST
    Then status 200
    And match response.flag == "true"
        
  Examples: 
     | smMeterCheckData |
     
  Scenario Outline: Check if the meter is not smart meter
 	Given path smartMeterCheckEndpoint
 	And set smMeterCheckBody.companyCode = companyCode
 	And set smMeterCheckBody.accountNumber = accountNumber
 	And set smMeterCheckBody.esid = esid
 	And form fields smMeterCheckBody
 	When method POST
    Then status 200
    And match response.flag == "false"
        
  Examples: 
     | nsmMeterCheckData |
