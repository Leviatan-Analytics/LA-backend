package com.leviatan.backend.service;

import com.leviatan.backend.dto.user.UserInfo;
import com.leviatan.backend.dto.user.UserInfoUpdate;
import com.leviatan.backend.dto.user.UserPaths;
import com.leviatan.backend.exception.BadRequestException;
import com.leviatan.backend.model.User;
import com.leviatan.backend.repository.UserRepository;
import com.leviatan.backend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SessionUtils sessionUtils;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, SessionUtils sessionUtils, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.sessionUtils = sessionUtils;
        this.passwordEncoder = passwordEncoder;
    }

    public UserPaths saveUserPaths(UserPaths userPaths) {
        User loggedUser = sessionUtils.getLoggedUserInfo();
        loggedUser.setLeagueClientBase(userPaths.getLeagueClientBase());
        loggedUser.setLeagueClientPath(userPaths.getLeagueClientPath());
        loggedUser.setLeagueReplay(userPaths.getLeagueReplay());
        return UserPaths.from(userRepository.save(loggedUser));
    }

    public UserInfo getUserInfo() {
        return UserInfo.from(sessionUtils.getLoggedUserInfo());
    }

    public UserInfo updateUserInfo(UserInfoUpdate userInfoUpdate) {
        User loggedUser = sessionUtils.getLoggedUserInfo();

        if (userInfoUpdate.getPassword() != null && userInfoUpdate.getNewPassword() != null){
            if (!passwordEncoder.matches(userInfoUpdate.getPassword(), loggedUser.getPassword()))
                throw new BadRequestException("Incorrect Password");
            loggedUser.setPassword(passwordEncoder.encode(userInfoUpdate.getNewPassword()));
        }

        if (userInfoUpdate.getEmail() != null && !userRepository.existsByEmail(userInfoUpdate.getEmail())) {
            loggedUser.setEmail(userInfoUpdate.getEmail());
        }

        if (userInfoUpdate.getLeagueClientBase() != null) {
            loggedUser.setLeagueClientBase(userInfoUpdate.getLeagueClientBase());
        }

        if (userInfoUpdate.getLeagueReplay() != null) {
            loggedUser.setLeagueReplay(userInfoUpdate.getLeagueReplay());
        }

        if (userInfoUpdate.getLeagueClientPath() != null) {
            loggedUser.setLeagueClientPath(userInfoUpdate.getLeagueClientPath());
        }

        return UserInfo.from(userRepository.save(loggedUser));
    }
}
