package com.leviatan.backend.service;

import com.leviatan.backend.config.JwtUtils;
import com.leviatan.backend.config.user_details.UserDetailsImpl;
import com.leviatan.backend.dto.auth.*;
import com.leviatan.backend.exception.BadRequestException;
import com.leviatan.backend.exception.NotFoundException;
import com.leviatan.backend.model.Organization;
import com.leviatan.backend.model.SecureToken;
import com.leviatan.backend.model.User;
import com.leviatan.backend.repository.OrganizationRepository;
import com.leviatan.backend.repository.SecureTokenRepository;
import com.leviatan.backend.repository.UserRepository;
import com.leviatan.backend.utils.EmailService;
import com.leviatan.backend.utils.ForgotPasswordEmailContext;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class AuthService {

    private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(20);

    @Value("${leviatan.app.resetPasswordExpiration}")
    private int tokenExpiration;

    @Value("${leviatan.app.senderEmail}")
    private String senderEmail;

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final SecureTokenRepository secureTokenRepository;

    private final OrganizationRepository organizationRepository;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService, SecureTokenRepository secureTokenRepository, OrganizationRepository organizationRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.secureTokenRepository = secureTokenRepository;
        this.organizationRepository = organizationRepository;
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

        user = userRepository.save(user);

        Organization organization = Organization.builder()
                .name(registerRequest.getUsername())
                .owner(user)
                .build();

        organization = organizationRepository.save(organization);

        user.setOrganization(organization);
        return userRepository.save(user);
    }

    public void resetPassword(PasswordReset passwordReset) {
        User user = userRepository.findUserByEmail(passwordReset.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));

        SecureToken token = generateToken(user);
        secureTokenRepository.save(token);

        ForgotPasswordEmailContext emailContext = new ForgotPasswordEmailContext();
        emailContext.init(user, senderEmail);
        emailContext.setToken(token.getToken());
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e){
            throw new BadRequestException("Can't send email");
        }

    }

    private SecureToken generateToken(User user) {
        String tokenValue = new String(Base64.encodeBase64URLSafe(DEFAULT_TOKEN_GENERATOR.generateKey()), StandardCharsets.US_ASCII);
        return new SecureToken(tokenValue, LocalDateTime.now().plusSeconds(tokenExpiration), user);
    }

    public User changePassword(ChangePasswordRequest changePasswordRequest) {
        User user = userRepository.findUserByEmail(changePasswordRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));

        SecureToken secureToken = secureTokenRepository.findSecureTokenByToken(changePasswordRequest.getToken())
                .orElseThrow(() -> new NotFoundException("Token not found"));

        if (!secureToken.getUser().getId().equals(user.getId()) || secureToken.isExpired())
            throw new BadRequestException("Invalid Token");

        secureTokenRepository.deleteById(secureToken.getId());
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getPassword()));
        return userRepository.save(user);
    }
}
