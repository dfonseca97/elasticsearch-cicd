FROM alpine:3.10

LABEL version="1.0"

USER root

#Elastic server connection variables.

#Install Java.
RUN apk update &&\
    apk upgrade &&\
    apk fetch openjdk8 &&\
    apk add openjdk8 &&\
    apk add curl
    
#Set Java environment.
ENV JAVA_HOME=/usr/lib/jvm/java-1.8-openjdk
ENV PATH="$JAVA_HOME/bin:${PATH}"  

#Copy the project.
WORKDIR /elasticsearch
COPY . /elasticsearch

#Cleans the previous compilation and executables and builds the project.
RUN ./gradlew clean
RUN ./gradlew build

RUN mkdir /startup
COPY jenkins/run_job.sh /startup/run_job.sh
RUN chmod -u+x /startup/run_job.sh

#Executes the application.
ENTRYPOINT /startup/run_job.sh

EXPOSE 8080
