def call(Map config) {
	node {	
		try {
			stage('\u2776 Clone') {
				checkout scm
			}
			stage ('\u2777 Build') {
				bat "echo ${config.projectName}"
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