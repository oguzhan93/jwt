package com.oguzhan.security.jwt.controller;

import com.oguzhan.security.jwt.dto.user.LoginRequest;
import com.oguzhan.security.jwt.dto.user.LoginResponse;
import com.oguzhan.security.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class PublicApiController {
    private final UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        String token = userService.loginAndGenerateToken(loginRequest);
        return new LoginResponse(token);
    }

    @PostMapping("/sign-up")
    public String signUp(@RequestBody LoginRequest signupRequest) {
        return "created successfully";
    }

}
