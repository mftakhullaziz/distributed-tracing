# Builder stage: Maven with Amazon Corretto JDK 17
FROM maven:3.9.9-amazoncorretto-21 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files into the container
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -Dmaven.test.skip=true

# Runtime stage: Amazon Corretto JDK 21 for running the application
FROM amazoncorretto:21

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the builder stage to the runtime stage
COPY --from=builder /app/target/distrimo-0.0.1-SNAPSHOT.jar /app/distrimo-SNAPSHOT.jar

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/distrimo-SNAPSHOT.jar"]