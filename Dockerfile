# syntax=docker/dockerfile:1

FROM maven:3.9.1-amazoncorretto-17
COPY . /tmp/
WORKDIR /tmp/
RUN mvn clean install
RUN mvn -B -f /tmp/pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve
