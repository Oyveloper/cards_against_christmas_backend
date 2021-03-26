FROM adoptopenjdk/openjdk13:latest

WORKDIR /opt/app

ARG JAR_FILE=target/cards_against_christmas_backend-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080