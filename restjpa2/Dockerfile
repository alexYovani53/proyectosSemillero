
FROM openjdk:8-jdk-alpine

VOLUME /tmp

COPY target/application.properties application.properties
COPY target/restjpa2-0.0.1.jar app.jar

#ENV URL_BD=jdbc:oracle:thin:@//3.144.207.157:51521/XE


ENTRYPOINT ["java","-jar","/app.jar"]