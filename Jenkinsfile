pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'DEV',
                    credentialsId: 'github-token',
                    url: 'https://github.com/paulmaxgithub/intern_final_task.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Run Authorization Test') {
            steps {
                sh 'mvn test -Dtest=AuthorizationTest'
            }
        }

        stage('Run Search Products Test') {
            steps {
                sh 'mvn test -Dtest=SearchProductsTest'
            }
        }

        stage('Run Add Product to Cart Test') {
            steps {
                sh 'mvn test -Dtest=AddProductToCartTest'
            }
        }

        stage('Run Product in Cart Test') {
            steps {
                sh 'mvn test -Dtest=ProductInCartTest'
            }
        }

        post {
            always {
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }
}