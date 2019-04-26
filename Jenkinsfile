pipeline {
    agent any

    tools {
        jdk 'Java 11'
        maven 'maven-3.5.2'
    }


    stages {
        stage('Build') {
            steps {
                bat 'mvn -B -DskipTests clean package'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Report') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }
    }

    post {
        failure {
            junit 'target/surefire-reports/*.xml'
        }
    }
}