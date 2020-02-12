 @offers
 Feature: Get Affiliate Offer funtionality
 
 Background:
  * url  BASE_SERVER_URL
  * json getaffilateofferrequest = read('classpath:com/it/multibrand/karate/affiliateoffers/GetAffiliateOffers.json')

@validMVIPromo
   Scenario: Validate Affilaite offers call response using a valid promocode for MVI
   * set getaffilateofferrequest.promoCode = 'WASD0K'
   * set getaffilateofferrequest.transactionType = 'MVI'
    Given path  '/NRGREST/rest/sales/offers'
    And  params getaffilateofferrequest
    When method GET
    Then status 200    
    And print 'MVI Promo Response Successful'
    And print 'Valid MVI Promo Response Time:' + responseTime
		And print 'Valid MVI Promo Response Below:' 
    And print response

    @validSWIPromo
   Scenario: Validate Affilaite offers call response using a valid promocode for SWI
   * set getaffilateofferrequest.promoCode = 'WASD0M'
   * set getaffilateofferrequest.transactionType = 'SWI'
    Given path  '/NRGREST/rest/sales/offers'
    And  params getaffilateofferrequest
    When method GET
    Then status 200
    And print 'SWI Promo Response Successful'
    And print 'Valid SWI Promo Response Time:' + responseTime
		And print 'Valid SWI Promo Response Below:' 
    And print response
    
@invalidPromoCode
   Scenario: Validate Affilaite offers call response using an invalid promocode
   * set getaffilateofferrequest.promoCode = '12345'
    Given path  '/NRGREST/rest/sales/offers'
    And  params getaffilateofferrequest
    When method GET
    Then status 200  
    And print 'Invalid Promo Response Successful'
    And print 'Invalid Promo Response Time:' + responseTime
		And print 'Invalid Promo Response Below:' 
    And print response