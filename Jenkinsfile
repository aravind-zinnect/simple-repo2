pipeline {
    agent any
    tools {
        maven 'maven123'
    }
    
    parameters {
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: 'Version to deploy on prod')
        booleanParam(name: 'executeTests', defaultValue: true, description: 'Run tests before deployment')
    }

    environment {
        NEW_VERSION = "${params.VERSION}"
        SERVER_CREDENTIALS = credentials('server-credentials') // Ensure the credential ID is correct
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
                    bat """
                        echo Running deployment script...
                        if exist some-windows-script.bat (
                            some-windows-script.bat %USER% %PWD%
                        ) else (
                            echo Script not found in workspace.
                            exit /B 1
                        )

                        echo Configuring Git...
                        git config --global user.email "your-email@example.com"
                        git config --global user.name "Your Name"

                        echo Checking for changes before committing...
                        git status
                        git diff --quiet || (
                            git add some-windows-script.bat
                            git commit -m "Add deployment script"
                        )

                        echo Pushing changes to GitHub...
                        git push https://<YOUR_GITHUB_TOKEN>@github.com/aravind-zinnect/simple-repo2.git main
                    """
                }
            }
        }
    }
}
