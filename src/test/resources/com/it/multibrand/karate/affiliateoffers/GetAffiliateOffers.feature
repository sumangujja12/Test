 Feature: Get Affiliate Offer funtionality 
 Background:
  * url  BASE_SERVER_URL
  * def utility = Java.type('com.it.multibrand.karate.utils.BaseFunctions')
  * json getaffilateofferrequest = read('classpath:com/it/multibrand/karate/affiliateoffers/GetAffiliateOffers.json')

  
    
    
@valuePromoCode
   Scenario: check affilaite offers call
   * set getaffilateofferrequest.promoCode = 'WB3677'
    Given path  '/NRGREST/rest/oeResource/getAffiliateOffers'
    And  request getaffilateofferrequest
    When method POST
    Then status 200    
    And print response
    And  print responseTime
    
    
@invalidPromoCode
   Scenario: check affilaite offers call
   * set getaffilateofferrequest.promoCode = '12345'
    Given path  '/NRGREST/rest/oeResource/getAffiliateOffers'
    And  request getaffilateofferrequest
    When method POST
    Then status 200    
    And print response
    And  print responseTime