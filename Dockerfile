FROM openjdk:17
ARG JAR_FILE=/build/libs/sopo-2.0.0.jar
COPY ${JAR_FILE} /sopo.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-jar","/sopo.jar","-Duser.timezone=Asia/Seoul"]