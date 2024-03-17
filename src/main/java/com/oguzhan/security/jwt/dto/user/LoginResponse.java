package com.oguzhan.security.jwt.dto.user;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
}
