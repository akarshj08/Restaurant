# ==========================
# 1. Build Stage (Maven Build)
# ==========================
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Set working directory
WORKDIR /workspace

# Copy pom.xml and download dependencies first (caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the application (skip tests for faster build)
RUN mvn clean package -DskipTests

# ==========================
# 2. Runtime Stage (Slim JDK)
# ==========================
FROM eclipse-temurin:21-jdk-jammy

# Set working directory
WORKDIR /app

# Copy built JAR from builder image
COPY --from=builder /workspace/target/*.jar app.jar

# Add a non-root user for security
RUN useradd -m springuser
USER springuser

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Health check (Cloud Run will use this)
HEALTHCHECK --interval=30s --timeout=10s --start-period=30s --retries=3 \
  CMD wget --quiet --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# JVM optimizations for container environments
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:+UseG1GC"

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
