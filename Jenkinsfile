pipeline{

    agent any

    stages{

        stage('Build Jar'){
            steps{
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Image'){
            steps{
                sh 'docker build -t=rbisaria/selenium .'
            }
        }

        stage('push image'){
            environment{
                DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps{
                sh 'docker login -u ${DOCKER_HUB_USR} -p {DOCKER_HUB_PSW}'
                sh "docker push rbisaria/selenium"
            }
        }

    }

    post{
        always{
            sh 'docker logout'
        }
    }

}