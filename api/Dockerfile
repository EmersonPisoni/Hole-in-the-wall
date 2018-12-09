FROM openjdk:8-jdk-alpine
EXPOSE 80
ADD /target/hole-in-the-wall-api-0.0.1-SNAPSHOT.jar hole-in-the-wall-api.jar
ENTRYPOINT ["java","-jar","hole-in-the-wall-api.jar", "--server.port=80"]