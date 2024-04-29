FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN addgroup --gid 1001 gytty && adduser --disabled-password --gecos "" --uid 1001 --home /home/gytty --ingroup gytty gytty
ENTRYPOINT ["java","-jar","/app.jar"]
