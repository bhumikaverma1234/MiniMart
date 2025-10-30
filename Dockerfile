# 1. Java + Maven wala base image use kar rahe hain (for building)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# 2. Ye project files copy karega container ke andar
COPY pom.xml .
COPY src ./src

# 3. Ye command jar file banayegi
RUN mvn -B -DskipTests package

# 4. Ab ek chhota image banayenge jisme sirf jar chalega
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# 5. Port 8081 expose kar rahe hain (Spring Boot ka port)
EXPOSE 8081

# 6. App start hone ka command
ENTRYPOINT ["java", "-jar", "app.jar"]
