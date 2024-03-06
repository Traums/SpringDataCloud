# Базовый образ с Java 17
FROM openjdk:17-jdk AS build

LABEL maintainer="SpringDataCloud"
# Установка переменных окружения
ENV APP_HOME=/app

# Создание директории приложения
WORKDIR $APP_HOME

# Копирование файлов сборки
COPY build.gradle settings.gradle gradlew $APP_HOME/
COPY gradle $APP_HOME/gradle
COPY src $APP_HOME/src

RUN microdnf install findutils
# Собираем проект с помощью Gradle
RUN ./gradlew build

# Базовый образ для запуска Spring Boot
FROM openjdk:17-jdk

# Копирование JAR файла из предыдущего этапа сборки
COPY --from=build /app/build/libs/SpringDataCloud-0.0.1-SNAPSHOT.jar /app/SpringDataCloud-0.0.1-SNAPSHOT.jar

EXPOSE 8189

# Запуск приложения Spring Boot
CMD ["java", "-jar", "/app/SpringDataCloud-0.0.1-SNAPSHOT.jar"]

