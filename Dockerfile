FROM openjdk:8-jdk

ARG PR_PROJECT_TARGET_NAME="${PR_PROJECT_TARGET_NAME}"
ENV TZ=Asia/Seoul

COPY ${PR_PROJECT_TARGET_NAME} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
