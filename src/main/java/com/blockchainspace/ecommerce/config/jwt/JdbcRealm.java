package com.blockchainspace.ecommerce.config.jwt;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.Subject;

public class JdbcRealm extends org.apache.shiro.realm.jdbc.JdbcRealm {

    AuthorizationInfo getAuthorizationInfo(Subject subject) {
        return doGetAuthorizationInfo(subject.getPrincipals());
    }
}
