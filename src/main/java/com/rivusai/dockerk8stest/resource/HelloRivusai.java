package com.rivusai.dockerk8stest.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HelloRivusai {

    @GetMapping
    public String hello () {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t\t<title>rivus.ai</title>\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                "\t<p style=\"margin-bottom:300px;\"></p>\n" +
                "\t\t<img src=\"/img/rivusai_logo.png\" width=\"500\" style=\"display:block; margin-left: auto; margin-right: auto;\" />\n" +
                "\t</body>\n" +
                "</html>";
    }
}
