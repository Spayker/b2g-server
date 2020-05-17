FROM openjdk:11-jre

ADD ./target/consumer-service.jar /app/
CMD ["java", "-Xmx400m", "-jar", "/app/consumer-service.jar"]

EXPOSE $CONSUMER_SERVICE_DEV_PORT $CONSUMER_SERVICE_DEV_DEBUG_PORT