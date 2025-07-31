# Etapa 1: Compilar todo el proyecto (incluye commons)
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Etapa 2: Imagen ligera con el microservicio seleccionado
FROM eclipse-temurin:21-jdk
WORKDIR /app
ARG MICROSERVICIO
COPY --from=build /app/${MICROSERVICIO}/target/${MICROSERVICIO}-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

