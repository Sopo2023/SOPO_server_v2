FROM openjdk:17
COPY build/libs/sopo-2.0.0.jar /sopo.jar
ENTRYPOINT ["java", "-jar", "/sopo.jar"]
