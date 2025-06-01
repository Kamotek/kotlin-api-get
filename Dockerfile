FROM gradle:7.5.1-jdk17 AS build
LABEL authors="kamil"
WORKDIR /home/gradle/project


COPY gradlew .
COPY gradle gradle
COPY settings.gradle.kts settings.gradle.kts
COPY build.gradle.kts build.gradle.kts
COPY gradle.properties .

RUN chmod +x ./gradlew && \
    ./gradlew --no-daemon --parallel :dependencies || echo "somethings wrong here"

COPY src src

RUN ./gradlew clean shadowJar -x test --no-daemon --parallel

###
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /home/gradle/project/build/libs/*-all.jar app.jar

RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

ENTRYPOINT ["java", "-jar", "app.jar"]