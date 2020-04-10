 @submitKBAAnswersFeature
 Feature: Submit KBA Answers functionality
 
 Background:
  * url  BASE_SERVER_URL_1
	* json submitkbaAnswersRequest = read('classpath:com/it/multibrand/karate/resources/oeResource/requestBody/SubmitKBAAnswers.json')
  * json submitkbaAnswersData = read('classpath:com/it/multibrand/karate/resources/oeResource/testData/SubmitKBAAnswersTestData.json')

   Scenario Outline:
   # English & Spanish
	 # submitKBAAnswers API Test with POSID Fail but KBA Pass with Correct Answer
	 # submitKBAAnswers API Test with POSID Fail & KBA Fail due to Incorrect Answer
	 # submitKBAAnswers API Test with POSID Pass & KBA Pass with Correct Answer
   # submitKBAAnswers API Test with POSID Pass & KBA Fail due to Incorrect Answer
	 # Test with no Tracking Id field
	 # Test with no Channel Type field
		
   * set submitkbaAnswersRequest.languageCode = langCode
   * set submitkbaAnswersRequest.trackingId = trackId
   * set submitkbaAnswersRequest.transactionKey = transKey
   * set submitkbaAnswersRequest.channelType = chnlType
   * eval if (testScenario=='Test with tracking id field not passed') karate.remove('submitkbaAnswersRequest','$.trackingId')
   * eval if (testScenario=='Test with channel type field not passed') karate.remove('submitkbaAnswersRequest','$.channelType')

   Given path  '/NRGREST/rest/sales/kba-result'
   And  request submitkbaAnswersRequest
   When method POST
   Then status 200
   And match response.statusCode == teststatus
   And print " Response Status = " + response.statusCode
   And print testScenario + " Response Time = " + responseTime
	 And print response
   
   Examples:
   | submitkbaAnswersData |