FROM openjdk:8-jdk

ENV TZ=Asia/Seoul

WORKDIR /usr/src/app

COPY ./mys-bems-boot/target/mys-bems-boot-1.0.0-spring-boot.jar /usr/src/app

ENTRYPOINT ["java", "-jar", "mys-bems-boot-1.0.0-spring-boot.jar"]
