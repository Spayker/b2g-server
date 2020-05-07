FROM openjdk:11-jre

ADD ./target/consumer-account.jar /app/
CMD ["java", "-Xmx400m", "-jar", "/app/consumer-account.jar"]

EXPOSE $CONSUMER_SERVICE_DEV_PORT $CONSUMER_SERVICE_DEV_DEBUG_PORT