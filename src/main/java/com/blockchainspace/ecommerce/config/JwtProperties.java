package com.blockchainspace.ecommerce.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

@Getter
@ToString
public class JwtProperties {

    @Value("${blockchainspace.jwt.secret}")
    private String secret;

    @Value("${blockchainspace.jwt.expiration:30}")
    private int expiration;

    @Value("${blockchainspace.jwt.header:Authorization}")
    private String header;

    @Value("${blockchainspace.jwt.prefix:Bearer}")
    private String prefix;

    @Value("${blockchainspace.jwt.url:/auth/authenticate}")
    private String url;

}
