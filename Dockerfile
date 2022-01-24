FROM anapsix/alpine-java:8_server-jre_unlimited

VOLUME /temp

COPY ./target/ classroom-server

ENTRYPOINT ["java", "-jar", "app.jar"]