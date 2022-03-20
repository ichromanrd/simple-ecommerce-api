package com.blockchainspace.ecommerce.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityManager securityManager() {
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        return new DefaultSecurityManager(iniRealm);
    }

}
