# Build stage
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Install Maven
RUN apt-get update && apt-get install -y maven

# Copy source code
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Package stage
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/global-variables-api-1.0.0.jar app.jar
EXPOSE 8080
CMD ["sh", "-c", "java -jar app.jar --server.port=$PORT"] 