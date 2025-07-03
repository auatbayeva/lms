# Используем официальный Maven образ
FROM maven:3.9.7-eclipse-temurin-17 AS build

WORKDIR /app

# Сначала копируем только файлы зависимостей — чтобы кешировать зависимости
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# Затем копируем остальной код проекта
COPY . .

# Полная сборка проекта
RUN mvn clean package -DskipTests

# Финальный образ — JRE
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
