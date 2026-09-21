# Build stage
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM tomcat:10.1-jdk17-temurin
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/DevVault.war /usr/local/tomcat/webapps/ROOT.war
ENV CATALINA_OPTS="-Xms128m -Xmx384m"
EXPOSE 8080
CMD ["catalina.sh", "run"]