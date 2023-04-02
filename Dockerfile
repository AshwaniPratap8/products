FROM maven:3.9.0-amazoncorretto-8 AS builder

ADD ./pom.xml pom.xml
ADD ./src src/

RUN mvn clean package

FROM openjdk:8-jre-alpine
COPY --from=builder target/products-0.0.1-SNAPSHOT.jar products-0.0.1-SNAPSHOT.jar
#COPY target/products-0.0.1-SNAPSHOT.jar products-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","products-0.0.1-SNAPSHOT.jar"]
