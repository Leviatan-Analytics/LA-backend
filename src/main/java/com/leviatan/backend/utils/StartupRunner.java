package com.leviatan.backend.utils;

import com.leviatan.backend.model.User;
import com.leviatan.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StartupRunner {

    private final UserRepository userRepository;

    @Autowired
    public StartupRunner(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void storeInitialUser(){
        Optional<User> user = userRepository.findUserByEmail("test@gmail.com");
        if (user.isEmpty()) userRepository.save(User.builder().username("test").email("test@gmail.com").password("test").build());
    }
}
