package com.blockchainspace.ecommerce.service.impl;

import com.blockchainspace.ecommerce.persistence.Users;
import com.blockchainspace.ecommerce.persistence.mapper.UsersMapper;
import com.blockchainspace.ecommerce.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ichroman Raditya Duwila
 * @since 2022-03-30
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
