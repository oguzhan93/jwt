package com.oguzhan.security.jwt.service;

import com.oguzhan.security.jwt.model.User;
import com.oguzhan.security.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found With username " + username));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.getRoles().stream().map((role) -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList()));
    }
}
