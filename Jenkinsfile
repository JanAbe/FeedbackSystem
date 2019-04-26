pipeline {
    agent any

    tools {
        maven 'maven-3.5.2'
    }

    stages { // Contains all stages, shown in the jenkins ui, the pipeline will go through
        stage('Build') {
            steps { // Runs maven command to build application without running tests, bat is for windows
                bat 'mvn -B -DskipTests clean package'
            }
        }
    }
}