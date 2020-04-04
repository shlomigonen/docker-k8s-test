package com.rivusai.dockerk8stest.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/rivusai/hello")
public class HelloRivusai {

    @GetMapping
    public String hello () {
        return "Hello from Rivus.ai - your Streaming AI Platform \n" +
                "Now Orchestrating with Jenkins Pipeline and integrating with (1) Git (2) Maven (3) Docker (4) Kubernetes";
    }
}
