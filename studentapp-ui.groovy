pipeline {
    agent any

    stages {
        stage('Pull') {
            steps {
                git changelog: false, poll: false, url: 'https://github.com/riddhidhengle/studentapp.ui'
                echo 'Hello World'
            }
        }
                stage('Build') {
            steps {
                sh '/opt/apache-maven-3.9.6/bin/mvn clean package'
                echo 'Here we are deploying the code'
            }
        }
                stage('Test') {
            steps {
                sh '''/opt/apache-maven-3.9.6/bin/mvn sonar:sonar \\
                        -Dsonar.projectKey=student-ui \\
                        -Dsonar.host.url=http://54.219.58.68:9000 \\
                        -Dsonar.login=283ab4fe2f00328ee0f57d591bba8484ebb244af'''
                echo 'Testing done'
            }
        }
                stage('Deploy') {
            steps {
                echo 'Deploy done'
            }
        }
    }
}