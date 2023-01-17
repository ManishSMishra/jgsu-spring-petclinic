pipeline {
    agent any

/*    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }*/

    stages {
        stage('Checkout') {
            steps {
                // Get some code from a GitHub repository
                git url: 'https://github.com/ManishSMishra/jgsu-spring-petclinic.git', branch: 'main'
            }

        }
        
        stage('Build') {
            steps {

                // Run Maven on a Unix agent.
                sh "./mvnw clean package"

            }
            
            post {
            always {

                junit '**/target/surefire-reports/*.xml'
                archiveArtifacts 'target/*.jar'
                    }
                }

        }
        
        
    }
    
    

}
