FROM openjdk:11-jre

ADD ./target/monitoring.jar /app/
CMD ["java", "-Xmx400m", "-jar", "/app/monitoring.jar"]

EXPOSE $MONITORING_SERVICE_DEV_PORT $MONITORING_SERVICE_DEV_DEBUG_PORT