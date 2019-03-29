FROM openjdk:8-jdk-alpine

CMD ["/usr/bin/bash", "gradlew", "bootjar"]

COPY build/libs/me.namila.reservbox-0.1.0.jar /server.jar

CMD ["/usr/bin/java", "-jar" , "/server.jar"]

EXPOSE 8080