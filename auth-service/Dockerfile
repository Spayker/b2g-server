FROM openjdk:11-jre

ADD ./target/auth-consumer.jar /app/
CMD ["java", "-Xmx400m", "-jar", "/app/auth-consumer.jar"]

EXPOSE $AUTH_SERVICE_DEV_PORT $AUTH_SERVICE_DEV_DEBUG_PORT