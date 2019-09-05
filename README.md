# DevOps Practice - elasticsearch-cicd
This is a simple elasticsearch application to be run with Docker and accessed through Java to be set up using a cicd 
environment.

## Project

We want an application to store and search for document titles, date and contents. Ideally it should be rest application 
that allows me to store text files contents along with the date they are added and title. After that 
we would like to be able to search through keywords the contents of the documents then get the 
title of the text file, date it was added and highlight content where the keyword was found.

## Running the project

To run the project Docker and docker-compose must be installed beforehand:

https://docs.docker.com/install/
https://docs.docker.com/compose/install/

Before running the project, the environment variables must be set:

```bash
export ELASTIC_SERVER=<IP address of the Elastic Search server>
export ELASTIC_SERVER_PORT=<Port where the Elastic Server is listening>
export JENKINS_USER=<user for jenkins login>
export JENKINS_PASSWORD=<password for jenkins login>
```

The ELASTIC_SERVER variable must be an IP address or the name of the Elastic Search service in Docker, elastic-server, in this case. Localhost **does not** work in this case since the Spring application resides in a different container. The port should be 9200 in this case since that is where the server is running. Another external server could be used as well.

The jenkins user and password for the initial run are **both** admin. All jenkins users were configured to have no privileges at all since this instance is only configured to run this project.

To start the Elastic Search, Spring Application and Jenkins servers run:

> $docker-compose up 

This will start the Elastic Server container and the Jenkins container first. After these are both up and running the Spring Application will start.

* The Jenkins UI can be accessed on *http://localhost:8787*
* The Spring app's health check can be accessed on *http://localhost:8080* or *http://localhost:8080/health* and the available endpoints are

```bash
[GET]
http://localhost:8080/index

parameters: 
docsPath=Absolute address to the documents to be indexed.

[GET]
http://localhost:8080/search

parameters: 
query=Text to search for inside the indexed documents.

```

* The elastic server is located on *http://localhost:9200*

## Further insights on the project

More documentation about how the automation was made possible on this repository's wiki https://github.com/dfonseca97/elasticsearch-cicd/wiki
