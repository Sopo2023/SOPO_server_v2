FROM openjdk:17
ARG JAR_FILE=build/libs/SOPO_server_v2-0.0.1-SNAPSHOT-plain.jar
COPY ${JAR_FILE} SOPO_server_v2-0.0.1-SNAPSHOT-plain.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-jar","/SOPO_server_v2-0.0.1-SNAPSHOT-plain.jar","-Duser.timezone=Asia/Seoul"]
