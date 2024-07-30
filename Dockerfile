FROM openjdk:17
ARG JAR_FILE=build/libs/SOPO_server_v2-0.0.2-SNAPSHOT.jar
COPY ${JAR_FILE} sopo.jar
ENTRYPOINT ["java","-jar","/sopo.jar"]