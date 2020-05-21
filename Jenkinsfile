@Library('nrg-commons')
//Jenkinsfile version-2.0

def args = [
	//##### Config param #####
	emailRecepients: 'NRGDigitalIT-All@nrg.com, saurabh.tripathi@nrg.com, Sanjesh.Malviya@nrg.com, BBARMAN@nrg.com',
	buildTimeOut: 45, //MINUTES
	stgPromotionApvrlWaitTime: 15,//MINUTES
	projectArtifact: 'target/nrgrest.war, src/main/resources/properties/environment.properties*',
	
	//##### UCD param #####
	applicationName: 'NRGREST',
	ucdComponent: [
        sharedLibComp: '',
        propsComp: 'NRGREST-PROPS',
        webComp: 'NRGREST-APP'
    ],
	path: [
        sharedLibPath: '',
        propsPath: 'src/main/resources/properties',
        warPath: 'target'
    ],

	//##### Test URLs #####
	devUrl: "",
	stgUrl: "",
	
	
	//##### Maven commands #####
	mvnCompileTest: 'clean test -Pserver-profile -U -T 1C',
	mvnVerify: 'verify -Pserver-profile -U -T 1C',
	mvnPackage: 'clean package -Pserver-profile -U -T 1C',

	//##### Batch commands #####
	zipLib: 'cd target/ & jar -cMf shared-lib.zip lib/ & cd ../../',
	deployBranch: 'develop',
	
	typeOfTest: [
		postman: [
			postmanCollectionId: '3890864-441e570b-23ce-42c2-808d-6c3bde41c331',
			postmanEnvIdDev: '',
			postmanEnvIdStg: '10503842-ae725690-84bf-4138-8d27-1078d22d5912'
		],
		karate: [],
		selenium: []
	]
]

javaWebAppTemplate(args)