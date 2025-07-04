pipeline {
agent any

    environment {
        ORDER_IMAGE = "order-service"
        INVENTORY_IMAGE = "inventory-service"
        TAG = "latest"
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/malleswar-reddy/Ecommerce-Java-backend.git'
            }
        }

        stage('Build Order Service') {
            steps {
                dir('order-service') {
                    sh 'mvn clean install -DskipTests'
                    sh "docker build -t ${ORDER_IMAGE}:${TAG} ."
                }
            }
        }

        stage('Build Inventory Service') {
            steps {
                dir('inventory-service') {
                    sh 'mvn clean install -DskipTests'
                    sh "docker build -t ${INVENTORY_IMAGE}:${TAG} ."
                }
            }
        }

        stage('Start Services with Docker Compose') {
            steps {
                sh 'docker-compose down || true'  // Stop if already running
                sh 'docker-compose up -d --build'
            }
        }
    }

    post {
        failure {
            echo "❌ Build or deploy failed. Check logs."
        }
        success {
            echo "✅ Deployment completed successfully!"
        }
    }
}
