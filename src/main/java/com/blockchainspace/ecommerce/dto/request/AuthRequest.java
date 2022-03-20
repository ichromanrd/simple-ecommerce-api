package com.blockchainspace.ecommerce.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest extends BaseAuthRequest {

    private String username;

    private String password;

}
