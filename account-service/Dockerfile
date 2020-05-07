FROM openjdk:11-jre

ADD ./target/account-consumer.jar /app/
CMD ["java", "-Xmx400m", "-jar", "/app/account-consumer.jar"]

EXPOSE $ACCOUNT_SERVICE_DEV_PORT $ACCOUNT_SERVICE_DEV_DEBUG_PORT