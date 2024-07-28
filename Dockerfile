FROM openjdk:17
COPY build/libs/SOPO_server_v2-0.0.1-SNAPSHOT-plain.jar sopo-2.0.0.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java", "-jar", "/sopo-2.0.0.jar", "-Duser.timezone=Asia/Seoul"]