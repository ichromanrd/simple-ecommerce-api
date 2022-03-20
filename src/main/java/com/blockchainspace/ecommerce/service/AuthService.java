package com.blockchainspace.ecommerce.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private SecurityManager securityManager;

    public Subject getCurrentUser() {
        SecurityUtils.setSecurityManager(securityManager);
        return SecurityUtils.getSubject();
    }

}
