pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh './gradlew clean'
        sh './gradlew build'
      }
    }
    stage('Test') {
      steps {
        sh '''export ELASTIC_SERVER=172.19.167.158
export ELASTIC_SERVER_PORT=9200
./gradlew integrationTest'''
      }
    }
  }
}