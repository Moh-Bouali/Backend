FROM azul/zulu-openjdk-alpine:17
COPY build/libs/S3-Individual-0.0.1-SNAPSHOT.jar S3-Individual-0.0.1.jar
ENTRYPOINT ["java","-jar","/S3-Individual-0.0.1.jar"]