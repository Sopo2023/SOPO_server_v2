FROM openjdk:17-alpine
COPY build/libs/sopo-2.0.0.jar /sopo.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java", "-jar", "/sopo.jar", "-Duser.timezone=Asia/Seoul"]
