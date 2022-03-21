package com.blockchainspace.ecommerce.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserIdResponse {

    private int id;

    private String username;

    private String firstName;

    private String lastName;

}
