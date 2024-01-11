FROM openjdk:19-slim-bullseye

WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080

ENTRYPOINT exec java $STARTUP_ARGS -jar app.jar
# exec necessary: https://akomljen.com/stopping-docker-containers-gracefully/
