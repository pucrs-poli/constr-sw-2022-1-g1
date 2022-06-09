FROM gradle:jdk17-alpine
RUN mkdir /constswg1
COPY . /constswg1
WORKDIR /constswg1
RUN gradle build --no-daemon
ENTRYPOINT ["java","-jar","/constswg1/build/libs/g1-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080