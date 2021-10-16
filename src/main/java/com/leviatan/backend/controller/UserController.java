package com.leviatan.backend.controller;

import com.leviatan.backend.dto.user.UserInfo;
import com.leviatan.backend.dto.user.UserPaths;
import com.leviatan.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/paths")
    public UserPaths saveUserPaths(@RequestBody @Valid UserPaths userPaths) {
        return userService.saveUserPaths(userPaths);
    }

    @GetMapping("/info")
    public UserInfo getUserInfo() {
        return userService.getUserInfo();
    }
}
