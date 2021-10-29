package com.leviatan.backend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoUpdate {

    private String email;

    private String password;

    private String newPassword;

    private String leagueClientPath;

    private String leagueClientBase;

    private String leagueReplay;
}
