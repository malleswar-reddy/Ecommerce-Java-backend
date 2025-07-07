package com.ecommerce.userservice.dtos;

import lombok.Data;
import org.springframework.security.web.webauthn.api.AuthenticatorResponse;

@Data
public class LoginRequest {
    private String email;
    private String password;
}

