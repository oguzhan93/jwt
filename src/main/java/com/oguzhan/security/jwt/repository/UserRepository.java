package com.oguzhan.security.jwt.repository;

import com.oguzhan.security.jwt.model.User;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
