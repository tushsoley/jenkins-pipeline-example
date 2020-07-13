FROM openjdk:8u201-jre-alpine3.9

RUN apk add curl jq

#Workspace
WORKDIR /usr/share/ci-cd-demo

#Copying main, test jar files
ADD target/ci-cd.jar         ci-cd.jar
ADD target/ci-cd-tests.jar   ci-cd-tests.jar

#Copying libs files on to the container
ADD target/libs                         libs

#Copying configurations
ADD src                                 src
ADD config                       config

#Copying testng XML file
ADD ci-cd-testng.xml                ci-cd-testng.xml

ADD healthcheck.sh                      healthcheck.sh

#execution
ENTRYPOINT sh healthcheck.sh


