#
# Build stage
#
FROM gradle:7.6-openjdk-11 AS build
WORKDIR /app
COPY . /app/
RUN mvn clean package

#
# Package stage
#
FROM openjdk:11-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8765
ENTRYPOINT ["java","-jar","app.jar"]