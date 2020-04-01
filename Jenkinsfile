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
      stage('Image') {
         steps {
            // Run Maven on a Unix agent.
            echo 'Create a docker image..'

         }
      }
   }
}
