function() {
    var env = karate.env; // get java system property 'karate.env'
   
    karate.log('Environment:', env);
   // karate.configure("ssl", true)
   
    if (!env) {
      env = 'dev'; 
    }
   
    var config = {
      env: env,
      BASE_SERVER_URL_1 : 'http://dev1-ws.nrgenergy.com'
          };
   
    
    if(env == 'stg') {
    	config.BASE_SERVER_URL_1 = 'http://stg1-ws.nrgenergy.com'
    };
   
    if(env == 'prelive') {
    	config.BASE_SERVER_URL_1 = 'http://stg1-ws.nrgenergy.com'
    		
    };
    
    karate.log('karate.env selected environment was:', env);
    // karate.configure('retry',{ count:3, interval:5000});
    karate.configure('connectTimeout', 60000);
    karate.configure('readTimeout', 60000);
    return config;
  }