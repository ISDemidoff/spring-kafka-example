# common layers for all spring-boot containers
FROM java:openjdk-8-jre-alpine
RUN adduser -S -D -g '' spring-boot-app-user \
    && apk add --no-cache \
        curl \
    && apk add --no-cache --repository http://dl-cdn.alpinelinux.org/alpine/edge/community/ \
        jattach \
    && echo done
USER spring-boot-app-user
WORKDIR /opt/spring-boot-app
## Additional java options are sent via JAVA_TOOL_OPTIONS
ENTRYPOINT [\
    "java"\
    , "-jar", "spring-boot-app.jar"\
]

COPY *.jar /opt/spring-boot-app/spring-boot-app.jar
