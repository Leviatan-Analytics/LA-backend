package com.leviatan.backend.service;

import com.leviatan.backend.config.JwtUtils;
import com.leviatan.backend.config.user_details.UserDetailsImpl;
import com.leviatan.backend.dto.auth.LoginRequest;
import com.leviatan.backend.dto.auth.LoginResponse;
import com.leviatan.backend.dto.auth.RegisterRequest;
import com.leviatan.backend.exception.BadRequestException;
import com.leviatan.backend.exception.NotFoundException;
import com.leviatan.backend.model.User;
import com.leviatan.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userRepository.findById(userDetails.getId()).orElseThrow(() -> new NotFoundException("User not found"));
        return LoginResponse.from(user, jwt);
    }

    public User registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername()))
            throw new BadRequestException("Username already in use");

        if (userRepository.existsByEmail(registerRequest.getEmail()))
            throw new BadRequestException("Email already in use");

        User user = User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        return userRepository.save(user);
    }
}
