def call(Map config) {
	node {	
		try {
			stage('Clone') {
				checkout scm
			}
			stage ('Build') {
				bat "echo ${config.find('projectName')}"
			}
			/*
			stage ('Test') {
				parallel 'static': {
					bat "echo 'run static tests'"
				}, 'unit': {
					bat "echo 'run unit tests'"
				}, 'integration': {
					bat "echo 'run integration tests'"
				}
			}
			stage ('Deploy') {
				bat "echo 'deploying to server ${config.serverDomain}'"
			}
			*/
		} catch (err) {
			currentBuild.result = "FAILED"
			throw err
		}
	}
}