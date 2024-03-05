package com.ua.hlibkorobov.testreport.service;

import com.ua.hlibkorobov.testreport.dto.AuthenticationRequest;
import com.ua.hlibkorobov.testreport.dto.AuthenticationResponse;
import com.ua.hlibkorobov.testreport.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

}
