pipeline {
    agent any

    environment {
        DOCKERHUB_CREDS = credentials('dockerhub-credentials')
        IMAGE            = "${DOCKERHUB_CREDS_USR}/eduplaty-backend"
        KUBECONFIG_FILE  = credentials('kubeconfig')
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Maven Build') {
            steps {
                sh 'mvn clean package -DskipTests -B'
            }
        }

        stage('Maven Test') {
            steps {
                sh 'mvn test -B'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                sh """
                    echo ${DOCKERHUB_CREDS_PSW} | docker login -u ${DOCKERHUB_CREDS_USR} --password-stdin
                    docker build -t ${IMAGE}:${BUILD_NUMBER} -t ${IMAGE}:latest .
                    docker push ${IMAGE}:${BUILD_NUMBER}
                    docker push ${IMAGE}:latest
                """
            }
        }

        stage('Bootstrap K8s (first time only)') {
            steps {
                withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]) {
                    sh '''
                        kubectl apply -f k8s/namespace.yaml
                        kubectl apply -f k8s/mysql/
                        kubectl apply -f k8s/backend/
                        kubectl apply -f k8s/frontend/
                    '''
                }
            }
        }

        stage('Deploy Backend') {
            steps {
                withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]) {
                    sh """
                        kubectl set image deployment/eduplaty-backend \
                            eduplaty-backend=${IMAGE}:${BUILD_NUMBER} \
                            -n eduplaty
                        kubectl rollout status deployment/eduplaty-backend -n eduplaty --timeout=120s
                    """
                }
            }
        }
    }

    post {
        success { echo "Backend deployed — build #${BUILD_NUMBER}" }
        failure { echo "Backend pipeline failed" }
        always  { sh 'docker logout || true' }
    }
}
