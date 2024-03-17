package com.oguzhan.security.jwt.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class LoginRequest {
    private String username;
    private String password;
}
