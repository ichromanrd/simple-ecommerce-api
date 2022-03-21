package com.blockchainspace.ecommerce;

import com.blockchainspace.ecommerce.persistence.Users;
import com.blockchainspace.ecommerce.persistence.mapper.UsersMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Users> usersList = usersMapper.selectList(null);
        Assertions.assertEquals(2, usersList.size());
        usersList.forEach(System.out::println);
    }

}
