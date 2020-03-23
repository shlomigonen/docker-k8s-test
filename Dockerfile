FROM openjdk:14
COPY target/docker-k8s-test.jar docker-k8s-test.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "docker-k8s-test.jar"]
