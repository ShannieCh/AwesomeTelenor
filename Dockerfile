FROM openjdk:17
COPY target/*-SNAPSHOT.jar /app.jar
COPY ./testTelenor.csv .
ENTRYPOINT ["java","-jar","/app.jar"]