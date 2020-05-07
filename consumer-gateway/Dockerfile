FROM openjdk:11-jre

ADD ./target/consumer-gateway.jar /app/
CMD ["java", "-Xmx400m", "-jar", "/app/consumer-gateway.jar"]

EXPOSE $CONSUMER_GATEWAY_DEV_PORT $CONSUMER_GATEWAY_DEV_DEBUG_PORT