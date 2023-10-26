# syntax=docker/dockerfile:1
FROM eclipse-temurin:17-jdk-alpine
ARG SERVICE=user
COPY ./${SERVICE}-service/${SERVICE}-container/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]