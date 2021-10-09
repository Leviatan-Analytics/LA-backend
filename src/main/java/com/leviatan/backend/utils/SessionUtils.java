package com.leviatan.backend.utils;

import com.leviatan.backend.config.user_details.UserDetailsImpl;
import com.leviatan.backend.exception.NotFoundException;
import com.leviatan.backend.model.User;
import com.leviatan.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SessionUtils {

    private final UserRepository userRepository;

    @Autowired
    public SessionUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getLoggedUserInfo() {
        final UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findById(user.getId()).orElseThrow(() -> new NotFoundException("Cannot find logged user id"));
    }
}
