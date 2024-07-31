<<<<<<< HEAD
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} sopo-2.0.0.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-jar","/sopo-2.0.0.jar","-Duser.timezone=Asia/Seoul"]
=======
FROM openjdk:17
ARG JAR_FILE=build/libs/SOPO_server_v2-0.0.2-SNAPSHOT.jar
COPY ${JAR_FILE} sopo.jar
ENTRYPOINT ["java","-jar","/sopo.jar"]
>>>>>>> 12f73049a4d31fff0df65fbbc7b5fa5b6e7dd993
