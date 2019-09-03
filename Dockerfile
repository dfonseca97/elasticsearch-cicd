FROM alpine:3.10

LABEL version="1.0"

USER root

#Elastic server connection variables.

#Install Java.
RUN apk update &&\
    apk upgrade &&\
    apk fetch openjdk8 &&\
    apk add openjdk8
    
#Set Java environment.
ENV JAVA_HOME=/usr/lib/jvm/java-1.8-openjdk
ENV PATH="$JAVA_HOME/bin:${PATH}"  

#Copy the project.
WORKDIR /elasticsearch
COPY . /elasticsearch
RUN cd /elasticsearch

#Cleans the previous compilation and executables and builds the project.
RUN ./gradlew clean
RUN ./gradlew build

#Executes the application.
ENTRYPOINT java -jar build/libs/*.jar

EXPOSE 8080
