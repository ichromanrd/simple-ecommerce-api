package com.blockchainspace.ecommerce.config.jwt;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class JwtToken implements AuthenticationToken {

    private final String principal;
    private final String token;
    private final Set<String> roles;
    private final Set<String> permission;

    public JwtToken(String principal, String token, Collection<String> roles, Collection<String> permission) {
        this.principal = principal;
        this.token = token;
        this.roles = Collections.unmodifiableSet(new HashSet<>(roles));
        this.permission = Collections.unmodifiableSet(new HashSet<>(permission));
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    public AuthenticationInfo getAuthenticationInfo(String realmName) {
        return new JwtAuthenticationInfo(realmName);
    }

    public class JwtAuthenticationInfo implements AuthenticationInfo {

        private final PrincipalCollection principals;

        public JwtAuthenticationInfo(String realmName) {
            this.principals = new JwtPrincipalCollection(realmName);
        }

        @Override
        public PrincipalCollection getPrincipals() {
            return null;
        }

        @Override
        public Object getCredentials() {
            return null;
        }
    }

    public class JwtPrincipalCollection extends SimplePrincipalCollection {

        public JwtPrincipalCollection(String realmName) {
            super(principal, realmName);
        }

        public AuthorizationInfo getAuthorizationInfo() {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addRoles(roles);
            info.addStringPermissions(permission);
            return info;
        }

    }

}
