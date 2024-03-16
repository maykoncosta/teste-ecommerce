FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/teste-loja-1.0.0.jar teste-loja-1.0.0.jar
EXPOSE 8080
CMD ["java", "-jar", "teste-loja-1.0.0.jar"]