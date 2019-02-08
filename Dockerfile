FROM openjdk:8-jdk-alpine

COPY build/libs/signature-1.0.war signature.war

EXPOSE 8081

CMD java -jar signature.war