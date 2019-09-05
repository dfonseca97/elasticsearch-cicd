job('elastic-search-DSL') {
    scm {
        git('git://github.com/dfonseca97/elasticsearch-cicd.git')
    }
    triggers {
        scm('H/15 * * * *')
    }

    steps {
        shell('./gradlew clean')
        shell('./gradlew build')
        shell('export SPRING_SERVER=spring-app-test export SPRING_SERVER_PORT=8080; export ELASTIC_SERVER=elastic; export ELASTIC_SERVER_PORT=9200; ./gradlew integrationTest')
          
    }
}
