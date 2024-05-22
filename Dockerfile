FROM openjdk:17-jdk-slim as BUILDER

WORKDIR /app

ARG DOCKER_HOST=localhost

COPY . .

RUN sed -i "s|localhost|${DOCKER_HOST}|" src/main/resources/application.properties

RUN ./mvnw package -Dmaven.test.skip

FROM bitnami/java:17-debian-11

WORKDIR /app

RUN apt update && apt install -y curl

RUN addgroup app_group && adduser --ingroup app_group app_user

USER app_user

COPY --from=BUILDER /app/target/*.jar /app/*.jar

ENTRYPOINT ["/bin/bash"]
# ENTRYPOINT ["java", "-jar", "/app/*.jar"]