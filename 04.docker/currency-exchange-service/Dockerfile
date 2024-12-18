# Step 1: Use a JDK base image to build the application
FROM openjdk:21-jdk-slim AS build

# Install Maven (for running Maven commands)
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven/Gradle project files (pom.xml or build.gradle) and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code to the container
COPY src ./src

# Build the application (make sure your Spring Boot application is compiled)
RUN mvn clean install -DskipTests

# Step 2: Use a smaller JDK image to run the application
FROM openjdk:21-jdk-slim

# Set the working directory for running the application
WORKDIR /app

# Copy the JAR file built in the build stage to the final image
COPY --from=build /app/target/currency-exchange-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot application will run on
EXPOSE 8000

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]