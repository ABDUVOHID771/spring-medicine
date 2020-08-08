FROM alpine:edge
RUN apk add --no-cache openjdk11-jdk
RUN addgroup -S appuser && adduser -S appuser -G appuser
VOLUME /tmp
# Creating dirs
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
RUN chown -R appuser:appuser /app
USER appuser:appuser
# The application's jar file
ARG JAR_FILE=target/spring-medicine.jar
ADD ${JAR_FILE} /app/app.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]