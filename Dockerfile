# Use Maven + JDK 17 as base image
FROM maven:3.9.0-eclipse-temurin-17

# Set working directory inside container
WORKDIR /app

# Copy pom.xml first (for dependency caching)
COPY pom.xml .

# Download all Maven dependencies
RUN mvn dependency:go-offline -B

# Copy entire project into container
COPY . .

# Run TestNG tests
CMD ["mvn", "clean", "test", "-DsuiteXmlFile=testNG.xml"]