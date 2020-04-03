#!/bin/bash
sed "s/BUILD_VERSION/$1/g" docker_k8s_test_deployment.yaml > docker_k8s_test_deployment.yaml