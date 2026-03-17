# === BUILD STAGE ===
# Use a JDK image with Maven to build the application
FROM maven:3.9.8-eclipse-temurin-21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and source code into the container
COPY src /app/src
COPY pom.xml /app

# Build the application (creates a JAR file in the 'target' directory)
RUN mvn clean install -U

# === RUNTIME STAGE ===
# Use a minimal JRE (Java Runtime Environment) image for the final runtime
FROM eclipse-temurin:21-jre-alpine

# Set the working directory in the runtime stage
WORKDIR /app

# Copy the JAR file from the 'build' stage to the runtime stage
COPY --from=build /app/target/*.jar /app/app.jar

# (Optional) Create a non-root user for security best practices
RUN addgroup -S javauser && adduser -S javauser -G javauser
USER javauser

# Expose the port the application runs on (e.g., 8080 for Spring Boot)
EXPOSE 8080

# Command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
