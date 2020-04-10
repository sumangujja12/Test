 @getKBAQuestionsFeature
 Feature: Get KBA Questions functionality
 
 Background:
  * url  BASE_SERVER_URL_1
	* json getkbaQuestionsRequest = read('classpath:com/it/multibrand/karate/resources/oeResource/requestBody/GetKBAQuestions.json')
  * json getkbaQuestionsData = read('classpath:com/it/multibrand/karate/resources/oeResource/testData/GetKBAQuestionsTestData.json')

   Scenario Outline:
   	# English & Spanish
		# Test with POSID KBA Pass data
		# Test with POSID KBA Fail data
		# Test with Invalid data
		# Test with Channel Type blank
		# Test with Channel Type AA
		# Test with Channel Type WEB
		# Test with Channel Type AFF
		# Test with no Tracking Id field
		# Test with no Channel Type field
		
   * set getkbaQuestionsRequest.languageCode = langCode
   * set getkbaQuestionsRequest.trackingId = trackId
   * set getkbaQuestionsRequest.firstName = fName
   * set getkbaQuestionsRequest.middleName = mName
   * set getkbaQuestionsRequest.lastName = lName
   * set getkbaQuestionsRequest.tokenSSN = tokenizedSSN
   * set getkbaQuestionsRequest.channelType = chnlType
   * eval if (testScenario=='Test with tracking id field not passed') karate.remove('getkbaQuestionsRequest','$.trackingId')
   * eval if (testScenario=='Test with channel type field not passed') karate.remove('getkbaQuestionsRequest','$.channelType')   
	 
   Given path  '/NRGREST/rest/sales/kba-questions'
   And  request getkbaQuestionsRequest
   When method POST
   Then status 200
   And match response.statusCode == teststatus
   # And print "Transaction Key = " + transKey
   And print " Response Status = " + response.statusCode
   And print testScenario + " Response Time = " + responseTime
	 And print response
   
   Examples:
   | getkbaQuestionsData |