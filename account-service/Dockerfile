FROM java:8-jre
MAINTAINER Oleksandr Kuzmin <spykerstar@gmail.com>

ADD ./target/account-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/account-service.jar"]

EXPOSE 5000