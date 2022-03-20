package com.blockchainspace.ecommerce.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blockchainspace.ecommerce.dto.response.UserResponse;
import com.blockchainspace.ecommerce.persistence.User;
import com.blockchainspace.ecommerce.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void validateUserLogin() {
        // TODO
    }

    public List<UserResponse> getUserList() {
        // TODO remove this
        List<User> users = userMapper.selectList(new QueryWrapper<>());
        return users.stream().map(this::constructResponse).collect(Collectors.toList());
    }

    public UserResponse getUserById(int id) {
        User user = userMapper.selectById(id);
        return constructResponse(user);
    }

    private UserResponse constructResponse(User user) {
        return UserResponse.builder().username(user.getUsername()).firstName(user.getFirstName())
                .lastName(user.getLastName()).build();
    }

}
