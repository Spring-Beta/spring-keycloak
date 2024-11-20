package com.example.springkeycloak.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint!";
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/secure-endpoint")
    public String secureEndpoint() {
        return "This is a secure endpoint! You are authenticated.";
    }
}
