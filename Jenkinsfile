pipeline {
    agent any   
    parameters {
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: 'Version to deploy on prod')
        booleanParam(name: 'executeTests', defaultValue: true, description: 'Run tests before deployment')
    }
    stages {
        stage("Init") {
            steps {
                script {
                    gv = load "script.groovy" // Load external script
                }
            }
        }
        
        stage("Build") {
            steps {
                script {
                    gv.buildApp() // Call function properly
                }
            }
        }
        
        stage("Test") {
            when {
                expression { return params.executeTests }
            }
            steps {
                script {
                    gv.testApp() // Call function properly
                }
            }
        }

        stage("Deploy") {
            steps {
                script {
                    gv.deployApp(params.VERSION) // Pass parameters correctly
                }
            }
        }
    }
}
