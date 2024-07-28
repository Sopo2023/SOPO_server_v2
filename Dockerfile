FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} sopo-2.0.0.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-jar","/sopo-2.0.0.jar","-Duser.timezone=Asia/Seoul"]