# Use official OpenJDK 17 image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY target/global-variables-api-1.0.0.jar app.jar

# Expose the port (Render will set $PORT)
EXPOSE 8080

# Run the JAR file, using the PORT environment variable
CMD ["sh", "-c", "java -jar app.jar --server.port=$PORT"] 