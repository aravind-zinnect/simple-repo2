pipeline {
    agent any

    environment {
        NODE_VERSION = '20'
    }

    stages {
        stage('Checkout Code') {
            steps {
                script {
                    echo "🔹 Checking out code from Git repository..."
                    checkout scm
                }
            }
        }

        stage('Setup Node.js v20') {
            steps {
                script {
                    echo "🔹 Checking Node.js version..."
                    bat 'node -v || echo Node.js is not installed'

                    echo "🔹 Installing Node.js v20 if not installed..."
                    bat """
                    where node || choco install nodejs --version=${NODE_VERSION} -y
                    node -v
                    """
                }
            }
        }

        stage('Install Dependencies') {
            steps {
                script {
                    echo "🔹 Installing project dependencies..."
                    bat 'npm install'
                }
            }
        }

        stage('Build Project') {
            steps {
                script {
                    echo "🔹 Building the project..."
                    bat 'npm run build'  // Change if no build step
                }
            }
        }

        stage('Run Project') {
            steps {
                script {
                    echo "🚀 Starting the application..."
                    bat 'npm start'
                }
            }
        }
    }

    post {
        success {
            echo "✅ Build and deployment successful!"
        }
        failure {
            echo "❌ Build failed! Check logs for details."
        }
    }
}
