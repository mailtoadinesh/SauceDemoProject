pipeline {
    agent any

    environment {
        PROJECT_NAME = 'sauce-demo-tests'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Pulling latest code from GitHub...'
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image...'
                bat 'docker build -t sauce-demo-tests .'
            }
        }

        stage('Start Selenium Grid') {
            steps {
                echo 'Starting Selenium Hub and Chrome node...'
                bat 'docker-compose up -d selenium-hub chrome'
                // Wait for grid to be ready
                sleep(time: 15, unit: 'SECONDS')
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running Selenium TestNG tests...'
                bat 'docker-compose up --abort-on-container-exit test-runner'
            }
        }

        stage('Publish Reports') {
            steps {
                echo 'Publishing TestNG reports...'
            }
            post {
                always {
                    testNG reportFilenamePattern: '**/testng-results.xml'
                }
            }
        }
    }

    post {
        always {
            echo 'Stopping and cleaning up Docker containers...'
            bat 'docker-compose down'
        }
        success {
            echo 'Build and Tests PASSED!'
        }
        failure {
            echo 'Build or Tests FAILED!'
        }
    }
}