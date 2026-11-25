FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY pom.xml .
COPY src ./src

RUN mvn -B -DskipTests package \
    && cp target/fortus-*.jar /workspace/app.jar

FROM eclipse-temurin:21-jre

WORKDIR /app

RUN groupadd --system --gid 10001 app \
    && useradd --system --uid 10001 --gid app --home-dir /app app

COPY --from=build --chown=10001:10001 /workspace/app.jar ./app.jar

USER 10001:10001

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
