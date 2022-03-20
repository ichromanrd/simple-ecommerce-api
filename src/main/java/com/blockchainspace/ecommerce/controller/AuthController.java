package com.blockchainspace.ecommerce.controller;

import com.blockchainspace.ecommerce.dto.request.AuthRequest;
import com.blockchainspace.ecommerce.dto.request.InvalidateSessionRequest;
import com.blockchainspace.ecommerce.dto.request.RefreshTokenRequest;
import com.blockchainspace.ecommerce.dto.response.AuthResponse;
import com.blockchainspace.ecommerce.dto.response.UserResponse;
import com.blockchainspace.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public AuthResponse authenticate(@RequestBody AuthRequest request) {
        // TODO implement service
        return null;
    }

    @PostMapping("/refresh")
    public AuthResponse refreshToken(@RequestBody RefreshTokenRequest request) {
        // TODO implement service
        return null;
    }

    @DeleteMapping("/invalidate")
    public void invalidate(@RequestBody InvalidateSessionRequest request) {
        // TODO implement service
    }

    @GetMapping
    public List<UserResponse> getUserList() {
        return userService.getUserList();
    }

}
