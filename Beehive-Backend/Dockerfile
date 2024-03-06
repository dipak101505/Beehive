FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy application JAR
COPY target/*.jar app.jar

# Expose port 9001
EXPOSE 9091

# Run the application
CMD ["java", "-jar", "app.jar"]
