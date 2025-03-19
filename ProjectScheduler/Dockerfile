# Use OpenJDK 11 as the base image
FROM openjdk:11-jre-slim

# Set working directory inside the container
WORKDIR /app

# Copy the built jar file into the container
COPY target/ProjectScheduler-1.0-SNAPSHOT.jar app.jar

# Copy the tasks.json file into the container
COPY tasks.json /app/tasks.json

# Expose the application port (optional if needed)
EXPOSE 4001

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
