pipeline {
    agent any  // Runs on any available agent

    environment {
        HEADLESS_MODE = 'true'  // Set to 'true' for headless mode in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from your GitHub repository
                git branch: 'DEV',
                    credentialsId: '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',  // Ensure credentials are securely stored in Jenkins
                    url: 'https://github.com/paulmaxgithub/intern_final_task.git'  // Your repository URL
            }
        }

        stage('Build') {
            steps {
                // Build the project without running tests
                sh 'mvn clean package -DskipTests'  // Package the project and skip tests
            }
        }

        stage('Run Tests') {
            steps {
                // Run the tests
                sh 'mvn test'  // Run the tests (ensure this matches your test framework)
            }

            post {
                always {
                    // Collect test reports
                    junit '**/target/surefire-reports/*.xml'  // Collect JUnit test reports
                }
            }
        }
    }
}