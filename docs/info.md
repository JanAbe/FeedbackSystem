## Information

#### Maven
- A build lifecycle is the process for building and distributing an artifact (JAR/WAR).
- There are multiple phases in a build lifecycle.
- Each phase represents a different stage in the lifecycle.
- Common phases:
    - Validate
        - Validate that the project is correct and all necessary information is available.
    - Compile
        - Compile the source code of the project.
    - Test
        - Test the compiled source code using a unit test framework (e.g. JUnit).
    - Package
        - Package the compiled source code in its distributable format (JAR/WAR).
    - Verify
        - Run checks on the results of integrations tests.
    - Install
        - Install the package (JAR/WAR) into the local repository.
    - Deploy
        - Copy the final package to the remote repository.
- Source: https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#Build_Lifecycle_Basics 


#### Jenkins
A Jenkinsfile is a file that contains the definition of a Jenkins Pipeline. It describes how the Pipeline is made.

Anatomy of our [Jenkinsfile](../Jenkinsfile):

\
This contains all information about the Pipeline.
```
pipeline { ... }
```

\
This specifies which agent (e.g. machine, container) the job should be executed on.
```
agent any
```

\
Tools contain the necessary tools to run the pipeline (e.g jdk and maven)
The names used in tools should be configured in Jenkins (Global Tool Configuration). 
In this case the name 'Java 11' is defined and points to the local location of JDK-11.
```
tools {
    jdk 'Java 11'
    maven 'maven-3.5.2'
}
```

\
Stages contain all stages the pipeline will go through. The names of these stages are shown
in the Jenkins UI.
```
stages { ... }
```

\
Defines a stage called 'Build' with one step, which runs a maven command to build the application without running tests.
In this case 'bat' is used, which indicates a windows machine. If 'sh' is used, it indicates a unix machine.
```
stage('Build') {
    steps {
        bat 'mvn -B -DskipTests clean package'
    }
}
```

\
Defines a stage called 'Test' with one step, which runs a maven command to execute all unit tests.
The 'post' section will be run after the stage has completed all its steps. The 'always' keyword indicates that the body
will be executed whether or not the stage failed or succeeded.
```
stage('Test') {
    steps {
        bat 'mvn test'
    }
    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
```
