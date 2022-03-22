package com.blockchainspace.ecommerce.controller;

import com.blockchainspace.ecommerce.dto.request.AuthRequest;
import com.blockchainspace.ecommerce.dto.response.AuthResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/authenticate")
    public AuthResponse authenticate(@RequestBody AuthRequest request) {
        // TODO implement service
        return null;
    }

}
