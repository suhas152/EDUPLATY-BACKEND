pipeline {
    agent any

    environment {
        DOCKERHUB_CREDS = credentials('dockerhub-credentials')
        IMAGE            = "${DOCKERHUB_CREDS_USR}/eduplaty-backend"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Maven Build') {
            steps {
                bat 'mvn clean package -DskipTests -B'
            }
        }

        stage('Maven Test') {
            steps {
                bat 'mvn test -B'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                bat "docker login -u %DOCKERHUB_CREDS_USR% -p %DOCKERHUB_CREDS_PSW%"
                bat "docker build -t %IMAGE%:%BUILD_NUMBER% -t %IMAGE%:latest ."
                bat "docker push %IMAGE%:%BUILD_NUMBER%"
                bat "docker push %IMAGE%:latest"
            }
        }

        stage('Bootstrap K8s') {
            steps {
                withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]) {
                    bat 'kubectl apply -f k8s/namespace.yaml'
                    bat 'kubectl apply -f k8s/mysql/'
                    bat 'kubectl apply -f k8s/backend/'
                    bat 'kubectl apply -f k8s/frontend/'
                }
            }
        }

        stage('Deploy Backend') {
            steps {
                withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]) {
                    bat "kubectl set image deployment/eduplaty-backend eduplaty-backend=%IMAGE%:%BUILD_NUMBER% -n eduplaty"
                    bat "kubectl rollout status deployment/eduplaty-backend -n eduplaty --timeout=120s"
                }
            }
        }
    }

    post {
        success {
            echo "Backend deployed successfully — build #${BUILD_NUMBER}"
        }
        failure {
            echo "Backend pipeline failed — check logs above"
        }
    }
}
