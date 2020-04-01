pipeline {
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
             }
        }
        stage('Dockerize') {
            steps {
                echo 'Create and Push Docker Container..'
                // Create Docker image.
                // 1st parameter is the docker registry url
                // 2nd parameter is the name of the credentials defined in Jenkins for my docker hub account
                docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {

                    def customImage = docker.build("shlomigonen/docker_k8s_test_container:${env.BUILD_ID}")

                    // Push the container to the custom Registry
                    customImage.push()
                }
            }
        }
   }
}
