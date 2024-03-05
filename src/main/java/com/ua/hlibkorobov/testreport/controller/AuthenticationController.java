package com.ua.hlibkorobov.testreport.controller;

import com.ua.hlibkorobov.testreport.dto.AuthenticationRequest;
import com.ua.hlibkorobov.testreport.dto.AuthenticationResponse;
import com.ua.hlibkorobov.testreport.dto.RegisterRequest;
import com.ua.hlibkorobov.testreport.pojo.Role;
import com.ua.hlibkorobov.testreport.pojo.User;
import com.ua.hlibkorobov.testreport.repository.UserRepository;
import com.ua.hlibkorobov.testreport.service.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Log4j2
public class AuthenticationController {

    public final UserRepository userRepository;
    private final AuthenticationServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        log.info("Register request: {}", request);
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        log.info("Authenticate request: {}", request);
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping
    public void authenticate123() {
        User user = User.builder()
                .email("test")
                .password("test")
                .username("test")
                .role(Role.USER)
                .build();
        log.info("User authenticated");
        userRepository.save(user);
    }

}