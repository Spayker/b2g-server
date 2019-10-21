FROM java:8-jre
MAINTAINER Oleksandr Kuzmin <spykerstar@gmail.com>

ADD ./target/eureka.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/eureka.jar"]

EXPOSE 8761