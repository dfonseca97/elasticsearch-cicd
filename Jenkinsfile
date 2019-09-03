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
        sh './gradlew integrationTest'
      }
    }
  }
}