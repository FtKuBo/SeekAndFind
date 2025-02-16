FROM eclipse-temurin:21-jdk-alpine 

RUN apk --no-cache add curl maven

# Expose port 8081
EXPOSE 8081

# Set the working directory
WORKDIR /app

# Copy the pom.xml file to the working directory
COPY pom.xml .

# Resolve the dependencies in the pom.xml file
RUN mvn dependency:resolve

# Copy the source code to the working directory
COPY src src

# Build the project
RUN mvn package -DskipTests

# Run the application
ENTRYPOINT ["java", "-jar", "target/application.jar"]
