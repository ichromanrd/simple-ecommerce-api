package com.blockchainspace.ecommerce.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    private long expiresIn;

    private String accessToken;

}
