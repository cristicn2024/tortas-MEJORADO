# Etapa 1: Build con Maven
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagen ligera solo con el .jar ejecutable
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/gateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8222
ENTRYPOINT ["java", "-jar", "app.jar"]
