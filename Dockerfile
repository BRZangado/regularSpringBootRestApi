FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=builder /app/rest-api/target/rest-api-0.0.1-SNAPSHOT.jar /app/rest-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar"]
CMD ["/app/rest-api.jar"]