package com.leviatan.backend.dto.auth;

import com.leviatan.backend.config.user_details.UserDetailsImpl;
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

    public static LoginResponse from(UserDetailsImpl userDetails, String jwt) {
        return LoginResponse.builder()
                .token(jwt)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .build();
    }
}
