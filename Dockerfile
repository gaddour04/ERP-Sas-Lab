FROM openjdk:8
WORKDIR /app
EXPOSE 8484
COPY target/springBootERP.jar .
ENTRYPOINT ["java", "-jar", "springBootERP.jar"]
