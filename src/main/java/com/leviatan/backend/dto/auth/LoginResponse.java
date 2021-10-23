package com.leviatan.backend.dto.auth;

import com.leviatan.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String token;
    private String id;
    private String username;
    private String email;
    private boolean pathsSet;

    public static LoginResponse from(User user, String jwt) {
        return LoginResponse.builder()
                .token(jwt)
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .pathsSet(user.getLeagueClientPath() != null
                        && user.getLeagueClientBase() != null
                        && user.getLeagueReplay() != null)
                .build();
    }
}
