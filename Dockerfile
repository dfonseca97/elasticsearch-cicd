FROM alpine:3.10

LABEL version="1.0"

USER root

RUN apk update
RUN apk upgrade
RUN apk fetch openjdk8
RUN apk add openjdk8
RUN apk add maven

ENV JAVA_HOME=/usr/lib/jvm/java-1.8-openjdk
ENV PATH="$JAVA_HOME/bin:${PATH}"  

WORKDIR /elasticsearch
COPY . /elasticsearch
RUN cd /elasticsearch

RUN ./gradlew clean
RUN ./gradlew build

ENTRYPOINT java -jar build/libs/*.jar

EXPOSE 8080
