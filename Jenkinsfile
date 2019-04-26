pipeline {
    agent any

    stages { // Contains all stages, shown in the jenkins ui, the pipeline will go through
        stage('Build') {
            steps {
                bat 'mvn -B -DskipTests clean package' // Runs maven command to build application without running tests, bat is for windows
            }
        }
    }
}