FROM openjdk:17-jdk
RUN mkdir /app
WORKDIR /app
COPY target/*.jar /app/votacao-pauta.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/votacao-pauta.jar"]
