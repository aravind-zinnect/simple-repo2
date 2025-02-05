pipeline {
    agent any
    tools {
        maven 'maven123'
    }
    
    parameters {
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0', '1.4.0', '1.5.0'], description: 'Version to deploy on prod')
        booleanParam(name: 'executeTests', defaultValue: true, description: 'Run tests before deployment')
    }

    environment {
        NEW_VERSION = "${params.VERSION}"
        SERVER_CREDENTIALS = credentials('server-credentials') // Add correct credential ID here
    }

    stages {
        stage("Build") {
            steps {
                echo "Building the application..."
                echo "Building version ${NEW_VERSION}"
            }
        }
        
        stage("Test") {
            when {
                expression {
                    return params.executeTests == true
                }
            }
            steps {
                echo "Testing the application..."
            }
        }

        stage("Deploy") {
            steps {
                echo "Deploying the application..."
                echo "Deploying version ${NEW_VERSION}"
                
                withCredentials([
                    usernamePassword(credentialsId: 'server-credentials', usernameVariable: 'USER', passwordVariable: 'PWD')
                ]) {
                    bat "some_script.bat %USER% %PWD%"
                }
            }
        }
    }
}
