FROM openjdk:latest
COPY ./target/Main-0.0.1-SNAPSHOT.jar Main-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar","Main-0.0.1-SNAPSHOT.jar"]