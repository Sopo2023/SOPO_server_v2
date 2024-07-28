FROM openjdk:17
ARG JAR_FILE=build/libs/SOPO_server_v2-0.0.1-SNAPSHOT-plain.jar
COPY ${JAR_FILE} /sopo.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-jar","/sopo.jar","-Duser.timezone=Asia/Seoul"]