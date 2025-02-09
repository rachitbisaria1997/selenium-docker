pipeline{

    agent any

    stages{

        stage('Build Jar'){
            steps{
                sh "mvn clean package -DskipTests"
            }
        }

        stage('Build Image'){
            steps{
                sh "docker build -t=rbisaria/selenium ."
            }
        }

        stage('push image'){
            steps{
                sh "docker push rbisaria/selenium"
            }
        }

    }

}