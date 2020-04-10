@ProfileCheck
Feature: Profile Check API

  Background: 
    * configure headers = {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json', 'Authorization': 'Basic NTI4MVZNSjpTdGFnaW5nMQ==' }
#    * configure ssl = true
    
 Scenario: Check if the API is up and running
 	Given url BASE_SERVER_URL_1 + '/NRGREST/rest/profile/profileCheck'
 	And multipart fields { companyCode : '0391', contractAccountNumber :'000009213136', email:'CLZENNER1@GMAIL.COM', checkDigit:'6', brandName:'CE'}
 	When method POST
    Then status 200
    
 Scenario: Test for profile match
    * print 'running Test for profile match'
 	Given url BASE_SERVER_URL_1 + '/NRGREST/rest/profile/profileCheck'
 	And multipart fields { companyCode : '0391', contractAccountNumber :'000009213136', email:'CLZENNER1@GMAIL.COM', checkDigit:'6', brandName:'CE'}
 	When method POST
 	Then status 200
 	And match $ contains {resultCode:"0"}