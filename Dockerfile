FROM maven:3.5.4-jdk-11-slim AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:11-jre-slim

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/spring-boot-application.jar /app/

ENTRYPOINT ["java", "-jar", "spring-boot-application.jar"]