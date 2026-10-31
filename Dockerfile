# Estágio 1: compila e gera o jar
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests

# Estágio 2: só roda a aplicação
FROM eclipse-temurin:21-jre
ENV LANGUAGE='en_US:en'
WORKDIR /app
COPY --from=build /build/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]