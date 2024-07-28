FROM node:16-alpine as builder
ARG JAR_FILE=build/libs/sopo-2.0.0.jar
COPY ${JAR_FILE} /sopo.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-jar","/sopo.jar","-Duser.timezone=Asia/Seoul"]