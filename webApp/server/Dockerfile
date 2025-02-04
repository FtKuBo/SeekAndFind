FROM openjdk:21

RUN mkdir -p /app

WORKDIR /app

COPY build/libs/*.jar app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]
