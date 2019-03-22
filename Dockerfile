FROM java:openjdk-8-alpine

COPY build/libs/me.namila.reservbox-0.1.0.jar /server.jar

CMD ["/usr/bin/java", "-jar" , "/server.jar"]