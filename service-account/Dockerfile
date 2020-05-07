FROM openjdk:11-jre

ADD ./target/service-account.jar /app/
CMD ["java", "-Xmx400m", "-jar", "/app/service-account.jar"]

EXPOSE $SERVICE_ACCOUNT_DEV_PORT $SERVICE_ACCOUNT_DEV_DEBUG_PORT