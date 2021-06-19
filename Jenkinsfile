pipeline {
    agent any
    stages {
        stage ('Build Backend') {
            steps {
               bat 'mvn clean package -DskipTests=true'
           }
        }
        stage ('Unit Tests') {
            steps {
               bat 'mvn test'
            }
        }
        stage ('Sonar Analysis') {
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }        
            steps {
                  withSonarQubeEnv('Sonar') {
                    bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://localhost:9000 -Dsonar.java.binaries=target -Dsonar.login=6e16cd03f4673ebc23d057d0577dbc628f3d3388 -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**Application.java"               
                  }
           }
        }  
           stage ('Quality Gate') {       
            steps {
                sleep(15)
                  timeout(time: 1, unit: 'MINUTES') {
                     waitForQualityGate abortPipeline: true
                  }
           }
        }   
    }        
}

