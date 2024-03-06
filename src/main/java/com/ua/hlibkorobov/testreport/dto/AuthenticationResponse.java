package com.ua.hlibkorobov.testreport.dto;

import lombok.Builder;
import lombok.Data;


/**
 * AuthenticationResponse DTO
 */
@Data
@Builder
public class AuthenticationResponse {
    private String token;
}
