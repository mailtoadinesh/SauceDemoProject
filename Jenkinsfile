pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        DOCKER_COMPOSE = 'docker-compose'
    }

    stages {

        stage('Checkout') {
            steps {
                git url: 'https://github.com/your-repo/saucedemo-automation.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Start Selenium Grid') {
            steps {
                sh '${DOCKER_COMPOSE} up -d selenium-hub chrome'
            }
        }

        stage('Run Tests') {
            steps {
                sh '${DOCKER_COMPOSE} run saucedemo-tests'
            }
        }

        stage('Generate Reports') {
            steps {
                archiveArtifacts artifacts: 'test-output/**', fingerprint: true
            }
        }
    }

    post {
        always {
            sh '${DOCKER_COMPOSE} down'
        }
    }
}