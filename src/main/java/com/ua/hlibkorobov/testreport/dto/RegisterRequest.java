package com.ua.hlibkorobov.testreport.dto;

import com.ua.hlibkorobov.testreport.pojo.Role;
import lombok.Builder;
import lombok.Data;

/**
 * Dto for registration request
 */
@Data
@Builder
public class RegisterRequest {
    private String email;
    private String username;
    private String password;
    private Role role;

}