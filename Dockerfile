# Use Java 17
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Give permission to mvnw
RUN chmod +x mvnw

# Build the app
RUN ./mvnw clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the jar
CMD ["java", "-jar", "target/Jobtracker-0.0.1-SNAPSHOT.jar"]