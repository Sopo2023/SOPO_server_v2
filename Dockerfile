FROM openjdk:17
WORKDIR /app
COPY build/libs/myapp.jar app.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java", "-Duser.timezone=Asia/Seoul", "-jar", "/app/app.jar"]