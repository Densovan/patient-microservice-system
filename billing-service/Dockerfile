
FROM --platform=linux/amd64 maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn --version

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package


FROM --platform=linux/amd64 openjdk:21-jdk AS runner


WORKDIR /app

COPY --from=builder ./app/target/billing-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 4001
EXPOSE 9001

ENTRYPOINT ["java", "-jar", "app.jar"]