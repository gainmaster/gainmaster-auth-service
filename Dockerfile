FROM gainmaster/gradle
MAINTAINER Tony Hesjevik <tony@hesjevik.no>

COPY ./ /srv/http/gainmaster-oauth-service

WORKDIR /srv/http/gainmaster-oauth-service

RUN ["gradle", "build"]

CMD ["java", "-jar", "./build/libs/gainmaster-oauth-service-0.1.0.war"]
