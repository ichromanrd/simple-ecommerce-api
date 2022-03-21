package com.blockchainspace.ecommerce.config;

import com.blockchainspace.ecommerce.service.UserService;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public JwtProperties jwtProperties() {
        return new JwtProperties();
    }

    @Bean
    public JwtUsernamePasswordAuthFilter jwtUsernamePasswordAuthFilter(JwtProperties jwtProperties,
            @Qualifier("jdbcRealm") Realm jdbcRealm, UserService userService) {
        return new JwtUsernamePasswordAuthFilter(jwtProperties, jdbcRealm, userService);
    }

    @Bean
    public JwtTokenAuthFilter jwtTokenAuthFilter(JwtProperties properties) {
        return new JwtTokenAuthFilter(properties);
    }

    @Bean
    public BcryptPasswordService bcryptPasswordService() {
        return new BcryptPasswordService();
    }

    @Bean("jdbcRealm")
    public Realm jdbcRealm(DataSource dataSource, BcryptPasswordService bcryptPasswordService) {
        PasswordMatcher passwordMatcher = new PasswordMatcher();
        passwordMatcher.setPasswordService(bcryptPasswordService);
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);
        jdbcRealm.setAuthenticationQuery(QueryConstants.AUTHENTICATION_QUERY);
        jdbcRealm.setUserRolesQuery(QueryConstants.QUERY_ROLE_QUERY);
        jdbcRealm.setPermissionsQuery(QueryConstants.PERMISSIONS_QUERY);
        jdbcRealm.setCredentialsMatcher(passwordMatcher);
        return jdbcRealm;
    }

    @Bean("jwtTokenRealm")
    public Realm jwtTokenRealm() {
        return new JwtTokenRealm();
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(List<Realm> realms) {
        return new DefaultWebSecurityManager(realms);
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition(JwtProperties properties) {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition(properties.getUrl(), "jwtUsernamePasswordAuth");
        chainDefinition.addPathDefinition("/**", "jwtTokenAuth");
        return chainDefinition;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager,
            JwtUsernamePasswordAuthFilter jwtUsernamePasswordAuthFilter, JwtTokenAuthFilter jwtTokenAuthFilter,
            ShiroFilterChainDefinition filterChainDefinition) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.getFilters().put("jwtUsernamePasswordAuth", jwtUsernamePasswordAuthFilter);
        filterFactoryBean.getFilters().put("jwtTokenAuth", jwtTokenAuthFilter);
        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinition.getFilterChainMap());
        return filterFactoryBean;
    }

}
