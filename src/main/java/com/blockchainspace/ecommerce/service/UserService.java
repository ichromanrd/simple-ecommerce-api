package com.blockchainspace.ecommerce.service;

import com.blockchainspace.ecommerce.dto.response.UserIdResponse;
import com.blockchainspace.ecommerce.dto.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

//    @Autowired
//    private UsersMapper usersMapper;

    public List<UserResponse> getUserList() {
        // TODO remove this
//        List<Users> users = usersMapper.selectList(new QueryWrapper<>());
//        return users.stream().map(this::constructResponse).collect(Collectors.toList());
        return null;
    }

//    public UserResponse getUserById(int id) {
//        Users users = usersMapper.selectById(id);
//        return constructResponse(users);
//    }

    public UserIdResponse getUserByUsername(String username) {
//        Users users = usersMapper.selectOne(new QueryWrapper<Users>().eq("username", username));
//        return UserIdResponse.builder().username(users.getUsername()).firstName(users.getFirstName())
//                .lastName(users.getLastName()).id(users.getId()).build();
        return null;
    }

//    private UserResponse constructResponse(Users users) {
//        return UserResponse.builder().username(users.getUsername()).firstName(users.getFirstName())
//                .lastName(users.getLastName()).build();
//    }

}
