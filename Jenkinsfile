@Library('nrg-commons')
//Jenkinsfile version-2.1

def args = [
	//##### Config param #####
	emailRecepients: 'NRGDigitalIT-All@nrg.com, saurabh.tripathi@nrg.com, Sanjesh.Malviya@nrg.com, BBARMAN@nrg.com',
	buildTimeOut: 60, //MINUTES
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
			typeOfTestStage: [
				E2E:[
					collections: [
						sales: [
							postmanCollectionId: '3890864-cf7ddaa5-f64f-4d62-8a82-917230f86813',
							postmanEnvIdDev: '',
							postmanEnvIdStg: '3890864-43f48261-041a-4135-a5f0-08ec3f53f5c9',
							dataSourceArray: [
							],
							foldersToinclude: [
							]
						]
					]
				],
				smoke: [
					collections: [
						// Add additional collections for smoke here 
					]
				]
			]
		],
		karate: [],
		selenium: []
	]
]

javaWebAppTemplate(args)