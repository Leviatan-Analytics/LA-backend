package com.leviatan.backend.dto.user;

import com.leviatan.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPaths {

    @NotNull
    private String leagueClientPath;

    @NotNull
    private String leagueClientBase;

    @NotNull
    private String leagueReplay;

    public static UserPaths from(User user) {
        return UserPaths.builder()
                .leagueClientPath(user.getLeagueClientPath())
                .leagueClientBase(user.getLeagueClientBase())
                .leagueReplay(user.getLeagueReplay())
                .build();
    }
}
