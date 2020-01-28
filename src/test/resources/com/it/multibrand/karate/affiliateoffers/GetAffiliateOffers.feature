 Feature: Get Affiliate Offer funtionality 
 Background:
  * url  'http://stg1-ws.nrgenergy.com/NRGREST/rest/oeResource/getAffiliateOffers'
  * def utility = Java.type('com.it.multibrand.karate.utils.BaseFunctions')
  * json getaffilateofferrequest = read('classpath:com/it/multibrand/karate/affiliateoffers/GetAffiliateOffers.json')
  
   @getaffilateofferrequest
   Scenario: check affilaite offers call
    Given request getaffilateofferrequest
    When method POST
    Then status 200    
    And print response
    And  print responseTime
    
    