def mvn = "/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/3.6.3/bin/mvn"

pipeline {
    agent { label 'linux' }
    parameters {
        string(name: 'TAG', defaultValue: '@all', description: 'тег для запуска')
        string(name: 'BROWSER_TYPE', defaultValue: 'chrome', description: 'тип браузера')
    }
    stages {
        stage('Build') {
            steps {
                sh "${mvn} clean compile"
            }
        }
        stage('Run Tests') {
            steps {
                sh "${mvn} test -Dtype.browser=${params.BROWSER_TYPE} -Dcucumber.filter.tags=${params.TAG}"
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