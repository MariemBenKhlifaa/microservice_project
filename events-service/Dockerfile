FROM openjdk:8-jre-alpine
WORKDIR /app
COPY target/events-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
CMD ["java", "-jar", "app.jar"]
