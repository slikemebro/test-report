package com.ua.hlibkorobov.testreport.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto for authentication request
 */
@Getter
@Setter
public class AuthenticationRequest {

    private String email;
    private String password;
}