package com.ecommerce.userservice.controller;



import com.ecommerce.userservice.dtos.*;
import com.ecommerce.userservice.service.UserService;
import com.ecommerce.userservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        String token = userService.register(request);
        return new AuthResponse(token);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        String token = userService.login(request);
        return new AuthResponse(token);
    }

    @GetMapping("/profile")
    public UserProfile getProfile(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        String email = jwtUtil.extractEmail(jwt);
        return userService.getProfile(email);
    }
}
