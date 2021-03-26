FROM maven:3.6-jdk-11 as maven
WORKDIR /app
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src

RUN mvn clean package && cp target/cards_against_christmas_backend-0.0.1-SNAPSHOT.jar app.jar

# Rely on Docker's multi-stage build to get a smaller image based on JRE
FROM adoptopenjdk/openjdk13:latest
LABEL maintainer="yvind.monsen@gmail.com"
WORKDIR /app
COPY --from=maven /app/app.jar ./app.jar

# VOLUME /tmp
EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]