package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dtos.LoginRequest;
import com.ecommerce.userservice.dtos.RegisterRequest;
import com.ecommerce.userservice.dtos.UserProfile;


public interface UserService {
        String register(RegisterRequest request);
        String login(LoginRequest request);
        UserProfile getProfile(String email);
    }

