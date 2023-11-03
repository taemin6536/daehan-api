FROM openjdk:8-jdk

ENV TZ=Asia/Seoul

COPY ./mys-bems-boot/target/mys-bems-boot-1.0.0-spring-boot.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
