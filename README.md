# DevOps Practice - elasticsearch-cicd
This is a simple elasticsearch application to be run with Docker and accessed through Java to be set up using a cicd 
environment.

## Starting simple elasticsearch server

The elasticsearch server is created through Docker Compose. To start up the server simply run:

```bash
docker-compose up -d
``` 

You can find the docker-compose file at the root of this project.

You can find out that the elasticsearch server is running by accessing through any browser:

```bash
http://localhost:9200
``` 

## Springboot application startup

This application is a rest service created with springboot. Through gradle you can start up the rest service by running:

```bash
gradlew bootRun
or
gradlew build && java -jar elastic-cicd-0.1.0.jar
``` 

You can check that the service is running by hitting the root or health resource:

```bash
http://localhost:8080
or
http://localhost:8080/health
```

## Project

We want an application to store and search for document titles, date and contents. Ideally it should be rest application 
that allows me to store text files contents along with the date they are added and title. After that 
we would like to be able to search through keywords the contents of the documents then get the 
title of the text file, date it was added and highlight content where the keyword was found.

## Project REST endpoints

Elasticsearch works as a search engine, where you can store (index) any content you desire to then
search.The REST endpoints to do so are found below:

```bash
# Index REST endpoint
http://localhost:8080/index?docsPath=TestSample

# Search REST endpoint
http://localhost:8080/search?query=computer
```