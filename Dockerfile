FROM openjdk:14-alpine

ENV APPLICATION_USER ktor
RUN adduser -D -g '' $APPLICATION_USER

RUN mkdir /app
RUN chown -R $APPLICATION_USER /app

USER $APPLICATION_USER

COPY api-salary.jar /app/api-salary.jar
WORKDIR /app

CMD ["java", "-jar", "api-salary.jar"]
