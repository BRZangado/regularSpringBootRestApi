# flyway/Dockerfile
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/flyway/target/flyway-0.0.1-SNAPSHOT.jar /app/flyway.jar
ENTRYPOINT ["java", "-jar", "/app/flyway.jar"]