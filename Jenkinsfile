pipeline {
    environment {
    registry = "shlomigonen/docker_k8s_test_container"
    registryCredential = 'dockerhub'
    registryURL = 'https://registry.hub.docker.com'
    repository = 'https://github.com/shlomigonen/docker-k8s-test.git'
    repositoryCredentials = 'Github'
    clusterCredentials = 'KMaster'
    clusterDeployFile = "docker_k8s_test_deployment.yaml"
    k8sMasterAddress = "ubuntu@172.31.6.178"
    }

    agent any

    stages {
        stage('Pull latest Code') {
             steps {
                // Get some code from a GitHub repository
                echo 'Pulling latest code..'
                git branch: 'master', credentialsId: repositoryCredentials, url: repository
             }
        }
        stage('Build Code') {
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
                echo 'Create the Docker Container..'
                dockerContainer = docker.build registry
                }
            }
        }
        stage('Push Container to Registry') {
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
        stage('Deploy to K8S cluster') {
            steps{
                    echo 'Deploy on Kubernetes cluster..'
                    // enable execution mode on the script file
                    sh "chmod +x update_build_version.sh"
                    sh "./update_build_version.sh ${env.BUILD_NUMBER} ${clusterDeployFile}"

                    sshagent(['KMaster']) {
                        sh "scp -o StrictHostKeyChecking=no ${clusterDeployFile} ${k8sMasterAddress}:/home/ubuntu/"
                        script {
                            try {
                                sh "ssh ${k8sMasterAddress} sudo kubectl apply -f ${clusterDeployFile}"
                            } catch(error){
                                sh "ssh ${k8sMasterAddress} sudo kubectl create -f ${clusterDeployFile}"
                            }
                    }
                }
            }
        }
   }
}
