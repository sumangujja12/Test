@Library('nrg-commons')
//Jenkinsfile version-1.0

def args = [
	//##### Config param #####
	emailRecepients: 'SKODALI1@nrg.com, Shichang.Jiang@nrg.com, Harish.Singaipally@nrg.com, Thanesh.Kumar@nrg.com, Swathi.Boozula@nrg.com, JYogapa1@reliant.com, NagaJyothi.Katragadda@nrg.com, RajaMeghana.Yanamadala@nrg.com, Sundeep.Gudapati@nrg.com, saurabh.tripathi@nrg.com, Sanjesh.Malviya@nrg.com, BBARMAN@nrg.com, JPURBEY1@reliant.com',
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
	deployBranch: 'develop'
]

javaWebAppTemplate(args)