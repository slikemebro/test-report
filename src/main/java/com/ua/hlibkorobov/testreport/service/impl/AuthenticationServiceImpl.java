package com.ua.hlibkorobov.testreport.service.impl;

import com.ua.hlibkorobov.testreport.configuration.JwtUtils;
import com.ua.hlibkorobov.testreport.dto.AuthenticationRequest;
import com.ua.hlibkorobov.testreport.dto.AuthenticationResponse;
import com.ua.hlibkorobov.testreport.dto.RegisterRequest;
import com.ua.hlibkorobov.testreport.exception.UserNotFoundException;
import com.ua.hlibkorobov.testreport.pojo.User;
import com.ua.hlibkorobov.testreport.repository.UserRepository;
import com.ua.hlibkorobov.testreport.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(encodedPassword)
                .role(request.getRole())
                .build();
        repository.save(user);

        String jwtToken = jwtUtils.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User wasn't found"));
        String jwtToken = jwtUtils.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
