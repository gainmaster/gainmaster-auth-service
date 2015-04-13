FROM bachelorthesis/java:latest
MAINTAINER Tony Hesjevik <tony@hesjevik.no>

COPY ./ /srv/http/gainmaster-oauth-service

WORKDIR /srv/http/gainmaster-oauth-service

RUN ["./gradlew", "build"]

CMD ["java", "-jar", "./build/libs/gainmaster-oauth-service-0.1.0.war"]
