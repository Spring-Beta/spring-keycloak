package com.example.springkeycloak.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint!";
    }

    @GetMapping("/secure-endpoint")
    public String secureEndpoint() {
        log.info("Authenticated to secure endpoint!");
        return "This is a secure endpoint! You are authenticated.";
    }
}
