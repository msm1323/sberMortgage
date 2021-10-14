def mvn = "/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/3.6.3/bin/mvn"

pipeline {
    agent { label 'linux' }
    parameters {
        string(name: 'TAG', defaultValue: '@all', description: 'тег для запуска')
        string(name: 'BROWSER_TYPE', defaultValue: 'remote', description: 'тип браузера')
        string(name: 'REMOTE_BROWSER_TYPE', defaultValue: 'chrome', description: 'тип браузера для удаленного запуска')
        string(name: 'REMOTE_BROWSER_VERSION', defaultValue: '81.0', description: 'версия браузера для удаленного запуска')
    }
    stages {
        stage('Build') {
            steps {
                sh "${mvn} clean compile"
            }
        }
        stage('Run Tests') {
            steps {
                sh "${mvn} test -Dtype.browser=${params.BROWSER_TYPE}  -Dremote.browser.type=${params.REMOTE_BROWSER_TYPE} " +
                        "-Dremote.browser.version=${params.REMOTE_BROWSER_VERSION} -Dcucumber.filter.tags=${params.TAG}"
            }
        }
        stage('Allure Report Generation') {
            steps {
                allure includeProperties: false,
                        jdk: '',
                        results: [[path: 'target/reports/allure-results']]
            }
        }
    }
    post {
        always {
            cleanWs notFailBuild: true
        }
    }
}