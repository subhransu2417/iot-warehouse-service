FROM openjdk:8-jdk-alpine
MAINTAINER subhransu2417
COPY target/iot-warehouse-service-0.0.1-SNAPSHOT.jar iot-warehouse-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/iot-warehouse-service-0.0.1-SNAPSHOT.jar"]