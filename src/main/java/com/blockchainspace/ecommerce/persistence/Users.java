package com.blockchainspace.ecommerce.persistence;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Users {

    @TableId
    private int id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

}
