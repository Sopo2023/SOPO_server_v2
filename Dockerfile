FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} sopo.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java", "-jar", "/sopo.jar", "-Duser.timezone=Asia/Seoul"]