package com.blockchainspace.ecommerce.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blockchainspace.ecommerce.dto.response.UserIdResponse;
import com.blockchainspace.ecommerce.dto.response.UserResponse;
import com.blockchainspace.ecommerce.persistence.Users;
import com.blockchainspace.ecommerce.persistence.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UsersMapper usersMapper;

    public List<UserResponse> getUserList() {
        // TODO remove this
        List<Users> users = usersMapper.selectList(new QueryWrapper<>());
        return users.stream().map(this::constructResponse).collect(Collectors.toList());
    }

    public UserResponse getUserById(int id) {
        Users users = usersMapper.selectById(id);
        return constructResponse(users);
    }

    public UserIdResponse getUserByUsername(String username) {
        Users users = usersMapper.selectOne(new QueryWrapper<Users>().eq("username", username));
        return UserIdResponse.builder().username(users.getUsername()).firstName(users.getFirstName())
                .lastName(users.getLastName()).id(users.getId()).build();
    }

    private UserResponse constructResponse(Users users) {
        return UserResponse.builder().username(users.getUsername()).firstName(users.getFirstName())
                .lastName(users.getLastName()).build();
    }

}
