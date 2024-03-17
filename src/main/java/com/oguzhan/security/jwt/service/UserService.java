package com.oguzhan.security.jwt.service;

import com.oguzhan.security.jwt.dto.user.LoginRequest;
import com.oguzhan.security.jwt.model.User;
import com.oguzhan.security.jwt.repository.UserRepository;
import com.oguzhan.security.jwt.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public String loginAndGenerateToken(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        return jwtUtil.createToken(authentication);
    }

    public void createUser(User user) {
        if(userExists(user.getUsername()))
            throw new RuntimeException("Username Already Exists.");

        this.userRepository.save(user);
    }

    private boolean userExists(String username) {
        return !this.userRepository.findByUsername(username).isPresent();
    }
}
