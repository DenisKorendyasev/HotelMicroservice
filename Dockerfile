# Используем официальный базовый образ OpenJDK
FROM openjdk:17-jdk-alpine

# Задаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем файл JAR из текущей директории в контейнер
COPY target/HotelMicroservice-0.0.1-SNAPSHOT.jar /app/HotelMicroservice.jar

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "/app/HotelMicroservice.jar"]

# Альтернативный способ:
# CMD ["java", "-jar", "/app/your-application.jar"]