FROM openjdk:14
COPY out/artifacts/docker_k8s_test_jar docker_k8s_test_jar/docker-k8s-test.jar docker-k8s-test.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "docker-k8s-test.jar"]
