package com.leviatan.backend.repository;

import com.leviatan.backend.model.SecureToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecureTokenRepository extends JpaRepository<SecureToken, String> {
    Optional<SecureToken> findSecureTokenByToken(String token);
}
