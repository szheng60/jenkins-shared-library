def call(body) {
	def config = [:]
	body.resolveStrategy = Closure.DELEGATE_FIRST
	body.delegate = config
	body()
	
	node {
		deleteDir()
		
		try {
			stage('Clone') {
				checkout scm
			}
			stage ('Build') {
				bat "echo 'building ${config.projectName}'"
			}
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
		} catch (err) {
			currentBuild.result = "FAILED"
			throw err
		}
	}
}