package com.rivusai.dockerk8stest.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/rivusai/hello")
public class HelloRivusai {

    @GetMapping
    public String hello () {
        return "Hello from Rivus.ai - your Streaming AI Platform integrating with Jenkins for Git and Maven";
    }
}
