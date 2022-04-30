FROM maven:3.5.4-jdk-11-slim AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn -f /build/pom.xml clean package -DskipTests

FROM openjdk:11-jre-slim

WORKDIR /app/

COPY --from=MAVEN_BUILD /build/target/backend-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]