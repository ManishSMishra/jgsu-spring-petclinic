pipeline {
    agent any

/*    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }*/

        triggers {
        pollSCM('* * * * *')
    }

    stages {

        // Implicit checkout step
        // stage('Checkout') {
        //     steps {
        //         // Get some code from a GitHub repository
        //         git url: 'https://github.com/ManishSMishra/jgsu-spring-petclinic.git', branch: 'main'
        //     }

        // }
        
        stage('Build') {
            steps {

                // Run Maven on a Unix agent.
                sh "./mvnw clean package"

            }

        }
        }

        post {
            always {


                junit '**/target/surefire-reports/*.xml'
                archiveArtifacts 'target/*.jar'
            //         }

            // changed {

                emailext attachLog: true, 
                body: "Please go to ${BUILD_URL} and verify the build. Build Duration: ${currentBuild.durationString}", 
                compressLog: true, 
                recipientProviders: [upstreamDevelopers(), requestor()], 
                subject: "Job \'${JOB_NAME}\' (build ${BUILD_NUMBER}) ${currentBuild.result}", 
                to: "manishsmishra@test.com"

                
            }
                }

}