package com.leviatan.backend.service;

import com.leviatan.backend.dto.user.UserInfo;
import com.leviatan.backend.dto.user.UserPaths;
import com.leviatan.backend.model.User;
import com.leviatan.backend.repository.UserRepository;
import com.leviatan.backend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SessionUtils sessionUtils;

    @Autowired
    public UserService(UserRepository userRepository, SessionUtils sessionUtils) {
        this.userRepository = userRepository;
        this.sessionUtils = sessionUtils;
    }

    public UserPaths saveUserPaths(UserPaths userPaths) {
        User loggedUser = sessionUtils.getLoggedUserInfo();
        loggedUser.setLeagueClientBase(userPaths.getLeagueClientBase());
        loggedUser.setLeagueClientPath(userPaths.getLeagueClientPath());
        return UserPaths.from(userRepository.save(loggedUser));
    }

    public UserInfo getUserInfo() {
        return UserInfo.from(sessionUtils.getLoggedUserInfo());
    }
}
