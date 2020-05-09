FROM openjdk:11-jre

ADD ./target/consumer-auth.jar /app/
CMD ["java", "-Xmx400m", "-jar", "/app/consumer-auth.jar"]

EXPOSE $CONSUMER_AUTH_DEV_PORT $CONSUMER_AUTH_DEV_DEBUG_PORT