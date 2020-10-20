FROM openjdk:8-jdk-alpine

ENV APP_HOME /home/app
RUN mkdir $APP_HOME
RUN mkdir $APP_HOME/config

ADD target/order-microservice-0.0.1-SNAPSHOT.jar $APP_HOME/order-microservice-0.0.1-SNAPSHOT.jar
ADD src/main/resources/application.properties $APP_HOME/config/application.properties

ENTRYPOINT ["java", "-jar", "/home/app/order-microservice-0.0.1-SNAPSHOT.jar", "--spring.config.location=file:/home/app/config/application.properties"]
