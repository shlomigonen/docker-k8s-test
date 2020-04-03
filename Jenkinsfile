pipeline {
    environment {
    registry = "shlomigonen/docker_k8s_test_container"
    registryCredential = 'dockerhub'
    registryURL = 'https://registry.hub.docker.com'
    repository = 'https://github.com/shlomigonen/docker-k8s-test.git'
    repositoryCredentials = 'Github'
    clusterCredentials = 'KMaster'
    clusterDeployFile = "docker_k8s_test_deployment.yaml"
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
                    sshagent(['KMaster']) {
                    sh "scp -o StrictHostKeyChecking=no docker_k8s_test_deployment.yaml ubuntu@18.144.174.238"
                    script {
                        try {
                            sh "ssh ubuntu@18.144.174.238 sudo kubectl apply -f docker_k8s_test_deployment.yaml"
                        } catch(error){
                            sh "ssh ubuntu@18.144.174.238 sudo kubectl create -f docker_k8s_test_deployment.yaml"
                        }
                    }
                }
//                     kubernetesDeploy    configs: '/home/ubuntu/docker_k8s_test_deployment.yaml',
//                                         kubeconfigId: 'K8SMaster',
//                                         ssh: [sshCredentialsId: '*', sshServer: ''],
//                                         textCredentials: [certificateAuthorityData: '', clientCertificateData: '', clientKeyData: '', serverUrl: 'https://']
//
//                     withCredentials([kubeconfigContent( configs: '/home/ubuntu/docker_k8s_test_deployment.yaml',
//                                                         credentialsId: clusterCredentials)]) {
//                         sh '''echo "$KUBECONFIG_CONTENT" > kubeconfig && cat kubeconfig && rm kubeconfig'''
//
//                     }

            }
        }
   }
}
