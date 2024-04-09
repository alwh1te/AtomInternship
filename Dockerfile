FROM openjdk:17-oracle

WORKDIR /app

COPY build/libs/Atom-0.0.1-SNAPSHOT.jar AtomApp.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
