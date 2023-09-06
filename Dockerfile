# Use the official OpenJDK 11 image as the base image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/*.jar profile-details.jar

# Expose the port your Spring Boot application is running on
#EXPOSE 8080

# Define the command to run the Spring Boot application
CMD ["java", "-jar", "profile-details.jar"]