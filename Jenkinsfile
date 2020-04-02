pipeline {
    environment {
    registry = "shlomigonen/docker_k8s_test_container"
    registryCredential = 'dockerhub'
    registryURL = 'https://registry.hub.docker.com'
    }

    agent any

    stages {
        stage('Pull') {
             steps {
                // Get some code from a GitHub repository
                echo 'Pulling latest code..'
                git 'https://github.com/shlomigonen/docker-k8s-test.git'
             }
        }
        stage('Build') {
             steps {
                // Run Maven on a Unix agent.
                echo 'Building..'
                sh "mvn package -Dmaven.test.skip=true"
                // sh "mvn clean package"
             }
        }
        stage('Create Docker Container') {
            steps{
                script {
                dockerContainer = docker.build registry
                }
            }
        }
        stage('Push Container to Dockerhub') {
            steps {
                echo 'Push Docker Container to Dockerhub..'

                script {
                    // Login to a registry. Dockerhub is the default and does not need a URL
                    docker.withRegistry(registryURL, registryCredential ) {
                        dockerContainer.push("${env.BUILD_NUMBER}")
                        // Push another container with latest label
                        dockerContainer.push("latest")
                    }
                }
            }
        }
   }
}
