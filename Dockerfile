FROM openjdk:12
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Respuestas.jar
ENTRYPOINT ["java","-jar","/Respuestas.jar"]