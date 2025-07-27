pipeline {
    agent any

    parameters {
        string(name: 'TAG', defaultValue: '@regression', description: 'Cucumber tag to run')
    }

    environment {
        MAVEN_OPTS = '-Dmaven.test.failure.ignore=false'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Install Dependencies') {
            steps {
                sh 'mvn clean install -DskipTests=true'
            }
        }

        stage('Run Tests') {
            steps {
                sh "mvn test -Dcucumber.filter.tags=${params.TAG}"
            }
        }

        stage('Generate Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/cucumber-reports/*.html', allowEmptyArchive: true
            junit '**/target/cucumber-reports/*.xml'
        }
    }
}
