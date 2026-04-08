FROM eclipse-temurin:17-jre-ubi9-minimal
COPY target/*.jar /app.jar
ENV SPRING_PROFILES_ACTIVE=docker
ENTRYPOINT ["java","-jar","/app.jar"]