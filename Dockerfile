FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY target/your-application.jar app.jar

CMD ["java", "-jar", "app.jar"]
