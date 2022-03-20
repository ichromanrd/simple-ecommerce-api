package com.blockchainspace.ecommerce.config;

import com.blockchainspace.ecommerce.config.jwt.JdbcRealm;
import com.blockchainspace.ecommerce.config.jwt.JwtUsernamePasswordAuthFilter;
import com.blockchainspace.ecommerce.config.jwt.JwtProperties;
import com.blockchainspace.ecommerce.config.jwt.JwtTokenAuthFilter;
import com.blockchainspace.ecommerce.config.jwt.JwtTokenRealm;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
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
            @Qualifier("jdbcRealm") Realm jdbcRealm) {
        return new JwtUsernamePasswordAuthFilter(jwtProperties, jdbcRealm);
    }

    @Bean
    public JwtTokenAuthFilter jwtTokenAuthFilter(JwtProperties properties) {
        return new JwtTokenAuthFilter(properties);
    }

    @Bean("jdbcRealm")
    public Realm jdbcRealm(DataSource dataSource) {
        PasswordMatcher passwordMatcher = new PasswordMatcher();
        passwordMatcher.setPasswordService(new BcryptPasswordService());
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
        chainDefinition.addPathDefinition(properties.getUrl(), "noSessionCreation, jwtUsernamePasswordAuth");
        chainDefinition.addPathDefinition("/**", "noSessionCreation, jwtTokenAuth");
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

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("defaultWebSecurityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager);
        return sourceAdvisor;
    }

}
