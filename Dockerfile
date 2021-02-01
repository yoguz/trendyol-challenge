FROM openjdk:11
MAINTAINER yoguz
VOLUME /tmp
EXPOSE 8080
ADD target/converter-1.0.0.jar converterdocker.jar
ENTRYPOINT ["java","-jar","/converterdocker.jar"]