# Etapa 1: Build con Maven (compila todo el proyecto incluyendo commons)
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
# Compilar e instalar todas las dependencias locales (incluye commons)
RUN mvn clean install -DskipTests

# Etapa 2: Imagen ligera solo con el .jar ejecutable del microservicio
FROM eclipse-temurin:21-jdk
WORKDIR /app
# Copiar solo el jar final del microservicio servicio-productos
COPY --from=build /app/servicio-productos/target/servicio-productos-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8070
ENTRYPOINT ["java", "-jar", "app.jar"]
