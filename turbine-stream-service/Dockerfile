FROM openjdk:11-jre

ADD ./target/turbine-stream-service.jar /app/
CMD ["java", "-Xmx400m", "-jar", "/app/turbine-stream-service.jar"]

EXPOSE $TURBINE_SERVICE_DEV_PORT $TURBINE_SERVICE_DEV_DEBUG_PORT