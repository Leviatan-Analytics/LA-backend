package com.leviatan.backend.dto.user;

import com.leviatan.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {

    private String username;

    private String email;

    private String leagueClientPath;

    private String leagueClientBase;

    private boolean pathsSet;

    public static UserInfo from(User user) {
        return UserInfo.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .leagueClientPath(user.getLeagueClientPath())
                .leagueClientBase(user.getLeagueClientBase())
                .pathsSet(user.getLeagueClientPath() != null && user.getLeagueClientBase() != null)
                .build();
    }
}
