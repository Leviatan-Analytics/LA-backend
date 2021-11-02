package com.leviatan.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecureToken extends UUIDEntity {

    private String token;

    private LocalDateTime creationTime;

    private LocalDateTime expireAt;

    @ManyToOne
    private User user;

    public SecureToken(String token, LocalDateTime expireAt, User user) {
        this.creationTime = LocalDateTime.now();
        this.token = token;
        this.expireAt = expireAt;
        this.user = user;
    }

    public boolean isExpired() {
        return getExpireAt().isBefore(LocalDateTime.now());
    }
}
