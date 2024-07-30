FROM openjdk:17
ARG JAR_FILE=Alimo-api/build/libs/sopo-2.0.0.jar
COPY ${JAR_FILE} sopo.jar
ENTRYPOINT ["java","-jar","/sopo.jar"]