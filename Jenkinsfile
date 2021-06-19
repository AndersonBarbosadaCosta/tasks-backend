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
                sleep(10)
              //  timeout(time: 1, unit: 'MINUTES') {
              //       waitForQualityGate abortPipeline: true
              bat 'echo quality Gate'
            }
        }  
        stage ('Deploy Backend') {       
                    steps {
                        deploy adapters: [tomcat8(credentialsId: 'TomCat_Login', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks-backend', war: 'target\\tasks-backend.war'  
                        }
        }
        stage ('API Test') {
             steps {
                 dir('api-test') {
                    git credentialsId: 'credentials_jenkins', url: 'https://github.com/AndersonBarbosadaCosta/apiTest'
                    bat 'mvn test'
                  }
             }
        }
        stage ('Deploy Front') {
             steps {
                 dir('frontend') {
                    git credentialsId: 'credentials_jenkins', url: 'https://github.com/AndersonBarbosadaCosta/tasks-frontend'
                    bat 'mvn clean package'
                    deploy adapters: [tomcat8(credentialsId: 'TomCat_Login', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks', war: 'target\\tasks.war'
                  }
             }
        }
    }
           
}

