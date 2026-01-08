# Stage 1: Build the application
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Create the final image
FROM jboss/wildfly:30.0.1.Final
COPY --from=builder /app/ear/target/voyageconnect1-ear-1.0.0-SNAPSHOT.ear /opt/jboss/wildfly/standalone/deployments/voyageconnect1.ear

# Expose the ports
EXPOSE 8080 9990

# Start WildFly
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
