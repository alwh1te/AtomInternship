FROM postgres:latest

ENV POSTGRES_DB=topics
ENV POSTGRES_USER=root
ENV POSTGRES_PASSWORD=1273

EXPOSE 5432

CMD ["postgres"]

FROM openjdk:17-oracle

WORKDIR /app

COPY ./build/libs/Atom-0.0.1-SNAPSHOT.jar AtomApp.jar

EXPOSE 8080

CMD ["java", "-jar", "AtomApp.jar"]