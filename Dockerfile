# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the argument for the JAR file location
ARG JAR_FILE=build/libs/*.jar

# Copy the JAR file into the container
COPY ${JAR_FILE} app.jar

# Set environment variables
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/hs_dzbb?currentSchema=auth_service
ENV SPRING_DATASOURCE_USERNAME=hp
ENV SPRING_DATASOURCE_PASSWORD=SFgIgwXeJD5QNWpFcMLDaVvL5BFANp0K

# Expose port 8080
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]
