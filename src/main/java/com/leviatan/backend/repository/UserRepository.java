package com.leviatan.backend.repository;

import com.leviatan.backend.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
