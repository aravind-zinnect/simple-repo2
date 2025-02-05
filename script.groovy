def buildApp() {
    echo 'Building the application...'
}

def testApp() {
    echo 'Running tests on the application...'
}

def deployApp(version) {
    echo "Deploying the application..."
    echo "Deploying version ${version}"
}

return this
