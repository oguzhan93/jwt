package com.oguzhan.security.jwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    public String sayHelloToUser() {
        return "Hello, User!";
    }
}
