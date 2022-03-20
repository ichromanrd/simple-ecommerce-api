package com.blockchainspace.ecommerce.persistence;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private int id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

}
