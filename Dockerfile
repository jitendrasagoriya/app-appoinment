FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
CMD mkdir p app
WORKDIR /app
COPY ${JAR_FILE} /app
EXPOSE 8085
ENTRYPOINT ["java","-jar","/app/demo-0.0.1-SNAPSHOT.jar"]
